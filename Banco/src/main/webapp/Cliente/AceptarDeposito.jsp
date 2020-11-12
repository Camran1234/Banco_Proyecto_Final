<%-- 
    Document   : AceptarDeposito
    Created on : Nov 11, 2020, 1:59:20 PM
    Author     : camran1234
--%>

<%@page import="File.SpecialOptions.CloseSession"%>
<%@page import="File.ErrorHandlers.FormatException"%>
<%@page import="SQL.Querys.Look.CorroboradorCuentaAsociada"%>
<%@page import="SQL.Querys.Look.CorroboradorUsuario"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="SQL.Get.InfoCuenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache");
    new CloseSession().redirigirSesionCerrada(request, response);
    InfoCuenta info = new InfoCuenta();
    String numeroCuenta = request.getParameter("codigoCuenta");
    String numeroCuentaCliente = request.getParameter("codigoCuentaRetirar");
    String cliente = (String)session.getAttribute("Codigo");
    String monto = request.getParameter("monto");
    request.removeAttribute("codigoCuenta");
    request.removeAttribute("monto");
    //Corroboramos que sea un numero
    try {
        Double.parseDouble(monto);
        } catch (Exception e) {
            request.getSession().setAttribute("Mensaje", "El monto establecido no es un numero");
            request.setAttribute("cuenta", numeroCuenta);
            request.setAttribute("dinero", monto);    
            response.sendRedirect("../Cliente/BancaVirtual.jsp");
            return;
        }
    //Corroboramos que no sea un numero negativo
    if(Double.parseDouble(monto)<0){
        request.getSession().setAttribute("Mensaje", "El monto establecido no puede ser negativo");
        request.setAttribute("cuenta", numeroCuenta);    
        request.setAttribute("dinero", monto);
        response.sendRedirect("../Cliente/BancaVirtual.jsp");
        return;
    }
    //Corroboramos que los codigos indicados existan
    if(info.checkIfCodeExists(numeroCuenta)==false){
        request.getSession().setAttribute("Mensaje", "No se encontro el numero de cuenta");
        response.sendRedirect("../Cliente/BancaVirtual.jsp");
        return;
    }
    //Obtenemos el nombre del propietario de la cueta
    String nombre = new InfoCuenta().GetNameOfCode(numeroCuenta);
    String resultado = new InfoCuenta().checkIfEnoughFounds(numeroCuentaCliente, monto,new CorroboradorUsuario().GetDpi(cliente));
    String tipo = "Credito";
    if(!resultado.equalsIgnoreCase("SI")){
        request.getSession().setAttribute("Mensaje", resultado);
        response.sendRedirect("../Cliente/BancaVirtual.jsp");
        return;
    }
    try {
        resultado =  new CorroboradorCuentaAsociada().AccountAssociated(numeroCuenta, numeroCuentaCliente);
        } catch (FormatException e) {
            request.getSession().setAttribute("Mensaje", e.getMessage());
            response.sendRedirect("../Cliente/BancaVirtual.jsp");
            return;
        }
    
    if(!resultado.equalsIgnoreCase("SI")){
        request.getSession().setAttribute("Mensaje", resultado);
        response.sendRedirect("../Cliente/BancaVirtual.jsp");
        return;
    }
    session.setAttribute("Tipo", tipo);
    session.setAttribute("NumeroCuenta", numeroCuenta);
    session.setAttribute("NumeroCuentaRetirar", numeroCuentaCliente);
    session.setAttribute("CodigoCliente", cliente);
    session.setAttribute("Monto", monto);
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
                      <p class="card-text">Nombre: <%=nombre%></p>
                      <p class="card-text">Número de la Cuenta a Depositar: <%=numeroCuenta%></p>
                      <p class="card-text">De Parte de la Cuenta: <%=numeroCuentaCliente%></p>
                      <p class="card-text">Cantidad a Depositar: <%=monto%></p>
                      <p class="card-text">Tipo Transaccion: <%= tipo%></p>
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
