/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.ParserData;

import File.ErrorHandlers.FormatException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author camran1234
 */
public class ParserTransaccion {
    private String codigo;
    private String idCajero;
    private String cuenta;
    private String fechaCreacion;
    private String horaCreacion;
    private String monto;
    private String tipo;
    
    
    /**
     * Obtiene los elementos del gerente
     * @param elementoXml
     * @return
     */
    public String obtainElements(Element elementoXml) throws FormatException{
        codigo = elementoXml.getElementsByTagName("CODIGO").item(0).getTextContent();
        cuenta = elementoXml.getElementsByTagName("CUENTA-ID").item(0).getTextContent();
        fechaCreacion = elementoXml.getElementsByTagName("FECHA").item(0).getTextContent();
        horaCreacion = elementoXml.getElementsByTagName("HORA").item(0).getTextContent();
        monto = elementoXml.getElementsByTagName("MONTO").item(0).getTextContent();
        tipo = elementoXml.getElementsByTagName("TIPO").item(0).getTextContent();
        idCajero = elementoXml.getElementsByTagName("CAJERO-ID").item(0).getTextContent();
        
        if(!tipo.equalsIgnoreCase("Credito") && !tipo.equalsIgnoreCase("Debito")){
            throw new FormatException ("El tipo de la transaccion "+codigo+" debe de ser Credito o Debito, Error: " + tipo);
        }
        try {
            if(Double.parseDouble(monto)<0){
                throw new FormatException ("El monto de la transaccion "+codigo+" no puede ser negativo, Error: "+monto);
            }
        } catch (Exception e) {
            throw new FormatException ("El monto de la transaccion "+codigo+" no es un numero");
        }
        try {
            fechaCreacion.replace("/", "-");
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(fechaCreacion);
        } catch (Exception e) {
            throw new FormatException ("No se pudo identificar la fecha de nacimiento de la transaccion"+codigo+ ", debe estar como en el formato siguiente \" 2020-05-17\" o \" 2020/05/17\"");
        }
        try {
            Date date1 = new SimpleDateFormat("HH:mm:ss").parse(horaCreacion);
        } catch (Exception e) {
            throw new FormatException ("La transaccion "+codigo+" su hora de creacion no tiene un formato correcto");
        }        
        
        return "Formatos Correctos Transaccion: "+ codigo;
    }
    
    public String returnCodigo(){
        return codigo;
    }
    
    public String returnCuenta(){
        return cuenta;
    }
    
    public String returnFechaCreacion(){
        return fechaCreacion;
    }
    
    public String returnHoraCreacion(){
        return horaCreacion;
    }
    
    public String returnMonto(){
        return monto;
    }
    
    public String returnIDCajero(){
        return idCajero;
    }
    
    public String returnTipo(){
        return tipo;
    }
  
}
