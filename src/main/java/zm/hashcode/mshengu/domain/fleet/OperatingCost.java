/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.fleet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author Ferox
 */
@Document
public class OperatingCost implements Serializable, Comparable<OperatingCost> {

    @Id
    private String id;
    private Date transactionDate;
    private String slipNo;
    private Integer speedometer;
    private Double fuelLitres;
    private BigDecimal fuelCost;
    private Double oilLitres;
    private BigDecimal oilCost;
    private BigDecimal randPerLitre;
    @DBRef(lazy = true)
    private Person driver;
    private String truckId;

    private OperatingCost() {
    }

    private OperatingCost(Builder builder) {
        this.id = builder.id;
        this.transactionDate = builder.transactionDate;
        this.slipNo = builder.slipNo;
        this.speedometer = builder.speedometer;
        this.fuelLitres = builder.fuelLitres;
        this.fuelCost = builder.fuelCost;
        this.oilLitres = builder.oilLitres;
        this.oilCost = builder.oilCost;
        this.randPerLitre = builder.randPerLitre;
        this.driver = builder.driver;
        this.truckId = builder.truckId;
    }

    public static class Builder {

        private String id;
        private final Date transactionDate;
        private String slipNo;
        private Integer speedometer;
        private Double fuelLitres;
        private BigDecimal fuelCost;
        private Double oilLitres;
        private BigDecimal oilCost;
        private BigDecimal randPerLitre;
        private Person driver;
        private String truckId;

        public Builder truckId(String value) {
            this.truckId = value;
            return this;
        }

        public Builder slipNo(String value) {
            this.slipNo = value;
            return this;
        }

        public Builder(Date transactionDate) {
            this.transactionDate = transactionDate;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder speedometer(Integer value) {
            this.speedometer = value;
            return this;
        }

        public Builder fuelLitres(Double value) {
            this.fuelLitres = value;
            return this;
        }

        public Builder fuelCost(BigDecimal value) {
            this.fuelCost = value;
            return this;
        }

        public Builder oilLitres(Double value) {
            this.oilLitres = value;
            return this;
        }

        public Builder oilCost(BigDecimal value) {
            this.oilCost = value;
            return this;
        }

        public Builder randPerLitre(BigDecimal value) {
            this.randPerLitre = value;
            return this;
        }

        public Builder driver(Person value) {
            this.driver = value;
            return this;
        }

        public OperatingCost build() {
            return new OperatingCost(this);
        }
    }

    @Override
    public int compareTo(OperatingCost o) {
        return transactionDate.compareTo(o.transactionDate);
    }
    public static Comparator<OperatingCost> DescOrderDateComparator = new Comparator<OperatingCost>() {
        @Override
        public int compare(OperatingCost operatingCost1, OperatingCost operatingCost2) {
            // Descending Order by Date
            return operatingCost2.getTransactionDate().compareTo(operatingCost1.getTransactionDate());
        }
    };
    public static Comparator<OperatingCost> DescOrderDateAscOrderTruckIdComparator = new Comparator<OperatingCost>() {
        @Override
        public int compare(OperatingCost operatingCost1, OperatingCost operatingCost2) {

            //Ascending order by Date
            int compareOne = operatingCost2.getTransactionDate().compareTo(operatingCost1.getTransactionDate());
//            System.out.println("(1)"
//                    + " TruckId= " + operatingCost1.getTruckId()
//                    + ", Date= " + operatingCost1.getTransactionDate()
//                    + " VS (2) TruckId= " + operatingCost2.getTruckId()
//                    + ", Date= " + operatingCost2.getTransactionDate());
            // Ascending Order by TruckId
            int compareTwo = operatingCost1.getTruckId().compareTo(operatingCost2.getTruckId());

            return ((compareOne == 0) ? compareTwo : compareOne);
        }
    };
    public static Comparator<OperatingCost> AscOrderDateAscOrderTruckIdComparator = new Comparator<OperatingCost>() {
        @Override
        public int compare(OperatingCost operatingCost1, OperatingCost operatingCost2) {

            //Ascending order by Date
            int compareOne = operatingCost1.getTransactionDate().compareTo(operatingCost2.getTransactionDate());
//            System.out.println("(1)"
//                    + " TruckId= " + operatingCost1.getTruckId()
//                    + ", Date= " + operatingCost1.getTransactionDate()
//                    + " VS (2) TruckId= " + operatingCost2.getTruckId()
//                    + ", Date= " + operatingCost2.getTransactionDate());
            // Ascending Order by TruckId
            int compareTwo = operatingCost1.getTruckId().compareTo(operatingCost2.getTruckId());

            return ((compareOne == 0) ? compareTwo : compareOne);
        }
    };

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
        final OperatingCost other = (OperatingCost) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @return the slipNo
     */
    public String getSlipNo() {
        return slipNo;
    }

    /**
     * @return the driver
     */
    public Person getDriver() {
        return driver;
    }

    /**
     * @return the speedometer
     */
    public Integer getSpeedometer() {
        return speedometer;
    }

    /**
     * @return the fuelLitres
     */
    public Double getFuelLitres() {
        return fuelLitres;
    }

    /**
     * @return the fuelCost
     */
    public BigDecimal getFuelCost() {
        return fuelCost;
    }

    /**
     * @return the oilLitres
     */
    public Double getOilLitres() {
        return oilLitres;
    }

    /**
     * @return the oilCost
     */
    public BigDecimal getOilCost() {
        return oilCost;
    }

    /**
     * @return the randPerLitre
     */
    public BigDecimal getRandPerLitre() {
        return randPerLitre;
    }

    public String getId() {
        return id;
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getDriverId() {
        if (!isNullObject(driver)) {
            return driver.getId();
        } else {
            return null;
        }
    }

    public String getDriverName() {
        if (!isNullObject(driver)) {
            return driver.getFirstname() + " " + driver.getLastname();
        } else {
            return null;
        }
    }

    /**
     * @return the truckId
     */
    public String getTruckId() {
        return truckId;
    }
}
