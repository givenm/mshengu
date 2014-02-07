/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.people;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.ui.util.Country;
import zm.hashcode.mshengu.domain.ui.util.JobPosition;
import zm.hashcode.mshengu.domain.ui.util.Status;
import zm.hashcode.mshengu.domain.ui.util.Terminate;

/**
 *
 * @author Ferox
 */
@Document
public class EmployeeDetail implements Serializable, Comparable<EmployeeDetail> {

    @Id
    private String id;
    private String idNumber;
    private String mainNumber;
    private String otherNumber;
    private String email;
    private Date permitExpire;
    private Country country;
    private JobPosition jobPosition;
    private String driversLicenceNo;
    private Date driversLicenceExpireDate;
    private Date pdpExpireDate;
    private Date endDate;
    private Date startDate;
    private String profileImage;
    private Set<String> files;
    private String employeeNumber;
    private String payrollNumber;
    private Set<String> notices;
    private boolean drivesCompanyCar;
    private Status employementStatus;
    private Date leaveEndDate;
    private Date leaveStartDate;
    private Terminate terminate;
    private String terminated;

    private EmployeeDetail() {
    }

    private EmployeeDetail(EmployeeDetail.Builder builder) {
        this.id = builder.id;
        this.idNumber = builder.idNumber;
        this.employeeNumber = builder.employeeNumber;
        this.email = builder.email;
        this.mainNumber = builder.mainNumber;
        this.otherNumber = builder.otherNumber;
        this.permitExpire = builder.permitExpire;
        this.country = builder.country;
        this.jobPosition = builder.jobPosition;
        this.driversLicenceNo = builder.driversLicenceNo;
        this.driversLicenceExpireDate = builder.driversLicenceExpireDate;
        this.pdpExpireDate = builder.pdpExpireDate;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.leaveStartDate = builder.leaveStartDate;
        this.leaveEndDate = builder.leaveEndDate;
        this.profileImage = builder.profileImage;
        this.files = builder.files;
        this.payrollNumber = builder.payrollNumber;
        this.notices = builder.notices;
        this.drivesCompanyCar = builder.drivesCompanyCar;
        this.employementStatus = builder.employementStatus;
        this.terminate = builder.terminate;
        this.terminated = builder.terminated;
    }

    @Override
    public int compareTo(EmployeeDetail o) {
        return driversLicenceNo.compareToIgnoreCase(o.driversLicenceNo);
    }

    /**
     * @return the id
     */
    public String getTerminated() {
        return terminated;
    }

    public Terminate getTerminate() {
        return terminate;
    }

    public String getPayrollNumber() {
        return payrollNumber;
    }

    public Set<String> getNotices() {
        return notices;
    }

