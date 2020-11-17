/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.SpecialOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class Time {
    private int years=0;
    private Date dateComparar;
    private final String inicioTurnoMatutino = "6:00:00";
    private final String finTurnoMatutino = "14:30:00";
    private final String inicioTurnoVespertino = "13:00:00";
    private final String finTurnoVespertino= "22:00:00";  
    
    public Time(){
    }
    
    /**
     * Constructor para especificar la cantidad de años menos que se le restara
     * @param years 
     */
    public Time(int years){
        this.years = years;
    }
    
    public String getTodayDate(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();  
        return date.format(now.minusYears(years));
    }
    
    public String getActualTime(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(date.format(now));
        return date.format(now);
    }
    
    /**
     * Funcion para comprobar el turno 
     * @param turno
     * @return 
     */
    public Boolean comprobarTurno(String turno){
        try {
            dateComparar = new SimpleDateFormat("HH:mm:ss").parse(this.getActualTime());
            if(turno.equalsIgnoreCase("Matutino")){
                if(dateComparar.after(new SimpleDateFormat("HH:mm:ss").parse(inicioTurnoMatutino)) && dateComparar.before(new SimpleDateFormat("HH:mm:ss").parse(finTurnoMatutino))){
                    return true;
                }
            }else if(turno.equalsIgnoreCase("Vespertino")){
                if(dateComparar.after(new SimpleDateFormat("HH:mm:ss").parse(inicioTurnoVespertino)) && dateComparar.before(new SimpleDateFormat("HH:mm:ss").parse(finTurnoVespertino))){
                    return true;
                }
            }else{
                return true;
            }
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
