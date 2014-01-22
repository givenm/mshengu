/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.rest.api.resources;

/**
 *
 * @author given
 */
public class PublicContact{
    private String contactPersonFirstname;
    private String contactPersonLastname;
    private String company;
    private String email; 
    private String phone;
    private String faxNumber;
    private String hearAboutUs;
    private String message;  

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getHearAboutUs() {
        return hearAboutUs;
    }

    public void setHearAboutUs(String hearAboutUs) {
        this.hearAboutUs = hearAboutUs;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PublicContact{" + "contactPersonFirstname=" + contactPersonFirstname + ", contactPersonLastname=" + contactPersonLastname + ", company=" + company + ", email=" + email + ", phone=" + phone + ", faxNumber=" + faxNumber + ", hearAboutUs=" + hearAboutUs + ", message=" + message + '}';
    }

    public String getContactPersonFirstname() {
        return contactPersonFirstname;
    }

    public void setContactPersonFirstname(String contactPersonFirstname) {
        this.contactPersonFirstname = contactPersonFirstname;
    }

    public String getContactPersonLastname() {
        return contactPersonLastname;
    }

    public void setContactPersonLastname(String contactPersonLastname) {
        this.contactPersonLastname = contactPersonLastname;
    }
    
    
    
}