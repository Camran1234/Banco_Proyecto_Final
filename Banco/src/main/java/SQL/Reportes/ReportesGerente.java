/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Reportes;

import File.ErrorHandlers.FormatException;
import File.ReportFiles.CajeroModel;
import File.ReportFiles.ClienteModel;
import File.ReportFiles.HistorialModel;
import File.ReportFiles.TransaccionModel;
import File.SpecialOptions.Time;
import SQL.Conexion.Conexion;
import SQL.Querys.Look.CorroboradorUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class ReportesGerente {
    private Connection connection;
    private List<HistorialModel> historial;
    private List<ClienteModel> clientes;
    private List<TransaccionModel> transacciones;
    private CorroboradorUsuario corroboradorUsuario = new CorroboradorUsuario();
    private List<String> nombreCliente;
    private List<CajeroModel> cajeros;
    
    
    /**
     * Obtiene el historial de una entidad en especifico
     * @param tipoEntidad
     * @return 
     */
    public List<HistorialModel> imprimirHistorialEntidad(String tipoEntidad){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            String time = new Time(0).getTodayDate();
            historial = new LinkedList<>();
            //Obtenemos las historiales pertenecientes al usuario cliente
            String comando = "SELECT H.NoActualizacion, H.FechaActualizacion, H.HoraActualizacion, H.IDUsuario, U.Tipo, H.IDGerente,  G.Nombre, H.DescripcionCambio"
                    + " FROM HISTORIAL_ACTUALIZACIONES H LEFT JOIN USUARIO U ON H.IDUsuario=U.Codigo LEFT JOIN GERENTE G ON H.IDGerente=G.DPI "
                    + "WHERE U.Tipo=? ORDER BY H.FechaActualizacion";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, tipoEntidad);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                historial.add(new HistorialModel(
                        resultado.getString("H.NoActualizacion"), 
                        resultado.getString("H.FechaActualizacion"),
                        resultado.getString("H.HoraActualizacion"),
                        resultado.getString("H.IDUsuario"),
                        resultado.getString("U.Tipo"), 
                        resultado.getString("H.IDGerente"),
                        resultado.getString("G.Nombre"),
                        resultado.getString("H.DescripcionCambio")));
            }
            new Conexion().CloseConnection();
            return historial;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    /**
     * Devuelve los clientes cuyas transacciones superaron el monto establecido por el gerente
     * @return 
     */
    public List<ClienteModel> imprimirClientesLimiteEnTransaccionSolitaria(){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            clientes = new LinkedList<>();
            //Obtenemos los clientes cuyos montos son mayores al permitido
            String comando = "SELECT Count(*) as Cantidad, SUM(T.Monto) AS TOTAL ,  CL.Nombre, CL.NoUsuario, CL.DPI, CL.FechaNacimiento, CL.Direccion, CL.Sexo "
                    + "FROM TRANSACCION T LEFT JOIN CUENTA C ON T.Cuenta = C.Codigo LEFT JOIN CLIENTE CL ON C.IDCliente = CL.DPI "
                    + "WHERE T.Monto > (SELECT Monto FROM MONTOTRANSACCION WHERE Estado=true) "
                    + "GROUP BY CL.Nombre ORDER BY SUM(T.Monto) DESC";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                clientes.add(new ClienteModel(
                        resultado.getString("CL.Nombre"), 
                        resultado.getString("CL.NoUsuario"),
                        resultado.getString("CL.DPI"),
                        resultado.getString("CL.FechaNacimiento"),
                        resultado.getString("CL.Direccion"), 
                        resultado.getString("CL.Sexo"),
                        resultado.getInt("Cantidad"),
                        resultado.getDouble("TOTAL")));
            }
            new Conexion().CloseConnection();
            return clientes;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    //SELECT COUNT(*) AS Cantidad, SUM(T.MONTO) AS Total,  CL.Nombre, T.Monto, CL.NoUsuario, CL.DPI, CL.FechaNacimiento, CL.Direccion, CL.Sexo FROM TRANSACCION T LEFT JOIN CUENTA C ON T.Cuenta = C.Codigo LEFT JOIN CLIENTE CL ON C.IDCliente = CL.DPI WHERE T.FechaRealizacion = '2020-12-11' GROUP BY CL.Nombre
        
    
    /**
     * Retorna los nombres de los clientes que superaron las transacciones permitidas en 1 dia y si tambien pasaron el limite de monto establecido
     * @return 
     */
    public List<ClienteModel> imprimirClientesLimiteEnTransaccionSumadas(String fecha){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            clientes = new LinkedList<>();
            //Obtenemos las transacciones realizadas por los clientes en una fecha especifica, retornando la cantidad de transacciones realizadas y los montos realizados
            String comando = "SELECT COUNT(*) AS Cantidad, SUM(T.MONTO) AS Total,  CL.Nombre, T.Monto, CL.NoUsuario, CL.DPI, CL.FechaNacimiento, CL.Direccion, CL.Sexo "
                    + "FROM TRANSACCION T LEFT JOIN CUENTA C ON T.Cuenta = C.Codigo LEFT JOIN CLIENTE CL ON C.IDCliente = CL.DPI "
                    + " GROUP BY CL.Nombre";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                clientes.add(new ClienteModel(
                        resultado.getString("CL.Nombre"), 
                        resultado.getString("CL.NoUsuario"),
                        resultado.getString("CL.DPI"),
                        resultado.getString("CL.FechaNacimiento"),
                        resultado.getString("CL.Direccion"), 
                        resultado.getString("CL.Sexo"),
                        resultado.getInt("Cantidad"),
                        resultado.getDouble("Total")));
            }
            //Ahora obtenemos los limites permitidos en administracion de las transacciones
            comando = "SELECT Cantidad,Monto  FROM MONTOTRANSACCIONVARIAS WHERE Estado=true";
            statement = connection.prepareStatement(comando);
            resultado = statement.executeQuery();
            if(resultado.next()){
                int cantidadCuentasPermitidas = resultado.getInt("Cantidad");
                double totalPermitido = resultado.getDouble("Monto");
                //Ahora quitaremos los clientes que no superan ese limite
                for(int indexClientes=0; indexClientes<clientes.size(); indexClientes++){
                    if( (clientes.get(indexClientes).getTotal()<totalPermitido) || (clientes.get(indexClientes).getCantidadCuentas()<=cantidadCuentasPermitidas && (clientes.get(indexClientes).getTotal()<totalPermitido))){
                        clientes.remove(indexClientes);
                        indexClientes=-1;
                    }
                }
            }
            new Conexion().CloseConnection();
            return clientes;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    
    /**
     * Obtiene los clientes con mayor cantidad de dinero
     * @param tipoEntidad
     * @return 
     */
    public List<ClienteModel> imprimirClientesMayorCredito(){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            clientes = new LinkedList<>();
            //Obtenemos de primero las cuentas con mayor dinero las organizamos y tomamos los datos de los clientes
            String comando = "SELECT COUNT(*) AS Cantidad, CL.Nombre, CL.DPI, CL.NoUsuario, CL.FechaNacimiento, CL.Direccion, CL.Sexo, SUM(C.Credito) AS TOTAL "
                    + "FROM CUENTA C LEFT JOIN CLIENTE CL ON C.IDCliente=CL.DPI GROUP BY CL.DPI ORDER BY SUM(C.Credito) DESC LIMIT 10";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                clientes.add(new ClienteModel(
                        resultado.getString("CL.Nombre"), 
                        resultado.getString("CL.NoUsuario"),
                        resultado.getString("CL.DPI"),
                        resultado.getString("CL.FechaNacimiento"),
                        resultado.getString("CL.Direccion"), 
                        resultado.getString("CL.Sexo"),
                        resultado.getInt("Cantidad"),
                        resultado.getDouble("TOTAL")));
            }
            new Conexion().CloseConnection();
            return clientes;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    /**
     * Devuelve los clientes que no han realizado una transaccion en un periodo de tiempo
     * @param fechaInicio
     * @param fechaFinal
     * @return 
     */
    public List<ClienteModel> imprimirClientesSinActividad(String fechaInicio, String fechaFinal){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            clientes = new LinkedList<>();
            //Obtenemos todos los datos de los clientes que no aparecen en las transacciones de x fecha a y fecha
            String comando = "SELECT Nombre, DPI, FechaNacimiento, Direccion, Sexo, NoUsuario "
                    + "FROM CLIENTE WHERE DPI NOT IN(SELECT  CL.DPI FROM TRANSACCION T LEFT JOIN CUENTA C ON T.Cuenta=C.Codigo LEFT JOIN CLIENTE CL ON C.IDCliente=CL.DPI "
                    + "WHERE T.FechaRealizacion BETWEEN ? AND ?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, fechaInicio);
            statement.setString(2, fechaFinal);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                clientes.add(new ClienteModel(
                        resultado.getString("Nombre"), 
                        resultado.getString("NoUsuario"),
                        resultado.getString("DPI"),
                        resultado.getString("FechaNacimiento"),
                        resultado.getString("Direccion"), 
                        resultado.getString("Sexo"),
                        0,
                        0));
            }
            new Conexion().CloseConnection();
            return clientes;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    /**
     * Devuelve el historial de un cliente
     * @param nombre
     * @return 
     */
    public List<TransaccionModel> imprimirHistorialTransaccionCliente(String nombre, double cantidadInicial, double cantidadFinal){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            transacciones = new LinkedList<>();
            //Obtenemos todos los datos de los clientes que no aparecen en las transacciones de x fecha a y fecha
            String comando = "SELECT CL.Nombre ,T.Codigo, T.FechaRealizacion, T.HoraRealizacion, T.Monto,T.Tipo,T.IDCajero, T.Cuenta  "
                    + " FROM TRANSACCION T LEFT JOIN CUENTA C ON T.Cuenta=C.Codigo LEFT JOIN CLIENTE CL ON C.IDCliente=CL.DPI"
                    + " WHERE CL.Nombre LIKE ? AND C.Credito>=? AND C.Credito<=? ORDER BY CL.Nombre DESC";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, nombre + "%");
            statement.setDouble(2, cantidadInicial);
            statement.setDouble(3, cantidadFinal);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                 transacciones.add(new TransaccionModel(
                        resultado.getString("T.Codigo"), 
                        resultado.getString("T.IDCajero"),
                        resultado.getString("T.Cuenta"),
                        resultado.getString("T.Monto"),
                        resultado.getString("T.Tipo"), 
                        resultado.getString("T.FechaRealizacion"),
                        resultado.getString("T.HoraRealizacion")));
            }
            new Conexion().CloseConnection();
            return transacciones;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    public List<CajeroModel> imprimirCajerosPorFecha(String fechaInicial, String fechaFinal){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            cajeros = new LinkedList<>();
            //Obtenemos todos los datos de los clientes que no aparecen en las transacciones de x fecha a y fecha
            String comando = "SELECT  COUNT(*) AS CantidadTransacciones, CA.DPI, CA.Nombre, CA.Turno, CA.Direccion, CA.Sexo, CA.NoUsuario "
                    + "FROM CAJERO CA LEFT JOIN TRANSACCION T ON CA.NoUsuario=T.IDCajero "
                    + "WHERE T.FechaRealizacion BETWEEN ? AND ? GROUP BY CA.DPI ORDER BY COUNT(*) DESC";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, fechaInicial);
            statement.setString(2, fechaFinal);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                 cajeros.add(new CajeroModel(
                        resultado.getString("CA.Nombre"), 
                        resultado.getString("CA.DPI"),
                        resultado.getString("CA.Direccion"),
                        resultado.getString("CA.Sexo"),
                        resultado.getString("CA.Turno"), 
                        resultado.getString("CA.NoUsuario"),
                        resultado.getString("CantidadTransacciones")));
            }
            new Conexion().CloseConnection();
            return cajeros;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
}
