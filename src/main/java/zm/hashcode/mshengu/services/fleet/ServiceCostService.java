/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet;

import java.util.List;
import zm.hashcode.mshengu.domain.fleet.ServiceCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Ferox
 */
public interface ServiceCostService {

    public List<ServiceCost> findAll();

    public void persist(ServiceCost truck);

    public void merge(ServiceCost truck);

    public ServiceCost findById(String id);

    public void delete(ServiceCost truck);

    public Truck findServiceCostTruck(String serviceCostId);
}
