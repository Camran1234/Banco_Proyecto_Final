/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.SpecialOptions;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

/**
 *
 * @author camran1234
 */
public class Time {
    public String getTodayDate(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-dd-MM");  
        LocalDateTime now = LocalDateTime.now();  
        return date.format(now);
    }
    
    public String getActualTime(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        return date.format(now);
    }
}
