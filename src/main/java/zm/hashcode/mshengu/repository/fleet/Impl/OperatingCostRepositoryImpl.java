/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.fleet.Impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.repository.fleet.OperatingCostRepositoryCustom;

/**
 *
 * @author Colin
 */
public class OperatingCostRepositoryImpl implements OperatingCostRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public List<OperatingCost> findOperatingCostBetweenTwoDates(Date from, Date to) {
        Query operatingCostListQuery = new Query();
        operatingCostListQuery.addCriteria(
                Criteria.where("transactionDate").exists(true)
                .andOperator(Criteria.where("transactionDate").gte(from),
                Criteria.where("transactionDate").lte(to)));

        /*
         List<OperatingCost> operatingCostList = mongoOperation.find(operatingCostListQuery, OperatingCost.class);
         System.out.println("operatingCostListQuery - "
         + operatingCostList.toString());

         for (OperatingCost operatingCost : operatingCostList) {
         System.out.println("operatingCostListQuery - " + operatingCost.getTransactionDate() + " | " + operatingCost.getSpeedometer());
         }
         */
        return mongoOperation.find(operatingCostListQuery, OperatingCost.class);
    }

    @Override
    public List<OperatingCost> getOperatingCostByTruckByMonth(Truck truck, Date month) {
        final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        Date from = dateTimeFormatHelper.resetTimeAndMonthStart(month);
        Date to = dateTimeFormatHelper.resetTimeAndMonthEnd(month);

        return getOperatingCosts(truck, from, to);
    }

    @Override
    public List<OperatingCost> getOperatingCostByTruckBetweenTwoDates(Truck truck, Date from, Date to) {
        return getOperatingCosts(truck, from, to);
    }

    private List<OperatingCost> getOperatingCosts(Truck truck, Date from, Date to) {
        Query truckOperatingCostListQuery = new Query();
        truckOperatingCostListQuery.addCriteria(
                Criteria.where("truckId").is(truck.getId())
                .andOperator(Criteria.where("transactionDate").gte(from),
                Criteria.where("transactionDate").lte(to)));

        /*   */
        List<OperatingCost> operatingCostList = mongoOperation.find(truckOperatingCostListQuery, OperatingCost.class);
//        System.out.println("TruckOperatingCostListQuery - "    + operatingCostList.toString());

        for (OperatingCost operatingCost : operatingCostList) {
            System.out.println("Annual OperatingCost ListQuery - " + operatingCost.getTransactionDate() + " | " + operatingCost.getSpeedometer());
        }

//        return mongoOperation.find(truckOperatingCostListQuery, OperatingCost.class);
        return operatingCostList;
    }
}
