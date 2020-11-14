/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import File.ErrorHandlers.FormatException;
import File.UploadFiles.HistorialActualizacion;
import File.UploadFiles.MontoSolitario;
import File.UploadFiles.MontoTransaccion;
import SQL.Get.InfoGerente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
@WebServlet("/ActualizarMontoMultiple")
public class ActualizarMontoMultiple extends HttpServlet {

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
        String monto = request.getParameter("monto");
        String cuenta = request.getParameter("cuentas");
        String mensaje = null;
        try {
                  mensaje = new MontoTransaccion(monto,cuenta).subirArchivo();
                  String dpiGerente = new InfoGerente().obtenerDPIGerente((String)request.getSession().getAttribute("Codigo"));
                  String cambios = "Se cambio el monto por: "+monto+ " por el ciudadano con el dpi: "+dpiGerente;
                  mensaje += " " +new HistorialActualizacion().subirHistorial(cambios, dpiGerente, null);
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
