<%-- 
    Document   : RedireccionadorUsuario
    Created on : Nov 10, 2020, 9:44:21 AM
    Author     : camran1234
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="SQL.Querys.Look.CorroboradorUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String codigo = request.getParameter("Codigo");
    String password = request.getParameter("Password");
    CorroboradorUsuario corroborador = new CorroboradorUsuario();
    String tipo ="";
    tipo = corroborador.checkLogIn(codigo, password);
    if( tipo == null || tipo.equalsIgnoreCase("null")){
        session.setAttribute("Mensaje", "Codigo o Contraseña Incorrectos");
        response.sendRedirect("../index.jsp");
        return;
    }
    session.setAttribute("Codigo", codigo);
    session.setAttribute("Password", password);
    %>
<!DOCTYPE html>

        <%
            switch(tipo){
                case "GERENTE":
                    response.sendRedirect("../Gerente/InicioGerente.jsp");
                    break;
                case "CLIENTE":
                    response.sendRedirect("../Cliente/InicioCliente.jsp");
                    break;    
                case "CAJERO":
                    response.sendRedirect("../Cajero/InicioCajero.jsp");
                    break;
            }
            %>
