<%-- 
    Document   : HistorialSolicitudesEnviadas
    Created on : Nov 14, 2020, 3:56:52 AM
    Author     : camran1234
--%>

<%@page import="File.ReportFiles.SolicitudModel"%>
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

    new CloseSession().redirigirSesionCerrada(request, response);
    String nombre = (String) session.getAttribute("nombre");
    String dpi = (String) session.getAttribute("dpi");
    List<SolicitudModel> listaTransaccion = (List<SolicitudModel>) session.getAttribute("transaccion");
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
                                    <h2 class="text-center" id="text" >Solicitudes Enviadas</h2>
                                    <%if(nombre!=null && dpi!=null){%>
                                         <h5 class="text-center" id="text" >Nombre: <%=nombre%></h5>
                                         <h5 class="text-center" id="text" >DPI: <%=dpi%></h5>
                                    <%}%>
                                </div>
                                <hr>
                                <form method="post" action="../ClienteSolicitudesEnviadas">
                                    <input class="btn btn-success btn-lg btn-block" type="submit"   value ="Generar Reporte" >
                                </form>
                                <br>
                                <form method="post" action="../Exportar">
                                    <input type="hidden" name="Valor" value="14">
                                    <input type="hidden" name="url" value="HistorialSolicitudesEnviadas">
                                    <input type="hidden" name="urlCompleta" value="./ReportesCliente/HistorialSolicitudesEnviadas.jsp">
                                    <%if(listaTransaccion!=null){%>
                                    <input type="hidden" name="nombre" value="<%=nombre%>">
                                    <input type="hidden" name="dpi" value="<%=dpi%>">
                                    <label>Guardar Como:</label>
                                    <%session.setAttribute("dataExportar", listaTransaccion);%>
                                    <br>
                                    <input type="text" placeholder="Nombre para el archivo pdf..." name="nombreArchivo" required/>
                                    <br>
                                    <input class="btn btn-warning btn-lg btn-block" type="submit"   value ="Guardar y Exportar" >
                                    <%}%>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
        
        <br><br><br><br><br>
        <table class="table table-borderless table-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Cuenta Enviada</th>
                <th scope="col">Persona Enviada</th>                
                <th scope="col">Tu Cuenta</th>
                <th scope="col">DPI Solicitante</th>
                <th scope="col">Estado</th>
            </tr>
            <% if(listaTransaccion!=null){
                for(int index=0;index<listaTransaccion.size(); index++){
                %>
                <tr>
                    <td><%=index+1 %></td>
                    <td><%= listaTransaccion.get(index).getCuentaReceptora()%></td>                    
                    <td><%=listaTransaccion.get(index).getNombre()%></td>
                    <td><%=listaTransaccion.get(index).getCuentaEmisora()%></td>
                    <td><%=listaTransaccion.get(index).getDpi()%></td>
                    <td><%=listaTransaccion.get(index).getEstado()%></td>
                </tr>
            <%}}%>
        </table>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>