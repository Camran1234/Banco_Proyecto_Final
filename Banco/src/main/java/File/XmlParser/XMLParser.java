/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.XmlParser;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author camran1234
 */
public class XMLParser {
    private ArrayList<NodeList> listasXML = new ArrayList<>();
    
    
    /**
     * Transformamos los atributos del xml a nodos o en su clase NodeList 
     * para extraer los elementos
     * @param path 
     */
    public void parsePathToNodes(String path){
        DocumentParser archivo = new DocumentParser();
        Document doc = archivo.loadFile(path);
        doc.getDocumentElement().normalize();
               
        
        NodeList listaGerente = doc.getElementsByTagName("GERENTE");
        NodeList listaCajero = doc.getElementsByTagName("CAJERO");
        NodeList listaCliente = doc.getElementsByTagName("CLIENTE");
        NodeList listaTransaccion = doc.getElementsByTagName("TRANSACCION");
        
        listasXML.add(listaGerente);
        listasXML.add(listaCajero);
        listasXML.add(listaCliente);
        listasXML.add(listaTransaccion);
    }
    
    /**
     * Retorna una lista de los nodos encontrados
     * en el archivo xml
     * Asegurarse haber transformado antes el archivo
     * @return 
     */
    public ArrayList<NodeList> GetElements(){
        return this.listasXML;
    }
}
