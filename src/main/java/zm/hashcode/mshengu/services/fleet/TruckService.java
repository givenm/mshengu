/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet;

import java.util.List;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Luckbliss
 */
public interface TruckService {

    public List<Truck> findAll();

    public void persist(Truck truck);

    public void merge(Truck truck);

    public Truck findById(String id);

    public void delete(Truck truck);

    public Truck findByVehicleNumber(String vehicleNumber);
    
    public List<Truck> findAllServiceAndUtilityVehicles();
    
    public Truck findBySiteName(String siteName);
}
