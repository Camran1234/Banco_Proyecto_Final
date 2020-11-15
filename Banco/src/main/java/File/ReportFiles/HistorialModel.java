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
public class HistorialModel implements Serializable{    
    private String noActualizacion;
    private String fechaActualizacion;
    private String horaActualizacion;
    private String idUsuario;
    private String tipoUsuario;
    private String idGerente;
    private String nombreGerente;
    private String descripcion;
    
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
    public HistorialModel(String noActualizacion, String fechaActualizacion, String horaActualizacion, String idUsuario, String tipoUsuario,
            String idGerente, String nombreGerente, String descripcion){
        this.noActualizacion = noActualizacion;
        this.fechaActualizacion = fechaActualizacion;
        this.horaActualizacion = horaActualizacion;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.idGerente = idGerente;
        this.nombreGerente = nombreGerente;
        this.descripcion = descripcion;
    }
    
    public String getNoActualizacion(){
        return noActualizacion;
    }
    
    public String getFechaActualizacion(){
        return fechaActualizacion;
    }
    
    public String getHoraActualizacion(){
        return horaActualizacion;
    }
    
    public String getIdUsuario(){
        return idUsuario;
    }
    
    public String getTipoUsuario(){
        return tipoUsuario;
    }
    
    public String getIdGerente(){
        return idGerente;
    }
    
    public String getNombreGerente(){
        return nombreGerente;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
}
