/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author camran1234
 */
public class Conexion {
    //url para conectar con la base de datos, se esta manejando la base de datos INTELAF_DATA
    private static final String urlBasica = "jdbc:mysql://localhost:3306/BANCO?useSSL=false";    
    //Dejamos estatica la conexion para poder utilizarla en cualquier momento
    private static Connection connection;
    
    
    /**
     * Establece conexion con la base de datos establecida en el String urlBasica
     * @return
     */
        public Connection CreateConnection(){
            try{
                //Si la conexion es nula la establecemos
                //de lo contrario solo la retornamos
                //ya que al ser estatica guardara su valor
                if(connection==null || connection.isClosed()==true){
                    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();  
                    String user = "root";
                    String password = "Exnihilehotdog1234.-";
                    Properties propiedades = new Properties();
                    propiedades.setProperty("user",user);
                    propiedades.setProperty("password",password);
                    connection = DriverManager.getConnection(urlBasica,propiedades);
                    System.out.println("Conexión exitosa");
                }
                
                return connection;
            }catch(Exception e){
                System.out.println("Conexión Fallida");
                e.printStackTrace();
                return null;
            }
        }
        
        /**
         * Cierra la conexion establecida con la base de datos
         */
        public void CloseConnection(){
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Coneccion Cerrada");
                } catch (SQLException ex) {
                    //trow Exception
                }
            }
        }
}
