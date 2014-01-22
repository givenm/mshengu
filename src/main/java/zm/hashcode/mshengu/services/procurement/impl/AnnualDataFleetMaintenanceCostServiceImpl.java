/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.repository.procurement.AnnualDataFleetMaintenanceCostRepository;
import zm.hashcode.mshengu.services.procurement.AnnualDataFleetMaintenanceCostService;

/**
 *
 * @author Colin
 */
@Service
public class AnnualDataFleetMaintenanceCostServiceImpl implements AnnualDataFleetMaintenanceCostService {

    @Autowired
    private AnnualDataFleetMaintenanceCostRepository repository;

    @Override
    public List<AnnualDataFleetMaintenanceCost> findAll() {
//        return ImmutableList.copyOf(repository.findAll(sortByTransactionDate()));
        return (List<AnnualDataFleetMaintenanceCost>) repository.findAll(sortByTransactionDate());
    }

    @Override
    public void persist(AnnualDataFleetMaintenanceCost value) {
        repository.save(value);
    }

    @Override
    public void merge(AnnualDataFleetMaintenanceCost value) {
        if (value.getId() != null) {
            repository.save(value);
        }
    }

    @Override
    public AnnualDataFleetMaintenanceCost findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(AnnualDataFleetMaintenanceCost value) {
        repository.delete(value);
    }

    private Sort sortByTransactionDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "transactionMonth"));
    }

    @Override
    public List<AnnualDataFleetMaintenanceCost> getMonthlyMaintenanceCostBtnTwoDates(Date start, Date end) {
        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        Date resetStartDate = dateTimeFormatHelper.resetTimeAndMonthStart(start);
        Date resetEndDate = dateTimeFormatHelper.resetTimeAndMonthEnd(end);

        List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostList = new ArrayList<>();
        List<AnnualDataFleetMaintenanceCost> allAnnualDataFleetMaintenanceCostList = findAll();

        for (AnnualDataFleetMaintenanceCost AnnualDataFleetMaintenanceCost : allAnnualDataFleetMaintenanceCostList) {
            Date transactionDate = dateTimeFormatHelper.resetTimeOfDate(AnnualDataFleetMaintenanceCost.getTransactionMonth());
            if (transactionDate.compareTo(resetStartDate) == 0 || transactionDate.compareTo(resetEndDate) == 0 || (transactionDate.after(resetStartDate) && transactionDate.before(resetEndDate))) {
                annualDataFleetMaintenanceCostList.add(AnnualDataFleetMaintenanceCost);
            }
        }
        return annualDataFleetMaintenanceCostList;
    }
}
