<%-- 
    Document   : AceptarTransaccion
    Created on : Nov 12, 2020, 12:02:04 PM
    Author     : camran1234
--%>

<%@page import="File.SpecialOptions.CloseSession"%>
<%  response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache");
    new CloseSession().redirigirSesionCerrada(request, response);
    //Obtenemos los valores enviados desde el servlet
    String nombreReceptor = (String)request.getAttribute("Nombre");
    String cuentaEmisora = (String)request.getAttribute("cuentaEmisora");
    String cuentaReceptora = (String)request.getAttribute("cuentaReceptora");
    String tipoTransaccion = (String)request.getAttribute("tipoTransaccion");
    String cantidad = (String)request.getAttribute("deposito");
    //Removemos los atributos
    request.removeAttribute("Nombre");
    request.removeAttribute("cuentaEmisora");
    request.removeAttribute("cuentaReceptora");
    request.removeAttribute("tipoTransaccion");
    request.removeAttribute("deposito");
    

    %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    #center button{
        margin: 0;
        position: absolute;
        left: 50%;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }
    input.ex1 {
            padding: 15px;
        }
    </style>
    <script>
function redirigir() {
    location.replace("../Cliente/BancaVirtual.jsp");
}
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Aceptar/Transaccion</title>
    </head>    
    <body>
         <%@ include file = "../Cliente/barraMenuCliente.html" %>
         <br><br><br>
         <form method="post" action="../CrearTransaccion">
            <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
                    <div class="card-header">Comprobación:</div>
                    <div class="card-body">
                      <h5 class="card-title">Datos de la Cuenta:</h5>
                      <p class="card-text">Nombre: <%=nombreReceptor%></p>
                      <p class="card-text">Número de la Cuenta a Depositar: <%=cuentaReceptora%></p>
                      <p class="card-text">De Parte de la Cuenta: <%=cuentaEmisora%></p>
                      <p class="card-text">Cantidad a Depositar: <%=cantidad%></p>
                      <p class="card-text">Tipo Transaccion: <%=tipoTransaccion%></p>
                      <div class="form-group align-center">
                              <input class="btn btn-success btn-lg btn-block" name="Codigo" type="submit"  value = "Crear Transacción">       
                              <input class="btn btn-danger btn-lg btn-block"  onclick="redirigir()" name="Codigo" type="Button"  value = "Regresar">        
                          </div>
                      </div>
                    </div>  
         </form>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
