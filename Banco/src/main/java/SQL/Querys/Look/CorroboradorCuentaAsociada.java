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
public class CorroboradorCuentaAsociada {
    
    
    /**
     * Comprueba si ambas cuentas estan asociadas
     * @param cuentaA
     * @param cuentaB
     * @return
     * @throws FormatException 
     */
    public String AccountAssociated(String cuentaA, String cuentaB) throws FormatException{
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            String mensaje=null;
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM CUENTAS_ASOCIADAS WHERE IDCuentaA=(?) AND IDCuentaB=(?) OR IDCuentaB=(?) AND IDCuentaA=(?) AND Estado=\"Aceptar\"";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, cuentaA);
            statement.setString(2, cuentaB);
            statement.setString(3, cuentaA);
            statement.setString(4, cuentaB);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                mensaje = resultado.getString("NoAsociacion");
                if(mensaje.equalsIgnoreCase("")==false || mensaje!=null){
                    return "SI";
                }
            }else{
                throw new FormatException("Las cuentas no estan asociadas");
            }
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
    }
    
    /**
     * Comprueba si ya existe una asociacion entre estas cuentas o si ya se envio
     * @param cuentaA
     * @param cuentaB
     * @return
     * @throws FormatException 
     */
    public String checkForRepeated(String cuentaA, String cuentaB) throws FormatException{
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            String mensaje=null;
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM CUENTAS_ASOCIADAS WHERE IDCuentaA=(?) AND IDCuentaB=(?) AND Estado=\"Pendiente\" OR Estado = \"Aceptar\"";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, cuentaA);
            statement.setString(2, cuentaB);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                mensaje = resultado.getString("NoAsociacion");
                if(mensaje.equalsIgnoreCase("")==false || mensaje!=null){
                    throw new FormatException("No puedes mandar 2 veces la solicitud de asociacion, a la misma cuenta");
                }
            }else{
                throw new FormatException("");
            }
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
    }
}
