<%-- 
    Document   : Retiros
    Created on : Nov 12, 2020, 1:27:02 PM
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
 
    new CloseSession().redirigirSesionCerrada(request, response);
    String mensaje = (String)session.getAttribute("Mensaje");
    session.removeAttribute("Mensaje");
    String codigoCuentaCliente = (String)request.getParameter("Codigo");
    if(codigoCuentaCliente!=null){
        codigoCuentaCliente = codigoCuentaCliente.split(" ")[1];   
    }
    if(! new CloseSession().redirigirFueraDelTurno(request, response)){
        response.sendRedirect("../Cajero/InicioCajero.jsp");
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
    #ErrorTextA{
        display: block;;
        font-family: Verdana;
        font-size: 15px;
        font-weight: bolder;
        color: white;
    }
    </style>
    <script>
function mensaje() {
  alert("Turno matutino de 6:00 a 14:30 y turno vespertino de 13:00 a 22:00");
}
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Banco</title>
    </head>    
    <body>
        <%@ include file = "../Cajero/barraMenuCajero.html" %>
        <br><br><br>
         <div class="container py-3">
            <div class="row">
                <div class="col-12 col-sm-8 col-md-6 mx-auto">
                    <div  class="card">
                        <div class="card-body">
                            <div class="card-title">
                                <h2 class="text-center">Realizar Retiro</h2>
                            </div>
                            <hr>
                            
                            <form method="post" action="../ManejarDatosTransaccionCajero"  >
                                
                                <div class="form-group ">
                                    <label>Numero de Cuenta a Retirar</label>
                                    <input name="cuentaReceptora" type="text" class="form-control" value= "" required/>
                                </div>
                                <div class="form-group ">
                                    <label>DPI del solicitante</label>
                                    <input name="dpi" type="text" class="form-control" value= "" required/>
                                </div>                                
                                <div class="form-group ">
                                    <label>Cantidad a Retirar</label>
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">Q</span>
                                        <input name="deposito" type="text" value= "" class="form-control" placeholder="Ingrese la cantidad a depositar..." required/>        
                                    </div>
                                </div>
                                
                                <div class="form-group ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">Tipo</span>
                                        <input name="tipoTransaccion" type="text" value= "Debito" class="form-control" placeholder="Ingrese la cantidad a depositar..." required readonly/>        
                                    </div>
                                </div>
                                
                                <br>
                                <div class="form-group"><!-- comment -->
                                    <input  type="submit" value ="Comprobar" class="btn btn-warning btn-lg btn-block" required/>
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
