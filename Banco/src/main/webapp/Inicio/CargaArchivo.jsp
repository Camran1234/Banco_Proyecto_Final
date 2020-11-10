<%-- 
    Document   : CargaArchivo
    Created on : Nov 8, 2020, 11:45:50 PM
    Author     : camran1234
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  String codigo = request.getParameter("Codigo");
    String password = request.getParameter("Password");
    
    if(codigo!=null && password!=null){
        if(!codigo.equals("MasterAdmin") && !password.equals("admin1234")){
            session.setAttribute("Mensaje", "Código o Contraseña Erróneos");
            response.sendRedirect("../Inicio/OpcionEspecial.jsp");
        }
    }
    

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
        <title>Cargar Archivo</title>
    </head>    
        
        
</head>
<body>
    
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" id='nav1'>
        <a class="navbar-brand" href="../index.jsp" id='textImage'><img src ="../resources/LogoBanco.png" height="100" width="100"   />Banco Anillado</a>
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
    <label id="formsText">Cargar Archivos XML</label><br><br>
    
    <form class="col-lg-6 offset-lg-4" method ="post" action="../UploadFileXML" enctype="multipart/form-data">
            <span class="input-group-btn">
                <input class="ex1" type="file" name = "file" accept='.xml' required>
                <input type="submit" class="btn btn-primary" value="Subir Archivo">
            </span>
            <div>
    </form>      
    
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
