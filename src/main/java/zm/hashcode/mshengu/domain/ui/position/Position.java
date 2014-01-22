/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.position;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.office.Department;
import zm.hashcode.mshengu.domain.office.Office;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.job.Job;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author boniface
 */
@Document
public final class Position implements Serializable, Comparable<Position> {

    @Id
    private String id;
    private String positionCode;
    //Make it from Job and Position Title Nurse-Code
    private String positionTitle;
    private String description;
    private Date postionEntryDate;
    private Date positionEndDate;
    @DBRef
    private Person currentOccupant;
    @DBRef
    private PositionType positionType;
    @DBRef
    private Status positionStatus; //Vacant. RETIRED, OCCUPIED
    private String positionComments;
    @DBRef
    private Office office;
    private Set<String> subodinateIds;
    @DBRef
    private Position supervisor;
    @DBRef
    private Department department;
    @DBRef
    private Job job;
    private Set<PositionLifeCycle> positionLifeCycle;

    private Position() {
    }

    private Position(Builder builder) {
        this.id = builder.id;
        this.positionCode = builder.positionCode;
        this.positionTitle = builder.positionTitle;
        this.description = builder.description;
        this.postionEntryDate = builder.postionEntryDate;
        this.positionEndDate = builder.positionEndDate;
        this.currentOccupant = builder.currentOccupant;
        this.positionType = builder.positionType;
        this.positionStatus = builder.positionStatus;
        this.positionComments = builder.positionComments;
        this.office = builder.office;
        this.subodinateIds = builder.subodinateIds;
        this.supervisor = builder.supervisor;
        this.department = builder.department;
        this.job = builder.job;
        this.positionLifeCycle = builder.positionLifeCycle;


    }

    public static class Builder {

        private String id;
        private final String positionCode;
        private String positionTitle;
        private String description;
        private Date postionEntryDate;
        private Date positionEndDate;
        private Person currentOccupant;
        private PositionType positionType;
        private Status positionStatus;
        private String positionComments;
        private Office office;
        private Set<String> subodinateIds;
        private Position supervisor;
        private Department department;
        private Job job;
        private Set<PositionLifeCycle> positionLifeCycle;

        public Builder(String value) {
            this.positionCode = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder positionTitle(String value) {
            this.positionTitle = value;
            return this;
        }

        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public Builder postionEntryDate(Date value) {
            this.postionEntryDate = value;
            return this;
        }

        public Builder positionEndDate(Date value) {
            this.positionEndDate = value;
            return this;
        }

        public Builder currentOccupant(Person value) {
            this.currentOccupant = value;
            return this;
        }

        public Builder positionType(PositionType value) {
            this.positionType = value;
            return this;
        }

        public Builder positionStatus(Status value) {
            this.positionStatus = value;
            return this;
        }

        public Builder positionComments(String value) {
            this.positionComments = value;
            return this;
        }

        public Builder office(Office value) {
            this.office = value;
            return this;
        }

        public Builder subodinateIds(Set<String> value) {
            this.subodinateIds = value;
            return this;
        }

        public Builder supervisor(Position value) {
            this.supervisor = value;
            return this;
        }

        public Builder department(Department value) {
            this.department = value;
            return this;
        }

        public Builder job(Job value) {
            this.job = value;
            return this;
        }

        public Builder positionLifeCycle(Set<PositionLifeCycle> value) {
            this.positionLifeCycle = value;
            return this;
        }

        public Position build() {
            return new Position(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public String getDescription() {
        return description;
    }

    public Date getPostionEntryDate() {
        return postionEntryDate;
    }

    public Date getPositionEndDate() {
        return positionEndDate;
    }

    public Person getCurrentOccupant() {
        return currentOccupant;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public Status getPositionStatus() {
        return positionStatus;
    }

    public String getPositionComments() {
        return positionComments;
    }

    public Office getOffice() {
        return office;
    }

    public Set<String> getSubodinateIds() {
        return ImmutableSet.copyOf(subodinateIds);
    }

    public Position getSupervisor() {
        return supervisor;
    }

    public Department getDepartment() {
        return department;
    }

    public Job getJob() {
        return job;
    }

    public Set<PositionLifeCycle> getPositionLifeCycle() {
        return ImmutableSet.copyOf(positionLifeCycle);
    }

    @Override
    public int compareTo(Position o) {
        return positionCode.compareToIgnoreCase(o.positionCode);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Position other = (Position) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
