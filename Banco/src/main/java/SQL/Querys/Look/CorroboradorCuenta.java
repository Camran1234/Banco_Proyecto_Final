/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Querys.Look;

import File.ErrorHandlers.FormatException;
import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author camran1234
 */
public class CorroboradorCuenta {
    
    /**
     * Funcion para comprobar los valores enviados
     * @param codigo
     * @param nombre
     * @param dpi
     * @return
     * @throws FormatException 
     */
    public String checkCorrectData(String codigo, String nombre, String dpi) throws FormatException{
        
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            
            if(dpi.length()!=13){                    
                throw new FormatException (" El dpi no contiene 13 digitos ");    
            }
            try{                    
                Long.parseLong(dpi);                
            }catch(Exception ex){
                throw new FormatException (" El dpi no es un numero ");
            }
            String comprobadorCodigo=null;
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM CUENTA WHERE Codigo=? AND IDCliente=(SELECT DPI FROM CLIENTE WHERE DPI=? AND Nombre LIKE ?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            statement.setString(2, dpi);
            statement.setString(3, nombre+"%");
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                return "Datos Correctos";
            }else{
                throw new FormatException ("No se encontro el cliente");
            }
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();     
               throw new FormatException ("Error Fatal");          
        }   
    }
    
     /**
     * Comprueba si el codigo enviado existe en la base de datos
     * @param numeroCuenta
     * @return 
     */
    public Boolean checkIfCodeExists(String numeroCuenta){
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            String cuentaComprobada = "";
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM CUENTA WHERE Codigo=?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, numeroCuenta);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                cuentaComprobada = resultado.getString("Codigo");
            }
            new Conexion().CloseConnection();
            if(cuentaComprobada.equalsIgnoreCase(numeroCuenta)){
                return true;
            }
            return false;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return false;
    }
    
    /**
     * Comprueba si la cuenta enviada tiene los suficientes fondos
     * @param numeroCuenta
     * @param monto
     * @param cliente
     * @return 
     */
    public String checkIfEnoughFounds(String numeroCuenta, String monto, String cliente){
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            String dinero = null;
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT Credito FROM CUENTA WHERE CODIGO=? AND IDCliente=?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, numeroCuenta);
            statement.setString(2, cliente);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                dinero = resultado.getString("Credito");
            }
            new Conexion().CloseConnection();
            if(dinero==null){
                return "La cuenta dada no le pertenece";
            }
            if(Double.parseDouble(monto)>Double.parseDouble(dinero)){
                return "El dinero de la cuenta no soporta el monto establecido de: Q"+monto+ " se tiene: Q"+dinero;
            }
            return "SI";
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return "ERROR FATAL";
    }
}
