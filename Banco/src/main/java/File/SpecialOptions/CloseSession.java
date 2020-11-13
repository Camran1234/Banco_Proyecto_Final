/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.SpecialOptions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author camran1234
 */
public class CloseSession {
    
    
    /**
     * Comprueba si no esta cerrada la sesion y si el codigo de la sesion es nulo entonces lo redirige
     * al menu principal
     * @param request
     * @param response 
     */
    public void redirigirSesionCerrada(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("Codigo") == null){
            try {
                response.sendRedirect("../index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(CloseSession.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }
}
