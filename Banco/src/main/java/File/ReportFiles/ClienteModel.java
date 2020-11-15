/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.ReportFiles;

import java.io.Serializable;

/**
 *
 * @author camran1234
 */
public class ClienteModel implements Serializable{
    private int cantidadCuentas;
    private double total;
    private String nombre;
    private String noUsuario;
    private String dpi;
    private String fechaNacimiento;
    private String direccion;
    private String sexo;
    
    /**
     * Clase para almacenar los datos con un modelo
     * del cliente
     * @param codigo
     * @param fecha
     * @param credito
     * @param idCliente 
     */
    public ClienteModel(String nombre, String noUsuario, String dpi, String fechaNacimiento, String direccion, String sexo, int cantidad, double total){
        this.nombre = nombre;
        this.noUsuario = noUsuario;
        this.dpi = dpi;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.sexo = sexo;
        this.cantidadCuentas = cantidad;
        this.total = total; 
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getNoUsuario(){
        return noUsuario;
    }
    
    public String getDpi(){
        return dpi;
    }
    
    public String getFechaNacimiento(){
        return  fechaNacimiento;
    }
    
    public String getDireccion(){
        return direccion;
    }
    
    public String getSexo(){
        return sexo;
    }
    
    public int getCantidadCuentas(){
        return cantidadCuentas;
    }
    
    public double getTotal(){
        return total;
    }
}
