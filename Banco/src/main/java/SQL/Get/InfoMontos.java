/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Get;

import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class InfoMontos {
    
    /**
     * Devuelve el deposito permitido
     * @return 
     */
    public String obtenerMontoSolitario(){
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            String monto = null;
            ArrayList<String> datos = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM MONTOTRANSACCION WHERE Estado=true";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                monto = resultado.getString("Monto");
            }
            new Conexion().CloseConnection();
            return monto;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
    }
    
    /**
     * Devuelve la cantidad aceptada en varias transacciones
     * @return 
     */
    public String obtenerMontoMultiple(){
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            String monto=null;
            ArrayList<String> datos = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM MONTOTRANSACCIONVARIAS WHERE Estado=true";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                monto = resultado.getString("Monto");
            }
            new Conexion().CloseConnection();
            return monto;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
    }
    
    /**
     * Devuelve la cantidad de cuentas aceptadas para varias transacciones
     * @return 
     */
    public String obtenerCantidadCuentas(){
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            String monto=null;
            ArrayList<String> datos = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM MONTOTRANSACCIONVARIAS WHERE Estado=true";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                monto = resultado.getString("Cantidad");
            }
            new Conexion().CloseConnection();
            return monto;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
    }
}
