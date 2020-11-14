/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.ReportFiles;

/**
 *
 * @author camran1234
 */
public class CuentaModel {
    private double credito;
    private String fecha;
    private String codigo;
    private String idCliente;
    
    /**
     * Clase para almacenar los datos con un modelo
     * del cliente
     * @param codigo
     * @param fecha
     * @param credito
     * @param idCliente 
     */
    public CuentaModel(String codigo, String fecha, String credito, String idCliente){
        this.credito = Double.parseDouble(credito);
        this.codigo = codigo;
        this.fecha = fecha;
        this.idCliente = idCliente;
    }
    
    public String getCodigo(){
        return codigo;
    }
    
 
    
    public String getIDCliente(){
        return idCliente;
    }
    
    public String getFechaCreacion(){
        return  fecha;
    }
    
    public String getCredito(){
        return Double.toString(credito);
    }
}
