/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import File.UploadFiles.Transaccion;
import SQL.Querys.Look.CorroboradorUsuario;
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
@WebServlet(name = "CrearDeposito", urlPatterns = {"/CrearDeposito"})
public class CrearTransaccionCajero extends HttpServlet {

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
        String mensaje ="";
        //Obtenemos los valores enviados desde el servlet
        String nombreReceptor = (String) request.getSession().getAttribute("Nombre");
        String cuentaReceptora = (String) request.getSession().getAttribute("cuentaReceptora");
        String tipoTransaccion = (String) request.getSession().getAttribute("tipoTransaccion");
        String cantidad = (String) request.getSession().getAttribute("deposito");
        CorroboradorUsuario corroborador = new CorroboradorUsuario();
        String idCajero = (String) request.getSession().getAttribute("Codigo");
        //Removemos los atributos de la sesion
        request.getSession().removeAttribute("Nombre");
        request.getSession().removeAttribute("cuentaReceptora");
        request.getSession().removeAttribute("tipoTransaccion");
        request.getSession().removeAttribute("deposito");

        if(tipoTransaccion.equalsIgnoreCase("Credito")){
            Transaccion transaccionA = new Transaccion(null, idCajero, cuentaReceptora, cantidad, "Credito");
            mensaje += transaccionA.subirArchivo();
        }else{
            Transaccion transaccionC = new Transaccion(null, idCajero, cuentaReceptora, cantidad, "Debito");
            mensaje += transaccionC.subirArchivo();
        }
        request.getSession().setAttribute("Resultado", mensaje);
        response.sendRedirect("./Cajero/Resultados.jsp");
    
    
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
