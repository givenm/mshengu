/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;
import zm.hashcode.mshengu.repository.procurement.AnnualDataFleetMaintenanceMileageRepository;
import zm.hashcode.mshengu.services.procurement.AnnualDataFleetMaintenanceMileageService;

/**
 *
 * @author Colin
 */
@Service
public class AnnualDataFleetMaintenanceMileageServiceImpl implements AnnualDataFleetMaintenanceMileageService {

    @Autowired
    private AnnualDataFleetMaintenanceMileageRepository repository;

    @Override
    public List<AnnualDataFleetMaintenanceMileage> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByTransactionDate()));
    }

    @Override
    public void persist(AnnualDataFleetMaintenanceMileage value) {
        repository.save(value);
    }

    @Override
    public void merge(AnnualDataFleetMaintenanceMileage value) {
        if (value.getId() != null) {
            repository.save(value);
        }
    }

    @Override
    public AnnualDataFleetMaintenanceMileage findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(AnnualDataFleetMaintenanceMileage value) {
        repository.delete(value);
    }

    private Sort sortByTransactionDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "transactionMonth"));
    }

    @Override
    public List<AnnualDataFleetMaintenanceMileage> getMonthlyMileageCostBtnTwoDates(Date start, Date end) {
        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        Date resetStartDate = dateTimeFormatHelper.resetTimeOfDate(start);
        Date resetEndDate = dateTimeFormatHelper.resetTimeOfDate(end);

        List<AnnualDataFleetMaintenanceMileage> annualDataFleetMileageList = new ArrayList<>();
        List<AnnualDataFleetMaintenanceMileage> allAnnualDataFleetMileageList = findAll();

        for (AnnualDataFleetMaintenanceMileage AnnualDataFleetMileage : allAnnualDataFleetMileageList) {
            Date transactionDate = dateTimeFormatHelper.resetTimeOfDate(AnnualDataFleetMileage.getTransactionMonth());
            if (transactionDate.compareTo(resetStartDate) == 0 || transactionDate.compareTo(resetEndDate) == 0 || (transactionDate.after(resetStartDate) && transactionDate.before(resetEndDate))) {
                annualDataFleetMileageList.add(AnnualDataFleetMileage);
            }
        }
        return annualDataFleetMileageList;
    }
}
