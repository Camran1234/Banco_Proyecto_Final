/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.UploadFiles;

import File.ErrorHandlers.FormatException;
import File.ParserData.ParserTransaccion;
import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;

/**
 *
 * @author camran1234
 */
public class Transaccion {
    private String mensaje;
    private String codigo;
    private String idCajero;
    private String cuenta;
    private String fechaCreacion;
    private String horaCreacion;
    private String monto;
    private String tipo; 
    private ParserTransaccion parser = new ParserTransaccion();
    
    public Transaccion(Element elementoXML) throws FormatException{
        mensaje = parser.obtainElements(elementoXML);
        codigo = parser.returnCodigo();
        idCajero = parser.returnIDCajero();
        cuenta = parser.returnCuenta();
        fechaCreacion = parser.returnFechaCreacion();
        horaCreacion = parser.returnHoraCreacion();
        monto = parser.returnMonto();
        tipo = parser.returnTipo();
    }
    
    public String subirArchivo(){
        try {
            
            String comando1 = "INSERT INTO TRANSACCION (Codigo, FechaRealizacion, HoraRealizacion, Monto, Tipo, IDCajero, Cuenta) VALUES (?,?,?,?,?,?,?)";
            Connection connection = new Conexion().CreateConnection();
            PreparedStatement statement = null;
            //Preparamos el statement para subirlo en la base de datos de Cuentas
            statement = connection.prepareStatement(comando1);
            statement.setString(1, codigo);
            statement.setString(2, fechaCreacion);
            statement.setString(3, horaCreacion);
            statement.setDouble(4, Double.parseDouble(monto));
            statement.setString(5, tipo);
            statement.setString(6, idCajero);
            statement.setString(7, cuenta);
            statement.executeUpdate();
            //Subimos el archivo
            new Conexion().CloseConnection();
            return " Transaccion "+codigo+" con monto Q" + monto+" subida correctamente";    
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (Exception e) {
            new Conexion().CloseConnection();
            e.printStackTrace();
            return " Transaccion "+codigo+" con monto Q" + monto+" no subida por " + e.getMessage();    
        }
    }
}
