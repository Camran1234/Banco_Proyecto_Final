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
import javax.swing.JOptionPane;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author camran1234
 */
public class ParserCliente {
    private String codigo;
    private String nombre;
    private String password;
    private String sexo;
    private String dpi;
    private String direccion;
    private String direccionPDF;
    private String fechaNacimiento;
    private ArrayList<String> codigoCuentas = new ArrayList<>();
    private ArrayList<String> fechaCreacion = new ArrayList<>();
    private ArrayList<String> creditos = new ArrayList<>();
    
    /**
     * Obtiene los elementos del gerente
     * @param elementoXml
     * @return
     */
    public String obtainElements(Element elementoXml) throws FormatException{
        codigo = elementoXml.getElementsByTagName("CODIGO").item(0).getTextContent();
        nombre = elementoXml.getElementsByTagName("NOMBRE").item(0).getTextContent();
        try{
            if(elementoXml.getElementsByTagName("DPI").item(0).getTextContent().length()==15){
                Long.parseLong(elementoXml.getElementsByTagName("DPI").item(0).getTextContent());
                dpi = elementoXml.getElementsByTagName("DPI").item(0).getTextContent();
            }else{
                throw new FormatException (" El dpi no contiene 15 digitos ");
            }
        }catch(Exception ex){
            throw new FormatException (" El dpi no es un numero ");
        }
        direccion = elementoXml.getElementsByTagName("DIRECCION").item(0).getTextContent();
        sexo = elementoXml.getElementsByTagName("SEXO").item(0).getTextContent();
        if(!sexo.equalsIgnoreCase("Femenino") && !sexo.equalsIgnoreCase("Masculino")){
            throw new FormatException (" El genero debe de ser Masculino o Femenino ");
        }
        password = elementoXml.getElementsByTagName("PASSWORD").item(0).getTextContent();
        direccionPDF = elementoXml.getElementsByTagName("DPI-PDF").item(0).getTextContent();
        fechaNacimiento = elementoXml.getElementsByTagName("BIRTH").item(0).getTextContent();
        try {
            fechaNacimiento.replace("/", "-");
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimiento);
        } catch (Exception e) {
            throw new FormatException (" No se pudo identificar la fecha de nacimiento, debe estar como en el formato siguiente \" 2020-05-17\" o \" 2020/05/17\"");
        }
        
        NodeList childNodes = (NodeList) elementoXml.getElementsByTagName("CUENTAS");
        Element elementosNode = (Element) childNodes.item(0);
        NodeList childAux = (NodeList) elementosNode.getElementsByTagName("CUENTA");
        Element elementoAux;
        
        String codigoCuenta;
        String fechaCreada;
        double credito;
        for(int indexNodo=0;indexNodo<elementosNode.getElementsByTagName("CUENTA").getLength();indexNodo++){
                    elementoAux = (Element) childAux.item(indexNodo);
                    try {
                        codigoCuenta = elementoAux.getElementsByTagName("CODIGO").item(0).getTextContent();
                        fechaCreada = elementoAux.getElementsByTagName("CREADA").item(0).getTextContent();
                        credito = Double.parseDouble(elementoAux.getElementsByTagName("CREDITO").item(0).getTextContent());
                        if(credito<0){
                            throw new FormatException ("El credito no puede ser negativo ");
                        }
                        try {           
                            fechaNacimiento.replace("/", "-");
                            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimiento);
                        } catch (Exception e) {
                            throw new FormatException (" No se pudo identificar la fecha de nacimiento de la cuenta, debe estar como en el formato siguiente \" 2020-05-17\" o \" 2020/05/17\" ");
                        }
                        codigoCuentas.add(codigoCuenta);
                        fechaCreacion.add(fechaCreada);
                        creditos.add(Double.toString(credito));
                    } catch (Exception e) {
                        throw new FormatException (" El credito no era un numero ");
                    }
          
        }
        
        return " Formatos Correctos cliente:"+dpi;
    }
    
    public String returnCodigo(){
        return codigo;
    }
    public String returnNombre(){
        return nombre;
    }
    public String returnPassword(){
        return password;
    }
    public String returnSexo(){
        return sexo;
    }
    public String returnDpi(){
        return dpi;
    }
    public String returnDireccion(){
        return direccion;
    }
    
    public String returnDireccionPDF(){
        return direccionPDF;
    }
    
    public String returnFechaNacimiento(){
        return this.fechaNacimiento;
    }
    
    
    
    public ArrayList<String> returnCodigoCuentas() {
        return codigoCuentas;
    }
    
    public ArrayList<String> returnfechaCuentas() {
        return this.fechaCreacion;
    }
    
    public ArrayList<String> returnMontosCuentas() {
        return this.creditos;
    }
}
