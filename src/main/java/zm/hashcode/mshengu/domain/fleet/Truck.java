/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.fleet;

import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.List;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.ui.util.PaymentMethod;

/**
 *
 * @author Ferox
 */
@Document
public class Truck implements Serializable, Comparable<Truck> {

    @Id
    private String id;
    private String numberPlate;
    private String vehicleNumber;
    private String vinNo;
    private String engineNo;
    private Integer startMileage;
    @DBRef
    private TruckCategory category;
    private String brand;
    private String model;
    private String tare;
    private String trackerGPS;
    private String radioSerialNumber;
    private String receiptNo;
    private Date paymentDate;
    private BigDecimal vehicleCost;
    @DBRef
    private PaymentMethod paymentMethod;
    private String description;
    private int registerYear;
    private Date dateOfExpire;
    private boolean isActive;
    @DBRef
    private Person driver;
    @DBRef
    private List<Site> routes;
    @DBRef(lazy = true)
    private List<OperatingCost> operatingCosts = new ArrayList<>();
    @DBRef(lazy = true)
    private List<ServiceCost> serviceCosts = new ArrayList<>();
    // NEW Attributes
    private Double operatingSpec; // in R/km
    private Double manufacturingSpec; // in Ltrs/Km
    private BigDecimal operationalAllowance; //
    private Integer fuelSpec;
    private Set<String> files;

    private Truck() {
    }

    private Truck(Builder builder) {
        this.id = builder.id;
        this.numberPlate = builder.numberPlate;
        this.vehicleNumber = builder.vehicleNumber;
        this.startMileage = builder.startMileage;
        this.vehicleNumber = builder.vehicleNumber;
        this.vinNo = builder.vinNo;
        this.engineNo = builder.engineNo;
        this.category = builder.category;
        this.brand = builder.brand;
        this.model = builder.model;
        this.tare = builder.tare;
        this.trackerGPS = builder.trackerGPS;
        this.radioSerialNumber = builder.radioSerialNumber;
        this.vehicleCost = builder.vehicleCost;
        this.description = builder.description;
        this.registerYear = builder.registerYear;
        this.dateOfExpire = builder.dateOfExpire;
        this.isActive = builder.isActive;
        this.driver = builder.driver;
        this.routes = builder.routes;
        this.operatingCosts = builder.operatingCosts;
        this.serviceCosts = builder.serviceCosts;
        // NEW Attributes
        this.operatingSpec = builder.operatingSpec; // in R/km
        this.manufacturingSpec = builder.manufacturingSpec; // in Ltrs/Km
        this.operationalAllowance = builder.operationalAllowance; //
        this.fuelSpec = builder.fuelSpec; // Litrs/100Km
        this.files = builder.files;
    }

