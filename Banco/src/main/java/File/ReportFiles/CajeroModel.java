/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.ReportFiles;

/**
 * Contiene un modelo para reportes de cajero
 * @author camran1234
 */
public class CajeroModel {
    private String turno;
    private String codigo;
    private String nombre;
    private String sexo;
    private String dpi;
    private String mensaje;
    private String direccion;
    private String cantidad;
    
    public CajeroModel(String nombre, String dpi, String direccion, String sexo, String turno, String codigoUsuario, String cantidad){
        this.nombre=nombre;
        this.dpi = dpi;
        this.direccion=direccion;
        this.sexo=sexo;
        this.turno = turno;
        this.codigo = codigoUsuario;
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
        return codigo;
    }
    public String getCantidad(){
        return cantidad;
    }
}
