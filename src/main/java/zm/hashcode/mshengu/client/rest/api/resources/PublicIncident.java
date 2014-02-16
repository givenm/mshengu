/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.rest.api.resources;

/**
 *
 * @author given
 */
public class PublicIncident {

    private String clientName;
    private String contactPerson;
    private String email;
    private String contactNumber;
    private String incidentType;
    private String site;
    private String suburb;
    private String toiletType;
    private String remarks;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getToiletType() {
        return toiletType;
    }

    @Override
    public String toString() {
        return "PublicIncident{" + "clientName=" + clientName + ", contactPerson=" + contactPerson + ", contactNumber=" + contactNumber + ", incidentType=" + incidentType + ", site=" + site + ", suburb=" + suburb + ", toiletType=" + toiletType + ", remarks=" + remarks + '}';
    }

    public void setToiletType(String toiletType) {
        this.toiletType = toiletType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
