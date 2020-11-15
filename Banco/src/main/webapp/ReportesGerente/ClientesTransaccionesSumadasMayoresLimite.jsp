<%-- 
    Document   : ClientesTransaccionesSumadasMayoresLimite
    Created on : Nov 14, 2020, 6:10:11 AM
    Author     : camran1234
--%>


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
    List<ClienteModel> listaClientes = (List<ClienteModel>) session.getAttribute("clientes");
    String fecha = (String) session.getAttribute("fecha");
    session.removeAttribute("clientes");
    session.removeAttribute("fecha");
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
                                    <h2 class="text-center" id="text" >Clientes con Transacciones Mayores Al Monto Permitido por Transacción Monetaria Multiples</h2>
                                         <h5 class="text-center" id="text" >Monto Permitido Q<%=new InfoMonto().obtenerMontoMultiple()%></h5>
                                         <h5 class="text-center" id="text" >Cantidad Transacciones Permitidas <%=new InfoMonto().obtenerCantidadCuentas()%></h5>
                                         <%if(fecha!=null){%>
                                         <h5 class="text-center" id="text" >Resultados de la Fecha: <%=fecha%></h5>
                                         <%}%>
                                </div>
                                <hr>
                                <form method="post" action="../GerenteTransaccionesSumadasLimite">
                                    <hr><br>
                                    <input class="btn btn-success btn-lg btn-block" type="submit"   value ="Generar Reporte" >
                                </form>
                                <br>
                                <form method="post" action="../Exportar">
                                    <input type="hidden" name="Valor" value="3">
                                    <input type="hidden" name="Monto" value="<%=new InfoMonto().obtenerMontoMultiple()%>">
                                    <input type="hidden" name="CantidadPermitida" value="<%=new InfoMonto().obtenerCantidadCuentas()%>">
                                    <input type="hidden" name="url" value="ClientesTransaccionesSumadasMayoresLimite">
                                    <input type="hidden" name="urlCompleta" value="./ReportesGerente/ClientesTransaccionesSumadasMayoresLimite.jsp">
                                    <%if(listaClientes!=null){%>
                                    <label>Guardar Como:</label>
                                    <%session.setAttribute("dataExportar", listaClientes);%>
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
                <th scope="col">Nombre Cliente</th>
                <th scope="col">Codigo de Usuario</th>
                <th scope="col">DPI</th>
                <th scope="col">Fecha Nacimiento</th>
                <th scope="col">Direccion</th>
                <th scope="col">Sexo</th>
                <th scope="col">Cantidad Cuentas Infraccionarias</th>
                <th scope="col">Monto Infraccionario</th>
            </tr>
            <% if(listaClientes!=null){
                for(int index=0;index<listaClientes.size(); index++){
                %>
                <tr>
                    <td><%=index+1 %></td>
                    <td><%= listaClientes.get(index).getNombre()%></td>                     
                    <td><%=listaClientes.get(index).getNoUsuario()%></td>
                    <td><%=listaClientes.get(index).getDpi()%></td>                   
                    <td><%=listaClientes.get(index).getFechaNacimiento()%></td>
                    <td><%=listaClientes.get(index).getDireccion()%></td>
                    <td><%=listaClientes.get(index).getSexo()%></td>
                    <td><%=listaClientes.get(index).getCantidadCuentas()%></td>
                    <td><%=listaClientes.get(index).getTotal()%></td>
                </tr>
            <%}}%>
        </table>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
