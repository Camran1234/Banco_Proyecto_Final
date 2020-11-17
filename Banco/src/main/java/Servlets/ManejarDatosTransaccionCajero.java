/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import SQL.Get.InfoCuenta;
import SQL.Querys.Look.CorroboradorCuenta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author camran1234
 */
@WebServlet(name = "ManejarDatosTransaccionCajero", urlPatterns = {"/ManejarDatosTransaccionCajero"})
public class ManejarDatosTransaccionCajero extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String mensaje;
    String cuentaReceptora = (String)request.getParameter("cuentaReceptora");
    String nombreReceptor = new InfoCuenta().getNameOfCode(cuentaReceptora);
    String tipoTransaccion = (String)request.getParameter("tipoTransaccion");
    String cantidad = (String)request.getParameter("deposito");
    String dpi = (String) request.getParameter("dpi");
    //Todo esto sera manejo de excepciones
    CorroboradorCuenta corroborador = new CorroboradorCuenta();
    //Corroboramos que los codigos indicados existan
    if(corroborador.checkIfCodeExists(cuentaReceptora)==false){
        request.getSession().setAttribute("Mensaje", "No se encontro el numero de cuenta");
        if(tipoTransaccion.equalsIgnoreCase("Credito")){
                response.sendRedirect("./Cajero/Depositos.jsp");            
                return;            
            }else{        
                response.sendRedirect("./Cajero/Retiros.jsp");
                return;            
            }
    }
    
    
    //Manejo de comprobacion del dpi en la cuenta
    InfoCuenta info = new InfoCuenta();
    if(dpi!=null){
        if(!dpi.equalsIgnoreCase(info.getDpiOfAccountCode(cuentaReceptora))){
            mensaje = "La cuenta no le pertenece al ciudadano indicado";                
            request.getSession().setAttribute("Mensaje", mensaje);        
            if(tipoTransaccion.equalsIgnoreCase("Credito")){
                response.sendRedirect("./Cajero/Depositos.jsp");            
                return;            
            }else{        
                response.sendRedirect("./Cajero/Retiros.jsp");
                return;            
            }
        }
    }
    
    //Manejo de errores de numeros
    try {
            if(Double.parseDouble(cantidad) < 0){
                mensaje = "La cantidad a depositar no puede ser negativa";
                request.getSession().setAttribute("Mensaje", mensaje);
                if(tipoTransaccion.equalsIgnoreCase("Credito")){
                    response.sendRedirect("./Cajero/Depositos.jsp");
                    return;
                }else{
                    response.sendRedirect("./Cajero/Retiros.jsp");
                    return;
                }
            }
        } catch (Exception e) {
            mensaje = "La cantidad a depositar no es un numero";                
            request.getSession().setAttribute("Mensaje", mensaje);            
            if(tipoTransaccion.equalsIgnoreCase("Credito")){            
                response.sendRedirect("./Cajero/Depositos.jsp");                
                return;                
            }else{            
                response.sendRedirect("./Cajero/Retiros.jsp");                
                return;    
            }
        }
        //Manejo de errores de Fondos
        if(tipoTransaccion.equalsIgnoreCase("Debito")){
            String resultado = new CorroboradorCuenta().checkIfEnoughFounds(cuentaReceptora, cantidad, new InfoCuenta().getDpiOfAccountCode(cuentaReceptora));
            if(!resultado.equalsIgnoreCase("SI")){
                request.getSession().setAttribute("Mensaje", resultado);
                if(tipoTransaccion.equalsIgnoreCase("Credito")){
                    response.sendRedirect("./Cajero/Depositos.jsp");            
                    return;            
                }else{        
                    response.sendRedirect("./Cajero/Retiros.jsp");
                    return;            
                }
            }
        }

        //Removemos los atributos
        request.removeAttribute("Nombre");
        request.removeAttribute("cuentaEmisora");
        request.removeAttribute("cuentaReceptora");
        request.removeAttribute("tipoTransaccion");
        request.removeAttribute("deposito");

        //Lo agregamos a la sesion
        request.getSession().setAttribute("Nombre", nombreReceptor);
        request.getSession().setAttribute("cuentaReceptora", cuentaReceptora);
        request.getSession().setAttribute("tipoTransaccion", tipoTransaccion);
        request.getSession().setAttribute("deposito", cantidad);
        response.sendRedirect("./Cajero/AceptarTransaccion.jsp");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
