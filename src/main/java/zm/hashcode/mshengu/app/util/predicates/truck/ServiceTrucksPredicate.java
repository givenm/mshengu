package zm.hashcode.mshengu.app.util.predicates.truck;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author boniface
 */
public class ServiceTrucksPredicate implements Predicate<Truck> {

    private String vehicleCategory1 = "Service Vehicle";    
    private String vehicleCategory2 = "Utility Vehicle";
    
//    public ServiceTrucksPredicate(String contractType){
//        this.vehicleCategory1 =  contractType;
//    }
    
    @Override
    public boolean apply(Truck truck) {
        if (vehicleCategory1.equalsIgnoreCase(getTruckCategory(truck))) {
            return true;
        }else if (vehicleCategory2.equalsIgnoreCase(getTruckCategory(truck))) {
            return true;
        } 
        return false;
    }

    private String getTruckCategory(Truck truck) {
        if (truck != null) {
            return truck.getCategoryName();
        }
        return null;

    }
    
}
