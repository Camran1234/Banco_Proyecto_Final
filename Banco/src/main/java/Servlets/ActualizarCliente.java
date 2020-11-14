/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import SQL.Actualizadores.ActualizadorCliente;
import File.ErrorHandlers.FormatException;
import File.UploadFiles.Cliente;
import File.UploadFiles.HistorialActualizacion;
import SQL.Get.InfoGerente;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author camran1234
 */
@WebServlet(name = "ActualizarCliente", urlPatterns = {"/ActualizarCliente"})
public class ActualizarCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final long serialVersionUID = 1L;
        final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
        final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
        final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
        final String UPLOAD_DIRECTORY = "upload";
        // checks if the request actually contains upload file
        String path = null;                    
        String nombre = null;        
        String dpi = null;        
        String direccion = null;        
        String sexo = null;        
        String fecha = null;        
        String codigoUsuario = null;        
        String password=null;        
        String password2 = null;
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("file")
                + File.separator + UPLOAD_DIRECTORY;
         
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (uploadDir.exists() == false) {
            uploadDir.mkdirs();
        }
        // redireccionamos a la pagina correspondiente segun el atributo establecido en la sesion
        try {
            // parses the request's content to extract file data
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    //Para conseguir la path del archivo subido
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        path = filePath;
                        if(path !=null){
                            request.getSession().setAttribute("urlArchivo", path);
                        }else{
                            request.getSession().setAttribute("urlArchivo", null);
                        }
                        File storeFile = new File(filePath);
                        // saves the file on disk
                        item.write(storeFile);
                    } else if (item.isFormField()) {
                        String nombreInput = item.getFieldName();
                        String valorInput = item.getString();
                            switch(nombreInput){
                                case "Nombre":
                                    nombre=valorInput;
                                    break;    
                                case "dpi":
                                    dpi = valorInput;
                                    break;    
                                case "Direccion":
                                    direccion = valorInput;
                                    break;    
                                case "Sexo":
                                    sexo = valorInput;
                                    break;    
                                case "fechaNacimiento":
                                    fecha = valorInput;
                                    break;     
                                case "codigoUsuario":
                                    codigoUsuario = valorInput;
                                    break; 
                                case "password":
                                    password = valorInput;
                                    break;    
                                case "password2":
                                    password2=valorInput;
                                    break;
                            } 
                    }
                    
                }
            }
        } catch (Exception ex) {
            String error = ex.getMessage();
            String error2 = ex.toString();
            request.getSession().setAttribute("error", error + error2);
            ex.printStackTrace();
        }
        String mensaje = null;
        if(codigoUsuario!=null){
            try {
                  mensaje = new ActualizadorCliente(nombre, dpi,direccion, sexo, fecha, codigoUsuario, password, password2, path).subirArchivo();
                  String cambios = "Se actualizo el cliente de codigo "+codigoUsuario + "  Que tiene el numero de dpi: "+dpi+"Se cambio la direccion por: "+direccion+"Se cambio el sexo por: "+sexo+
                          "Se cambio la fecha de nacimiento por: "+fecha+", quien realizo estos cambios fue el ciudadano cuyo codigo es: " + request.getSession().getAttribute("Codigo");
                  String dpiGerente = new InfoGerente().obtenerDPIGerente((String)request.getSession().getAttribute("Codigo"));
                  mensaje += " " +new HistorialActualizacion().subirHistorial(cambios, dpiGerente, codigoUsuario);
                  //Crear historial
            } catch (FormatException ex) {
                  mensaje = ex.getMessage();
            }
        }
        request.getSession().setAttribute("Resultado", mensaje);
        response.sendRedirect("./Gerente/Resultado.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
