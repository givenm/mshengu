/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.incidents.models;

import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferox
 */
public class IncidentBean {

    private String id;
    private Date actionDate;
    private Date resolvedDate;
    private Date qualityAssuranceDate;
    
    private String refNumber;
    
    private String customer;//(ongoing outstanding, resolved) 
    
    private String contactPerson;
    
    private String contactNumber;
    private String site;
    private String suburb;
    
    private String toiletType;
    private boolean closed;
    
    private String incidentType;
//    private Set<IncidentAction> incidentAction = new HashSet<>();
    private String serviceProvider;
    private String comment;
    
    private String mailNotifications;
    
    private String status;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the actionDate
     */
    public Date getActionDate() {
        return actionDate;
    }

    /**
     * @return the resolvedDate
     */
    public Date getResolvedDate() {
        return resolvedDate;
    }

    /**
     * @return the qualityAssuranceDate
     */
    public Date getQualityAssuranceDate() {
        return qualityAssuranceDate;
    }

    /**
     * @param qualityAssuranceDate the qualityAssuranceDate to set
     */
    public void setQualityAssuranceDate(Date qualityAssuranceDate) {
        this.qualityAssuranceDate = qualityAssuranceDate;
    }

    /**
     * @param resolvedDate the resolvedDate to set
     */
    public void setResolvedDate(Date resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    /**
     * @param actionDate the actionDate to set
     */
    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * @return the contactPerson
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * @param contactPerson the contactPerson to set
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site the site to set
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * @return the suburb
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * @param suburb the suburb to set
     */
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    /**
     * @return the toiletType
     */
    public String getToiletType() {
        return toiletType;
    }

    /**
     * @param toiletType the toiletType to set
     */
    public void setToiletType(String toiletType) {
        this.toiletType = toiletType;
    }

    /**
     * @return the closed
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * @param closed the closed to set
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * @return the incidentType
     */
    public String getIncidentType() {
        return incidentType;
    }

    /**
     * @param incidentType the incidentType to set
     */
    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }
//
//    /**
//     * @return the incidentAction
//     */
//    public Set<IncidentAction> getIncidentAction() {
//        return incidentAction;
//    }
//
//    /**
//     * @param incidentAction the incidentAction to set
//     */
//    public void setIncidentAction(Set<IncidentAction> incidentAction) {
//        this.incidentAction = incidentAction;
//    }

    /**
     * @return the serviceProvider
     */
    public String getServiceProvider() {
        return serviceProvider;
    }

    /**
     * @param serviceProvider the serviceProvider to set
     */
    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the mailNotifications
     */
    public String getMailNotifications() {
        return mailNotifications;
    }

    /**
     * @param mailNotifications the mailNotifications to set
     */
    public void setMailNotifications(String mailNotifications) {
        this.mailNotifications = mailNotifications;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the refNumber
     */
    public String getRefNumber() {
        return refNumber;
    }

    /**
     * @param refNumber the refNumber to set
     */
    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }
}
