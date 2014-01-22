/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.password.util;

import java.io.Serializable;
import zm.hashcode.mshengu.app.security.GetUserCredentials;
import zm.hashcode.mshengu.app.security.PasswordEncrypt;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author boniface
 */
public class PasswordCheckUtil implements Serializable {

    private final GetUserCredentials creds = new GetUserCredentials();
    private final Person person = creds.getLoggedInPerson();

    public boolean checkOldPassowrd(String password) {
        boolean isCorrect = false;
        String passwd = PasswordEncrypt.encrypt(password);
        String savedPassword = person.getPassword();
        if (passwd.equals(savedPassword)) {
            isCorrect = true;
        }
        return isCorrect;
    }

    public static boolean comparePasswords(String password, String repeat) {
        boolean isSame = false;
        if (password.equals(repeat)) {
            isSame = true;
        }
        return isSame;
    }
}
