/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.repository.fleet.OperatingCostRepository;
import zm.hashcode.mshengu.repository.fleet.TruckRepository;
import zm.hashcode.mshengu.services.fleet.OperatingCostService;

/**
 *
 * @author Ferox
 */
@Service
public class OperatingCostServiceImpl implements OperatingCostService {

    @Autowired
    private OperatingCostRepository repository;
    @Autowired
    private TruckRepository truckRepository;

    @Override
    public List<OperatingCost> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByTransactionDate()));
    }

    @Override
    public void persist(OperatingCost truck) {
        repository.save(truck);

    }

    @Override
    public void merge(OperatingCost truck) {
        if (truck.getId() != null) {
            repository.save(truck);
        }
    }

    @Override
    public OperatingCost findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(OperatingCost truck) {
        repository.delete(truck);
    }

    private Sort sortByTransactionDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "transactionDate"));
    }

    @Override
    public Truck findOperationallCostTruck(String operationalCostId) {
        List<Truck> truckList = ImmutableList.copyOf(truckRepository.findAll());
        for (Truck truck : truckList) {
            if (getTruckOperationalCost(truck.getOperatingCosts(), operationalCostId)) {
                return truck;
            }
        }
        return null;

    }

    private boolean getTruckOperationalCost(List<OperatingCost> operationalCostList, String serviceCostId) {

        if (operationalCostList != null) {
            for (OperatingCost serviceCost : operationalCostList) {
                if (serviceCost.getId().equalsIgnoreCase(serviceCostId)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<OperatingCost> getOperatingCostByTruckByMonth(Truck truck, Date month) {
        boolean found = false;
        boolean isTrigger = false;
        List<OperatingCost> operatingCosts = new ArrayList<>();
        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        List<OperatingCost> operatingCostList = new ArrayList<>() ;
        
        Truck truckk = null;
        if (truck.getId() != null) {
            truckk = truckRepository.findOne(truck.getId());
        }
        
        if(truckk != null){
            operatingCostList.addAll(truckk.getOperatingCosts());
        }

        String filteredMonthNumber = dateTimeFormatHelper.getMonthNumber(month);
        String filteredYearNumber = dateTimeFormatHelper.getYearNumber(month);

        for (OperatingCost operatingCost : operatingCostList) {
            if (dateTimeFormatHelper.getMonthNumber(operatingCost.getTransactionDate()).equalsIgnoreCase(filteredMonthNumber)
                    && dateTimeFormatHelper.getYearNumber(operatingCost.getTransactionDate()).equalsIgnoreCase(filteredYearNumber)) {
                operatingCosts.add(operatingCost);
            }
        }
        Collections.sort(operatingCosts);
        return operatingCosts;
    }

    @Override
    public List<OperatingCost> getOperatingCostBtnTwoDates(Date start, Date end) {
        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        Date resetStartDate = dateTimeFormatHelper.resetTimeAndMonthStart(start);
        Date resetEndDate = dateTimeFormatHelper.resetTimeAndMonthEnd(end);

        List<OperatingCost> operatingCostList = new ArrayList<>();
        List<OperatingCost> allOperatingCostList = findAll();

        for (OperatingCost operatingCost : allOperatingCostList) {
            Date transactionDate = dateTimeFormatHelper.resetTimeOfDate(operatingCost.getTransactionDate());
            if (transactionDate.compareTo(resetStartDate) == 0 || transactionDate.compareTo(resetEndDate) == 0 || (transactionDate.after(resetStartDate) && transactionDate.before(resetEndDate))) {
                operatingCostList.add(operatingCost);
            }
        }
        return operatingCostList;
    }
}
