/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.products;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author boniface
 */
@Document
public final class UnitServiceLog implements Serializable, Comparable<UnitServiceLog> {

    @Id
    private String id;
    private Date serviceDate;
    private Date serviceTime;
    private Truck servicedBy;
    private String statusMessage;
    private boolean pumpOut;
    private boolean washBucket;
    private boolean suctionOut;
    private boolean scrubFloor;
    private boolean rechargeBacket;
    private boolean cleanPerimeter;
    private String siteName;
    private String incident;
    // private Set<UnitCleaningActivities> unitCleaningActivities;
    private String parentId;

    private UnitServiceLog() {
    }

    private UnitServiceLog(Builder builder) {
        this.id = builder.id;
        this.serviceDate = builder.serviceDate;
        this.serviceTime = builder.serviceTime;
        this.servicedBy = builder.servicedBy;
        //  this.unitCleaningActivities = builder.unitCleaningActivities;
        this.statusMessage = builder.statusMessage;

        this.pumpOut = builder.pumpOut;
        this.washBucket = builder.washBucket;
        this.suctionOut = builder.suctionOut;
        this.scrubFloor = builder.scrubFloor;
        this.rechargeBacket = builder.rechargeBacket;
        this.cleanPerimeter = builder.cleanPerimeter;

        this.incident = builder.incident;
        this.parentId = builder.parentId;
        this.siteName = builder.siteName;
    }

    @Override
    public int compareTo(UnitServiceLog o) {
        return serviceDate.compareTo(o.serviceDate);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final UnitServiceLog other = (UnitServiceLog) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the washBucket
     */
    public boolean isWashBucket() {
        return washBucket;
    }

    /**
     * @return the suctionOut
     */
    public boolean isSuctionOut() {
        return suctionOut;
    }

    /**
     * @return the scrubFloor
     */
    public boolean isScrubFloor() {
        return scrubFloor;
    }

    /**
     * @return the rechargeBacket
     */
    public boolean isRechargeBacket() {
        return rechargeBacket;
    }

    /**
     * @return the cleanPerimeter
     */
    public boolean isCleanPerimeter() {
        return cleanPerimeter;


    }

    /**
     * @return the siteName
     */
    public String getSiteName() {
        return siteName;
    }

    public static class Builder {

        private String id;
        private final Date serviceDate;
        private Date serviceTime;
        private Truck servicedBy;
        //  private Set<UnitCleaningActivities> unitCleaningActivities;
        private String statusMessage;
        private String parentId;
        private boolean pumpOut;
        private boolean washBucket;
        private boolean suctionOut;
        private boolean scrubFloor;
        private boolean rechargeBacket;
        private boolean cleanPerimeter;
        private String incident;
        private String siteName;

        public Builder(Date serviceDate) {
            this.serviceDate = serviceDate;
        }

        public Builder unitServiceLog(UnitServiceLog unitServiceLog) {
            this.id = unitServiceLog.getId();
//        this.serviceDate = unitServiceLog.getServiceDate();
            this.serviceTime = unitServiceLog.getServiceTime();
            this.servicedBy = unitServiceLog.getServicedBy();
            //  this.unitCleaningActivities = unitServiceLog.getunitCleaningActivities;
            this.statusMessage = unitServiceLog.getStatusMessage();
            this.pumpOut = unitServiceLog.isPumpOut();
            this.incident = unitServiceLog.getIncident();
            this.parentId = unitServiceLog.getParentId();
            this.siteName = unitServiceLog.getSiteName();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder siteName(String value) {
            this.siteName = value;
            return this;
        }

        public Builder parentId(String value) {
            this.parentId = value;
            return this;
        }

        public Builder incident(String value) {
            this.incident = value;
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

        public Builder statusMessage(String value) {
            this.statusMessage = value;
            return this;
        }

        public Builder pumpOut(boolean value) {
            this.pumpOut = value;
            return this;
        }

        public Builder washBucket(boolean value) {
            this.washBucket = value;
            return this;
        }

        public Builder suctionOut(boolean value) {
            this.suctionOut = value;
            return this;
        }

        public Builder scrubFloor(boolean value) {
            this.scrubFloor = value;
            return this;
        }

        public Builder rechargeBacket(boolean value) {
            this.rechargeBacket = value;
            return this;
        }

        public Builder cleanPerimeter(boolean value) {
            this.cleanPerimeter = value;
            return this;
        }

//        public Builder unitCleaningActivities(Set<UnitCleaningActivities> value) {
//            this.unitCleaningActivities = value;
//            return this;
//        }
        public UnitServiceLog build() {
            return new UnitServiceLog(this);
        }
    }

    public String getId() {
        return id;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    /**
     * @return the pumpOut
     */
    public boolean isPumpOut() {
        return pumpOut;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @return the incident
     */
    public String getIncident() {
        return incident;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @return the serviceTime
     */
    public Date getServiceTime() {
        return serviceTime;
    }

    public Truck getServicedBy() {
        return servicedBy;
    }
//    public Set<UnitCleaningActivities> getUnitCleaningActivities() {
//        return ImmutableSet.copyOf(unitCleaningActivities);
//    }

    /**
     * @return the isValid
     */
    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getDriverId() {
        if (!isNullObject(servicedBy)) {
            return servicedBy.getId();
        } else {
            return null;
        }
    }

    public String getDriverName() {
        if (!isNullObject(servicedBy)) {
            return servicedBy.getDriverName();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "UnitServiceLog{" + "id=" + id + ", serviceDate=" + serviceDate + ", serviceTime=" + serviceTime + ", servicedBy=" + servicedBy + ", statusMessage=" + statusMessage + ", pumpOut=" + pumpOut + ", washBucket=" + washBucket + ", suctionOut=" + suctionOut + ", scrubFloor=" + scrubFloor + ", rechargeBacket=" + rechargeBacket + ", cleanPerimeter=" + cleanPerimeter + ", siteName=" + siteName + ", incident=" + incident + ", parentId=" + parentId + '}';
    }
    
    
}
