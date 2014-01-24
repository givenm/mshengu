/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.fleet.ServiceCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.repository.fleet.ServiceCostRepository;
import zm.hashcode.mshengu.repository.fleet.TruckRepository;
import zm.hashcode.mshengu.services.fleet.ServiceCostService;

/**
 *
 * @author Ferox
 */
@Service
public class ServiceCostServiceImpl implements ServiceCostService {

    @Autowired
    private ServiceCostRepository repository;
    @Autowired
    private TruckRepository truckRepository;

    @Override
    public List<ServiceCost> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(ServiceCost truck) {

        repository.save(truck);

    }

    @Override
    public void merge(ServiceCost truck) {
        if (truck.getId() != null) {
            repository.save(truck);
        }
    }

    @Override
    public ServiceCost findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(ServiceCost truck) {
        repository.delete(truck);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "transactionDate"));
    }

    @Override
    public Truck findServiceCostTruck(String serviceCostId) {
        List<Truck> truckList = ImmutableList.copyOf(truckRepository.findAll());
        for (Truck truck : truckList) {
            if (getTruckServiceCost(truck.getServiceCosts(), serviceCostId)) {
                return truck;
            }
        }
        return null;

    }

    private boolean getTruckServiceCost(List<ServiceCost> serviceCostList, String serviceCostId) {
        boolean serviceCostFound = false;
        if (serviceCostList != null) {
            for (ServiceCost serviceCost : serviceCostList) {
                if (serviceCost.getId().equalsIgnoreCase(serviceCostId)) {
                    serviceCostFound = true;
                    break;
                }
            }
        }
        return serviceCostFound;
    }
}
