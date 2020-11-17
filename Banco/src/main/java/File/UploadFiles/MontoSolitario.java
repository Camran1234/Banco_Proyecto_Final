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
public class MontoSolitario {
    private String monto;
    
    public MontoSolitario(String monto) throws FormatException{
        try {
            Double.parseDouble(monto);
        } catch (Exception e) {
            throw new FormatException ("El monto no es un numero");
        }
        if(Double.parseDouble(monto)<0){
            throw new FormatException ("El monto no puede ser negativo");
        }
        this.monto = monto;
    }
    
    public String subirArchivo(){
        try {
            String comando1 = "UPDATE MONTOTRANSACCION SET Estado=False";
            String comando3 = "INSERT INTO MONTOTRANSACCION (Estado, Monto) VALUES (?,?)";
            //Comprobamos de que no halla repeticiones en el usuario
            Connection connection = new Conexion().CreateConnection();
            PreparedStatement statement = null;
            //Preparamos el statement para subirlo en la base de datos de Usuario            
            statement = connection.prepareStatement(comando1);            
            statement.executeUpdate();            
            statement = connection.prepareStatement(comando3);            
            statement.setBoolean(1, true);            
            statement.setDouble(2, Double.parseDouble(monto));            
            statement.executeUpdate();         
            new Conexion().CloseConnection();
            //Subimos el archivo
            return "Monto Actualizado a: Q"+monto;    
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (Exception e) {
            new Conexion().CloseConnection();
            e.printStackTrace();
            return "No se pudo actualizar el monto";
        }
    }
}
