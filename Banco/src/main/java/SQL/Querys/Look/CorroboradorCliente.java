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
public class CorroboradorCliente {
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
            String comando = "SELECT * FROM CLIENTE WHERE DPI=(?) AND Nombre=(?) AND NoUsuario=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, dpi);
            statement.setString(2, nombre);
            statement.setString(3, codigo);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("NoUsuario");
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
