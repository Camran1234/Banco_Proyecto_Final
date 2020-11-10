/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.UploadFiles;

import File.ErrorHandlers.FormatException;
import File.ParserData.ParserCliente;
import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;

/**
 *
 * @author camran1234
 */
public class Cliente extends Usuario{
    private String direccionPdf;
    private String fechaNacimiento;
    private ArrayList<String> creditos;
    private ArrayList<String> codigosCuentas;
    private ArrayList<String> fechasCuentas;
    private ParserCliente parser = new ParserCliente();
    
    public Cliente(Element elementoXML) throws FormatException{
        mensaje = parser.obtainElements(elementoXML);
        codigo = parser.returnCodigo();
        nombre = parser.returnNombre();
        password = parser.returnPassword();
        sexo = parser.returnSexo();
        dpi = parser.returnDpi();
        direccion = parser.returnDireccion();
        creditos = parser.returnMontosCuentas();
        fechasCuentas = parser.returnfechaCuentas();
        codigosCuentas = parser.returnCodigoCuentas();
        fechaNacimiento = parser.returnFechaNacimiento();
        direccionPdf = parser.returnDireccionPDF();
    }
    
    @Override
    public String subirArchivo(){
        try {
            String comando1 = "INSERT INTO USUARIO (Codigo, Password,Tipo) VALUES (?,?,?)";
            String comando2 = "INSERT INTO CLIENTE (DPI, Nombre, FechaNacimiento, Direccion, Sexo, DireccionPDF, NoUsuario) VALUES (?,?,?,?,?,?,?)";
            //Comprobamos de que no halla repeticiones en el usuario
            Connection connection = new Conexion().CreateConnection();
            PreparedStatement statement = null;
            //Preparamos el statement para subirlo en la base de datos de Usuario            
            statement = connection.prepareStatement(comando1);            
            statement.setString(1, codigo);
            statement.setString(2, password);            
            statement.setString(3, "CLIENTE");
            statement.executeUpdate();
            statement = connection.prepareStatement(comando2); 
            statement.setString(1, dpi);            
            statement.setString(2, nombre);            
            statement.setString(3, fechaNacimiento);            
            statement.setString(4, direccion);            
            statement.setString(5, sexo);
            statement.setString(6, direccionPdf);
            statement.setString(7, codigo);
            statement.executeUpdate();   
            
            for(int indexListas = 0; indexListas<codigosCuentas.size(); indexListas++){
                //Creamos la nueva Cuenta
                mensaje += new Cuenta(codigosCuentas.get(indexListas), fechasCuentas.get(indexListas), creditos.get(indexListas), dpi).subirArchivo();
            }
            
            new Conexion().CloseConnection();
            //Subimos el archivo
            return "Archivo Subido Correctamente: "+mensaje;    
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (Exception e) {
            new Conexion().CloseConnection();
            return "Archivo No subido Correctamente por: " + e.getMessage();
        }
    }
}
