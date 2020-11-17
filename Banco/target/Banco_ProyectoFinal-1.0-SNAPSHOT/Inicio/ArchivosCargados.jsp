<%-- 
    Document   : ArchivosCargados
    Created on : Nov 10, 2020, 12:42:33 AM
    Author     : camran1234
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="File.XmlParser.FileScanner"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%  FileScanner fileScanner = new FileScanner();
    String path =(String) session.getAttribute("urlArchivo");
    fileScanner.scannFile(path);
    ArrayList<String> mensaje = fileScanner.obtenerMensajes();
    ArrayList<String> mensajesErroneos = fileScanner.obtenerMensajesErroneos();
    ArrayList<String> nodos = fileScanner.obtenerNodos();
    ArrayList<String> nodosErrores = fileScanner.obtenerNodosErroneos();
    %>
<style>
    .etiqueta{
        
        color: black;
        margin: 20px;
        
        display: inline-block;
    }
    #nav1{  
        opacity: 0.8;     
    }
        
     
    #textImage{
        font-size: 35px;
    }
    
    #text{
        font-size: 25px;
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
    
    #TittleText{
        font-family: Verdana;
        font-size: 25px;
        font-weight: normal;
        color: white;
    }
    body {
        background-image: url("../resources/fondoInicio.jpg");
    }
    
    .container{
        margin: 140px;
         border-style: solid;
    border-width: 5px;
        height: 400px;
        width: 100px;
        position: relative;
        
    }
    
    .ButtonOptions {
        display: block;
        text-align: center;
        background-color: darkgray; /* Green */
        border-radius: 15px;
        color: black;
        padding: 5px 30px;
        
        font-size: 16px;
      }
        input.ex1 {
            padding: 15px;
        }
   
</style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Archivos Cargados</title>
    </head>    
        
        
</head>
<body>
    
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" id='nav1'>
        <a class="navbar-brand" href="../index.jsp" id='textImage'><img src ="../resources/LogoBanco.png" height="100" width="100"   />El Billeton</a>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="../index.jsp" id='text'>Inicio</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="../Inicio/OpcionEspecial.jsp" id='text'>Opciones Especiales</a>
            </li>
    <!-- <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">            Dropdown on Right</a>
      <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <a class="dropdown-item" href="#">Action</a>
        <a class="dropdown-item" href="#">Another action with a lot of text inside of an item</a>
      </div>
    </li>
       -->
        </ul>
    </nav>
    
    <br><br>
    <label id="formsText">ARCHIVOS CARGADOS</label><br><br>
    
    <table class="table table-dark">
  <thead>
    <tr>
        <th scope="col">#</th>
      <th scope="col">Etiqueta</th>
      <th scope="col">Descripcion</th>
    </tr>
  </thead>
  <tbody>
    <%      
        for(int indexEtiquetas=0; indexEtiquetas<(nodos.size()); indexEtiquetas++){
            %>
            <tr>
            <th scope="row"><%=indexEtiquetas+1%></th>    
             <td><%=nodos.get(indexEtiquetas)%></td>    
             <td><%=mensaje.get(indexEtiquetas)%></td>    
            </tr>
        <%}%>
        <%
        for(int indexEtiquetas=0; indexEtiquetas<(nodosErrores.size()); indexEtiquetas++){
            %>
            <tr>
            <th scope="row"><%=indexEtiquetas+1%></th>    
             <td><%=nodosErrores.get(indexEtiquetas)%></td>    
             <td><%=mensajesErroneos.get(indexEtiquetas)%></td>    
            </tr>
        <%}%>
  </tbody>
</table>   
    
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
