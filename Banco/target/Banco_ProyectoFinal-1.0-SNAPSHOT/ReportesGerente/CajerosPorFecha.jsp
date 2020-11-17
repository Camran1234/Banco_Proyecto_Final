<%-- 
    Document   : CajerosPorFecha
    Created on : Nov 14, 2020, 11:44:02 AM
    Author     : camran1234
--%>

<%@page import="File.ReportFiles.CajeroModel"%>
<%@page import="File.ReportFiles.ClienteModel"%>
<%@page import="File.ReportFiles.HistorialModel"%>
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
    List<CajeroModel> listaCajeros = (List<CajeroModel>) session.getAttribute("clientes");
    String fechaInicial = (String)session.getAttribute("fechaInicial");
    String fechaFinal = (String)session.getAttribute("fechaFinal");
    session.removeAttribute("clientes");
    session.removeAttribute("fechaInicial");
    session.removeAttribute("fechaFinal");
    %>
    <style>
    #nav1{  
        opacity: 0.8;     
    }
    body{
        background-image: url("../resources/Gerente.jpg");
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
        
        
        <%@ include file = "../Gerente/barraNavegacionGerente.html" %>
        
            <div class="container py-3">
                <div class="row">
                    <div class="col-12 col-sm-8 col-md-6 mx-auto">
                        <div  class="card">
                            <div class="card-body">
                                <div class="card-title">
                                    <h2 class="text-center" id="text" >Cajeros Con Mayores Transacciones, Por Fecha:</h2>
                                    <% if(fechaInicial!=null){%>
                                    <h5 class="text-center" id="text" >Resultados en el Rango</h5>
                                    <h5 class="text-center" id="text" >Fecha Inicial: <%=fechaInicial%> FechaFinal: <%=fechaFinal%></h5>
                                    <%}%>
                                </div>
                                <hr>
                                <form method="post" action="../GerenteCajerosPorFecha">
                                    <div class="row">
                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="cc-exp" class="control-label">Fecha Inicio</label>
                                                <input class="form-control" type="date" name="fechaInicial" min="1900-01-01" max="2500-01-31"required>
                                            </div>
                                        </div>    
                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="cc-exp" class="control-label">Fecha Final</label>
                                                <input class="form-control" type="date" name="fechaFinal" min="1900-01-01" max="2500-01-31"required>
                                            </div>
                                        </div>
                                    </div>
                                    <hr><br>
                                    <input class="btn btn-success btn-lg btn-block" type="submit"   value ="Generar Reporte" >
                                </form>
                                <br>
                                <form method="post" action="../Exportar">
                                    <input type="hidden" name="Valor" value="7">
                                    <input type="hidden" name="url" value="CajerosPorFecha">
                                    <input type="hidden" name="fechaInicial" value="<%= fechaInicial%>">
                                    <input type="hidden" name="fechaFinal" value="<%= fechaFinal%>">
                                    <input type="hidden" name="urlCompleta" value="./ReportesGerente/CajerosPorFecha.jsp">
                                    <%if(listaCajeros!=null){%>
                                    <label>Guardar Como:</label>
                                    <%session.setAttribute("dataExportar", listaCajeros);%>
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
                <th scope="col">Nombre Del Cajero</th>
                <th scope="col">DPI</th>
                <th scope="col">Direccion</th>
                <th scope="col">Sexo</th>
                <th scope="col">Turno</th>
                <th scope="col">Codigo Usuario</th>
                <th scope="col">Cantidad Transacciones</th>
            </tr>
            <% if(listaCajeros!=null){
                for(int index=0;index<listaCajeros.size(); index++){
                %>
                <tr>
                    <td><%=index+1 %></td>
                    <td><%= listaCajeros.get(index).getNombre()%></td>                     
                    <td><%=listaCajeros.get(index).getDpi()%></td>
                    <td><%=listaCajeros.get(index).getDireccion()%></td>                   
                    <td><%=listaCajeros.get(index).getSexo()%></td>
                    <td><%=listaCajeros.get(index).getTurno()%></td>
                    <td><%=listaCajeros.get(index).getNoUsuario()%></td>
                    <td><%=listaCajeros.get(index).getCantidad()%></td>
                </tr>
            <%}}%>
        </table>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
