/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import File.UploadFiles.Transaccion;
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
@WebServlet("/CrearTransaccion")
public class CrearTransaccion extends HttpServlet {

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
        //Obtenemos los datos de la cuenta en la sesion
        String numeroCuenta = (String)request.getSession().getAttribute("NumeroCuenta");
        String numeroCuentaRetirar =(String) request.getSession().getAttribute("NumeroCuentaRetirar");
        String tipo = (String)request.getSession().getAttribute("Tipo");
        String cliente = (String)request.getSession().getAttribute("CodigoCliente");
        String monto =(String) request.getSession().getAttribute("Monto");
        request.getSession().removeAttribute("NumeroCuenta");
        request.getSession().removeAttribute("NumeroCuentaRetirar");
        request.getSession().removeAttribute("Tipo");
        request.getSession().removeAttribute("CodigoCliente");
        request.getSession().removeAttribute("Monto");
        //Creamos 2 transacciones una de acreditar a la cuenta solicitada y otra le quitamos a la cuenta indicada del cleinte
        Transaccion transaccion = new Transaccion(null, "101", numeroCuentaRetirar, monto, "Credito");
        Transaccion transaccion1 = new Transaccion(null, "101", numeroCuenta, monto, "Debito");
        String mensaje = transaccion.subirArchivo();
        transaccion1.subirArchivo();
        request.getSession().setAttribute("Resultado", mensaje);
        response.sendRedirect("./Cliente/ResultadoTransaccion.jsp");
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
