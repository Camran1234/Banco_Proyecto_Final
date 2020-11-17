/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.UploadFiles;

import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author camran1234
 */
public class Cuenta {
    private Conexion conexion = new Conexion();
    private double credito;
    private String fecha;
    private String codigo;
    private String idCliente;
    
    public Cuenta(String codigo, String fecha, String credito, String idCliente){
        this.credito = Double.parseDouble(credito);
        this.codigo = codigo;
        this.fecha = fecha;
        this.idCliente = idCliente;
    }
    
    public String subirArchivo(){
        try {
            String comando1 = "INSERT INTO CUENTA (Codigo, FechaCreacion, Credito, IDCliente) VALUES (?,?,?,?)";
            Connection connection = new Conexion().CreateConnection();
            PreparedStatement statement = null;
            //Preparamos el statement para subirlo en la base de datos de Cuentas
            statement = connection.prepareStatement(comando1);
            statement.setString(1, codigo);
            statement.setString(2, fecha);
            statement.setDouble(3, credito);
            statement.setString(4, idCliente);
            statement.executeUpdate();
            //Subimos el archivo
            new Conexion().CloseConnection();
            return " Cuenta "+codigo+" con monto Q" + credito+" subida correctamente";    
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (Exception e) {
            new Conexion().CloseConnection();
            return " Cuenta "+codigo+" con monto Q" + credito+" no subida por " + e.getMessage();    
        }
    }
}
