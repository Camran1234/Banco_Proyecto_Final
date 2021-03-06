/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Reportes;

import SQL.Querys.Look.CorroboradorUsuario;
import SQL.Reportes.ReportesCliente;
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
@WebServlet("/TransaccionesIntervaloTiempo")
public class ClienteTransaccionesIntervaloTiempo extends HttpServlet {
    ReportesCliente reportes = new ReportesCliente();
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
        try {
            String codigo = (String)request.getSession().getAttribute("Codigo");
            String fechaInicio = request.getParameter("fechaInicio");
            String fechaFinal = request.getParameter("fechaFinal");
            request.getSession().setAttribute("transaccion", reportes.imprimirTransaccionesIntervaloTiempo(codigo,fechaInicio, fechaFinal));
            request.getSession().setAttribute("nombre", new CorroboradorUsuario().getName(codigo));
            request.getSession().setAttribute("dpi", new CorroboradorUsuario().getDpi(codigo));
            request.getSession().setAttribute("fechaInicial", fechaInicio);
            request.getSession().setAttribute("fechaFinal", fechaFinal);
            response.sendRedirect("./ReportesCliente/TransaccionesIntervaloTiempo.jsp");
        } catch (IOException | NumberFormatException  e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
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
