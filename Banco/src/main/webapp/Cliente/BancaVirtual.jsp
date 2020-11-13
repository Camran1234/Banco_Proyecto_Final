<%-- 
    Document   : BancaVirtual
    Created on : Nov 11, 2020, 12:32:08 PM
    Author     : camran1234
--%>

<%@page import="File.SpecialOptions.CloseSession"%>
<%@page import="SQL.Get.InfoMonto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="File.SpecialOptions.Time"%>
<%@page import="SQL.Querys.Look.CorroboradorUsuario"%>
<%@page import="javax.swing.JOptionPane"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache");
    new CloseSession().redirigirSesionCerrada(request, response);
    String mensaje = (String)session.getAttribute("Mensaje");
    session.removeAttribute("Mensaje");
    String codigoCuentaCliente = (String)request.getParameter("Codigo");
    if(codigoCuentaCliente!=null){
        codigoCuentaCliente = codigoCuentaCliente.split(" ")[1];   
    }

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
        color: red;
    }
    #ErrorTextA{
        display: block;;
        font-family: Verdana;
        font-size: 15px;
        font-weight: bolder;
        color: white;
    }
    </style>
    <script>
function redirigir() {
    location.replace("../Cliente/VerCuentas.jsp");
}
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Banca Virtual</title>
    </head>    
    <body>
        
        <%@ include file = "../Cliente/barraMenuCliente.html" %>
        <br><br><br>
         <div class="container py-3">
            <div class="row">
                <div class="col-12 col-sm-8 col-md-6 mx-auto">
                    <div  class="card">
                        <div class="card-body">
                            <div class="card-title">
                                <h2 class="text-center">Realizar Transaccion</h2>
                            </div>
                            <hr>
                            <form action="../ManejarDatosTransaccionVirtual" method="post" >
                                <div class="form-group ">
                                    <label>Numero de Cuenta a Depositar</label>
                                    <input name="codigoCuenta" type="text" class="form-control" value= ""required/>
                                </div>
                                <div class="form-group ">
                                    <label>Numero de Cuenta a Retirar</label>
                                    <input name="codigoCuentaRetirar" type="text" class="form-control" 
                                           <% if(codigoCuentaCliente!=null){
                                        %>
                                        value= "<%=codigoCuentaCliente%>"
                                        <%
                                    }else{%>
                                        value= ""
                                    <%
                                    }%>"required readonly/>
                                </div>
                                <div class="form-group ">
                                    <label>Cantidad a Depositar</label>
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">Q</span>
                                        <input name="monto" type="text" value= "" class="form-control" placeholder="Ingrese la cantidad a depositar..." required/>        
                                    </div>
                                </div>
                                <div class="form-group ">
                                    
                                </div>
                                <div class="row">
                                       <div class="col-6">
                                           <div class="form-group text-center">
                                               <input  type="submit" value ="Comprobar" class="btn btn-warning btn-lg btn-block" required/>
                                            </div>
                                       </div>
                                            <div class="form-group text-center">
                                               <input   class="btn btn-success btn-lg btn-block" type="button"  onclick="redirigir()" value ="Buscar Cuenta" >
                                            </div>
                                   </div>
                                <div class="form-group">
                                    <%if(mensaje!=null){
                                        %>
                                                <label id="ErrorText"><%=mensaje%></label>
                                                <%
                                    }%>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
                <br><br><br>
                <p class="card-text" id="ErrorTextA">Codigo: <%=session.getAttribute("Codigo")%> </p>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
