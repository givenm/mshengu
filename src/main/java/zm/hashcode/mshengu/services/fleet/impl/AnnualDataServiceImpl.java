/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet.impl;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.fleet.AnnualDataFleetFuel;
import zm.hashcode.mshengu.repository.fleet.AnnualDataFleetFuelRepository;
import zm.hashcode.mshengu.repository.fleet.TruckRepository;
import zm.hashcode.mshengu.services.fleet.AnnualDataFleetFuelService;

/**
 *
 * @author ColinWa
 */
@Service
public class AnnualDataServiceImpl implements AnnualDataFleetFuelService {

    @Autowired
    private AnnualDataFleetFuelRepository repository;
//    @Autowired
//    private TruckRepository truckRepository;

    @Override
    public List<AnnualDataFleetFuel> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByTransactionDate()));
    }

    @Override
    public void persist(AnnualDataFleetFuel annualData) {
        repository.save(annualData);
    }

    @Override
    public void merge(AnnualDataFleetFuel annualData) {
        if (annualData.getId() != null) {
            repository.save(annualData);
        }
    }

    @Override
    public AnnualDataFleetFuel findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(AnnualDataFleetFuel annualData) {
        repository.delete(annualData);
    }

    private Sort sortByTransactionDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "transactionDate"));
    }
//        @Override
//    public Truck findOperationallCostTruck(String operationalCostId) {
//        List<Truck> truckList = ImmutableList.copyOf(truckRepository.findAll());
//        for (Truck truck : truckList) {
//            if (getTruckOperationalCost(truck.getOperatingCosts(), operationalCostId)) {
//                return truck;
//            }
//        }
//        return null;
//    }
//
//           private boolean getTruckOperationalCost(List<OperatingCost> operationalCostList, String serviceCostId) {
//        boolean serviceCostFound = false;
//        if (operationalCostList != null) {
//            for (OperatingCost serviceCost : operationalCostList) {
//                if (serviceCost.getId().equalsIgnoreCase(serviceCostId)) {
//                    serviceCostFound = true;
//                    break;
//                }
//            }
//        }
//        return serviceCostFound;
//    }
}
