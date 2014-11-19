/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.products;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Ferox
 */
@Document
public class SiteServiceContractLifeCycle implements Serializable, Comparable<SiteServiceContractLifeCycle> {

    @Id
    private String id;
    private Date dateofAction;
    private int expectedNumberOfUnits;
    private int numberOfUnits;
    @DBRef(lazy = true)
    private Set<SiteUnit> siteUnit = new HashSet<>();
    private int frequency;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;
    private String contractType;
    private String parentId;
    private int monthlyServices;
    private int weeklyServices;

    private SiteServiceContractLifeCycle() {
    }

    private SiteServiceContractLifeCycle(Builder builder) {
        this.id = builder.id;
        this.dateofAction = builder.dateofAction;
        this.expectedNumberOfUnits = builder.expectedNumberOfUnits;
        this.numberOfUnits = builder.numberOfUnits;
        this.siteUnit = builder.siteUnit;
        this.frequency = builder.frequency;
        this.monday = builder.monday;
        this.tuesday = builder.tuesday;
        this.wednesday = builder.wednesday;
        this.thursday = builder.thursday;
        this.friday = builder.friday;
        this.saturday = builder.saturday;
        this.sunday = builder.sunday;
        this.parentId = builder.parentId;
        this.monthlyServices = builder.monthlyServices;
        this.weeklyServices = builder.weeklyServices;
        this.contractType = builder.contractType;

    }

    @Override
    public int compareTo(SiteServiceContractLifeCycle o) {
        return getDateofAction().compareTo(o.getDateofAction());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.getId());
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
        final SiteServiceContractLifeCycle other = (SiteServiceContractLifeCycle) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the expectedNumberOfUnits
     */
    public int getExpectedNumberOfUnits() {
        return expectedNumberOfUnits;
    }

    /**
     * @return the monthlyServices
     */
    public int getMonthlyServices() {
        return monthlyServices = frequency * expectedNumberOfUnits * 4;
    }

    /**
     * @return the weeklyServices
     */
    public int getWeeklyServices() {
        return weeklyServices = frequency * expectedNumberOfUnits;
    }

    /**
     * @return the contractType
     */
    public String getContractType() {
        return contractType;
    }

    public static class Builder {

        private String id;
        private final Date dateofAction;
        private int numberOfUnits;
        private int expectedNumberOfUnits;
        private Set<SiteUnit> siteUnit = new HashSet<>();
        private int frequency;
        private boolean monday;
        private boolean tuesday;
        private boolean wednesday;
        private boolean thursday;
        private boolean friday;
        private boolean saturday;
        private boolean sunday;
        private String contractType;
        //private String status;
        private String parentId;
        private int monthlyServices;
        private Truck truck;
        private int weeklyServices;

        public Builder siteServiceContractLifeCycle(SiteServiceContractLifeCycle serviceContractLifeCycle) {
            this.id = serviceContractLifeCycle.getId();
//        this.dateofAction = siteUnit.dateofAction;
            
            this.contractType = serviceContractLifeCycle.getContractType();
            this.expectedNumberOfUnits = serviceContractLifeCycle.getExpectedNumberOfUnits();
            this.numberOfUnits = serviceContractLifeCycle.getNumberOfUnits();
            this.siteUnit = serviceContractLifeCycle.getSiteUnit();
            this.frequency = serviceContractLifeCycle.getFrequency();
            this.monday = serviceContractLifeCycle.isMonday();
            this.tuesday = serviceContractLifeCycle.isTuesday();
            this.wednesday = serviceContractLifeCycle.isWednesday();
            this.thursday = serviceContractLifeCycle.isThursday();
            this.friday = serviceContractLifeCycle.isFriday();
            this.saturday = serviceContractLifeCycle.isSaturday();
            this.sunday = serviceContractLifeCycle.isSunday();
            this.parentId = serviceContractLifeCycle.getParentId();
            this.weeklyServices = serviceContractLifeCycle.getWeeklyServices();
            return this;
        }

