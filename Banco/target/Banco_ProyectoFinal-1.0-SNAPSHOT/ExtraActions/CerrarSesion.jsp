<%-- 
    Document   : CerrarSesion
    Created on : Nov 10, 2020, 2:14:56 PM
    Author     : camran1234
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    session.removeAttribute("Codigo");
    session.removeAttribute("Password");
    session.invalidate();
    response.sendRedirect("../index.jsp");
%>
