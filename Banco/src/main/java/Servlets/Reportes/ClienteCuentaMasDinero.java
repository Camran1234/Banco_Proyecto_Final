/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Reportes;

import File.ReportFiles.CuentaModel;
import SQL.Querys.Look.CorroboradorUsuario;
import SQL.Reportes.ReportesCliente;
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
@WebServlet("/ClienteCuentaMasDinero")
public class ClienteCuentaMasDinero extends HttpServlet {
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
        try {
            String codigo = (String)request.getSession().getAttribute("Codigo");
            String fechaInicio = request.getParameter("fechaInicio");
            String fechaFinal = request.getParameter("fechaFinal");
            CuentaModel cuentaModel = reportes.imprimirCuentaMayorDinero(codigo);
            request.getSession().setAttribute("transaccion", reportes.imprimirTransaccionesCuenta(cuentaModel.getCodigo(),fechaInicio, fechaFinal));
            request.getSession().setAttribute("cuentas", cuentaModel);
            request.getSession().setAttribute("fechaInicial", fechaInicio);
            request.getSession().setAttribute("fechaFinal", fechaFinal);
            request.getSession().setAttribute("nombre", new CorroboradorUsuario().getName(codigo));
            request.getSession().setAttribute("dpi", new CorroboradorUsuario().getDpi(codigo));
            response.sendRedirect("./ReportesCliente/CuentaMasDinero.jsp");
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
