/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import File.ErrorHandlers.FormatException;
import SQL.Get.InfoCuenta;
import SQL.Querys.Look.CorroboradorCuenta;
import SQL.Querys.Look.CorroboradorCuentaAsociada;
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
@WebServlet(name = "ManejarDatosTransaccionVirtual", urlPatterns = {"/ManejarDatosTransaccionVirtual"})
public class ManejarDatosTransaccionVirtual extends HttpServlet {

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
        CorroboradorCuenta info = new CorroboradorCuenta();
        String numeroCuenta = request.getParameter("codigoCuenta");
        String numeroCuentaCliente = request.getParameter("codigoCuentaRetirar");
        String cliente = (String)request.getSession().getAttribute("Codigo");
        String monto = request.getParameter("monto");
        
        //Corroboramos que sea un numero
        try {
                if(Double.parseDouble(monto)<0){
                    request.getSession().setAttribute("Mensaje", "El monto establecido no puede ser negativo");
                    response.sendRedirect("./Cliente/BancaVirtual.jsp");
                    return;
                }
            } catch (Exception e) {
                request.getSession().setAttribute("Mensaje", "El monto establecido no es un numero");
                response.sendRedirect("./Cliente/BancaVirtual.jsp");
                return;
            }
        //Corroboramos que los codigos indicados existan
        if(info.checkIfCodeExists(numeroCuenta)==false){
            request.getSession().setAttribute("Mensaje", "No se encontro el numero de cuenta");
            response.sendRedirect("./Cliente/BancaVirtual.jsp");
            return;
        }
        //Obtenemos el nombre del propietario de la cueta
        String nombre = new InfoCuenta().getNameOfCode(numeroCuenta);
        String resultado = new CorroboradorCuenta().checkIfEnoughFounds(numeroCuentaCliente, monto,new CorroboradorUsuario().getDpi(cliente));
        String tipo = "Credito";
        if(!resultado.equalsIgnoreCase("SI")){
            request.getSession().setAttribute("Mensaje", resultado);
            response.sendRedirect("./Cliente/BancaVirtual.jsp");
            return;
        }
        try {
            resultado =  new CorroboradorCuentaAsociada().accountAssociated(numeroCuenta, numeroCuentaCliente);
            } catch (FormatException e) {
                request.getSession().setAttribute("Mensaje", e.getMessage());
                response.sendRedirect("./Cliente/BancaVirtual.jsp");
                return;
            }

        if(!resultado.equalsIgnoreCase("SI")){
            request.getSession().setAttribute("Mensaje", resultado);
            response.sendRedirect("./Cliente/BancaVirtual.jsp");
            return;
        }
        request.getSession().setAttribute("Tipo", tipo);
        request.getSession().setAttribute("NumeroCuenta", numeroCuenta);
        request.getSession().setAttribute("NumeroCuentaRetirar", numeroCuentaCliente);
        request.getSession().setAttribute("CodigoCliente", cliente);
        request.getSession().setAttribute("Monto", monto);
        request.getSession().setAttribute("Nombre", nombre);
        
        response.sendRedirect("./Cliente/AceptarDeposito.jsp");
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
