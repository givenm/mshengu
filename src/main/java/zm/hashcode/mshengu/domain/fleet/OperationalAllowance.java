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
public class OperationalAllowance implements Serializable {

    @Id
    private String id;
    private BigDecimal operationalAllowance;
    private Date date;

    private OperationalAllowance() {
    }

    private OperationalAllowance(Builder builder) {
        this.id = builder.id;
        this.operationalAllowance = builder.operationalAllowance;
        this.date = builder.date;
    }

    /**
     * @return the setDate
     */
    public Date getDate() {
        return date;
    }

    public static class Builder {

        private String id;
        private final BigDecimal operationalAllowance;
        private Date date;

        public Builder(BigDecimal operationalAllowance) {
            this.operationalAllowance = operationalAllowance;

        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder date(Date value) {
            this.date = value;
            return this;
        }

        public OperationalAllowance build() {
            return new OperationalAllowance(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final OperationalAllowance other = (OperationalAllowance) obj;
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
     * @return the operationalAllowance
     */
    public BigDecimal getOperationalAllowance() {
        return operationalAllowance;
    }
}
