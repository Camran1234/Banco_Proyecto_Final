/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Reportes;

import File.ReportFiles.CuentaModel;
import File.ReportFiles.SolicitudModel;
import File.ReportFiles.TransaccionModel;
import File.SpecialOptions.Time;
import File.UploadFiles.Transaccion;
import SQL.Conexion.Conexion;
import SQL.Get.InfoCuenta;
import SQL.Querys.Look.CorroboradorUsuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Genera listas para crear los reportes de los clientes
 * @author camran1234
 */
public class ReportesCliente {
    private CorroboradorUsuario corroboradorUsuario = new CorroboradorUsuario();
    List<TransaccionModel> transaccion;
    CuentaModel cuentaModel;
    private Connection connection;
    /**
     * Imprime las ultimas 15 transacciones mas grandes generadas en el ultimo año,
     *  Se seleccionan las cuentas segun el codigo de cliente indicado
     * El limite se indica el limite de las transacciones mostradas
     * @param codigo
     * @return 
     */
    public List<TransaccionModel> imprimirTransaccionesConLimite(String codigo, int limite){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            String idCliente = corroboradorUsuario.getDpi(codigo);
            String time = new Time(1).getTodayDate();
            ArrayList<String> cuentas = new ArrayList<>();
            transaccion = new LinkedList<>();
            //Obtenemos las transacciones pertenecientes al usuario cliente
            String comando = "SELECT T.Codigo, T.FechaRealizacion, T.HoraRealizacion, T.Cuenta, T.Monto, T.Tipo, T.IDCajero "
                    + "FROM TRANSACCION T LEFT JOIN CUENTA C ON T.Cuenta=C.Codigo LEFT JOIN CLIENTE CL ON C.IDCliente=CL.DPI "
                    + "WHERE CL.DPI=? AND FechaRealizacion>=? ORDER BY T.Monto DESC LIMIT ?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, idCliente);
            statement.setString(2, time);
            statement.setInt(3, limite);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                transaccion.add(new TransaccionModel(
                        resultado.getString("T.Codigo"), 
                        resultado.getString("T.IDCajero"),
                        resultado.getString("T.Cuenta"),
                        resultado.getString("T.Monto"),
                        resultado.getString("T.Tipo"), 
                        resultado.getString("T.FechaRealizacion"),
                        resultado.getString("T.HoraRealizacion")));
            }
            //Ahora regresamos las transacciones que poseen alguna de estas cuentas
            new Conexion().CloseConnection();
            
            if(transaccion.size()==0){
               return null;
            }
            
