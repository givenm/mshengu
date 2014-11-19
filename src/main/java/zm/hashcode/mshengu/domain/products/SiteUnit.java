/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.products;

import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author boniface
 */
//Toilet
@Document
public final class SiteUnit implements Serializable, Comparable<SiteUnit> {

    @Id
    private String id;
    @Indexed(unique = true)
    private String unitId;
    private String description;
    private Status operationalStatus; // OUT ORDER
    @DBRef
    private UnitType unitType;
    @DBRef(lazy = true)
    private List<UnitServiceLog> unityLogs = new ArrayList<>();
    @DBRef(lazy = true)
    private List<UnitLocationLifeCycle> unitLocationLifeCycle = new ArrayList<>();
    private String parentId;
    private String siteName;

    private SiteUnit() {
    }

    private SiteUnit(Builder builder) {
        this.id = builder.id;
        this.unitId = builder.unitId;
        this.description = builder.description;
        this.operationalStatus = builder.operationalStatus;
        this.unitType = builder.unitType;
        this.unityLogs = builder.unityLogs;
        this.unitLocationLifeCycle = builder.unitLocationLifeCycle;
        this.parentId = builder.parentId;
        this.siteName = builder.siteName;

    }

    @Override
    public int compareTo(SiteUnit o) {
        return unitId.compareToIgnoreCase(o.unitId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final SiteUnit other = (SiteUnit) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the unitId
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @return the siteName
     */
    public String getSiteName() {
        if (getLatestDeployment() != null) {
            UnitLocationLifeCycle lastLifeCycle = getLatestDeployment();
            return lastLifeCycle.getSiteName();
        }
        return "";
    }

    /**
     * @return the unitID
     */
    public static class Builder {

        private String id;
        private String unitId;
        private boolean deployed;
        private String description;
        private Status operationalStatus;
        private final UnitType unitType;
        private List<UnitServiceLog> unityLogs;
        private List<UnitLocationLifeCycle> unitLocationLifeCycle;
        private String parentId;
        private String siteName;

        public Builder unitId(String value) {
            this.unitId = value;
            return this;
        }

        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder siteUnit(SiteUnit siteUnit) {

            if (siteUnit != null) {
                this.id = siteUnit.getId();
                this.unitId = siteUnit.getUnitId();
                this.description = siteUnit.getDescription();
                this.operationalStatus = siteUnit.getOperationalStatus();
//            this.unitType = siteUnit.getUnitType();
                this.unityLogs = siteUnit.getUnityLogs();
                this.unitLocationLifeCycle = siteUnit.getUnitLocationLifeCycle();
                this.parentId = siteUnit.getParentId();
                this.siteName = siteUnit.getSiteName();
            }
            return this;

        }

        public Builder operationalStatus(Status value) {
            this.operationalStatus = value;
            return this;
        }

        public Builder siteName(String value) {
            this.siteName = value;
            return this;
        }

        public Builder(UnitType value) {
            this.unitType = value;
//            return this;
        }

        public Builder unityLogs(List<UnitServiceLog> value) {
            this.unityLogs = value;
            return this;
        }

        public Builder unitLocationLifeCycle(List<UnitLocationLifeCycle> value) {
            this.unitLocationLifeCycle = value;
            return this;
        }

        public SiteUnit build() {
            return new SiteUnit(this);
        }
        /**
         * @return the deployment
         */
    }

    private List<UnitLocationLifeCycle> sortUnitLocationLifeCycle() {
        List<UnitLocationLifeCycle> sortedUnitLocationLifeCycleList = new ArrayList<>();
        sortedUnitLocationLifeCycleList.addAll(unitLocationLifeCycle);
        Collections.sort(sortedUnitLocationLifeCycleList);
        return sortedUnitLocationLifeCycleList;
    }

    public List<UnitLocationLifeCycle> getUnitLocationLifeCycle() {
        if (unitLocationLifeCycle != null) {
            return ImmutableList.copyOf(sortUnitLocationLifeCycle());
        } else {
            return ImmutableList.copyOf(unitLocationLifeCycle);
        }
    }

    public UnitLocationLifeCycle getLatestDeployment() {
        if (unitLocationLifeCycle.size() > 0) {
            return sortUnitLocationLifeCycle().get(0);
        } else {
            return null;
        }
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Status getOperationalStatus() {
        return operationalStatus;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    private List<UnitServiceLog> sortUnitServiceLog() {
        List<UnitServiceLog> sortedUnitServiceLogList = new ArrayList<>();
        sortedUnitServiceLogList.addAll(unityLogs);
        Collections.sort(sortedUnitServiceLogList);
        return sortedUnitServiceLogList;
    }

    public List<UnitServiceLog> getUnityLogs() {
        if (unitLocationLifeCycle != null) {
            return ImmutableList.copyOf(sortUnitServiceLog());
//            return ImmutableList.copyOf(unityLogs);
        } else {
            return ImmutableList.copyOf(unityLogs);
        }
    }

    public UnitServiceLog getLatestServiceLog() {
        if (unityLogs.size() > 0) {
            return sortUnitServiceLog().get(0);
        } else {
            return null;
        }
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getUnitTypeName() {
        if (!isNullObject(unitType)) {
            return unitType.getName();
        } else {
            return null;
        }
    }

    public String getUnitTypeId() {
        if (!isNullObject(unitType)) {
            return unitType.getId();
        } else {
            return null;
        }
    }

    public String getOperationalStatusName() {
        if (!isNullObject(operationalStatus)) {
            return operationalStatus.getName();
        } else {
            return null;
        }
    }

    public String getOperationalStatusId() {
        if (!isNullObject(operationalStatus)) {
            return operationalStatus.getId();
        } else {
            return null;
        }
    }
}
