/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.UploadFiles;

import File.ErrorHandlers.FormatException;
import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author camran1234
 */
public class AsociacionCuenta {
    private String cuentaA;
    private String cuentaB;
    
    /**
     * Clase para enviar, aceptar o rechazar solicitudes
     * IDCuentaB es la cuenta emisora
     * IDCuentaA es la cuenta receptora
     * @param cuentaA
     * @param cuentaB 
     */
    public AsociacionCuenta(String cuentaA, String cuentaB){
        this.cuentaA = cuentaA;
        this.cuentaB = cuentaB;
    }
    
    /**
     * Sube un nuevo archivo nuevo de cuentas asociadas
     * @return 
     */
    public String subirArchivo() throws FormatException{
        try {
            //Comando para ingresar una nueva tabla
            String comando1 = "INSERT INTO CUENTAS_ASOCIADAS (IDCuentaA, IDCuentaB, Estado) VALUES (?,?,?)";
            Connection connection = new Conexion().CreateConnection();
            PreparedStatement statement = null;
            //Preparamos el statement para subirlo en la base de datos de Cuentas_Asociadas
            statement = connection.prepareStatement(comando1);            
            statement.setString(1, cuentaA);
            statement.setString(2, cuentaB);
            statement.setString(3, "Pendiente");
            statement.executeUpdate();                                                            
            //Subimos el archivo
            new Conexion().CloseConnection();
            return "Se envio la solicitud de Asociacion";    
        } catch (Exception e) {
            new Conexion().CloseConnection();
            e.printStackTrace();
            throw new FormatException("Archivo No subido Correctamente por: " + e.getMessage());
        }
    }
    
    /**
     * Acepta o rechaza una solicitud de asociacion
     * @return
     * @throws FormatException 
     */
    public String modificarArchivo(String decision) throws FormatException{
        try {
            //Comando para ingresar una nueva tabla
            String comando1 = "UPDATE CUENTAS_ASOCIADAS SET Estado=? WHERE IDCuentaA=? AND IDCuentaB=?";
            if(decision.equalsIgnoreCase("Rechazar")){
                comando1 = "UPDATE CUENTAS_ASOCIADAS SET Estado=? WHERE IDCuentaA=? AND IDCuentaB=? ";
            }
            Connection connection = new Conexion().CreateConnection();
            PreparedStatement statement = null;
            //Preparamos el statement para subirlo en la base de datos de Cuentas_Asociadas
            statement = connection.prepareStatement(comando1);        
            statement.setString(1, decision);            
            statement.setString(2, cuentaA);            
            statement.setString(3, cuentaB);            
            statement.executeUpdate();                                                            
            //Subimos el archivo
            new Conexion().CloseConnection();
            return "";    
        } catch (Exception e) {
            new Conexion().CloseConnection();
            e.printStackTrace();
            throw new FormatException("Error Fatal: Ocurrio un error al aceptar las cuentas, por favor vuelva a intentar");
        }
    }
}
