/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.ReportFiles;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class TransaccionModel implements Serializable{
    private String codigo;
    private String cajero;
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
        this.cajero = idCajero;
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
        if(idCajero.equalsIgnoreCase("101")){
            this.tipo = "Deposito Virtual";
        }
    }
    
    
    
    public String getCodigo(){
        return codigo;
    }
    
    public void setCodigo(String codigo){
        this.codigo=codigo;
    }
    
    public String getCajero(){
        return cajero;
    }
    
    public void setCajero(String cajero){
        this.cajero=cajero;
    }
    
    public String getCuenta(){
        return cuenta;
    }
    
    public void setCuenta(String cuenta){
        this.cuenta=cuenta;
    }
    
    public String getFechaCreacion(){
        return fechaCreacion;
    }
    
    public void setFechaCreacion(String fechaCreacion){
        this.fechaCreacion=fechaCreacion;
    }
    
    public String getHoraCreacion(){
        return horaCreacion;
    }
    
    public void setHoraCreacion(String horaCreacion){
        this.horaCreacion=horaCreacion;
    }
    
    public String getMonto(){
        return monto;
    }
    
    public void setMonto(String monto){
        this.monto=monto;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo=tipo;
    }
    
    public String getNombrePropietarioCuenta(){
        return this.nombrePropietarioCuenta;
    }
    
    public void setNombrePropietarioCuenta(String nombrePropietarioCuenta){
        this.nombrePropietarioCuenta = nombrePropietarioCuenta;
    }
    
    public double getTotalMonto(){
        if(tipo.equalsIgnoreCase("Deposito") || tipo.equalsIgnoreCase("Deposito Virtual")){
            return Double.parseDouble(monto);
        }else{
            return (Double.parseDouble(monto)*-1);
        }
    }
    
    public void setTotalMonto(String monto){
        this.monto = monto;
    }
}
