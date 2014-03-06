/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.procurement.Impl;

import java.util.Calendar;
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

    final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageBetweenTwoDates(Date from, Date to) {
        Date fromDate = dateTimeFormatHelper.resetTimeAndMonthStart(from);
        Date toDate = dateTimeFormatHelper.resetTimeAndMonthEnd(to);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate);
        // Set time fields to last hour:minute:second:millisecond
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        //
        Query truckAnnualDataMileageListQuery = new Query();
        truckAnnualDataMileageListQuery.addCriteria(
                Criteria.where("transactionMonth").exists(true)
                .andOperator(Criteria.where("transactionMonth").gte(fromDate),
                Criteria.where("transactionMonth").lte(calendar.getTime())));

        /*
         List<AnnualDataFleetMaintenanceMileage> truckAnnualDataMileageList = mongoOperation.find(truckAnnualDataMileageListQuery, AnnualDataFleetMaintenanceMileage.class);
         System.out.println("AnnualDataFleetMaintenanceMileageRepository  - GENERAL QUERY - Start= " + from + " | To= " + to);
         if (truckAnnualDataMileageList.isEmpty()) {
         System.out.println("AnnualDataFleetMaintenanceMileageRepository  - GENERAL QUERY - NO MATCHING RECORDS FOUND");
         }

         for (AnnualDataFleetMaintenanceMileage annualDataMileage : truckAnnualDataMileageList) {
         System.out.println("AnnualDataFleetMaintenanceMileageRepository - GENERAL QUERY - Date= " + annualDataMileage.getTransactionMonth() + " | Mileage= " + annualDataMileage.getMonthlyMileage() + " | Truck= " + annualDataMileage.getTruckId());
         }
         System.out.println("--==--");
         */

        return mongoOperation.find(truckAnnualDataMileageListQuery, AnnualDataFleetMaintenanceMileage.class);
    }

    @Override
    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageByTruckBetweenTwoDates(Truck truck, Date from, Date to) {
        Date fromDate = dateTimeFormatHelper.resetTimeAndMonthStart(from);
        Date toDate = dateTimeFormatHelper.resetTimeAndMonthEnd(to);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate);
        // Set time fields to last hour:minute:second:millisecond
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return getAnnualDataMileages(truck, fromDate, calendar.getTime());
    }

    @Override
    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageByTruckForMonth(Truck truck, Date month) {

        Date from = dateTimeFormatHelper.resetTimeAndMonthStart(month);
        Date to = dateTimeFormatHelper.resetTimeAndMonthEnd(month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(to);
        // Set time fields to last hour:minute:second:millisecond
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return getAnnualDataMileages(truck, from, calendar.getTime());
    }

    private List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileages(Truck truck, Date from, Date to) {
        Query truckAnnualDataMileageListQuery = new Query();
        truckAnnualDataMileageListQuery.addCriteria(
                Criteria.where("truckId").is(truck.getId())
                .andOperator(Criteria.where("transactionMonth").gte(from),
                Criteria.where("transactionMonth").lte(to)));

        /*
         List<AnnualDataFleetMaintenanceMileage> truckAnnualDataMileageList = mongoOperation.find(truckAnnualDataMileageListQuery, AnnualDataFleetMaintenanceMileage.class);

         System.out.println("AnnualDataFleetMaintenanceMileageRepository  - TRUCK QUERY - Start= " + from + " | To= " + to + "  FOR truckId: " + truck.getId());
         if (truckAnnualDataMileageList.isEmpty()) {
         System.out.println("AnnualDataFleetMaintenanceMileageRepository  - TRUCK QUERY  - NO MATCHING RECORDS FOUND");
         }
         for (AnnualDataFleetMaintenanceMileage annualDataMileage : truckAnnualDataMileageList) {
         System.out.println("AnnualDataFleetMaintenanceMileageRepository - TRUCK QUERY - Date= " + annualDataMileage.getTransactionMonth() + " | Mileage= " + annualDataMileage.getMonthlyMileage() + " | Truck= " + annualDataMileage.getTruckId());
         }
         System.out.println("--==--");
         */
        return mongoOperation.find(truckAnnualDataMileageListQuery, AnnualDataFleetMaintenanceMileage.class);
    }
}
