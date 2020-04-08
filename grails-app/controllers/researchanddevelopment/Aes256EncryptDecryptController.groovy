package researchanddevelopment



import groovy.sql.Sql
import java.io.*; // ito giezel need 
import java.net.*;
import java.util.*; // ito giezel need
import java.util.concurrent.ConcurrentHashMap;
import org.*
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.json.parser.JSONParser
import org.springframework.web.context.request.RequestContextHolder
import javax.servlet.http.HttpSession
import java.text.DateFormat; // ito giezel need
import java.text.SimpleDateFormat; // ito giezel need
import java.util.Calendar; // ito giezel need
import java.util.Date; // ito giezel need
import java.util.Formatter.DateTime // ito giezel need
import java.util.Iterator; 
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.codec.binary.Base64;

class Aes256EncryptDecryptController {

    def index() {
    
    }
    
    def Aes256Encrypt(String wordsToEncrypt, String secret){
        try{
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    def Aes256Decrypt(String wordsToDecrypt, String secret){
        try {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
        }
}
