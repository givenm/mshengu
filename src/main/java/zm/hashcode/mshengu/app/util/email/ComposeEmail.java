/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.email;

import java.util.List;

/**
 *
 * @author given
 */
public class ComposeEmail {
    private String from;
    private String subject;
    private List<String> addressesTo;
    private List<String> addressesCC;
    private List<String> addressesBCC;
    private String password;
    private String body;

    public String getEmailBody() {
        return body;
    }

    public void setEmailBody(String body) {
        this.body = body;
    }
        
    public void setPassword(String password) {
        this.password = password;
    }   

    public String getPassword() {
        return password;
    }
    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getAddressesTo() {
        return addressesTo;
    }

    public void setAddressesTo(List<String> addresses) {
        this.addressesTo = addresses;
    }    

    public List<String> getAddressesCC() {
        return addressesCC;
    }

    public void setAddressesCC(List<String> addressesCC) {
        this.addressesCC = addressesCC;
    }

    public List<String> getAddressesBCC() {
        return addressesBCC;
    }

    public void setAddressesBCC(List<String> addressesBCC) {
        this.addressesBCC = addressesBCC;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
}
