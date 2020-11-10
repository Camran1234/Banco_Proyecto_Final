/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.UploadFiles;

import SQL.Conexion.Conexion;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author camran1234
 */
public class Usuario {
    Conexion conexion = new Conexion();
    String password;
    String codigo;
    String nombre;
    String sexo;
    String dpi;
    String mensaje;
    String direccion;
    
    
    public String subirArchivo(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
