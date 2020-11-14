/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.ReportFiles;

import SQL.Get.InfoCuenta;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class SolicitudModel {
     
    private String cuentaEmisora;    
    private String cuentaReceptora;
    private String estado;
    private String nombre;
    private String dpi;
    
    public SolicitudModel(String cuentaReceptora, String cuentaEmisora, String estado, String nombre, String dpi){
        this.cuentaReceptora = cuentaReceptora;
        this.cuentaEmisora = cuentaEmisora;
        this.estado = estado;
        if(estado.equalsIgnoreCase("Aceptar")){
            this.estado = "Aceptada";
        }else if(estado.equalsIgnoreCase("Rechazar")){
            this.estado = "Rechazada";
        }
        this.nombre = nombre;
        this.dpi = dpi;
    }
    
    public String getCuentaEmisora(){
        return cuentaEmisora;
    }
    
    public String getCuentaReceptora(){
        return cuentaReceptora;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getDpi(){
        return dpi;
    }
    
}
