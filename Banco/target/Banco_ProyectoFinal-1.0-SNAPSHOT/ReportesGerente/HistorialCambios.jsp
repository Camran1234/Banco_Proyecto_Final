<%-- 
    Document   : HistorialCambios
    Created on : Nov 14, 2020, 5:29:34 AM
    Author     : camran1234
--%>

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
    List<HistorialModel> listaHistorial = (List<HistorialModel>) session.getAttribute("transaccion");
   session.removeAttribute("transaccion");
   if(listaHistorial!=null){
       session.setAttribute("dataExportar", listaHistorial);
   }
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
                                    <h2 class="text-center" id="text" >Historial Cambios Por Entidad</h2>
                                </div>
                                <hr>
                                <form method="post" action="../GerenteHistorialCambios">
                                    <div class="row">
                                        <div class="col-6">
                                            <div class="form-group">
                                                <label class="Text">Tipo Entidad a Buscar</label>    
                                            </div>
                                        </div>
                                        <div class="col-6">
                                            <div class="form-group">
                                                <select class="form-control" name="tipo" required>
                                                    <option>Gerente</option> 
                                                    <option>Cajero</option> 
                                                    <option>Cliente</option> 
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <hr><br><!-- salto de linea -->
                                    <input class="btn btn-success btn-lg btn-block" type="submit"   value ="Generar Reporte" >
                                </form>
                                <br>
                                <form method="post" action="../Exportar">
                                    <input type="hidden" name="Valor" value="1">
                                    <input type="hidden" name="url" value="HistorialCambios">
                                    <input type="hidden" name="urlCompleta" value="./ReportesGerente/HistorialCambios.jsp">
                                    <%if(listaHistorial!=null){%>
                                    <label>Guardar Como:</label>
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
                <th scope="col">No. Historial</th>
                <th scope="col">Nombre Gerente Quien Actualizo</th>
                <th scope="col">DPI Gerente Quien Actualizo</th>
                <th scope="col">Fecha Actualizacion</th>                
                <th scope="col">Hora Actualizacion</th>
                <th scope="col">Codigo Usuario</th>
                <th scope="col">Tipo Usuario</th>
                <th scope="col">Descripciones Cambios</th>
            </tr>
            <% if( listaHistorial !=null ){
                for(int index=0;index<listaHistorial.size(); index++){
                %>
                <tr>
                    <td><%=index+1 %></td>
                    <td><%= listaHistorial.get(index).getNoActualizacion()%></td>                     
                    <td><%=listaHistorial.get(index).getNombreGerente()%></td>
                    <td><%=listaHistorial.get(index).getIdGerente()%></td>                   
                    <td><%=listaHistorial.get(index).getFechaActualizacion()%></td>
                    <td><%=listaHistorial.get(index).getHoraActualizacion()%></td>
                    <td><%=listaHistorial.get(index).getIdUsuario()%></td>
                    <td><%=listaHistorial.get(index).getTipoUsuario()%></td>
                    <td><%=listaHistorial.get(index).getDescripcion()%></td>
                </tr>
            <%}}%>
        </table>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
