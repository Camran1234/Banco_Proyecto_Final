/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.XmlParser;

import File.ErrorHandlers.FormatException;
import File.UploadFiles.Cajero;
import File.UploadFiles.Cliente;
import File.UploadFiles.Gerente;
import File.UploadFiles.Transaccion;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author camran1234
 */
public class FileScanner {
    private ArrayList<NodeList> listasXML;
    private ArrayList<String> nodoMensaje = new ArrayList<>();
    private ArrayList<String> nodoErrores = new ArrayList<>();
    private ArrayList<String> mensajeErrores = new ArrayList<>();
    private ArrayList<String> mensajes = new ArrayList<>();
    /**
     * Convierte un archivo en listas NodeList
     * @param path
     * @return 
     */
    public String ScannFile(String path){
        XMLParser xmlParser = new XMLParser();
        xmlParser.parsePathToNodes(path);
        listasXML = xmlParser.GetElements();
        NodeList list;
        Node node;
        Element elementNode;
        
        for(int indexListaXML=0; indexListaXML<listasXML.size(); indexListaXML++){
            list = listasXML.get(indexListaXML);
            for(int indexList = 0; indexList<list.getLength() ; indexList++){
                
                node = list.item(indexList);
                //Agregar nombre nodo
                if(node.getNodeType() == Node.ELEMENT_NODE){
                elementNode = (Element) node; 
                    switch(indexListaXML){
                        case 0:
                            try {
                                
                                mensajes.add(new Gerente(elementNode).subirArchivo());
                                nodoMensaje.add("GERENTE");
                            } catch (FormatException e) {
                                if(e.getMessage()!=null){
                                    mensajeErrores.add(e.getMessage());
                                    nodoErrores.add("GERENTE");
                                }
                            }
                            break;
                        case 1:
                            try {
                                
                                mensajes.add(new Cajero(elementNode).subirArchivo());
                                nodoMensaje.add("CAJERO");
                            } catch (FormatException e) {
                                if(e.getMessage()!=null){
                                    mensajeErrores.add(e.getMessage());
                                    nodoErrores.add("CAJERO");
                                }
                            }
                            break;
                        case 2:
                            try {
                                
                                mensajes.add(new Cliente(elementNode).subirArchivo());
                                nodoMensaje.add("CLIENTE");
                            } catch (FormatException e) {
                                if(e.getMessage()!=null){
                                    mensajeErrores.add(e.getMessage());
                                    nodoErrores.add("CLIENTE");
                                }
                            }
                            break;
                        case 3:
                            try {
                                
                                mensajes.add(new Transaccion(elementNode).subirArchivo());
                                nodoMensaje.add("TRANSACCION");
                            } catch (FormatException e) {
                                if(e.getMessage()!=null){
                                    mensajeErrores.add(e.getMessage());
                                    nodoErrores.add("TRANSACCION");
                                }
                            }
                            break;
                    }    
                }
            }
        }    
            
        return null;
    }
    
    public ArrayList<String> ObtenerMensajes(){
        return  mensajes;
    }
    
    public ArrayList<String> ObtenerMensajesErroneos(){
        return  mensajeErrores;
    }
    
    public ArrayList<String> ObtenerNodos(){
        return  nodoMensaje;
    }
    
    public ArrayList<String> ObtenerNodosErroneos(){
        return  nodoErrores
;    }
}
