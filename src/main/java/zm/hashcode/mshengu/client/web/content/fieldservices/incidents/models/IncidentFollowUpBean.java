/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.incidents.models;

import java.util.Date;

/**
 *
 * @author given
 */
public class IncidentFollowUpBean {
    private String id;
    private String parentId;
    private String staffId;
    private Date actionDate; 
    private Date resolvedDate; 
    private Date qualityAssuranceDate; 
    private String status;
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Date getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Date resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public Date getQualityAssuranceDate() {
        return qualityAssuranceDate;
    }

    public void setQualityAssuranceDate(Date qualityAssuranceDate) {
        this.qualityAssuranceDate = qualityAssuranceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
            
}
