/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.security;

import java.security.*;
import java.util.logging.*;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 *
 * @author abismail
 */
public class PasswordEncrypt {

    public static String encrypt(String freeText) {
        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        String result = encoder.encode(freeText);
        StringBuilder getString = new StringBuilder();
        try {
            MessageDigest msg = MessageDigest.getInstance("MD5", "SUN");
            byte bs[] = freeText.getBytes();
            byte digest[] = msg.digest(bs);
            for (int i = 0; i < digest.length; ++i) {
                getString.append(Integer.toHexString(0x0100 + (digest[i] & 0x00FF)).substring(1));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordEncrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(PasswordEncrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getString.toString();
    }
}
