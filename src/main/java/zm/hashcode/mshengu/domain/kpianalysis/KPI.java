/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.kpianalysis;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Luckbliss
 */
@Document
public class KPI implements Serializable, Comparable<KPI> {

    @Id
    private String id;
    private String name;
    private String tab;
    @DBRef
    private List<KPIItem> items;

    private KPI() {
    }

    private KPI(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.items = builder.items;
        this.tab = builder.tab;
    }

    @Override
    public int compareTo(KPI o) {
        return name.compareToIgnoreCase(o.name);
    }

    public static class Builder {

        private String id;
        private final String name;
        private List<KPIItem> items;
        private String tab;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder tab(String value) {
            this.tab = value;
            return this;
        }
        
        public Builder kpi(KPI kpi) {
            this.id = kpi.getId();
            this.items = kpi.getItems();
            this.tab = kpi.getTab();
            return this;
        }

        public Builder items(List<KPIItem> value) {
            this.items = value;
            return this;
        }

        public KPI build() {
            return new KPI(this);
        }
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
        if (!(object instanceof KPI)) {
            return false;
        }
        KPI other = (KPI) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<KPIItem> getItems() {
        return items;
    }

    public String getTab() {
        return tab;
    }
}
