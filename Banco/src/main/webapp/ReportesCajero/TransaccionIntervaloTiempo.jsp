<%-- 
    Document   : TransaccionIntervaloTiempo
    Created on : Nov 14, 2020, 5:02:10 AM
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

    new CloseSession().redirigirSesionCerrada(request, response);
    List<TransaccionModel> listaTransaccion = (List<TransaccionModel>) session.getAttribute("transaccion");
    String fechaInicial = (String)session.getAttribute("fechaInicial");
    String fechaFinal = (String)session.getAttribute("fechaFinal");
    String horaInicial = (String)session.getAttribute("horaInicial");
    String horaFinal = (String)session.getAttribute("horaFinal");
    session.removeAttribute("transaccion");
    session.removeAttribute("fechaInicial");
    session.removeAttribute("fechaFinal");
    session.removeAttribute("horaInicial");
    session.removeAttribute("horaFinal");
    
    Double totalMonto=0.0;
    if(listaTransaccion!=null){
        for(int index=0;index<listaTransaccion.size(); index++){                    
            totalMonto = totalMonto + listaTransaccion.get(index).getTotalMonto();
        }
    }
    %>
    <style>
    #nav1{  
        opacity: 0.8;     
    }
    body{
        background-image: url("../resources/Cajero.jpg");
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
        
        
        <%@ include file = "../Cajero/barraMenuCajero.html" %>
        
            <div class="container py-3">
                <div class="row">
                    <div class="col-12 col-sm-8 col-md-6 mx-auto">
                        <div  class="card">
                            <div class="card-body">
                                <div class="card-title">
                                    <h2 class="text-center" id="text" >Transacciones Realizadas</h2>
                                    <%if(listaTransaccion!=null){%>
                                         <h5 class="text-center" id="text" >ID: <%=listaTransaccion.get(0).getCajero()%></h5>
                                         <h5 class="text-center" id="text" >Fecha Inicial: <%= fechaInicial%> Fecha Final: <%= fechaFinal%></h5>
                                         <h5 class="text-center" id="text" >Hora Inicial: <%= horaInicial%> Hora Final: <%= horaFinal%></h5>
                                    <%}%>
                                </div>
                                <hr>
                                <form method="post" action="../CajeroTransaccionesTiempo">
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
                                    <div class="row">
                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="cc-exp" class="control-label">Hora Inicio</label>
                                                <input class="form-control" type="time" name="horaInicial" min="1900-01-01" max="2500-01-31"required>
                                            </div>
                                        </div>    
                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="cc-exp" class="control-label">Hora Final</label>
                                                <input class="form-control" type="time" name="horaFinal" min="1900-01-01" max="2500-01-31"required>
                                            </div>
                                        </div>
                                    </div>
                                    <input class="btn btn-success btn-lg btn-block" type="submit"   value ="Generar Reporte" >
                                
                                </form>
                                <br>
                                <form method="post" action="../Exportar">
                                    <input type="hidden" name="Valor" value="9">
                                    <input type="hidden" name="url" value="TransaccionIntervaloTiempo">
                                    <input type="hidden" name="urlCompleta" value="./ReportesCajero/TransaccionIntervaloTiempo.jsp">
                                    <%if(listaTransaccion!=null){%>                                    
                                    <input type="hidden" name="idCajero" value="<%=listaTransaccion.get(0).getCajero()%>">
                                    <input type="hidden" name="fechaInicial" value="<%=fechaInicial%>">
                                    <input type="hidden" name="fechaFinal" value="<%=fechaFinal%>">
                                    <input type="hidden" name="horaInicial" value="<%=horaInicial%>">
                                    <input type="hidden" name="horaFinal" value="<%=horaFinal%>">
                                    <input type="hidden" name="balanceFinal" value="<%=totalMonto%>">
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
                <th scope="col">Numero</th>
                <th scope="col">Codigo</th>
                <th scope="col">Cuenta</th>
                <th scope="col">Cantidad</th>
                <th scope="col">Tipo</th>
                <th scope="col">Fecha Creada</th>
                <th scope="col">Hora Creada</th>
                <th scope="col">Balance Final</th>
            </tr>
            <% if(listaTransaccion!=null){
                for(int index=0;index<listaTransaccion.size(); index++){
                    totalMonto = totalMonto + listaTransaccion.get(index).getTotalMonto();
                %> 
                <tr>
                    <td><%=index+1 %></td>
                    <td><%= listaTransaccion.get(index).getCodigo() %></td>
                    <td><%=listaTransaccion.get(index).getCuenta()%></td>
                    <td><%=listaTransaccion.get(index).getMonto()%></td>
                    <td><%=listaTransaccion.get(index).getTipo()%></td>
                    <td><%=listaTransaccion.get(index).getFechaCreacion()%></td>
                    <td><%=listaTransaccion.get(index).getHoraCreacion()%></td>
                    <% if(index==listaTransaccion.size()-1){ %>
                    <td><%=totalMonto%></td>
                    <%}%>
                </tr>
            <%}}%>
        </table>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>