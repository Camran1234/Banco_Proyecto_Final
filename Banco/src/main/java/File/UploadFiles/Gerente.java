/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.UploadFiles;

import File.ErrorHandlers.FormatException;
import File.ParserData.ParserEmpleado;
import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.w3c.dom.Element;

/**
 *
 * @author camran1234
 */
public class Gerente extends Usuario{
    private String turno;
    private ParserEmpleado parser = new ParserEmpleado();
    
    public Gerente(Element elementoXML) throws FormatException{
        mensaje = parser.obtainElements(elementoXML);
        codigo = parser.returnCodigo();
        nombre = parser.returnNombre();
        password = parser.returnPassword();
        sexo = parser.returnSexo();
        dpi = parser.returnDpi();
        turno = parser.returnNombre();
        direccion = parser.returnDireccion();
    }
    
    @Override
    public String subirArchivo(){
        try {
            String comando1 = "INSERT INTO USUARIO (Codigo, Password,Tipo) VALUES (?,?,?)";
            String comando3 = "INSERT INTO GERENTE (DPI, Nombre, Turno, Direccion, Sexo, NoUsuario) VALUES (?,?,?,?,?,?)";
            //Comprobamos de que no halla repeticiones en el usuario
            Connection connection = new Conexion().CreateConnection();
            PreparedStatement statement = null;
            //Preparamos el statement para subirlo en la base de datos de Usuario            
            statement = connection.prepareStatement(comando1);            
            statement.setString(1, codigo);
            statement.setString(2, password);
            statement.setString(3, "GERENTE");
            statement.executeUpdate();            
            statement = connection.prepareStatement(comando3);            
            statement.setString(1, dpi);            
            statement.setString(2, nombre);            
            statement.setString(3, turno);            
            statement.setString(4, direccion);            
            statement.setString(5, sexo);
            statement.setString(6, codigo);
            statement.executeUpdate();         
            new Conexion().CloseConnection();
            //Subimos el archivo
            return "Archivo Subido Correctamente: "+mensaje;    
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (Exception e) {
            new Conexion().CloseConnection();
            e.printStackTrace();
            return "Archivo No subido Correctamente por: " + e.getMessage();
        }
    }
}
