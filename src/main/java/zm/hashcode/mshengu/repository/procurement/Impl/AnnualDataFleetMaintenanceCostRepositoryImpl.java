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
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.repository.procurement.AnnualDataFleetMaintenanceCostRepositoryCustom;

/**
 *
 * @author Colin
 */
public class AnnualDataFleetMaintenanceCostRepositoryImpl implements AnnualDataFleetMaintenanceCostRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public List<AnnualDataFleetMaintenanceCost> getAnnualDataCostBetweenTwoDates(Date from, Date to) {
        Query truckAnnualDataCostListQuery = new Query();
        truckAnnualDataCostListQuery.addCriteria(
                Criteria.where("transactionMonth").exists(true)
                .andOperator(Criteria.where("transactionMonth").gte(from),
                Criteria.where("transactionMonth").lte(to)));

        /*
         List<AnnualDataFleetMaintenanceCost> truckAnnualDataCostList = mongoOperation.find(truckAnnualDataCostListQuery, AnnualDataFleetMaintenanceCost.class);

         System.out.println("AnnualDataFleetMaintenanceCostRepository  - GENERAL QUERY - Start= " + from + " | To= " + to);
         if (truckAnnualDataCostList.isEmpty()) {
         System.out.println("AnnualDataFleetMaintenanceCostRepository  - GENERAL QUERY - NO MATCHING RECORDS FOUND");
         }

         for (AnnualDataFleetMaintenanceCost annualDataCost : truckAnnualDataCostList) {
         System.out.println("AnnualDataFleetMaintenanceCostRepository - GENERAL QUERY  - Date= " + annualDataCost.getTransactionMonth() + " | Cost= " + annualDataCost.getMonthlyMaintenanceCost() + " | Truck= " + annualDataCost.getTruckId());
         }
         System.out.println("--==--");
         */
        return mongoOperation.find(truckAnnualDataCostListQuery, AnnualDataFleetMaintenanceCost.class);
    }

    @Override
    public List<AnnualDataFleetMaintenanceCost> getAnnualDataCostByTruckBetweenTwoDates(Truck truck, Date from, Date to) {
        return getAnnualDataCosts(truck, from, to);
    }

    @Override
    public List<AnnualDataFleetMaintenanceCost> getAnnualDataCostByTruckForMonth(Truck truck, Date month) {
        final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        Date from = dateTimeFormatHelper.resetTimeAndMonthStart(month);
        Date to = dateTimeFormatHelper.resetTimeAndMonthEnd(month);

        return getAnnualDataCosts(truck, from, to);
    }

    private List<AnnualDataFleetMaintenanceCost> getAnnualDataCosts(Truck truck, Date from, Date to) {
        Query truckAnnualDataCostListQuery = new Query();
        truckAnnualDataCostListQuery.addCriteria(
                Criteria.where("truckId").is(truck.getId())
                .andOperator(Criteria.where("transactionMonth").gte(from),
                Criteria.where("transactionMonth").lte(to)));

        /*
         List<AnnualDataFleetMaintenanceCost> truckAnnualDataCostList = mongoOperation.find(truckAnnualDataCostListQuery, AnnualDataFleetMaintenanceCost.class);

         System.out.println("AnnualDataFleetMaintenanceCostRepository  - TRUCK QUERY - Start= " + from + " | To= " + to + "  FOR truckId: " + truck.getId());
         if (truckAnnualDataCostList.isEmpty()) {
         System.out.println("AnnualDataFleetMaintenanceCostRepository  - TRUCK QUERY - NO MATCHING RECORDS FOUND");
         }

         for (AnnualDataFleetMaintenanceCost annualDataCost : truckAnnualDataCostList) {
         System.out.println("AnnualDataFleetMaintenanceCostRepository - TRUCK QUERY  - Date= " + annualDataCost.getTransactionMonth() + " | Cost= " + annualDataCost.getMonthlyMaintenanceCost() + " | Truck= " + annualDataCost.getTruckId());
         }
         System.out.println("--==--");
         */
        return mongoOperation.find(truckAnnualDataCostListQuery, AnnualDataFleetMaintenanceCost.class);
    }
}
