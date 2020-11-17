<%-- 
    Document   : HistorialTransaccionCliente
    Created on : Nov 14, 2020, 7:10:22 AM
    Author     : camran1234
--%>

<%@page import="SQL.Get.InfoCuenta"%>
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
    List<TransaccionModel> listaTransaccion = (List<TransaccionModel>) session.getAttribute("transaccion");
    String cantidadInicial = (String) session.getAttribute("cantidadInicial");
    String cantidadFinal = (String) session.getAttribute("cantidadFinal");
    if(cantidadInicial==null){
        cantidadInicial="";
        cantidadFinal="";
    }
    
   session.removeAttribute("transaccion");
   session.removeAttribute("cantidadInicial");
   session.removeAttribute("cantidadFinal");
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
                                    <h2 class="text-center" id="text" >Historial Transacciones por Cliente</h2>
                                </div>
                                <hr>        
                                        <form method="post" action="../GerenteHistorialCliente">
                                            <div class="row">
                                                <div class="col-6">
                                                    <div class="form-group">
                                                        <label class="Text">Cliente a Buscar</label>
                                                        <input type="text" name="nombreCliente" placeholder="Coloque un nombre del cliente..."  />
                                                    </div>
                                                </div>
                                            </div><br>
                                            <div class="row">
                                                <div class="col-6">
                                                    <div class="form-group">
                                                        <label class="Text">Cantidad Inicial</label>
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text" id="basic-addon1">Q</span>
                                                            <input type="text" name="cantidadInicial" placeholder="Dinero Inicial en la Cuenta..." value="<%=cantidadInicial%>" required/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-6">
                                                    <div class="form-group">
                                                        <label class="Text">Cantidad Final</label>
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text" id="basic-addon1">Q</span>
                                                            <input type="text" name="cantidadFinal" placeholder="Dinero Inicial en la Cuenta..." value="<%=cantidadFinal%>"  required/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr><br><!-- salto de linea -->
                                            <input class="btn btn-success btn-lg btn-block" type="submit"   value ="Generar Reporte" >
                                        </form>
                                <br>
                                <form method="post" action="../Exportar">
                                    <input type="hidden" name="Valor" value="6">
                                    <input type="hidden" name="montoInicial" value="<%=cantidadInicial%>">
                                    <input type="hidden" name="montoFinal" value="<%=cantidadFinal%>">
                                    <input type="hidden" name="url" value="HistorialTransaccionCliente">
                                    <input type="hidden" name="urlCompleta" value="./ReportesGerente/HistorialTransaccionCliente.jsp">
                                    <%if(listaTransaccion!=null){%>
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
                <th scope="col">Codigo Transaccion</th>
                <th scope="col">Cuenta</th>
                <th scope="col">Propietario</th>
                <th scope="col">Cantidad</th>
                <th scope="col">Tipo</th>
                <th scope="col">Fecha Creada</th>
                <th scope="col">Hora Creada</th>
                <th scope="col">Codigo Cajero Encargado</th>
            </tr>
            <% if(listaTransaccion!=null){
                for(int index=0;index<listaTransaccion.size(); index++){
                    listaTransaccion.get(index).setNombrePropietarioCuenta(new InfoCuenta().getNameOfCode(listaTransaccion.get(index).getCuenta()));
                %>
                <tr>
                    <td><%=index+1 %></td>
                    <td><%= listaTransaccion.get(index).getCodigo() %></td>
                    <td><%=listaTransaccion.get(index).getCuenta()%></td>
                    <td><%=listaTransaccion.get(index).getNombrePropietarioCuenta()%></td>
                    <td><%=listaTransaccion.get(index).getMonto()%></td>
                    <td><%=listaTransaccion.get(index).getTipo()%></td>
                    <td><%=listaTransaccion.get(index).getFechaCreacion()%></td>
                    <td><%=listaTransaccion.get(index).getHoraCreacion()%></td>
                    <td><%=listaTransaccion.get(index).getCajero()%></td>
                </tr>
            <%}}%>
        </table>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>