    @Override
    public int compareTo(Truck o) {
        return vehicleNumber.compareTo(o.vehicleNumber);
    }
    public static Comparator<Truck> AscendingOrderVehicleNumberComparator = new Comparator<Truck>() {
        @Override
        public int compare(Truck truck1, Truck truck2) {
            //ascending order
            return truck1.getVehicleNumber().compareTo(truck2.getVehicleNumber());
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
        final Truck other = (Truck) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the vehicleCost
     */
    public BigDecimal getVehicleCost() {
        return vehicleCost;


    }

    /**
     * @return the registerYear
     */
    public int getRegisterYear() {
        return registerYear;
    }

    /**
     * @return the startMileage
     */
    public Integer getStartMileage() {
        return startMileage;
    }

    public Set<String> getFiles() {
        return files;
    }

    /**
     * @return the operatingSpec
     */
    public Double getOperatingSpec() {
        return operatingSpec;
    }

    /**
     * @return the manufacturingSpec
     */
    public Double getManufacturingSpec() {
        return manufacturingSpec;
    }

    /**
     * @return the operationalAllowance
     */
    public BigDecimal getOperationalAllowance() {
        return operationalAllowance;
    }

    /**
     * @return the fuelSpec
     */
    public Integer getFuelSpec() {
        return fuelSpec;
    }

    /**
     * @return the vehicleNumber
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public static class Builder {

        private String id;
        private final String numberPlate;
        private String vehicleNumber;
        private Integer startMileage;
        private String vinNo;
        private String engineNo;
        private TruckCategory category;
        private String brand;
        private String model;
        private String tare;
        private String trackerGPS;
        private String radioSerialNumber;
        private BigDecimal vehicleCost;
        private String description;
        private int registerYear;
        private Date dateOfExpire;
        private boolean isActive;
        private Person driver;
        private List<Site> routes = new ArrayList<>();
        private List<OperatingCost> operatingCosts = new ArrayList<>();
        private List<ServiceCost> serviceCosts = new ArrayList<>();
        private Double operatingSpec; // in R/km
        private Double manufacturingSpec; // in Ltrs/Km
        private BigDecimal operationalAllowance; //
        private Integer fuelSpec;
        private Set<String> files;

        public Builder(String numberPlate) {
            this.numberPlate = numberPlate;
        }

        public Builder truck(Truck truck) {
            this.id = truck.id;
//        this.numberPlate = truck.numberPlate;
            this.startMileage = truck.getStartMileage();
            this.vehicleNumber = truck.getVehicleNumber();
            this.vinNo = truck.getVinNo();
            this.engineNo = truck.getEngineNo();
            this.category = truck.getCategory();
            this.brand = truck.getBrand();
            this.model = truck.getModel();
            this.tare = truck.getTare();
            this.trackerGPS = truck.getTrackerGPS();
            this.radioSerialNumber = truck.getRadioSerialNumber();

            this.vehicleCost = truck.getVehicleCost();
            this.description = truck.getDescription();
            this.registerYear = truck.getRegisterYear();
            this.dateOfExpire = truck.getDateOfExpire();
            this.isActive = truck.isIsActive();
            this.driver = truck.getDriver();
            this.routes = truck.getRoutes();
            this.operatingCosts = truck.getOperatingCosts();
            this.serviceCosts = truck.getServiceCosts();

            this.operatingSpec = truck.getOperatingSpec(); // in R/km
            this.manufacturingSpec = truck.getManufacturingSpec(); // in Ltrs/Km
            this.operationalAllowance = truck.getOperationalAllowance(); //
            this.fuelSpec = truck.getFuelSpec(); // Litrs/100Km
            this.files = truck.getFiles();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder files(Set<String> value) {
            this.files = value;
            return this;
        }

        public Builder vehicleNumber(String value) {
            this.vehicleNumber = value;
            return this;
        }

        public Builder startMileage(Integer value) {
            this.startMileage = value;
            return this;
        }

        public Builder vinNo(String value) {
            this.vinNo = value;
            return this;
        }

        public Builder engineNo(String value) {
            this.engineNo = value;
            return this;
        }

        public Builder category(TruckCategory value) {
            this.category = value;
            return this;
        }

        public Builder brand(String value) {
            this.brand = value;
            return this;
        }

        public Builder model(String value) {
            this.model = value;
            return this;
        }

        public Builder trackerGPS(String value) {
            this.trackerGPS = value;
            return this;
        }

        public Builder tare(String value) {
            this.tare = value;
            return this;
        }

        public Builder radioSerialNumber(String value) {
            this.radioSerialNumber = value;
            return this;
        }

        public Builder vehicleCost(BigDecimal value) {
            this.vehicleCost = value;
            return this;
        }

        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public Builder registerYear(int value) {
            this.registerYear = value;
            return this;
        }

        public Builder dateOfExpire(Date value) {
            this.dateOfExpire = value;
            return this;
        }

        public Builder isActive(boolean value) {
            this.isActive = value;
            return this;
        }

        public Builder driver(Person value) {
            this.driver = value;
            return this;
        }

        public Builder routes(List<Site> value) {
            this.routes = value;
            return this;
        }

        public Builder operatingCosts(List<OperatingCost> value) {
            this.operatingCosts = value;
            return this;
        }

        public Builder serviceCosts(List<ServiceCost> value) {
            this.serviceCosts = value;
            return this;
        }

        public Builder operatingSpec(Double value) {
            this.operatingSpec = value;
            return this;
        }

        public Builder manufacturingSpec(Double value) {
            this.manufacturingSpec = value;
            return this;
        }

        public Builder operationalAllowance(BigDecimal value) {
            this.operationalAllowance = value;
            return this;
        }

        public Builder fuelSpec(Integer value) {
            this.fuelSpec = value;
            return this;
        }

        public Truck build() {
            return new Truck(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateOfExpire() {
        return dateOfExpire;
    }

    public Person getDriver() {
        return driver;
    }

    private List<Site> sortRoutes() {
        List<Site> routesList = new ArrayList<>();
        routesList.addAll(routes);
        Collections.sort(routesList);
        return routesList;
    }

    public List<Site> getRoutes() {
        if (routes != null) {
            return ImmutableList.copyOf(sortRoutes());
        } else {
            return ImmutableList.copyOf(routes);
        }
    }

    /**
     * @return the vinNo
     */
    public String getVinNo() {
        return vinNo;
    }

    /**
     * @return the engineNo
     */
    public String getEngineNo() {
        return engineNo;
    }

    /**
     * @return the category
     */
    public TruckCategory getCategory() {
        return category;
    }

    /**
     * @return the Tare
     */
    public String getTare() {
        return tare;
    }

    /**
     * @return the TrackerGPS
     */
    public String getTrackerGPS() {
        return trackerGPS;
    }

    /**
     * @return the radioSerialNumber
     */
    public String getRadioSerialNumber() {
        return radioSerialNumber;
    }

    /**
     * @return the receiptNo
     */
    public String getReceiptNo() {
        return receiptNo;
    }

    /**
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * @return the paymentMethod
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @return the isActive
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * @return the operatingCosts
     */
    public List<OperatingCost> getOperatingCosts() {
        return ImmutableList.copyOf(operatingCosts);
    }

    /**
     * @return the serviceCosts
     */
    public List<ServiceCost> getServiceCosts() {
        return ImmutableList.copyOf(serviceCosts);
//        return ImmutableSet.copyOf(serviceCosts);

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

    public String getCategoryId() {
        if (!isNullObject(category)) {
            return category.getId();
        } else {
            return null;
        }
    }

    public String getCategoryName() {
        if (!isNullObject(category)) {
            return category.getCategoryName();
        } else {
            return null;
        }
    }
}
