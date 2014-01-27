/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.kpianalysis;

import java.io.Serializable;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Luckbliss
 */
public class KPIValues implements Serializable, Comparable<KPIValues> {

    @Id
    private String id;
    private double value;
    private String month;
    private int year;

    private KPIValues() {
    }

    private KPIValues(Builder builder) {
        this.id = builder.id;
        this.value = builder.value;
        this.year = builder.year;
        this.month = builder.month;
    }

    @Override
    public int compareTo(KPIValues o) {
        return month.compareToIgnoreCase(o.month);
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
        private final String month;
        private int year;

        public Builder(String value) {
            this.month = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder value(double value) {
            this.value = value;
            return this;
        }

        public Builder year(int value) {
            this.year = value;
            return this;
        }

        public Builder kPIValues(KPIValues values) {
            this.id = values.getId();
            this.value = values.getValue();
            this.year = values.getYear();
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

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
