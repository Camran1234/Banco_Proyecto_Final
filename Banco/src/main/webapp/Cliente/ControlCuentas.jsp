<%-- 
    Document   : ControlCuentas
    Created on : Nov 11, 2020, 1:48:03 PM
    Author     : camran1234
--%>


<%@page import="File.SpecialOptions.CloseSession"%>
<%@page import="SQL.Get.InfoCuenta"%>
<%@page import="SQL.Get.InfoAsociacion"%>
<%@page import="java.util.ArrayList"%>
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
    .cardLeft card{
        margin: 0 auto; /* Added */
        float: left; /* Added */
        margin-bottom: 10px; /* Added */
    }
    .cardRight card{
        margin: 0 auto; /* Added */
        float: right; /* Added */
        margin-bottom: 10px; /* Added */
    }
    #ErrorTextA{
        display: block;;
        font-family: Verdana;
        font-size: 15px;
        font-weight: bolder;
        color: red;
    }
    #ErrorText{
        display: block;;
        font-family: Verdana;
        font-size: 15px;
        font-weight: bolder;
        color: white;
    }
    .scroll {
        max-height: 600px;
        overflow-y: auto;
    }
    #UL {
          /* Remove default list styling */
          list-style-type: none;
          padding: 0;
          margin: 0;
        }
    </style>
    <%  String mensaje =(String) session.getAttribute("Mensaje");
        session.removeAttribute("Mensaje");
        String cuenta = request.getParameter("Codigo");
        ArrayList<ArrayList> listasDatos = new InfoAsociacion().InformacionBasica((String) session.getAttribute("Codigo") );
        %>
        
<script>
    function redirigir(){
        location.replace("../Gerente/BuscadorClientes.jsp");
    }
     function f1(objButton){  
                alert(objButton.value);
            }
                
</script>
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
        <br>
        <div class="row">
            <div class="col-sm-6 scroll">
                <div class="card text-white bg-dark mb-3" id="cardLeft" style="max-width: 50rem;">
                    <div class="card-header">Solicitudes Recibidas</div>
                    <div class="card-body">
                        <ul >
                        <% if(listasDatos!=null){
                                for(int indexDatos=0; indexDatos<listasDatos.get(0).size(); indexDatos++ ){
                            %>
                                <li  class="list-group-item list-group-item-action flex-column align-items-start">
                                    <div class="row">
                                        <div class="col-sm-6 scroll">
                                            <div class="d-flex w-100 justify-content-between">
                                               <b><h4 class="mb-1">Nombre: <%= listasDatos.get(0).get(indexDatos)%></h4></b>
                                               <small>Desea asociarse a la cuenta: <%=listasDatos.get(3).get(indexDatos)%></small>
                                            </div>
                                            <p class="mb-1">DPI <%= listasDatos.get(1).get(indexDatos)%></p>
                                            <p class="mb-1">Numero de Cuenta: <%= listasDatos.get(2).get(indexDatos)%></p>
                                        </div>
                                        <div class="col-sm-6 scroll">
                                            <form method="post" action="../AceptarSolicitud"><!<!-- el parametro 2 es la cuenta emisora y el parametro 3 es la cuenta receptora -->
                                            <button   name="aceptar"  type="submit" class="btn btn-success btn-lg btn-block" value = "<%=listasDatos.get(2).get(indexDatos)%> <%=listasDatos.get(3).get(indexDatos)%>">Aceptar</button>
                                            </form>
                                            <form method="post" action="../RechazarSolicitud">
                                            <button   name="rechazar" type="submit" class="btn btn-danger btn-lg btn-block" value = "<%=listasDatos.get(2).get(indexDatos)%> <%=listasDatos.get(3).get(indexDatos)%>">Rechazar</button>
                                            </form>
                                        </div>
                                    </div>
                                     
                                </li>
                           <%   }
                            }%>
                        </ul>
                    </div>
            </div>
            </div>
                        
            <div class="col-sm-6">
                <div class="card text-white bg-dark mb-3" id="cardRight" style="max-width: 50rem;">
                    <div class="card-header" >Enviar Solicitud de Asociacion</div>
                        <div class="card-body">
                            <form method="post" action="../Cliente/VerCuentas.jsp"> 
                                <input  type="submit" name="redireccion" value="Seleccionar Cuenta"  class="btn btn-warning btn-lg btn-lg"  required/>
                            </form>
                            <form  method="post" action="../EnviarSolicitud" >
                                <div class="card-tittle">
                                    <h2 class="text-center">Datos de Socio</h2>
                                </div>
                                <hr>
                                <div class="form-group ">
                                    <label>Numero de Cuenta  *</label>
                                    <input name="cuentaSocio" type="text" class="form-control" value= ""required/>
                                </div>
                                <div class="form-group ">
                                    <label>Nombre</label>
                                    <input name="nombre" type="text" class="form-control" value= ""/>
                                </div>
                                <div class="form-group ">
                                    <label>DPI *</label>
                                    <input name="dpi" type="text" class="form-control" value= ""required/>
                                </div>
                                <hr>
                                <div class="card-tittle">
                                    <h2 class="text-center">Tus Datos</h2>
                                </div>
                                <div class="form-group ">
                                    <label>Codigo de Cuenta a Enlazar  *</label>
                                    <input name="cuenta" typec="text" class="form-control" value= "<%
                                        if(cuenta!=null){
                                            %><%=cuenta.split(" ")[1]%><%
                                        }
                                        %>"required/>
                                </div>
                                <div class="form-group ">
                                    <input type="submit" value="Enviar Solicitud" class="btn btn-success btn-lg btn-block"  required/>
                                </div>
                                <div class="form-group">
                                    <%if(mensaje!=null){
                                        %>
                                                <label id="ErrorTextA"><%=mensaje%></label>
                                                <%
                                    }%>
                                </div>
                            </form>
                        </div>
                </div>
            </div>
        </div>
                <p class="card-text" id="ErrorText">Codigo: <%=session.getAttribute("Codigo")%> </p>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>