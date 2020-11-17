/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Reportes;

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

/**
 *
 * @author camran1234
 */
public class ReportesCajero {
    private Connection connection;
    private List<TransaccionModel> transaccion;
    private CorroboradorUsuario corroboradorUsuario = new CorroboradorUsuario();
    
    /**
     * Obtiene las transacciones realizadas por el cajero durante el dia actual
     * @param codigoCajero
     * @return 
     */
    public List<TransaccionModel> imprimirTransaccionesTurno(String codigoCajero){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            String time = new Time(0).getTodayDate();
            ArrayList<String> cuentas = new ArrayList<>();
            transaccion = new LinkedList<>();
            //Obtenemos las transacciones pertenecientes al usuario cliente
            String comando = "SELECT T.Codigo, T.FechaRealizacion, T.HoraRealizacion, T.Cuenta, T.Monto, T.Tipo, T.IDCajero "
                    + "FROM TRANSACCION T LEFT JOIN CAJERO C ON T.IDCajero=C.DPI "
                    + "WHERE T.IDCajero=? AND T.FechaRealizacion=? ORDER BY Tipo";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoCajero);
            statement.setString(2, time);
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
     * Devuelve las transacciones del cajero indicado seleccionado en un intervalo de tiempo de fecha y hora
     * @param codigoCajero
     * @param fechaInicio
     * @param fechaFinal
     * @param horaInicio
     * @param horaFinal
     * @return 
     */
    public List<TransaccionModel> imprimirTransaccionesPorIntervalo(String codigoCajero, String fechaInicio, String fechaFinal, String horaInicio, String horaFinal){
        try {  
            //Se crea la coenxion
            connection = new Conexion().CreateConnection();
            ArrayList<String> cuentas = new ArrayList<>();
            transaccion = new LinkedList<>();
            //Obtenemos las transacciones pertenecientes al usuario cliente
            String comando = "SELECT T.Codigo, T.FechaRealizacion, T.HoraRealizacion, T.Cuenta, T.Monto, T.Tipo, T.IDCajero "
                    + "FROM TRANSACCION T LEFT JOIN CAJERO C ON T.IDCajero=C.DPI "
                    + "WHERE T.IDCajero=? AND T.FechaRealizacion BETWEEN ? AND ?  AND T.HoraRealizacion BETWEEN ? AND ?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoCajero);
            statement.setString(2, fechaInicio);
            statement.setString(3, fechaFinal);
            statement.setString(4, horaInicio);
            statement.setString(5, horaFinal);
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
}
