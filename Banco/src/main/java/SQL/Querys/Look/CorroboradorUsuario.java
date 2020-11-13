/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Querys.Look;

import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class CorroboradorUsuario {
    public String checkLogIn(String codigo, String password){
        
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            
            String comprobadorCodigo=null;
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM USUARIO WHERE Codigo=(?) AND Password=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            statement.setString(2, password);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("Tipo");
                System.out.println(comprobadorCodigo);
                if(comprobadorCodigo.equalsIgnoreCase("")==false || comprobadorCodigo!=null){
                    return comprobadorCodigo;
                }
            }
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
    }
    
    /**
     * Obtiene un nombre en base al codigo de usuario
     * @param codigo
     * @return 
     */
    public String getName(String codigo){
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            
            String comprobadorCodigo=null;
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT Nombre FROM GERENTE WHERE NoUsuario=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("Nombre");
                System.out.println(comprobadorCodigo);
                if(comprobadorCodigo.equalsIgnoreCase("")==false || comprobadorCodigo!=null){
                    return comprobadorCodigo;
                }
            }
            
            comando = "SELECT Nombre FROM CLIENTE WHERE NoUsuario=(?)";
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
             resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("Nombre");
                System.out.println(comprobadorCodigo);
                if(comprobadorCodigo.equalsIgnoreCase("")==false || comprobadorCodigo!=null){
                    return comprobadorCodigo;
                }
            }
            
            comando = "SELECT Nombre FROM CAJERO WHERE NoUsuario=(?)";
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
             resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("Nombre");
                System.out.println(comprobadorCodigo);
                if(comprobadorCodigo.equalsIgnoreCase("")==false || comprobadorCodigo!=null){
                    return comprobadorCodigo;
                }
            }
            
            
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
    }
    
    /**
     * Obtiene un dpi en base a un codigo de usuario
     * @param codigo
     * @return 
     */
    public String getDpi(String codigo){
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            
            String comprobadorCodigo=null;
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT DPI FROM GERENTE WHERE NoUsuario=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("DPI");
                System.out.println(comprobadorCodigo);
                if(comprobadorCodigo.equalsIgnoreCase("")==false || comprobadorCodigo!=null){
                    return comprobadorCodigo;
                }
            }
            
            comando = "SELECT DPI FROM CLIENTE WHERE NoUsuario=(?)";
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
             resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("DPI");
                System.out.println(comprobadorCodigo);
                if(comprobadorCodigo.equalsIgnoreCase("")==false || comprobadorCodigo!=null){
                    return comprobadorCodigo;
                }
            }
            
            comando = "SELECT DPI FROM CAJERO WHERE NoUsuario=(?)";
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
             resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("DPI");
                if(comprobadorCodigo.equalsIgnoreCase("")==false || comprobadorCodigo!=null){
                    return comprobadorCodigo;
                }
            }
            
            
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
    }
}
