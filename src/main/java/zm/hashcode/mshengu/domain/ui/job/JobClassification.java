/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.job;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class JobClassification implements Serializable, Comparable<JobClassification> {

    @Id
    private String id;
    private String currentTitle;
    private String oldTitle;
    private String oldCode;
    private String currentCode;
    private String codeConversion;
    private String comment;

    private JobClassification() {
    }

    private JobClassification(Builder builder) {
        this.id = builder.id;
        this.currentTitle = builder.currentTitle;
        this.oldTitle = builder.oldTitle;
        this.oldCode = builder.oldCode;
        this.currentCode = builder.currentCode;
        this.codeConversion = builder.codeConversion;
        this.comment = builder.comment;
    }

    @Override
    public int compareTo(JobClassification o) {
        return currentTitle.compareToIgnoreCase(o.currentTitle);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final JobClassification other = (JobClassification) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final String currentTitle;
        private String oldTitle;
        private String oldCode;
        private String currentCode;
        private String codeConversion;
        private String comment;

        public Builder(String value) {
            this.currentTitle = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder oldTitle(String value) {
            this.oldTitle = value;
            return this;
        }

        public Builder oldCode(String value) {
            this.oldCode = value;
            return this;
        }

        public Builder currentCode(String value) {
            this.currentCode = value;
            return this;
        }

        public Builder codeConversion(String value) {
            this.codeConversion = value;
            return this;
        }

        public Builder comment(String value) {
            this.comment = value;
            return this;
        }

        public JobClassification build() {
            return new JobClassification(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getCurrentTitle() {
        return currentTitle;
    }

    public String getOldTitle() {
        return oldTitle;
    }

    public String getOldCode() {
        return oldCode;
    }

    public String getCurrentCode() {
        return currentCode;
    }

    public String getCodeConversion() {
        return codeConversion;
    }

    public String getComment() {
        return comment;
    }
}