            return transaccion;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    /**
     * Obtiene las transacciones realizadas en un intervalo de tiempo
     * @param codigo
     * @return 
     */
    public List<TransaccionModel> imprimirTransaccionesIntervaloTiempo(String codigo, String fechaInicio, String fechaFinal){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            String idCliente = corroboradorUsuario.getDpi(codigo);
            ArrayList<String> cuentas = new ArrayList<>();
            transaccion = new LinkedList<>();
            //Obtenemos las transacciones pertenecientes al usuario cliente
            String comando = "SELECT T.Codigo, T.FechaRealizacion, T.HoraRealizacion, T.Cuenta, T.Monto, T.Tipo, T.IDCajero"
                    + " FROM TRANSACCION T LEFT JOIN CUENTA C ON T.Cuenta=C.Codigo LEFT JOIN CLIENTE CL ON C.IDCliente=CL.DPI  "
                    + "WHERE CL.DPI= ? AND T.FechaRealizacion BETWEEN ? AND ? ORDER BY T.FechaRealizacion DESC";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, idCliente);
            statement.setString(2, fechaInicio);
            statement.setString(3, fechaFinal);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                transaccion.add(new TransaccionModel(
                        resultado.getString("T.Codigo"), 
                        resultado.getString("T.IDCajero"),
                        resultado.getString("T.Cuenta"),
                        resultado.getString("T.Monto"),
                        resultado.getString("T.Tipo"), 
                        resultado.getString("T.FechaRealizacion"),
                        resultado.getString("T.HoraRealizacion")));
            }
            //Ahora regresamos las transacciones que poseen alguna de estas cuentas
            new Conexion().CloseConnection();
            
            if(transaccion.size()==0){
               return null;
            }
            
            return transaccion;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    
    
    
    
    /**
     * Devuelve la cuenta con mas dinero 
     * @param codigo
     * @param fechaInicio
     * @param fechaFinal
     * @return 
     */
    public CuentaModel imprimirCuentaMayorDinero(String codigo){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            //Obtenemos el dpi del cliente
            String idCliente = corroboradorUsuario.getDpi(codigo);
            //Obtenemos la cuenta con mayor dinero del idCliente
            String comando = "SELECT Codigo, FechaCreacion, Credito, IDCliente FROM CUENTA C WHERE IDCliente=? ORDER BY Credito DESC LIMIT 1";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, idCliente);
            ResultSet resultado = statement.executeQuery();
            //Creamos el constructor
            if(resultado.next()){
                cuentaModel = new CuentaModel(
                        resultado.getString("Codigo"), 
                        resultado.getString("FechaCreacion"),
                        resultado.getString("Credito"),
                        resultado.getString("IDCliente"));
            }else{
                return null;
            }
            //Ahora regresamos las transacciones que poseen alguna de estas cuentas
            new Conexion().CloseConnection();
            return cuentaModel;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    /**
     * Imprime las transacciones de una cuenta en especifico con un limite de tiempo
     * @param cuenta
     * @param fechaInicio
     * @param fechaFinal
     * @return 
     */
    public List<TransaccionModel> imprimirTransaccionesCuenta(String cuenta, String fechaInicio, String fechaFinal){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            transaccion = new LinkedList<>();
            //Obtenemos las transacciones pertenecientes al numero de cuenta indicado
            String comando = "SELECT * FROM TRANSACCION WHERE Cuenta=? AND FechaRealizacion Between ? AND ?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, cuenta);
            statement.setString(2, fechaInicio);
            statement.setString(3, fechaFinal);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                transaccion.add(new TransaccionModel(
                        resultado.getString("Codigo"), 
                        resultado.getString("IDCajero"),
                        resultado.getString("Cuenta"),
                        resultado.getString("Monto"),
                        resultado.getString("Tipo"), 
                        resultado.getString("FechaRealizacion"),
                        resultado.getString("HoraRealizacion")));
            }
            //Ahora regresamos las transacciones
            new Conexion().CloseConnection();
            
            if(transaccion.size()==0){
               return null;
            }
            
            return transaccion;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    /**
     * Obtiene las solicitudes enviadas de este usuario
     * @param cuenta
     * @param fechaInicio
     * @param fechaFinal
     * @return 
     */
    public List<SolicitudModel> imprimirSolicitudesEnviadas(String codigo){
        try {
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            String idCliente = corroboradorUsuario.getDpi(codigo);
            List<SolicitudModel> solicitudes = new LinkedList<>();
            //Selecciona las solicitudes enviadas
            String comando = "SELECT CA.IDCuentaA AS CuentaReceptora, CA.IDCuentaB AS CuentaEmisora, CA.Estado,  CL.Nombre, CL.DPI "
                    + "FROM CUENTAS_ASOCIADAS CA LEFT JOIN CUENTA C ON CA.IDCuentaB=C.Codigo LEFT JOIN CLIENTE CL ON CL.DPI=C.IDCliente "
                    + "WHERE CL.DPI=?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, idCliente);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                solicitudes.add(new SolicitudModel(
                        resultado.getString("CuentaReceptora"),
                        resultado.getString("CuentaEmisora"),
                        resultado.getString("CA.Estado"),
                        new InfoCuenta().getNameOfCode(resultado.getString("CuentaReceptora")),
                        resultado.getString("CL.DPI")));
            }
            //Ahora regresamos las transacciones
            new Conexion().CloseConnection();
            
            if(solicitudes.size()==0){
               return null;
            }
            
            return solicitudes;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    /**
     * Devuelve las solicitudes recibidas de este usuario
     * @param codigo
     * @return 
     */
    public List<SolicitudModel> imprimirSolicitudesRecibidas(String codigo){
        try {
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            String idCliente = corroboradorUsuario.getDpi(codigo);
            List<SolicitudModel> solicitudes = new LinkedList<>();
            //Selecciona las solicitudes enviadas
            String comando = "SELECT CA.IDCuentaA AS CuentaReceptora, CA.IDCuentaB AS CuentaEmisora, CA.Estado,   CL.DPI "
                    + " FROM CUENTAS_ASOCIADAS CA LEFT JOIN CUENTA C ON CA.IDCuentaA=C.Codigo LEFT JOIN CLIENTE CL ON CL.DPI=C.IDCliente "
                    + "WHERE CL.DPI=?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, idCliente);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                solicitudes.add(new SolicitudModel(
                        resultado.getString("CuentaReceptora"),
                        resultado.getString("CuentaEmisora"),
                        resultado.getString("CA.Estado"),
                        new InfoCuenta().getNameOfCode(resultado.getString("CuentaEmisora")),
                        resultado.getString("CL.DPI")));
            }
            //Ahora regresamos las transacciones
            new Conexion().CloseConnection();
            
            if(solicitudes.size()==0){
               return null;
            }
            
            return solicitudes;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    
    
    
    
}
