<%-- 
    Document   : CrearCuenta
    Created on : Nov 10, 2020, 4:44:30 PM
    Author     : camran1234
--%>

<%@page import="File.SpecialOptions.CloseSession"%>
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
    #center button{
        margin: 0;
        position: absolute;
        left: 50%;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }
    </style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Crear/Cienta</title>
    </head>    
    <body>
         <%@ include file = "../Gerente/barraNavegacionGerente.html" %>
         <br><br><br>
         <div class="container py-3">
            <div class="row">
                <div class="col-12 col-sm-8 col-md-6 mx-auto">
                    <div  class="card">
                        <div class="card-body">
                            <div class="card-title">
                                <h2 class="text-center">Crear Cuenta</h2>
                            </div>
                            <hr>
                            <form action="../CreateAccount" method="post" >
                                <div class="form-group ">
                                    <label>Codigo Cliente</label>
                                    <input name="codigoCliente" type="text" class="form-control" required/>
                                </div>
                                <div class="form-group ">
                                    <label>Nuevo Codigo de la Cuenta</label>
                                    <input name="codigoCuenta" type="text" class="form-control" required/>
                                </div>
                                <div class="form-group">
                                    <label  class="control-label">Nombre Cliente</label>
                                    <input  name="nombreCliente" placeholder="Nombre para corroborar datos" type="text" class="form-control" value="" required/>
                                </div>
                                <div class="form-group">
                                    <label  class="control-label">DPI Cliente</label>
                                    <input  name="dpi" type="text" placeholder="DPI para corroborar datos" class="form-control" value="" required/>
                                </div>
                                <br>
                                <div class="form-group text-center">
                                    <input   class="btn btn-success btn-lg btn-block" type="submit"  name = "nombre" value ="Crear" >
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>

