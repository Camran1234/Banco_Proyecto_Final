/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Actualizadores;

import File.ErrorHandlers.FormatException;
import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author camran1234
 */
public class ActualizadorGerente {
    private String codigo;
    private String nombre;
    private String password;
    private String sexo;
    private String dpi;
    private String direccion;
    private String turno;

    public ActualizadorGerente(String nombre, String dpi, String direccion, String sexo, String turno, String codigoUsuario, String password, String password2) throws FormatException {
        if(dpi.length()!=15){                        
            throw new FormatException (" El dpi no contiene 15 digitos ");                
        }        
        try{                            
            Long.parseLong(dpi);                            
        }catch(Exception ex){        
            throw new FormatException (" El dpi no es un numero ");            
        }
        if(!password.equals(password2)){
            throw new FormatException ("Las contraseñas no coinciden");
        }
        this.codigo = codigoUsuario;
        this.nombre = nombre;
        this.password = password;
        this.sexo = sexo;
        this.dpi = dpi;
        this.direccion = direccion;
        this.turno = turno;
    }

    public String subirArchivo() {
        try {
            String mensaje;
            String comando1 = "UPDATE USUARIO SET Password=? WHERE Codigo = ?";
            String comando2 = "UPDATE GERENTE SET Nombre=?, Turno=?, Direccion=?, Sexo=? WHERE NoUsuario=?";
            //Comprobamos de que no halla repeticiones en el usuario
            Connection connection = new Conexion().CreateConnection();
            PreparedStatement statement = null;
            //Preparamos el statement para subirlo en la base de datos de Usuario            
            statement = connection.prepareStatement(comando1);            
            statement.setString(1, password);
            statement.setString(2, codigo);            
            statement.executeUpdate();
            statement = connection.prepareStatement(comando2); 
            statement.setString(1, nombre);            
            statement.setString(2, turno);            
            statement.setString(3, direccion);            
            statement.setString(4, sexo);
            statement.setString(5, codigo);
            statement.executeUpdate();   
            
            
            new Conexion().CloseConnection();
            //Subimos el archivo
            return "Tus datos fueron actualizados correctamente";    
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (Exception e) {
            new Conexion().CloseConnection();
            e.printStackTrace();
            return "Archivo No Actualizado Correctamente por: " + e.getMessage();
        }
    }
}
