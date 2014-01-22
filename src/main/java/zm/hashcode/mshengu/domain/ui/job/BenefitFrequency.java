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
 * @author stud
 */
@Document
public final class BenefitFrequency implements Serializable, Comparable<BenefitFrequency> {

    @Id
    private String id;
    //Anual , Weekly, Monthly etc
    private String frequency;

    private BenefitFrequency() {
    }

    private BenefitFrequency(Builder builder) {
        this.id = builder.id;
        this.frequency = builder.frequency;
    }

    @Override
    public int compareTo(BenefitFrequency o) {
        return frequency.compareToIgnoreCase(o.frequency);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final BenefitFrequency other = (BenefitFrequency) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final String frequency;

        public Builder(String value) {
            this.frequency = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public BenefitFrequency build() {
            return new BenefitFrequency(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getFrequency() {
        return frequency;
    }
}
