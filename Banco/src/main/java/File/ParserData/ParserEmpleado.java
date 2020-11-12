/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.ParserData;

import File.ErrorHandlers.FormatException;
import org.w3c.dom.Element;

/**
 *
 * @author camran1234
 */
public class ParserEmpleado {
    private String codigo;
    private String nombre;
    private String password;
    private String sexo;
    private String dpi;
    private String turno;
    private String direccion;
    
    /**
     * Obtiene los elementos del gerente
     * @param elementoXml
     * @return
     */
    public String obtainElements(Element elementoXml) throws FormatException{
        codigo =elementoXml.getElementsByTagName("CODIGO").item(0).getTextContent();
        nombre = elementoXml.getElementsByTagName("NOMBRE").item(0).getTextContent();
        turno = elementoXml.getElementsByTagName("TURNO").item(0).getTextContent();
        if(!turno.equalsIgnoreCase("Matutino") && !turno.equalsIgnoreCase("Vespertino")){
            throw new FormatException (" El turno no es Matutino o Vespertino");
        }
        try{
            if(elementoXml.getElementsByTagName("DPI").item(0).getTextContent().length()==13){
                Long.parseLong(elementoXml.getElementsByTagName("DPI").item(0).getTextContent());
                dpi = elementoXml.getElementsByTagName("DPI").item(0).getTextContent();
            }else{
                throw new FormatException (" El dpi no contiene 13 digitos");
            }
        }catch(Exception ex){
            throw new FormatException ("El dpi no es un numero" + ex.getMessage());
        }
        direccion = elementoXml.getElementsByTagName("DIRECCION").item(0).getTextContent();
        sexo = elementoXml.getElementsByTagName("SEXO").item(0).getTextContent();
        if(!sexo.equalsIgnoreCase("Femenino") && !sexo.equalsIgnoreCase("Masculino")){
            throw new FormatException (" El genero debe de ser Masculino o Femenino");
        }
        password = elementoXml.getElementsByTagName("PASSWORD").item(0).getTextContent();
        return " Formatos Correctos Empleado:"+codigo;
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
    public String returnTurno(){
        return turno;
    }
    public String returnDireccion(){
        return direccion;
    }
}
