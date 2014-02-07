/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement.impl;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.MaintenanceSpendBySupplier;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.repository.procurement.MaintenanceSpendBySupplierRepository;
import zm.hashcode.mshengu.services.procurement.MaintenanceSpendBySupplierService;

/**
 *
 * @author Colin
 */
@Service
public class MaintenanceSpendBySupplierServiceImpl implements MaintenanceSpendBySupplierService {

    @Autowired
    private MaintenanceSpendBySupplierRepository repository;

    @Override
    public List<MaintenanceSpendBySupplier> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByTransactionDate()));
    }

    @Override
    public void persist(MaintenanceSpendBySupplier value) {
        repository.save(value);
    }

    @Override
    public void merge(MaintenanceSpendBySupplier value) {
        if (value.getId() != null) {
            repository.save(value);
        }
    }

    @Override
    public MaintenanceSpendBySupplier findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(MaintenanceSpendBySupplier value) {
        repository.delete(value);
    }

    private Sort sortByTransactionDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "transactionMonth"));
    }

//    @Override
//    public List<MaintenanceSpendBySupplier> getMonthlyMileageCostBtnTwoDates(Date start, Date end) {
//        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
//        Date resetStartDate = dateTimeFormatHelper.resetTimeOfDate(start);
//        Date resetEndDate = dateTimeFormatHelper.resetTimeOfDate(end);
//
//        List<MaintenanceSpendBySupplier> annualDataFleetMileageList = new ArrayList<>();
//        List<MaintenanceSpendBySupplier> allAnnualDataFleetMileageList = findAll();
//
//        for (MaintenanceSpendBySupplier AnnualDataFleetMileage : allAnnualDataFleetMileageList) {
//            Date transactionDate = dateTimeFormatHelper.resetTimeOfDate(AnnualDataFleetMileage.getTransactionDate());
//            if (transactionDate.compareTo(resetStartDate) == 0 || transactionDate.compareTo(resetEndDate) == 0 || (transactionDate.after(resetStartDate) && transactionDate.before(resetEndDate))) {
//                annualDataFleetMileageList.add(AnnualDataFleetMileage);
//            }
//        }
//        return annualDataFleetMileageList;
//    }
    @Override
    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBetweenTwoDates(Date from, Date to) {
        return repository.getMaintenanceSpendBetweenTwoDates(from, to);
    }

    @Override
    public List<MaintenanceSpendBySupplier> getMaintenanceSpendByTruckBetweenTwoDates(Truck truck, Date from, Date to) {
        return repository.getMaintenanceSpendByTruckBetweenTwoDates(truck, from, to);
    }

    @Override
    public List<MaintenanceSpendBySupplier> getMaintenanceSpendByTruckForMonth(Truck truck, Date month) {
        return repository.getMaintenanceSpendByTruckForMonth(truck, month);
    }

    @Override
    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBySupplierBetweenTwoDates(ServiceProvider serviceProvider, Date from, Date to) {
        return repository.getMaintenanceSpendBySupplierBetweenTwoDates(serviceProvider, from, to);
    }

    @Override
    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBySupplierForMonth(ServiceProvider serviceProvider, Date month) {
        return repository.getMaintenanceSpendBySupplierForMonth(serviceProvider, month);
    }
}
