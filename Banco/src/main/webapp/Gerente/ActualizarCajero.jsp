<%-- 
    Document   : ActualizarCajero
    Created on : Nov 10, 2020, 4:51:46 PM
    Author     : camran1234
--%>

<%@page import="SQL.Get.InfoCajero"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.util.ArrayList"%>
<%@page import="SQL.Get.InfoCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache");
    if(session.getAttribute("Codigo") == null){
        response.sendRedirect("./index.jsp");
        return;
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
    <%  String codigo = request.getParameter("Codigo");
        ArrayList<String> datos=null;
        if(codigo!=null){
            codigo = codigo.split(" ")[1];
             datos = new InfoCajero().InformacionCompleta(codigo);
        }
        request.removeAttribute("Codigo");
    %>
    <script>
function redirigir() {
    location.replace("../Gerente/BuscadorCajeros.jsp");
}
function mensaje() {
  alert("Turno matutino de 6:00 a 14:30 y turno vespertino de 13:00 a 22:00");
}
</script>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Actualizar/Cajero</title>
    </head>    
    <body>
         <%@ include file = "../Gerente/barraNavegacionGerente.html" %>
         <br><br><br><br><br><br>
         
         <form method ="post" action="../ActualizarCajero" >
            <div class="container">
               <div class="row">
                   <div class="col-12 col-sm-8 col-md-6 mx-auto">
                       <div  class="card">
                           <div class="card-body">
                               <div class="card-title">
                                   <h2 class="text-center">Actualizar Cajero</h2>
                               </div>
                               <hr>
                                   <div class="form-group ">
                                       <label>Nombre</label>
                                       <input name="Nombre" type="text" class="form-control" 
                                              <% if(datos!=null){
                                                  %> 
                                                  value="<%= datos.get(0)%>"
                                                  <%
                                              }%>
                                              
                                              required/>
                                   </div>
                                   <div class="form-group">
                                       <label  class="control-label">DPI</label>
                                       <input  name="dpi" type="text" class="form-control" 
                                               <% if(datos!=null){
                                                  %> 
                                                  value="<%= datos.get(1)%>"
                                                  <%
                                              }%>required readonly/>
                                   </div>
                                   <div class="form-group">
                                       <label  class="control-label">direccion</label>
                                       <input  name="Direccion" type="text" class="form-control" 
                                               <% if(datos!=null){
                                                  %> 
                                                  value="<%= datos.get(2)%>"
                                                  <%
                                              }%>required/>
                                   </div>
                                   <div class="row">
                                       <div class="col-6">
                                           <div class="form-group">
                                               <label for="cc-exp" class="control-label">Sexo</label>
                                               <select class="form-control" name="Sexo" required>
                                                   <% if(datos!=null){
                                                        if(datos.get(3).equalsIgnoreCase("Masculino")){
                                                            %>
                                                        <option>Masculino</option>
                                                   <option>Femenino</option>   
                                                            <%
                                                        }else{
                                                            %>
                                                            <option>Femenino</option>
                                                   <option>Masculino</option>
                                                            <%
                                                        }
                                                    }else{%>
                                                    <option>Masculino</option>
                                                    <option>Femenino</option>
                                                    <%}%>
                                                    
                                               </select>
                                           </div>
                                       </div>
                                       <div class="col-6">
                                           <div class="form-group">
                                               <label for="cc-exp" class="control-label">Turno</label>
                                               <div class="input-group">
                                               <select class="form-control" name="Turno" required>
                                                   <% if(datos!=null){
                                                        if(datos.get(3).equalsIgnoreCase("Vespertino")){
                                                            %>
                                                        <option>Vespertino</option>
                                                   <option>Matutino</option>   
                                                            <%
                                                        }else{
                                                            %>
                                                            <option>Matutino</option>
                                                   <option>Vespertino</option>
                                                            <%
                                                        }
                                                    }else{%>
                                                    <option>Matutino</option>
                                                    <option>Vespertino</option>
                                                    <%}%>
                                                    
                                               </select>
                                                    <input type="button" onclick="mensaje()" value="?" />
                                           </div>
                                           </div>
                                       </div>
                                   </div>
                                   <div class="form-group">
                                       <label  class="control-label">Codigo Usuario</label>
                                       <input  name="codigoUsuario" type="text" class="form-control"<% if(datos!=null){
                                                  %> 
                                                  value="<%= datos.get(5)%>"
                                                  <%
                                              }%> required readonly/>
                                   </div>
                                   <div class="form-group">
                                       <label  class="control-label">Contraseña</label>
                                       <input  name="password" type="password" class="form-control" value="" required/>
                                   </div>
                                   <div class="form-group">
                                       <label  class="control-label">Confirmar Contraseña</label>
                                       <input  name="password2" type="password" class="form-control" value="" required/>
                                   </div>
                                   <br>
                                   <div class="row">
                                       <div class="col-6">
                                           <div class="form-group text-center">
                                               <input   class="btn btn-success btn-lg btn-block" type="submit"  value ="Actualizar" >
                                            </div>
                                       </div>
                                            <div class="form-group text-center">
                                               <input   class="btn btn-success btn-lg btn-block" type="button"  onclick="redirigir()" value ="Buscar Cajero" >
                                            </div>
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
