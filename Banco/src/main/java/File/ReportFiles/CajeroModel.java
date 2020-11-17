/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.ReportFiles;

import java.io.Serializable;

/**
 * Contiene un modelo para reportes de cajero
 * @author camran1234
 */
public class CajeroModel implements Serializable{
    private String turno;
    private String noUsuario;
    private String nombre;
    private String sexo;
    private String dpi;
    private String direccion;
    private String cantidad;
    
    public CajeroModel(String nombre, String dpi, String direccion, String sexo, String turno, String codigoUsuario, String cantidad){
        this.nombre=nombre;
        this.dpi = dpi;
        this.direccion=direccion;
        this.sexo=sexo;
        this.turno = turno;
        this.noUsuario = codigoUsuario;
        this.cantidad = cantidad;
    }
    
    public String getNombre(){
        return nombre;
    }
    public String getDpi(){
        return dpi;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getSexo(){
        return sexo;
    }
    public String getTurno(){
        return turno;
    }
    public String getNoUsuario(){
        return noUsuario;
    }
    public String getCantidad(){
        return cantidad;
    }
}
