/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Get;

import SQL.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class InfoAsociacion {
 
    /**
     * Retorna el nombre,dpi y la cuenta de la asociacion de quien lo envia
     * El IDCuentaA representa la cuenta a quien se le envio, el IDCuentaB representa quien la envio
     * @return 
     */
    public ArrayList<ArrayList> informacionBasica(String codigo){
        try {  
            ArrayList<ArrayList> array = new ArrayList<>();
            ArrayList<String> nombre = new ArrayList<>();
            ArrayList<String> dpi = new ArrayList<>();
            ArrayList<String> cuentas = new ArrayList<>();
            ArrayList<String> cuentasReceptor = new ArrayList<>();
            //De primero encontramos todas las cuentas pertenecientes a este codigo
            ArrayList<String> codigos = this.GetCodigosCuentas(codigo);
            Connection connection = new Conexion().CreateConnection();
            //Luego agregamos todos los nombres que se vieron involucrados con esta cuenta
            String comando = "SELECT Nombre,DPI FROM CLIENTE WHERE DPI = (SELECT IDCliente FROM CUENTA WHERE Codigo=(SELECT IDCuentaB FROM CUENTAS_ASOCIADAS WHERE IDCuentaA=? AND Estado=\"Pendiente\"))";
            PreparedStatement statement = null;
            for(int indexCuenta=0; indexCuenta<codigos.size(); indexCuenta++){
                statement = connection.prepareStatement(comando);
                statement.setString(1, codigos.get(indexCuenta));
                ResultSet resultado = statement.executeQuery();
                while(resultado.next()){
                    nombre.add(resultado.getString("Nombre"));
                    dpi.add(resultado.getString("DPI"));
                }
            }
            //Ahora encontramos el numero de cuenta que pertenece 
            String comando1 = "SELECT IDCuentaB,IDCuentaA FROM CUENTAS_ASOCIADAS WHERE IDCuentaA=? AND Estado=False";
            for(int indexCuenta=0; indexCuenta<codigos.size(); indexCuenta++){
                statement = connection.prepareStatement(comando1);
                statement.setString(1, codigos.get(indexCuenta));
                ResultSet resultado = statement.executeQuery();
                while(resultado.next()){
                    
                    cuentas.add(resultado.getString("IDCuentaB"));
                    cuentasReceptor.add(resultado.getString("IDCuentaA"));
                }
            }
            array.add(nombre);               
            array.add(dpi);
            array.add(cuentas);          
            array.add(cuentasReceptor);          
            new Conexion().CloseConnection();
            return array;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
    }
    
    /**
     * Obtiene los codigos de cuentas pertenecientes al codigo de usuario
     * @return 
     */
    private ArrayList<String> GetCodigosCuentas(String codigo){
        try {  
            //Obtenderemos todas las cuentas pertenecientes a este usuario
            ArrayList<String> codigos = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT CODIGO FROM CUENTA WHERE IDCLIENTE=(SELECT DPI FROM CLIENTE WHERE NoUsuario=?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                codigos.add(resultado.getString("CODIGO"));
            }
            new Conexion().CloseConnection();
            return codigos;
        } catch (SQLException ex) {
               new Conexion().CloseConnection();
               ex.printStackTrace();               
        }   
        return null;
    }
}
