/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferox
 */
public final class StaffDetailsBean implements Serializable {

    private String id;
    
    private String firstname;
    
    private String lastname;
    private String othername;
    private Date dateofbirth;
    private String email;
    
    private String idNumber;
    
    private String mainNumber;
    private String otherNumber;
    private String streetAddress;
    private String postalCode;
    private boolean enabled;
    private Date permitExpire;
    
    private String countryId;
    
    private String jobPositionId;
    private String driversLicenceNo;
    private Date driversLicenceExpireDate;
    private Date pdpExpireDate;
    
    private Date endDate;
    
    private String terminateReason;
    private String terminateCode;
    private String terminateDate;
    private Date startDate;
    private String employeeDetailId;
    private String addressId;
    private String fileName;
    private String profileImage;
    private Set<String> files;
    private boolean requestor;
    
    private String employeeNumber;
    private boolean drivesCompanyCar;
    
    private String employementStatusId;
    private Date leaveEndDate;
    private Date leaveStartDate;

    public void setTerminateDate(String terminateDate) {
        this.terminateDate = terminateDate;
    }

    public String getTerminateDate() {
        return terminateDate;
    }

    public String getTerminateReason() {
        return terminateReason;
    }

    public void setTerminateReason(String terminateReason) {
        this.terminateReason = terminateReason;
    }

    public String getTerminateCode() {
        return terminateCode;
    }

    public void setTerminateCode(String terminateCode) {
        this.terminateCode = terminateCode;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public boolean isRequestor() {
        return requestor;
    }

    public void setRequestor(boolean requestor) {
        this.requestor = requestor;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Set<String> getFiles() {
        return files;
    }

    public void setFiles(Set<String> files) {
        this.files = files;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the permitExpire
     */
    public Date getPermitExpire() {
        return permitExpire;
    }

    /**
     * @param permitExpire the permitExpire to set
     */
    public void setPermitExpire(Date permitExpire) {
        this.permitExpire = permitExpire;
    }

    /**
     * @param driversLicenceNo the driversLicenceNo to set
     */
    public void setDriversLicenceNo(String driversLicenceNo) {
        this.driversLicenceNo = driversLicenceNo;
    }

    /**
     * @return the driversLicenceExpireDate
     */
    public Date getDriversLicenceExpireDate() {
        return driversLicenceExpireDate;
    }

    /**
     * @param driversLicenceExpireDate the driversLicenceExpireDate to set
     */
    public void setDriversLicenceExpireDate(Date driversLicenceExpireDate) {
        this.driversLicenceExpireDate = driversLicenceExpireDate;
    }

    /**
     * @return the pdpExpireDate
     */
    public Date getPdpExpireDate() {
        return pdpExpireDate;
    }

    /**
     * @param pdpExpireDate the pdpExpireDate to set
     */
    public void setPdpExpireDate(Date pdpExpireDate) {
        this.pdpExpireDate = pdpExpireDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the countryId
     */
    public String getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the jobPositionId
     */
    public String getJobPositionId() {
        return jobPositionId;
    }

    /**
     * @param jobPositionId the jobPositionId to set
     */
    public void setJobPositionId(String jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    /**
     * @return the driversLicenceNo
     */
    public String getDriversLicenceNo() {
        return driversLicenceNo;
    }

    /**
     * @return the mainNumber
     */
    public String getMainNumber() {
        return mainNumber;
    }

    /**
     * @param mainNumber the mainNumber to set
     */
    public void setMainNumber(String mainNumber) {
        this.mainNumber = mainNumber;
    }

    /**
     * @return the otherNumber
     */
    public String getOtherNumber() {
        return otherNumber;
    }

    /**
     * @param otherNumber the otherNumber to set
     */
    public void setOtherNumber(String otherNumber) {
        this.otherNumber = otherNumber;
    }

    /**
     * @return the streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return the employeeDetailId
     */
    public String getEmployeeDetailId() {
        return employeeDetailId;
    }

    /**
     * @param employeeDetailId the employeeDetailId to set
     */
    public void setEmployeeDetailId(String employeeDetailId) {
        this.employeeDetailId = employeeDetailId;
    }

    /**
     * @return the addressId
     */
    public String getAddressId() {
        return addressId;
    }

    /**
     * @param addressId the addressId to set
     */
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    /**
     * @return the drivesCompanyCar
     */
    public boolean isDrivesCompanyCar() {
        return drivesCompanyCar;
    }

    /**
     * @return the employementStatusId
     */
    public String getEmployementStatusId() {
        return employementStatusId;
    }

    /**
     * @return the leaveEndDate
     */
    public Date getLeaveEndDate() {
        return leaveEndDate;
    }

    /**
     * @return the leaveStartDate
     */
    public Date getLeaveStartDate() {
        return leaveStartDate;
    }

    /**
     * @param leaveEndDate the leaveEndDate to set
     */
    public void setLeaveEndDate(Date leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    /**
     * @param leaveStartDate the leaveStartDate to set
     */
    public void setLeaveStartDate(Date leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    /**
     * @param drivesCompanyCar the drivesCompanyCar to set
     */
    public void setDrivesCompanyCar(boolean drivesCompanyCar) {
        this.drivesCompanyCar = drivesCompanyCar;
    }

    /**
     * @param employementStatusId the employementStatusId to set
     */
    public void setEmployementStatusId(String employementStatusId) {
        this.employementStatusId = employementStatusId;
    }
    /**
     * @return the roleIds //
     */
//    public Set<Role> getRoleIds() {
//        return roleIds;
//    }
//
//    /**
//     * @param roleIds the roleIds to set
//     */
//    public void setRoleIds(Set<Role> roleIds) {
//        this.roleIds = roleIds;
//    }
}