/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.procurement.Impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;
import zm.hashcode.mshengu.repository.procurement.AnnualDataFleetMaintenanceMileageRepositoryCustom;

/**
 *
 * @author Colin
 */
public class AnnualDataFleetMaintenanceMileageRepositoryImpl implements AnnualDataFleetMaintenanceMileageRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageBetweenTwoDates(Date from, Date to) {
        Query truckAnnualDataMileageListQuery = new Query();
        truckAnnualDataMileageListQuery.addCriteria(
                Criteria.where("transactionMonth").exists(true)
                .andOperator(Criteria.where("transactionMonth").gte(from),
                Criteria.where("transactionMonth").lte(to)));

        /*
         List<AnnualDataFleetMaintenanceMileage> truckAnnualDataMileageList = mongoOperation.find(truckAnnualDataMileageListQuery, AnnualDataFleetMaintenanceMileage.class);
         //        System.out.println("truckAnnualDataMileageList - "    + truckAnnualDataMileageList.toString());

         for (AnnualDataFleetMaintenanceMileage annualDataMileage : truckAnnualDataMileageList) {
         System.out.println("AnnualDataMileage Query List - " + annualDataMileage.getTransactionMonth() + " | " + annualDataMileage.getTransactionMonth());
         }
         */
        return mongoOperation.find(truckAnnualDataMileageListQuery, AnnualDataFleetMaintenanceMileage.class);
    }

    @Override
    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageByTruckBetweenTwoDates(Truck truck, Date from, Date to) {
        return getAnnualDataMileages(truck, from, to);
    }

    @Override
    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageByTruckForMonth(Truck truck, Date month) {
        final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        Date from = dateTimeFormatHelper.resetTimeAndMonthStart(month);
        Date to = dateTimeFormatHelper.resetTimeAndMonthEnd(month);

        return getAnnualDataMileages(truck, from, to);
    }

    private List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileages(Truck truck, Date from, Date to) {
        Query truckAnnualDataMileageListQuery = new Query();
        truckAnnualDataMileageListQuery.addCriteria(
                Criteria.where("truckId").is(truck.getId())
                .andOperator(Criteria.where("transactionMonth").gte(from),
                Criteria.where("transactionMonth").lte(to)));

        /*   */
        List<AnnualDataFleetMaintenanceMileage> truckAnnualDataMileageList = mongoOperation.find(truckAnnualDataMileageListQuery, AnnualDataFleetMaintenanceMileage.class);
//        System.out.println("truckAnnualDataMileageList - "    + truckAnnualDataMileageList.toString());

        for (AnnualDataFleetMaintenanceMileage annualDataMileage : truckAnnualDataMileageList) {
            System.out.println("AnnualDataMileage Query List - truckId= " + annualDataMileage.getTruckId() + " | date= " + annualDataMileage.getTransactionMonth());
        }

//        return mongoOperation.find(truckAnnualDataMileageListQuery, AnnualDataFleetMaintenanceMileage.class);
        return truckAnnualDataMileageList;
    }
}
