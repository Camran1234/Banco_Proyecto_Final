/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import File.ErrorHandlers.FormatException;
import File.UploadFiles.AsociacionCuenta;
import SQL.Querys.Look.CorroboradorCuenta;
import SQL.Querys.Look.CorroboradorCuentaAsociada;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author camran1234
 */
@WebServlet(name = "EnviarSolicitud", urlPatterns = {"/EnviarSolicitud"})
public class EnviarSolicitud extends HttpServlet {

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
        
        String cuentaSocio = request.getParameter("cuentaSocio");
        String nombre  = request.getParameter("nombre");
        String dpi  = request.getParameter("dpi");
        String cuenta  = request.getParameter("cuenta");
        String mensaje;
        CorroboradorCuenta corroborador = new CorroboradorCuenta();
        
        try {
            CorroboradorCuentaAsociada corroboradorCuenta = new CorroboradorCuentaAsociada();
            corroboradorCuenta.checkForRepeated(cuentaSocio, cuenta);
        } catch (Exception e) {
            request.getSession().setAttribute("Mensaje", e.getMessage());
            response.sendRedirect("./Cliente/ControlCuentas.jsp");     
            return;
        }
        
        try {
            mensaje = corroborador.checkCorrectData(cuentaSocio, nombre, dpi);
            AsociacionCuenta asociar = new AsociacionCuenta(cuentaSocio, cuenta);
            mensaje += " y " + asociar.subirArchivo();
            request.getSession().setAttribute("Resultado", mensaje);
            response.sendRedirect("./Cliente/ResultadoTransaccion.jsp");
            return;
        } catch (FormatException ex) {
            mensaje = ex.getMessage();
            request.getSession().setAttribute("Mensaje", mensaje);
            response.sendRedirect("./Cliente/ControlCuentas.jsp");
            return;
        }
        
        
        
        
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