    public String getId() {
        return id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * @return the permitExpire
     */
    public Date getPermitExpire() {
        return permitExpire;
    }

    /**
     * @return the nationality
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @return the occupation
     */
    public JobPosition getJobPosition() {
        return jobPosition;
    }

    /**
     * @return the driversLicenceNo
     */
    public String getDriversLicenceNo() {
        return driversLicenceNo;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public Set<String> getFiles() {
        return files;
    }

    /**
     * @return the driversLicenceExpireDate
     */
    public Date getDriversLicenceExpireDate() {
        return driversLicenceExpireDate;
    }

    /**
     * @return the pdpExpireDate
     */
    public Date getPdpExpireDate() {
        return pdpExpireDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the mainNumber
     */
    public String getMainNumber() {
        return mainNumber;
    }

    /**
     * @return the otherNumber
     */
    public String getOtherNumber() {
        return otherNumber;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the drivesCompanyCar
     */
    public boolean isDrivesCompanyCar() {
        return drivesCompanyCar;
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
     * @return the employementStatus
     */
    public Status getEmployementStatus() {
        return employementStatus;
    }

    public static class Builder {

        private String id;
        private String idNumber;
        private String email;
        private String mainNumber;
        private String otherNumber;
        private Date permitExpire;
        private Country country;
        private JobPosition jobPosition;
        private String driversLicenceNo;
        private Date driversLicenceExpireDate;
        private Date pdpExpireDate;
        private Date endDate;
        private Date startDate;
        private Date leaveEndDate;
        private Date leaveStartDate;
        private String profileImage;
        private Set<String> files;
        private String employeeNumber;
        private String payrollNumber;
        private Set<String> notices;
        private boolean drivesCompanyCar;
        private Status employementStatus;
        private Terminate terminate;
        private String terminated;

        public Builder(String idNumber) {
            this.idNumber = idNumber;
        }

        public EmployeeDetail.Builder EmployeeDetails(EmployeeDetail employeeDetails) {
            this.id = employeeDetails.getId();
            this.idNumber = employeeDetails.getIdNumber();
            this.email = employeeDetails.getEmail();
            this.mainNumber = employeeDetails.getMainNumber();
            this.otherNumber = employeeDetails.getOtherNumber();
            this.permitExpire = employeeDetails.getPermitExpire();
            this.country = employeeDetails.getCountry();
            this.jobPosition = employeeDetails.getJobPosition();
            this.driversLicenceNo = employeeDetails.getDriversLicenceNo();
            this.driversLicenceExpireDate = employeeDetails.getDriversLicenceExpireDate();
            this.pdpExpireDate = employeeDetails.getPdpExpireDate();
            this.startDate = employeeDetails.getStartDate();
            this.endDate = employeeDetails.getEndDate();
            this.leaveStartDate = employeeDetails.getLeaveStartDate();
            this.leaveEndDate = employeeDetails.getLeaveEndDate();

            this.profileImage = employeeDetails.getProfileImage();
            this.files = employeeDetails.getFiles();
            this.employeeNumber = employeeDetails.getEmployeeNumber();
            this.payrollNumber = employeeDetails.getPayrollNumber();
            this.notices = employeeDetails.getNotices();
            this.drivesCompanyCar = employeeDetails.isDrivesCompanyCar();
            this.employementStatus = employeeDetails.getEmployementStatus();
            this.terminate = employeeDetails.getTerminate();
            this.terminated = employeeDetails.getTerminated();
            return this;
        }

        public EmployeeDetail.Builder id(String value) {
            this.id = value;
            return this;
        }

        public EmployeeDetail.Builder terminated(String value) {
            this.terminated = value;
            return this;
        }

        public EmployeeDetail.Builder terminate(Terminate value) {
            this.terminate = value;
            return this;
        }

        public EmployeeDetail.Builder payrollNumber(String value) {
            this.payrollNumber = value;
            return this;
        }

        public EmployeeDetail.Builder notices(Set<String> value) {
            this.notices = value;
            return this;
        }

        public EmployeeDetail.Builder employeeNumber(String value) {
            this.employeeNumber = value;
            return this;
        }

        public EmployeeDetail.Builder profileImage(String value) {
            this.profileImage = value;
            return this;
        }

        public EmployeeDetail.Builder files(Set<String> value) {
            this.files = value;
            return this;
        }

        public EmployeeDetail.Builder country(Country value) {
            this.country = value;
            return this;
        }

        public EmployeeDetail.Builder email(String value) {
            this.email = value;
            return this;
        }

        public EmployeeDetail.Builder mainNumber(String value) {
            this.mainNumber = value;
            return this;
        }

        public EmployeeDetail.Builder otherNumber(String value) {
            this.otherNumber = value;
            return this;
        }

        public EmployeeDetail.Builder permitExpire(Date value) {
            this.permitExpire = value;
            return this;
        }

        public EmployeeDetail.Builder jobPosition(JobPosition value) {
            this.jobPosition = value;
            return this;
        }

        public EmployeeDetail.Builder driversLicenceNo(String value) {
            this.driversLicenceNo = value;
            return this;
        }

        public EmployeeDetail.Builder driversLicenceExpireDate(Date value) {
            this.driversLicenceExpireDate = value;
            return this;
        }

        public EmployeeDetail.Builder pdpExpireDate(Date value) {
            this.pdpExpireDate = value;
            return this;
        }

        public EmployeeDetail.Builder startDate(Date value) {
            this.startDate = value;
            return this;
        }

        public EmployeeDetail.Builder endDate(Date value) {
            this.endDate = value;
            return this;
        }

        public EmployeeDetail.Builder leaveStartDate(Date value) {
            this.leaveStartDate = value;
            return this;
        }

        public EmployeeDetail.Builder leaveEndDate(Date value) {
            this.leaveEndDate = value;
            return this;
        }

        public EmployeeDetail.Builder drivesCompanyCar(boolean value) {
            this.drivesCompanyCar = value;
            return this;
        }

        public EmployeeDetail.Builder employementStatus(Status value) {
            this.employementStatus = value;
            return this;
        }

        public EmployeeDetail build() {
            return new EmployeeDetail(this);
        }
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getCountryName() {
        if (!isNullObject(country)) {
            return country.getName();
        } else {
            return null;
        }
    }

    public String getCountryNactional() {
        if (!isNullObject(country)) {
            return country.getNationality();
        } else {
            return null;
        }
    }

    public String getCountryId() {
        if (!isNullObject(country)) {
            return country.getId();
        } else {
            return null;
        }
    }

    public String getJobPositionName() {
        if (!isNullObject(jobPosition)) {
            return jobPosition.getName();
        } else {
            return null;
        }
    }

    public String getJobPositionId() {
        if (!isNullObject(jobPosition)) {
            return jobPosition.getId();
        } else {
            return null;
        }
    }

    public String getEmploymentStatusName() {
        if (!isNullObject(employementStatus)) {
            return employementStatus.getName();
        } else {
            return null;
        }
    }

    public String getEmploymentStatusId() {
        if (!isNullObject(employementStatus)) {
            return employementStatus.getId();
        } else {
            return null;
        }
    }
}