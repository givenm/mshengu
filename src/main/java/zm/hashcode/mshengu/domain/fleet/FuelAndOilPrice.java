/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.fleet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author geek
 */
@Document
public class FuelAndOilPrice implements Serializable {

    @Id
    private String id;
    private Date entryDate;
    private BigDecimal fuelPrice;
    private Date fuelEffectDate;
    private BigDecimal engineOilPrice;
    private Date engineOilEffectDate;

    public FuelAndOilPrice() {
    }

    private FuelAndOilPrice(Builder builder) {
        this.id = builder.id;
        this.entryDate = builder.entryDate;
        this.fuelPrice = builder.fuelPrice;
        this.fuelEffectDate = builder.fuelEffectDate;
        this.engineOilPrice = builder.engineOilPrice;
        this.engineOilEffectDate = builder.engineOilEffectDate;
    }

    public static class Builder {

        private String id;
        private final Date entryDate;
        private BigDecimal fuelPrice;
        private Date fuelEffectDate;
        private BigDecimal engineOilPrice;
        private Date engineOilEffectDate;

        public Builder(Date value) {
            this.entryDate = value;

        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder fuelPrice(BigDecimal value) {
            this.fuelPrice = value;
            return this;
        }

        public Builder fuelEffectDate(Date value) {
            this.fuelEffectDate = value;
            return this;
        }

        public Builder engineOilPrice(BigDecimal value) {
            this.engineOilPrice = value;
            return this;
        }

        public Builder engineOilEffectDate(Date value) {
            this.engineOilEffectDate = value;
            return this;
        }

        public FuelAndOilPrice build() {
            return new FuelAndOilPrice(this);
        }
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
        final FuelAndOilPrice other = (FuelAndOilPrice) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the entryDate
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * @return the fuelPrice
     */
    public BigDecimal getFuelPrice() {
        return fuelPrice;
    }

    /**
     * @return the fuelEffectDate
     */
    public Date getFuelEffectDate() {
        return fuelEffectDate;
    }

    /**
     * @return the engineOilPrice
     */
    public BigDecimal getEngineOilPrice() {
        return engineOilPrice;
    }

    /**
     * @return the engineOilEffectDate
     */
    public Date getEngineOilEffectDate() {
        return engineOilEffectDate;
    }
}
