<%-- 
    Document   : InicioCliente
    Created on : Nov 11, 2020, 12:31:58 PM
    Author     : camran1234
--%>

<%@page import="File.SpecialOptions.CloseSession"%>
<%@page import="SQL.Get.InfoMonto"%>
<%@page import="File.SpecialOptions.Time"%>
<%@page import="SQL.Querys.Look.CorroboradorUsuario"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache");
    new CloseSession().redirigirSesionCerrada(request, response);
    %>
    <style>
    #nav1{  
        opacity: 0.8;     
    }
    body{
        background-image: url("../resources/Cliente.jpg");
    }
    .card {
        margin: 0 auto; /* Added */
        float: none; /* Added */
        margin-bottom: 10px; /* Added */
    }
    #ErrorText{
        display: block;;
        font-family: Verdana;
        font-size: 15px;
        font-weight: bolder;
        color: white;
    }
    </style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Banco</title>
    </head>    
    <body>
        <%InfoMonto infoMonto = new InfoMonto();
            %>            
        <%@ include file = "../Cliente/barraMenuCliente.html" %>
        <br><br><br><br><br><br><br><br><br>
        <div class="card text-white bg-dark mb-3" style="max-width: 30rem;">
                <div class="card-header">Bienvenido</div>
                <div class="card-body">
                    <h5 class="card-title"><%= new CorroboradorUsuario().GetName((String) session.getAttribute("Codigo"))%></h5>
                    <p class="card-text">Hoy estamos <%= new Time().getTodayDate()%></p>
                     <h5 class="card-tittle">Datos del Dia de Hoy</h5>
                     <p class="card-text">Límite para Transacciones Individuales: Q<%= infoMonto.obtenerMontoSolitario()%></p>
                     <p class="card-text">Límite para Transacciones Multiples: Q<%= infoMonto.obtenerMontoMultiple()%> en <%=infoMonto.obtenerCantidadCuentas()%> cuentas</p>
                </div>
        </div>
                <br><br><br><br><br><br>  
                <p class="card-text" id="ErrorText">Codigo: <%=session.getAttribute("Codigo")%> </p>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
