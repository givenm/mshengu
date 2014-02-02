/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author geek
 */
public class TotalMaintenanceMileage implements Serializable, Comparable<TotalMaintenanceMileage> {

    private String id;
    private String vehicleNumber;
    private String numberPlate;
    private int truckMileagetotal = 0;

    @Override
    public int compareTo(TotalMaintenanceMileage o) {  // CompareTo for int and Integer types
        return this.truckMileagetotal - o.getTruckMileagetotal();
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
        final TotalMaintenanceMileage other = (TotalMaintenanceMileage) obj;
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
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the numberPlate
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * @param numberPlate the numberPlate to set
     */
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    /**
     * @return the truckMileagetotal
     */
    public int getTruckMileagetotal() {
        return truckMileagetotal;
    }

    /**
     * @param truckMileagetotal the truckMileagetotal to set
     */
    public void setTruckMileagetotal(int truckMileagetotal) {
        this.truckMileagetotal = truckMileagetotal;
    }

    /**
     * @return the vehicleNumber
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * @param vehicleNumber the vehicleNumber to set
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
