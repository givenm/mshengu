/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.kpianalysis;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Luckbliss
 */
public class KPIValues implements Serializable, Comparable<KPIValues> {

    @Id
    private String id;
    private double value;
    private Date date;

    private KPIValues() {
    }

    private KPIValues(Builder builder) {
        this.id = builder.id;
        this.value = builder.value;
        this.date = builder.date;
    }

    @Override
    public int compareTo(KPIValues o) {
        return date.compareTo(o.date);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KPIValues)) {
            return false;
        }
        KPIValues other = (KPIValues) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private double value;
        private Date date;

        public Builder(Date value) {
            this.date = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder value(double value) {
            this.value = value;
            return this;
        }

        public Builder kPIValues(KPIValues values) {
            this.id = values.getId();
            this.value = values.getValue();
            return this;
        }

        public KPIValues build() {
            return new KPIValues(this);
        }
    }

    public String getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }
}
