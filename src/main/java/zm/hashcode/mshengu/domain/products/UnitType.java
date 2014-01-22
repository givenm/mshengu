/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.products;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
 @Document
public final class UnitType implements Serializable, Comparable<UnitType> {

    private String id;
    private String name;
    private BigDecimal unitPrice;

    private UnitType() {
    }

    private UnitType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.unitPrice = builder.unitPrice;
    }

    @Override
    public int compareTo(UnitType o) {
        return name.compareToIgnoreCase(o.name);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final UnitType other = (UnitType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the unitPrice
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public static class Builder {

        private String id;
        private final String name;
        private BigDecimal unitPrice;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder unitPrice(BigDecimal value) {
            this.unitPrice = value;
            return this;
        }

        public UnitType build() {
            return new UnitType(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
