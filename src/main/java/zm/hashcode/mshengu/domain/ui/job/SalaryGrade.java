/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.job;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import org.springframework.data.annotation.Id;


import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.ui.util.Currency;

/**
 *
 * @author boniface
 */
@Document
public final class SalaryGrade implements Serializable, Comparable<SalaryGrade> {

    @Id
    private String id;
    private String gradeName;
    private String notes;
    private BigDecimal midAmount;
    private BigDecimal endAmount;
    private BigDecimal startAmount;
    private BigDecimal currentAmount;
    @DBRef
    private Currency currency;

    private SalaryGrade() {
    }

    private SalaryGrade(Builder builder) {
        this.id = builder.id;
        this.gradeName = builder.gradeName;
        this.notes = builder.notes;
        this.midAmount = builder.midAmount;
        this.endAmount = builder.endAmount;
        this.startAmount = builder.startAmount;
        this.currentAmount = builder.currentAmount;
        this.currency = builder.currency;
    }

    @Override
    public int compareTo(SalaryGrade o) {
        return gradeName.compareToIgnoreCase(o.gradeName);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final SalaryGrade other = (SalaryGrade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final String gradeName;
        private String notes;
        private BigDecimal midAmount;
        private BigDecimal endAmount;
        private BigDecimal startAmount;
        private BigDecimal currentAmount;
        private Currency currency;

        public Builder(String value) {
            this.gradeName = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder notes(String value) {
            this.notes = value;
            return this;
        }

        public Builder midAmount(BigDecimal value) {
            this.midAmount = value;
            return this;
        }

        public Builder endAmount(BigDecimal value) {
            this.endAmount = value;
            return this;
        }

        public Builder startAmount(BigDecimal value) {
            this.startAmount = value;
            return this;
        }

        public Builder currentAmount(BigDecimal value) {
            this.currentAmount = value;
            return this;
        }

        public Builder currency(Currency value) {
            this.currency = value;
            return this;
        }

        public SalaryGrade build() {
            return new SalaryGrade(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getGradeName() {
        return gradeName;
    }

    public String getNotes() {
        return notes;
    }

    public BigDecimal getMidAmount() {
        return midAmount;
    }

    public BigDecimal getEndAmount() {
        return endAmount;
    }

    public BigDecimal getStartAmount() {
        return startAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
