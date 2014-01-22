/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.job;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.ui.position.Position;

/**
 *
 * @author boniface
 */
@Document
public final class Job implements Serializable, Comparable<Job> {

    @Id
    private String id;
    private String title;
    private String code;
    private String description;
    @DBRef
    private SalaryGrade salaryGrade;
    @DBRef
    private JobClassification jobClassification;
    @DBRef
    private Set<Position> positions;

    private Job() {
    }

    private Job(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.code = builder.code;
        this.description = builder.description;
        this.salaryGrade = builder.salaryGrade;
        this.jobClassification = builder.jobClassification;
        this.positions = builder.positions;
    }

    public static class Builder {

        private String id;
        private final String title;
        private String code;
        private String description;
        private SalaryGrade salaryGrade;
        private JobClassification jobClassification;
        private Set<Position> positions;

        public Builder(String value) {
            this.title = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder code(String value) {
            this.code = value;
            return this;
        }

        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public Builder salaryGrade(SalaryGrade value) {
            this.salaryGrade = value;
            return this;
        }

        public Builder jobClassification(JobClassification value) {
            this.jobClassification = value;
            return this;
        }

        public Builder jobClassification(Set<Position> value) {
            this.positions = value;
            return this;
        }

        public Job build() {
            return new Job(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public SalaryGrade getSalaryGrade() {
        return salaryGrade;
    }

    public JobClassification getJobClassification() {
        return jobClassification;
    }

    public Set<Position> getPositions() {
        return ImmutableSet.copyOf(positions);
    }

    @Override
    public int compareTo(Job o) {
        return title.compareToIgnoreCase(o.title);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Job other = (Job) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
