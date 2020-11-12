/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.UploadFiles;

import File.ErrorHandlers.FormatException;
import File.ParserData.ParserTransaccion;
import File.SpecialOptions.Time;
import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    public Transaccion(String codigo, String idCajero, String cuenta, String monto, String tipo){
        this.codigo = codigo;
        this.idCajero = idCajero;
        this.cuenta = cuenta;
        this.fechaCreacion = new Time().getTodayDate();
        this.horaCreacion = new Time().getActualTime();
        this.monto = monto;
        this.tipo = tipo;
    }
    
    /**
     * Crea una transaccion y le agrega o quita el dinero dependiendo de la cuenta
     * ya sea credito o debito
     * @return 
     */
    public String subirArchivo(){
        try {
            Connection connection = new Conexion().CreateConnection();
            PreparedStatement statement = null;
            String comando1 = "INSERT INTO TRANSACCION (Codigo, FechaRealizacion, HoraRealizacion, Monto, Tipo, IDCajero, Cuenta) VALUES (?,?,?,?,?,?,?)";
            if(codigo==null){
                comando1 = "INSERT INTO TRANSACCION (FechaRealizacion, HoraRealizacion, Monto, Tipo, IDCajero, Cuenta) VALUES (?,?,?,?,?,?)";
                //Preparamos el statement para subirlo en la base de datos de Cuentas
                statement = connection.prepareStatement(comando1);
                statement.setString(1, fechaCreacion);
                statement.setString(2, horaCreacion);
                statement.setDouble(3, Double.parseDouble(monto));
                statement.setString(4, tipo);
                statement.setString(5, idCajero);
                statement.setString(6, cuenta);
                statement.executeUpdate();
            }else{
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
            }
            
            String comando2 = "UPDATE CUENTA SET Credito=Credito-? WHERE Codigo = ?";
            statement = connection.prepareStatement(comando2);
            if(tipo.equalsIgnoreCase("Credito")){
                statement.setDouble(1, Double.parseDouble(monto));
            }else{
                statement.setDouble(1, Double.parseDouble(monto)*-1);
            }
            statement.setString(2, cuenta);
            statement.executeUpdate();
            //Subimos el archivo
                        
            if(codigo==null){
                String comando3 = "SELECT Codigo FROM TRANSACCION WHERE IDCajero=? AND Cuenta=? AND FechaRealizacion=? AND HoraRealizacion=? AND Monto=? AND Tipo=?";
                statement = connection.prepareStatement(comando3);
                statement.setString(1, idCajero);
                statement.setString(2, cuenta);
                statement.setString(3, fechaCreacion);
                statement.setString(4, horaCreacion);
                statement.setDouble(5, Double.parseDouble(monto));
                statement.setString(6, tipo);
                ResultSet resultado = statement.executeQuery();
                if(resultado.next()){
                    codigo=resultado.getString("Codigo");
                }
            }
            
            new Conexion().CloseConnection();
            return " Transaccion "+codigo+" de "+tipo+" con monto Q" + monto+" realizada correctamente";    
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (Exception e) {
            new Conexion().CloseConnection();
            e.printStackTrace();
            return " Transaccion "+codigo+" con monto Q" + monto+" no subida por " + e.getMessage();    
        }
    }
}
