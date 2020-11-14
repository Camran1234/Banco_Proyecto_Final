/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.UploadFiles;

import File.SpecialOptions.Time;
import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class HistorialActualizacion {
    private Time timeA = new Time();
    
    public String subirHistorial(String descripcion, String dpiGerente, String IDUsuario){
        try {
            String fecha = timeA.getTodayDate();
            String hora = timeA.getActualTime();
            String comando1 = "INSERT INTO HISTORIAL_ACTUALIZACIONES (HoraActualizacion, FechaActualizacion,DescripcionCambio, IDUsuario , IDGerente) VALUES (?,?,?,?,?)";
            //Comprobamos de que no halla repeticiones en el usuario
            Connection connection = new Conexion().CreateConnection();
            PreparedStatement statement = null;
            //Preparamos el statement para subirlo en la base de datos de Usuario            
            statement = connection.prepareStatement(comando1);            
            statement.setString(1, hora);
            statement.setString(2, fecha);
            statement.setString(3, descripcion);
            statement.setString(4, IDUsuario);
            statement.setString(5, dpiGerente);
            statement.executeUpdate();            
            new Conexion().CloseConnection();
            //Subimos el archivo
            return "Historial Generado ";    
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (Exception e) {
            new Conexion().CloseConnection();
            e.printStackTrace();
            return "Archivo No subido Correctamente por: " + e.getMessage();
        }
    }
}
