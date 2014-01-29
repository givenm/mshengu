/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.kpianalysis;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Luckbliss
 */
@Document
public class KPIItem implements Serializable, Comparable<KPIItem> {

    @Id
    private String id;
    private String shortDescription;
    private String detailedDescription;
    private String uom;
    private String measureType;
    private String editTargets;
    private String weighting;
    private int kpiNumber;
    private List<KPIValues> values;

    private KPIItem() {
    }

    private KPIItem(Builder builder) {
        this.id = builder.id;
        this.shortDescription = builder.shortDescription;
        this.detailedDescription = builder.detailedDescription;
        this.uom = builder.uom;
        this.measureType = builder.measureType;
        this.kpiNumber = builder.kpiNumber;
        this.editTargets = builder.editTargets;
        this.weighting = builder.weighting;
        this.values = builder.values;
    }

    @Override
    public int compareTo(KPIItem o) {
        return shortDescription.compareToIgnoreCase(o.shortDescription);
    }

    public static class Builder {

        private String id;
        private final String shortDescription;
        private String detailedDescription;
        private String uom;
        private String measureType;
        private int kpiNumber;
        private String editTargets;
        private String weighting;
        private List<KPIValues> values;

        public Builder(String value) {
            this.shortDescription = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder values(List<KPIValues> value) {
            this.values = value;
            return this;
        }

        public Builder editTargets(String value) {
            this.editTargets = value;
            return this;
        }

        public Builder weighting(String value) {
            this.weighting = value;
            return this;
        }

        public Builder kpiNumber(int value) {
            this.kpiNumber = value;
            return this;
        }

        public Builder detailedDescription(String value) {
            this.detailedDescription = value;
            return this;
        }

        public Builder kpiitem(KPIItem item) {
            this.id = item.getId();
            this.detailedDescription = item.getDetailedDescription();
            this.measureType = item.getMeasureType();
            this.uom = item.getUom();
            this.kpiNumber = item.getKpiNumber();
            this.editTargets = item.getEditTargets();
            this.weighting = item.getWeighting();
            return this;
        }

        public Builder uom(String value) {
            this.uom = value;
            return this;
        }

        public Builder measureType(String value) {
            this.measureType = value;
            return this;
        }

        public KPIItem build() {
            return new KPIItem(this);
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
        if (!(object instanceof KPIItem)) {
            return false;
        }
        KPIItem other = (KPIItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public int getKpiNumber() {
        return kpiNumber;
    }

    public String getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public String getUom() {
        return uom;
    }

    public String getMeasureType() {
        return measureType;
    }

    public String getEditTargets() {
        return editTargets;
    }

    public void setEditTargets(String editTargets) {
        this.editTargets = editTargets;
    }

    public String getWeighting() {
        return weighting;
    }

    public void setWeighting(String weighting) {
        this.weighting = weighting;
    }

    public List<KPIValues> getValues() {
        return values;
    }
}
