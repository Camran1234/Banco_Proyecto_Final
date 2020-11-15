/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.SpecialOptions;

import File.ReportFiles.CajeroModel;
import File.ReportFiles.ClienteModel;
import File.ReportFiles.CuentaModel;
import File.ReportFiles.HistorialModel;
import File.ReportFiles.SolicitudModel;
import File.ReportFiles.TransaccionModel;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.ws.rs.core.HttpHeaders;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author camran1234
 */
@WebServlet("/Exportar")
public class Exportar extends HttpServlet {

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
        try {
            
            int opcionReporte = Integer.parseInt(request.getParameter("Valor"));
            String url = request.getParameter("url");
            String urlCompleta = request.getParameter("urlCompleta");
            String nombreArchivo = request.getParameter("nombreArchivo");
            //Redirigimos por si esta nulo
            if (request.getSession().getAttribute("dataExportar") == null) {
                response.sendRedirect(urlCompleta);
            }
            
            
            //Empezamos a operar
            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+nombreArchivo+".pdf");
            File file = new File(request.getServletContext().getRealPath("/resources/Reportes/"+url+".jrxml"));
            JasperReport jasperReports = JasperCompileManager.compileReport(file.getAbsolutePath());
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("CONTEXT", this.getServletContext().getRealPath("/"));
            JRBeanCollectionDataSource dataSource;
            switch(opcionReporte){
                case 1:
                        List<HistorialModel> historial1 = (List<HistorialModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(historial1);
                        parameters.put("entidadDataSource", dataSource);
                        parameters.put("titulo", historial1.get(0).getTipoUsuario());
                    break;    
                case 2:
                        List<ClienteModel> clientes2 = (List<ClienteModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(clientes2);
                        parameters.put("entidadDataSource", dataSource);                        
                        parameters.put("Monto", request.getParameter("Monto"));
                    break;    
                case 3:
                        List<ClienteModel> clientes3 = (List<ClienteModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(clientes3);
                        parameters.put("entidadDataSource", dataSource);                        
                        parameters.put("Monto", request.getParameter("Monto"));
                        parameters.put("cuentasPermitidas", request.getParameter("CantidadPermitida"));
                    break;    
                case 4:
                        List<ClienteModel> clientes4 = (List<ClienteModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(clientes4);
                        parameters.put("entidadDataSource", dataSource);                        
                        parameters.put("Monto", request.getParameter("Monto"));
                        parameters.put("cuentasPermitidas", request.getParameter("CantidadPermitida"));
                    break;
                case 5:
                        List<ClienteModel> clientes5 = (List<ClienteModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(clientes5);
                        parameters.put("entidadDataSource", dataSource);
                        parameters.put("fechaInicial", request.getParameter("fechaInicial"));
                        parameters.put("fechaFinal", request.getParameter("fechaFinal"));                        
                    break;    
                case 6:
                        List<TransaccionModel> transaccion6 = (List<TransaccionModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(transaccion6);
                        parameters.put("entidadDataSource", dataSource);
                        parameters.put("montoInicial", request.getParameter("montoInicial"));
                        parameters.put("montoFinal", request.getParameter("montoFinal"));
                    break; 
                case 7:
                        List<CajeroModel> cajero7 = (List<CajeroModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(cajero7);
                        parameters.put("entidadDataSource", dataSource);
                        parameters.put("fechaInicial", request.getParameter("fechaInicial"));
                        parameters.put("fechaFinal", request.getParameter("fechaFinal"));
                    break;    
                case 8:
                        List<TransaccionModel> transaccion8 = (List<TransaccionModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(transaccion8);
                        parameters.put("entidadDataSource", dataSource);
                        parameters.put("idCajero", request.getParameter("idCajero"));
                        parameters.put("total", request.getParameter("balanceFinal"));
                    break;
                case 9:
                        List<TransaccionModel> transaccion9 = (List<TransaccionModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(transaccion9);
                        parameters.put("entidadDataSource", dataSource);
                        parameters.put("idCajero", request.getParameter("idCajero"));
                        parameters.put("total", request.getParameter("balanceFinal"));
                        parameters.put("fechaInicial", request.getParameter("fechaInicial"));
                        parameters.put("fechaFinal", request.getParameter("fechaFinal"));
                        parameters.put("horaInicial", request.getParameter("horaInicial"));
                        parameters.put("horaFinal", request.getParameter("horaFinal"));
                    break;                    
                case 10:
                        List<TransaccionModel> transaccion10 = (List<TransaccionModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(transaccion10);
                        parameters.put("entidadDataSource", dataSource);
                        parameters.put("nombre", request.getParameter("nombre"));
                        parameters.put("dpi", request.getParameter("dpi"));
                    break;
                    
                case 11:
                        List<TransaccionModel> transaccion11 = (List<TransaccionModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(transaccion11);
                        parameters.put("entidadDataSource", dataSource);
                        parameters.put("nombre", request.getParameter("nombre"));
                        parameters.put("dpi", request.getParameter("dpi"));
                        parameters.put("fechaInicial", request.getParameter("fechaInicial"));
                        parameters.put("fechaFinal", request.getParameter("fechaFinal"));
                    break;    
                case 12:
                        List<TransaccionModel> transaccion12 = (List<TransaccionModel>) request.getSession().getAttribute("dataExportar");
                        CuentaModel cuenta12 = (CuentaModel) request.getSession().getAttribute("dataExportar2");
                        dataSource = new JRBeanCollectionDataSource(transaccion12);
                        parameters.put("codigo", cuenta12.getCodigo());
                        parameters.put("idCliente", cuenta12.getIDCliente());
                        parameters.put("fechaCreacion", cuenta12.getFechaCreacion());
                        parameters.put("credito", cuenta12.getCredito());
                        parameters.put("transaccionDataSource", dataSource);
                        parameters.put("nombre", request.getParameter("nombre"));
                        parameters.put("dpi", request.getParameter("dpi"));
                        parameters.put("fechaInicial", request.getParameter("fechaInicial"));
                        parameters.put("fechaFinal", request.getParameter("fechaFinal"));
                    break;
                    case 13:
                        List<SolicitudModel> solicitud13 = (List<SolicitudModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(solicitud13);
                        parameters.put("entidadDataSource", dataSource);
                        parameters.put("nombre", request.getParameter("nombre"));
                        parameters.put("dpi", request.getParameter("dpi"));
                    break;
                    case 14:
                        List<SolicitudModel> solicitud14 = (List<SolicitudModel>) request.getSession().getAttribute("dataExportar");
                        dataSource = new JRBeanCollectionDataSource(solicitud14);
                        parameters.put("entidadDataSource", dataSource);
                        parameters.put("nombre", request.getParameter("nombre"));
                        parameters.put("dpi", request.getParameter("dpi"));
                    break;
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReports, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
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
