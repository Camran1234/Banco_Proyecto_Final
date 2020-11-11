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
public class InfoCajero {
    /**
     * Funcion que devuelve una lista que contiene las lista de los nombre con los dpi y con los codigos de usuario
     * @return 
     */
    public ArrayList<ArrayList> InformacionBasica(){
        
        
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            ArrayList<ArrayList> array = new ArrayList<>();
            ArrayList<String> nombre = new ArrayList<>();
            ArrayList<String> dpi = new ArrayList<>();
            ArrayList<String> codigo = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM CAJERO ORDER BY DPI";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                nombre.add(resultado.getString("Nombre"));
                dpi.add(resultado.getString("DPI"));
                codigo.add(resultado.getString("NoUsuario"));
            }
            array.add(nombre);               
            array.add(dpi);            
            array.add(codigo);
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
            String comando = "SELECT * FROM CAJERO WHERE NoUsuario=?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                datos.add(resultado.getString("Nombre"));
                datos.add(resultado.getString("DPI"));
                datos.add(resultado.getString("Direccion"));
                datos.add(resultado.getString("Sexo"));
                datos.add(resultado.getString("Turno"));
                datos.add(resultado.getString("NoUsuario"));
            }
            new Conexion().CloseConnection();
            return datos;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
        
    }
}
