/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.SpecialOptions;

import SQL.Querys.Look.CorroboradorUsuario;
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
    private Time time = new Time();
    
    /**
     * Comprueba si no esta cerrada la sesion y si el codigo de la sesion es nulo entonces lo redirige
     * al menu principal
     * @param request
     * @param response 
     */
    public void redirigirSesionCerrada(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 100000000); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache");
        if(request.getSession().getAttribute("Codigo") == null){
            try {
                response.sendRedirect("../index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(CloseSession.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Comprueba el turno y verifica si puede estar aqui en este turno de lo contrario redirigira al login
     * @param request
     * @param response 
     */
    public Boolean redirigirFueraDelTurno(HttpServletRequest request, HttpServletResponse response){
        String turno = new CorroboradorUsuario().getTurno( (String) request.getSession().getAttribute("Codigo"));
        if( request.getSession().getAttribute("Codigo").toString().equalsIgnoreCase("101")){
            return false;
        }
        return time.comprobarTurno(turno);
    }
    
    
}
