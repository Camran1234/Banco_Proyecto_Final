/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Get;

import File.ErrorHandlers.FormatException;
import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class InfoCuenta {
    
    /**
     * Obtiene los codigos y creditos de la cuenta pertenecientes al idCliente
     * @param idCliente
     * @return 
     */
    public ArrayList<ArrayList> InformacionBasica(String idCliente){
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            ArrayList<ArrayList> array = new ArrayList<>();
            ArrayList<String> codigo = new ArrayList<>();
            ArrayList<String> credito = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM CUENTA WHERE IDCliente=? ORDER BY Codigo";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, idCliente);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                codigo.add(resultado.getString("Codigo"));
                credito.add(resultado.getString("Credito"));
            }
            array.add(codigo);               
            array.add(credito);            
            new Conexion().CloseConnection();
            return array;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
    }
    
     /**
     * Devuelve una lista con todos los datos para llenar el form
     * @param codigo
     * @return 
     */
    public ArrayList<String> InformacionCompleta(String codigo){
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            ArrayList<String> datos = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM CUENTA WHERE IDCliente=?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                datos.add(resultado.getString("Codigo"));
                datos.add(resultado.getString("Credito"));
            }
            new Conexion().CloseConnection();
            return datos;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
        
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
    
    /**
     * Retorna el nombre del propietario de la cuenta indicada
     * @param numeroCuenta
     * @return 
     */
    public String GetNameOfCode(String numeroCuenta){
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            String nombre = null;
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT Nombre FROM CLIENTE WHERE DPI=(SELECT IDCliente FROM CUENTA WHERE Codigo=?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, numeroCuenta);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                nombre = resultado.getString("Nombre");
            }
            new Conexion().CloseConnection();
            return nombre;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }
        return null;
    }
    
    
}
