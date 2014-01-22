/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.products;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author boniface
 */
@Document
public final class SiteServiceLog implements Serializable, Comparable<SiteServiceLog> {

    @Id
    private String id;
    private Date serviceDate;
    private Date serviceTime;
    @DBRef
    private Truck servicedBy;
    private String status;
    private String serviceStatus;
    private String completionStatus;
    private int numberOfUnitsServiced;
    private int numberOfUnitsNotServiced;
    private String comment;
    private String parentId;

    private SiteServiceLog() {
    }

    private SiteServiceLog(Builder builder) {
        this.id = builder.id;
        this.serviceDate = builder.serviceDate;
        this.serviceTime = builder.serviceTime;
        this.servicedBy = builder.servicedBy;
        this.status = builder.status;
        this.serviceStatus = builder.serviceStatus;
        this.completionStatus = builder.completionStatus;
        this.numberOfUnitsServiced = builder.numberOfUnitsServiced;
        this.numberOfUnitsNotServiced = builder.numberOfUnitsNotServiced;
        this.comment = builder.comment;
        this.parentId = builder.parentId;
    }

    @Override
    public int compareTo(SiteServiceLog o) {
        return serviceDate.compareTo(o.serviceDate);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SiteServiceLog other = (SiteServiceLog) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @return the completionStatus
     */
    public String getCompletionStatus() {
        return completionStatus;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    public static class Builder {

        private String id;
        private final Date serviceDate;
        private Date serviceTime;
        private Truck servicedBy;
        private String status;
        private String serviceStatus;
        private String completionStatus;
        private int numberOfUnitsServiced;
        private int numberOfUnitsNotServiced;
        private String comment;
        private String parentId;

        public Builder(Date value) {
            this.serviceDate = value;
        }

        public Builder siteServiceLog(SiteServiceLog siteServiceLog) {
            this.id = siteServiceLog.getId();
//        this.serviceDate = siteServiceLog.getServiceDate();
            this.serviceTime = siteServiceLog.getServiceTime();
            this.servicedBy = siteServiceLog.getServicedBy();
            this.status = siteServiceLog.getStatus();
            this.serviceStatus = siteServiceLog.getServiceStatus();
            this.numberOfUnitsServiced = siteServiceLog.getNumberOfUnitsServiced();
            this.numberOfUnitsNotServiced = siteServiceLog.getNumberOfUnitsNotServiced();
            this.comment = siteServiceLog.getComment();
            this.parentId = siteServiceLog.getParentId();
            this.completionStatus = siteServiceLog.getCompletionStatus();
            return this;

        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder serviceTime(Date value) {
            this.serviceTime = value;
            return this;
        }

        public Builder servicedBy(Truck value) {
            this.servicedBy = value;
            return this;
        }

        public Builder status(String value) {
            this.status = value;
            return this;
        }

        public Builder serviceStatus(String value) {
            this.serviceStatus = value;
            return this;
        }

        public Builder completionStatus(String value) {
            this.completionStatus = value;
            return this;
        }

        public Builder numberOfUnitsServiced(int value) {
            this.numberOfUnitsServiced = value;
            return this;
        }

        public Builder numberOfUnitsNotServiced(int value) {
            this.numberOfUnitsNotServiced = value;
            return this;
        }

        public Builder comment(String value) {
            this.comment = value;
            return this;
        }

        public SiteServiceLog build() {
            return new SiteServiceLog(this);
        }
    }

    public String getId() {
        return id;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public Truck getServicedBy() {
        return servicedBy;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public int getNumberOfUnitsServiced() {
        return numberOfUnitsServiced;
    }

    public int getNumberOfUnitsNotServiced() {
        return numberOfUnitsNotServiced;
    }

    public String getComment() {
        return comment;
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getVehicleNumber() {
        if (!isNullObject(servicedBy)) {
            return servicedBy.getVehicleNumber();
        } else {
            return null;
        }
    }

    public String getTruckId() {
        if (!isNullObject(servicedBy)) {
            return servicedBy.getId();
        } else {
            return null;
        }
    }

    public String getNumberPlate() {
        if (!isNullObject(servicedBy)) {
            return servicedBy.getNumberPlate();
        } else {
            return null;
        }
    }
}
