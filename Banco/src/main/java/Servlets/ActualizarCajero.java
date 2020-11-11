/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import File.ErrorHandlers.FormatException;
import File.UploadFiles.HistorialActualizacion;
import SQL.Actualizadores.ActualizadorCajero;
import SQL.Actualizadores.ActualizadorCliente;
import SQL.Get.InfoGerente;
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
@WebServlet("/ActualizarCajero")
public class ActualizarCajero extends HttpServlet {

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
        String nombre = request.getParameter("Nombre");
        String  dpi = request.getParameter("dpi");
        String direccion = request.getParameter("Direccion");
        String sexo = request.getParameter("Sexo");
        String turno = request.getParameter("Turno");
        String codigoUsuario = request.getParameter("codigoUsuario");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        
        String mensaje = null;
        try {
                  mensaje = new ActualizadorCajero(nombre, dpi, direccion, sexo, turno, codigoUsuario, password, password2).subirArchivo();
                  String cambios = "Se cambio el nombre por: "+nombre+" Se cambio el dpi por: "+dpi+" Se cambio la direccion por: "+direccion+" Se cambio el sexo por: "+sexo+
                          "Se cambio el turno por: "+turno+", quien realizo estos cambios fue el ciudadano cuyo dpi es: " + request.getSession().getAttribute("Codigo");
                  String dpiGerente = new InfoGerente().obtenerDPIGerente((String)request.getSession().getAttribute("Codigo"));
                  mensaje += " " +new HistorialActualizacion().subirHistorial(cambios, dpiGerente);
                  //Crear historial
            
        } catch (FormatException ex) {
            mensaje = ex.getMessage();    
        }
        request.getSession().setAttribute("Resultado", mensaje);
        response.sendRedirect("./Gerente/Resultado.jsp");
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
