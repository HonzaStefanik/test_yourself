package helpClasses;

import com.google.common.io.BaseEncoding;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.UnsupportedEncodingException;

public class PasswordUtil {

    public static String encryptPassword(String password){
        String inputContent = password;
        String base64String = "";
        try {
            base64String = BaseEncoding.base64().encode(inputContent.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64String;
    }

    public static String decryptPassword(String encryptedPassword){
        byte[] contentInBytes = BaseEncoding.base64().decode(encryptedPassword);
        try {
            return new String(contentInBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
