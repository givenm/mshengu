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
public final class BenefitType implements Serializable, Comparable<BenefitType> {

    @Id
    private String id;
    //Allowance, Travel Advance and Bonus. 
    private String benefitTypeName;
    private BigDecimal amount;
    @DBRef
    private Currency currency;

    private BenefitType() {
    }

    private BenefitType(Builder builder) {
        this.id = builder.id;
        this.benefitTypeName = builder.benefitTypeName;
        this.amount = builder.amount;
        this.currency = builder.currency;
    }

    @Override
    public int compareTo(BenefitType o) {
        return benefitTypeName.compareToIgnoreCase(o.benefitTypeName);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final BenefitType other = (BenefitType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final String benefitTypeName;
        private BigDecimal amount;
        private Currency currency;

        public Builder(String value) {
            this.benefitTypeName = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder amount(BigDecimal value) {
            this.amount = value;
            return this;
        }

        public Builder currency(Currency value) {
            this.currency = value;
            return this;
        }

        public BenefitType build() {
            return new BenefitType(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getBenefitTypeName() {
        return benefitTypeName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
