/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.SpecialOptions;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase que permite encriptar y desencriptar contraseñas
 * se utilizo la encriptacion BlowFish
 * @author camran1234
 */
public class Password {
    private static final String basedKey = "Data New Key";
    
    /**
     * Devuelve una cadena encripdata de una contraseña normal
     * @param password
     * @return
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException 
     */
    public String encryptionPassword(String password) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        SecretKeySpec key = new SecretKeySpec(basedKey.getBytes("UTF-8"), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        String encryptedData = new String(cipher.doFinal(password.getBytes("UTF-8")));
        return encryptedData;
    }
    
}
