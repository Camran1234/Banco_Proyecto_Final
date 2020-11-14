/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.ReportFiles;

import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class TransaccionModel {
    private String codigo;
    private String idCajero;
    private String cuenta;
    private String fechaCreacion;
    private String horaCreacion;
    private String monto;
    private String tipo;
    private String nombrePropietarioCuenta;
    
    /**
     * Clase que almacena y retorna todos los datos que un 
     * @param codigo
     * @param idCajero
     * @param cuenta
     * @param monto
     * @param tipo
     * @param fechaCreacion
     * @param horaCreacion 
     */
    public TransaccionModel(String codigo, String idCajero, String cuenta, String monto, String tipo, String fechaCreacion, String horaCreacion){
        this.codigo = codigo;
        this.idCajero = idCajero;
        this.cuenta = cuenta;
        this.fechaCreacion = fechaCreacion;
        this.horaCreacion = horaCreacion;
        this.monto = monto;
        this.tipo = tipo;
        if(this.tipo.equalsIgnoreCase("Debito")){
            this.tipo="Retiro";
        }else if(this.tipo.equalsIgnoreCase("Credito")){
            this.tipo="Deposito";
        }
    }
    
    public void setPropietarioCuenta(String nombre){
        this.nombrePropietarioCuenta = nombre;
    }
    
    public String getPropietarioCuenta(){
        return this.nombrePropietarioCuenta;
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public String getIDCajero(){
        return idCajero;
    }
    
    public String getCuenta(){
        return cuenta;
    }
    
    public String getFechaCreacion(){
        return fechaCreacion;
    }
    
    public String getHoraCreacion(){
        return horaCreacion;
    }
    
    public String getMonto(){
        return monto;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public double getTotalMonto(){
        if(tipo.equalsIgnoreCase("Deposito")){
            return Double.parseDouble(monto);
        }else{
            return (Double.parseDouble(monto)*-1);
        }
    }
}
