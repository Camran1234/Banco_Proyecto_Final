<%-- 
    Document   : UltimasTransacciones
    Created on : Nov 13, 2020, 12:03:14 AM
    Author     : camran1234
--%>

<%@page import="File.ReportFiles.TransaccionModel"%>
<%@page import="java.util.List"%>
<%@page import="File.UploadFiles.Transaccion"%>
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
    String nombre = (String) session.getAttribute("nombre");
    String dpi = (String) session.getAttribute("dpi");
    List<TransaccionModel> listaTransaccion = (List<TransaccionModel>) session.getAttribute("transaccion");
    session.removeAttribute("nombre");
    session.removeAttribute("dpi");
    session.removeAttribute("transaccion");
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
    #Text{
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
        
        
        <%@ include file = "../Cliente/barraMenuCliente.html" %>
        
            <div class="container py-3">
                <div class="row">
                    <div class="col-12 col-sm-8 col-md-6 mx-auto">
                        <div  class="card">
                            <div class="card-body">
                                <div class="card-title">
                                    <h2 class="text-center" id="text" >Ultimas 15 Transacciones</h2>
                                    <%if(nombre!=null && dpi!=null){%>
                                         <h5 class="text-center" id="text" >Nombre: <%=nombre%></h5>
                                         <h5 class="text-center" id="text" >DPI: <%=dpi%></h5>
                                    <%}%>
                                </div>
                                <hr>
                                <form method="post" action="../ClienteUltimasTransacciones">
                                <input class="btn btn-success btn-lg btn-block" type="submit"   value ="Generar Reporte" >
                                </form>
                                <br>
                                <form method="post" action="../Exportar">
                                    <input class="btn btn-warning btn-lg btn-block" type="submit"   value ="Exportar" >
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
        
        <br><br><br><br><br>
        <table class="table table-borderless table-dark">
            <tr>
                <th scope="col">Numero</th>
                <th scope="col">Codigo</th>
                <th scope="col">Codigo del Cajero</th>
                <th scope="col">Cuenta</th>
                <th scope="col">Cantidad</th>
                <th scope="col">Tipo</th>
                <th scope="col">Fecha Creada</th>
                <th scope="col">Hora Creada</th>
            </tr>
            <% if(listaTransaccion!=null){
                for(int index=0;index<listaTransaccion.size(); index++){
                %>
                <tr>
                    <td><%=index+1 %></td>
                    <td><%= listaTransaccion.get(index).getCodigo() %></td>
                    <td><%=listaTransaccion.get(index).getIDCajero()%></td>
                    <td><%=listaTransaccion.get(index).getCuenta()%></td>
                    <td><%=listaTransaccion.get(index).getMonto()%></td>
                    <td><%=listaTransaccion.get(index).getTipo()%></td>
                    <td><%=listaTransaccion.get(index).getFechaCreacion()%></td>
                    <td><%=listaTransaccion.get(index).getHoraCreacion()%></td>
                </tr>
            <%}}%>
        </table>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
