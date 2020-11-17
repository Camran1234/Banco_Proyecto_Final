<%-- 
    Document   : Transaccion
    Created on : Nov 11, 2020, 8:38:05 AM
    Author     : camran1234
--%>
<%@page import="File.SpecialOptions.CloseSession"%>
<%@page import="SQL.Get.InfoMonto"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.util.ArrayList"%>
<%@page import="SQL.Get.InfoCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
 
    new CloseSession().redirigirSesionCerrada(request, response);
    if(! new CloseSession().redirigirFueraDelTurno(request, response)){
        response.sendRedirect("../Gerente/InicioGerente.jsp");
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
    #formsText{
        display: block;
        text-align: center;
        font-family: Verdana;
        font-size: 30px;
        font-weight: bolder;
        color: white;
        text-decoration: underline;
    }
    #center button{
        margin: 0;
        position: absolute;
        left: 50%;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }
    </style>
    <%  String montoSolitario = new InfoMonto().obtenerMontoSolitario();
        String montoMultiple = new InfoMonto().obtenerMontoMultiple();
        String cantidadCuentas = new InfoMonto().obtenerCantidadCuentas();
    %>
    <script>
function redirigir() {
    location.replace("../Gerente/BuscadorClientes.jsp");
}
</script>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Buscador/Cliente</title>
    </head>    
    <body>
         <%@ include file = "../Gerente/barraNavegacionGerente.html" %>
         <br><br><br><br><br><br>
         
         <form method ="post" action="../ActualizarMontoSolitario" >
            <div class="container">
               <div class="row">
                   <div class="col-12 col-sm-8 col-md-6 mx-auto">
                       <div  class="card">
                           <div class="card-body">
                               <div class="card-title">
                                   <h2 class="text-center">Monto Permitido por Transacción Monetaria Individual</h2>
                               </div>
                               <hr>
                                   <div class="form-group ">
                                       <label>Monto</label>
                                       <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">Q</span>
                                            <input name="monto" type="text" placeholder="Ejemplo: 54.00" class="form-control"  value="<%= montoSolitario%>" required/>
                                        </div>
                                       
                                   </div>
                                              <div class="form-group text-center">                                         
                                                  <input   class="btn btn-success btn-lg btn-block" type="submit"  value ="Actualizar" >
                                              </div>
                           </div>
                       </div>
                   </div>
               </div>
           </div>
        </form>
        <br><br><br>
         <form method ="post" action="../ActualizarMontoMultiple" >
            <div class="container">
               <div class="row">
                   <div class="col-12 col-sm-8 col-md-6 mx-auto">
                       <div  class="card">
                           <div class="card-body">
                               <div class="card-title">
                                   <h2 class="text-center">Monto Permitido en Varias Transacciones Monetarias</h2>
                               </div>
                               <hr>
                                   <div class="form-group ">
                                       <label>Monto</label>
                                       <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">Q</span>
                                        <input name="monto" placeholder="Ejemplo: 540.00" type="text" class="form-control"  value="<%= montoMultiple%>" required/>
                                      </div>
                                       
                                   </div>
                                   <div class="form-group ">
                                       <label>Cuentas Permitidas</label>
                                       <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">Cuentas</span>
                                        <input name="cuentas" type="text" class="form-control" placeholder="Ejemplo: 6"  value="<%= cantidadCuentas%>" required/>
                                      </div>
                                       
                                   </div>
                                              <div class="form-group text-center">                                         
                                                  <input   class="btn btn-success btn-lg btn-block" type="submit"  value ="Actualizar" >
                                              </div>
                           </div>
                       </div>
                   </div>
               </div>
           </div>
        </form>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>