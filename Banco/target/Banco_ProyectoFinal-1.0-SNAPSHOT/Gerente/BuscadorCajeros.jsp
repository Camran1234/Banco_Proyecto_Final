<%-- 
    Document   : BuscadorCajeros
    Created on : Nov 11, 2020, 6:49:03 AM
    Author     : camran1234
--%>

<%@page import="File.SpecialOptions.CloseSession"%>
<%@page import="SQL.Get.InfoCajero"%>
<%@page import="java.util.ArrayList"%>
<%@page import="SQL.Get.InfoCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
 
    new CloseSession().redirigirSesionCerrada(request, response);
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
        #myInput {
            background-image: url('/css/searchicon.png'); /* Add a search icon to input */
            background-position: 10px 12px; /* Position the search icon */
            background-repeat: no-repeat; /* Do not repeat the icon image */
            width: 100%; /* Full-width */
            font-size: 16px; /* Increase font-size */
            padding: 12px 20px 12px 40px; /* Add some padding */
            border: 1px solid #ddd; /* Add a grey border */
            margin-bottom: 12px; /* Add some space below the input */
        }

        #myUL {
          /* Remove default list styling */
          list-style-type: none;
          padding: 0;
          margin: 0;
        }

        #myUL li a {
          border: 1px solid #ddd; /* Add a border to all links */
          margin-top: -1px; /* Prevent double borders */
          background-color: #f6f6f6; /* Grey background color */
          padding: 12px; /* Add some padding */
          text-decoration: none; /* Remove default text underline */
          font-size: 18px; /* Increase the font-size */
          color: black; /* Add a black text color */
          display: block; /* Make it into a block element to fill the whole list */
        }
    </style>
<%  InfoCajero info = new InfoCajero();
    ArrayList<ArrayList> listasDatos = info.informacionBasica();
    %>
    
    <script>
        function myFunction() {
          // Declare variables
          var input, filter, ul, li, div, h, i, txtValue;
          input = document.getElementById('myInput');
          filter = input.value.toUpperCase();
          ul = document.getElementById("UL");
          li = ul.getElementsByTagName('li');

          // Loop through all list items, and hide those who don't match the search query
          for (i = 0; i < li.length; i++) {
            div = li[i].getElementsByTagName("div")[0];
            h = li[i].getElementsByTagName("h5")[0];
            txtValue = h.textContent || h.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
              li[i].style.display = "";
            } else {
              li[i].style.display = "none";
            }
          }
        }
    </script>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Crear/Usuario</title>
    </head>    
    <body>
         <%@ include file = "../Gerente/barraNavegacionGerente.html" %>
         <br><br><br><br><br><br>
         <label id="formsText">Seleccionar Cajero</label><br>
         <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Buscar por Nombre...">
         <br><br>
         <form method="post" action="../Gerente/ActualizarCajero.jsp">
            <div class="list-group">
                <ul id="UL">
               <%for(int indexDatos=0; indexDatos<listasDatos.get(0).size(); indexDatos++ ){
                   
                %>
                    <li  class="list-group-item list-group-item-action flex-column align-items-start">
                         <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1"><%= listasDatos.get(0).get(indexDatos)%></h5>
                         </div>
                         <p class="mb-1">DPI: <%= listasDatos.get(1).get(indexDatos)%></p>
                         <p class="mb-1">Codigo: <%= listasDatos.get(2).get(indexDatos)%></p>
                        <input   name="Codigo" type="submit"  value = "Seleccionar: <%=listasDatos.get(2).get(indexDatos)%>">    
                    </li>
               <%}%>
                </ul>
            </div>     
         </form>
        
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