        public Builder(Date dateofAction) {
            this.dateofAction = dateofAction;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder contractType(String value) {
            this.contractType = value;
            return this;
        }

        public Builder numberOfUnits(int value) {
            this.numberOfUnits = value;
            return this;
        }

        public Builder expectedNumberOfUnits(int value) {
            this.expectedNumberOfUnits = value;
            return this;
        }

        public Builder siteUnit(Set<SiteUnit> value) {
            this.siteUnit = value;
            return this;
        }

        private Builder frequency() {
            this.frequency = booleanToInt(this.monday) + booleanToInt(this.tuesday) + booleanToInt(this.wednesday)
                    + booleanToInt(this.thursday) + booleanToInt(this.friday) + booleanToInt(this.saturday)
                    + booleanToInt(this.sunday);
            return this;
        }

        private Builder monthlySerices() {
            this.monthlyServices = this.expectedNumberOfUnits * this.frequency * 4;
            return this;
        }

        private Builder weeklyServices() {
            this.weeklyServices = this.expectedNumberOfUnits * this.frequency;
            return this;
        }

        public Builder monday(boolean value) {
            this.monday = value;
            return this;
        }

        public Builder tuesday(boolean value) {
            this.tuesday = value;
            return this;
        }

        public Builder wednesday(boolean value) {
            this.wednesday = value;
            return this;
        }

        public Builder thursday(boolean value) {
            this.thursday = value;
            return this;
        }

        public Builder friday(boolean value) {
            this.friday = value;
            return this;
        }

        public Builder saturday(boolean value) {
            this.saturday = value;
            return this;
        }

        public Builder sunday(boolean value) {
            this.sunday = value;
            return this;
        }

        public Builder parentId(String value) {
            this.parentId = value;
            return this;
        }

        public Builder truck(Truck value) {
            this.truck = value;
            return this;
        }

        public SiteServiceContractLifeCycle build() {
            frequency();
            weeklyServices();
            monthlySerices();
            return new SiteServiceContractLifeCycle(this);
        }

   
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the dateofAction
     */
    public Date getDateofAction() {
        return dateofAction;
    }

    /**
     * @return the numberOfUnits
     */
    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    
    /**
     * @return the siteUnit
     */
    public Set<SiteUnit> getSiteUnit() {
        if (siteUnit != null) {
            List<SiteUnit> siteUnitListSorted = new ArrayList<>();
            siteUnitListSorted.addAll(siteUnit);
            Collections.sort(siteUnitListSorted);
            return ImmutableSet.copyOf(siteUnitListSorted);
        } else {
            return ImmutableSet.copyOf(siteUnit);
        }
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @return the monday
     */
    public boolean isMonday() {
        return monday;
    }

    /**
     * @return the tuesday
     */
    public boolean isTuesday() {
        return tuesday;
    }

    /**
     * @return the wednesday
     */
    public boolean isWednesday() {
        return wednesday;
    }

    /**
     * @return the thursday
     */
    public boolean isThursday() {
        return thursday;
    }

    /**
     * @return the friday
     */
    public boolean isFriday() {
        return friday;
    }

    /**
     * @return the saturday
     */
    public boolean isSaturday() {
        return saturday;
    }

    /**
     * @return the sunday
     */
    public boolean isSunday() {
        return sunday;
    }

    private static int booleanToInt(boolean b) {
        return b ? 1 : 0;
    }

    public String visitDays() {
        String visitDays = "";
        int counter = 0;

        if (isMonday()) {
            counter += 1;
            visitDays += "Mon" + dotOrComma(counter);
        }

        if (isTuesday()) {
            counter += 1;
            visitDays += "Tue" + dotOrComma(counter);
        }

        if (isWednesday()) {
            counter += 1;
            visitDays += "Wed" + dotOrComma(counter);
        }
        if (isThursday()) {
            counter += 1;
            visitDays += "Thu" + dotOrComma(counter);
        }

        if (isFriday()) {
            counter += 1;
            visitDays += "Fri" + dotOrComma(counter);
        }

        if (isSaturday()) {
            counter += 1;
            visitDays += "Sat" + dotOrComma(counter);
        }

        if (isSunday()) {
            counter += 1;
            visitDays += "Sun" + dotOrComma(counter);
        }
        return visitDays;
    }

    private String dotOrComma(int counter) {

        if (counter < getFrequency()) {
            return ", ";
        } else {
            return ". ";
        }
    }
}
//  return ImmutableSet.copyOf(daysOfWeek);

