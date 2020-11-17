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
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author camran1234
 */
@WebServlet("/ClienteUltimasTransacciones")
public class ClienteUltimasTransacciones extends HttpServlet {
    ReportesCliente reportes = new ReportesCliente();

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
        try {
            String codigo = (String)request.getSession().getAttribute("Codigo");
            request.getSession().setAttribute("transaccion", reportes.imprimirTransaccionesConLimite( codigo,15));
            request.getSession().setAttribute("nombre", new CorroboradorUsuario().getName(codigo));
            request.getSession().setAttribute("dpi", new CorroboradorUsuario().getDpi(codigo));
            response.sendRedirect("./ReportesCliente/UltimasTransacciones.jsp");
        } catch (IOException | NumberFormatException  e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
