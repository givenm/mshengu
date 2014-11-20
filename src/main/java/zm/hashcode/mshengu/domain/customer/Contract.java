/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class Contract implements Serializable, Comparable<Contract> {

    @Id
    private String id;
    private Date startDate;
    private Date endDate;
    private Date dateofUpdate;
    private int numberOfUnits;
    private BigDecimal pricePerUnit;
    private String status;
    private Date dateofAction;
    @DBRef(lazy = true)
    private ContractType contractType;
    private String parentId;

    private Contract() {
    }

    private Contract(Builder builder) {
        this.id = builder.id;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.dateofUpdate = builder.dateofUpdate;
        this.status = builder.status;
        this.contractType = builder.contractType;
        this.numberOfUnits = builder.numberOfUnits;
        this.pricePerUnit = builder.pricePerUnit;
        this.parentId = builder.parentId;
        this.dateofAction = builder.dateofAction;
    }

    @Override
    public int compareTo(Contract o) {
        return -1 * dateofAction.compareTo(o.dateofAction);
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
        final Contract other = (Contract) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the numberOfUnits
     */
    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    /**
     * @return the pricePerUnit
     */
    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @return the dateofAction
     */
    public Date getDateofAction() {
        return dateofAction;
    }

    /**
     * @return the dateofUpdate
     */
    public Date getDateofUpdate() {
        return dateofUpdate;
    }

    public static class Builder {

        private String id;
        private  Date startDate;
        private Date endDate;
        private Date dateofUpdate;
        private String status;
        private ContractType contractType;
        private int numberOfUnits;
        private BigDecimal pricePerUnit;
        private String parentId;
        private final Date dateofAction;

        public Builder(Date value) {
            this.dateofAction = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder contract(Contract contract) {
            this.id = contract.getId();
//        this.startDate = contract.getStartDate();
            this.endDate = contract.getEndDate();
            this.status = contract.getStatus();
            this.dateofUpdate = contract.getDateofUpdate();
            this.contractType = contract.getContractType();
            this.numberOfUnits = contract.getNumberOfUnits();
            this.pricePerUnit = contract.getPricePerUnit();
            this.parentId = contract.getParentId();
            this.startDate = contract.getStartDate();
            return this;
        }

        public Builder endDate(Date value) {
            this.endDate = value;
            return this;
        }

        public Builder startDate(Date value) {
            this.startDate = value;
            return this;
        }

        public Builder dateofUpdate(Date value) {
            this.dateofUpdate = value;
            return this;
        }

        public Builder status(String value) {
            this.status = value;
            return this;
        }

        public Builder numberOfUnits(int value) {
            this.numberOfUnits = value;
            return this;
        }

        public Builder pricePerUnit(BigDecimal value) {
            this.pricePerUnit = value;
            return this;
        }

        public Builder contractType(ContractType value) {
            this.contractType = value;
            return this;
        }

        public Builder parentId(String value) {
            this.parentId = value;
            return this;
        }

        public Contract build() {
            return new Contract(this);
        }
    }

    public String getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public ContractType getContractType() {
        return contractType;
    }
//    public Set<Site> getSites() {
//        return ImmutableSet.copyOf(sites);
//    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getContractTypeName() {
        if (!isNullObject(contractType)) {
            return contractType.getType();
        } else {
            return null;
        }
    }

    public String getContractTypeId() {
        if (!isNullObject(contractType)) {
            return contractType.getId();
        } else {
            return null;
        }
    }
}
