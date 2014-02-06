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
import zm.hashcode.mshengu.domain.procurement.MaintenanceSpendBySupplier;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.repository.procurement.MaintenanceSpendBySupplierRepositoryCustom;

/**
 *
 * @author Colin
 */
public class MaintenanceSpendBySupplierRepositoryImpl implements MaintenanceSpendBySupplierRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBetweenTwoDates(Date from, Date to) {
        Query maintenanceSpendListQuery = new Query();
        maintenanceSpendListQuery.addCriteria(
                Criteria.where("transactionDate").exists(true)
                .andOperator(Criteria.where("transactionDate").gte(from),
                Criteria.where("transactionDate").lte(to)));

        /*   */
        List<MaintenanceSpendBySupplier> maintenanceSpendList = mongoOperation.find(maintenanceSpendListQuery, MaintenanceSpendBySupplier.class);
//        System.out.println("maintenanceSpendList - "    + maintenanceSpendList.toString());

        for (MaintenanceSpendBySupplier maintenanceSpend : maintenanceSpendList) {
            System.out.println("MaintenanceSpendList - " + maintenanceSpend.getTransactionDate() + " | " + maintenanceSpend.getTransactionDate());
        }

//        return mongoOperation.find(maintenanceSpendListQuery, MaintenanceSpendBySupplier.class);
        return maintenanceSpendList;
    }

    @Override
    public List<MaintenanceSpendBySupplier> getMaintenanceSpendByTruckBetweenTwoDates(Truck truck, Date from, Date to) {
        return getTruckMaintenanceSpend(truck, from, to);
    }

    @Override
    public List<MaintenanceSpendBySupplier> getMaintenanceSpendByTruckForMonth(Truck truck, Date month) {
        final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        Date from = dateTimeFormatHelper.resetTimeAndMonthStart(month);
        Date to = dateTimeFormatHelper.resetTimeAndMonthEnd(month);

        return getTruckMaintenanceSpend(truck, from, to);
    }

    @Override
    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBySupplierBetweenTwoDates(ServiceProvider serviceProvider, Date from, Date to) {
        return getSupplierMaintenanceSpend(serviceProvider, from, to);
    }

    @Override
    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBySupplierForMonth(ServiceProvider serviceProvider, Date month) {
        final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        Date from = dateTimeFormatHelper.resetTimeAndMonthStart(month);
        Date to = dateTimeFormatHelper.resetTimeAndMonthEnd(month);

        return getSupplierMaintenanceSpend(serviceProvider, from, to);
    }

    private List<MaintenanceSpendBySupplier> getTruckMaintenanceSpend(Truck truck, Date from, Date to) {
        Query truckMaintenanceSpendListQuery = new Query();
        truckMaintenanceSpendListQuery.addCriteria(
                Criteria.where("truckId").is(truck.getId())
                .andOperator(Criteria.where("transactionDate").gte(from),
                Criteria.where("transactionDate").lte(to)));

        /*   */
        List<MaintenanceSpendBySupplier> truckMaintenanceSpendList = mongoOperation.find(truckMaintenanceSpendListQuery, MaintenanceSpendBySupplier.class);
//        System.out.println("truckMaintenanceSpendList - "    + truckMaintenanceSpendList.toString());

        for (MaintenanceSpendBySupplier maintenanceSpend : truckMaintenanceSpendList) {
            System.out.println("TruckMaintenanceSpendList - truckId= " + maintenanceSpend.getTruckId() + " | date= " + maintenanceSpend.getTransactionDate());
        }

//        return mongoOperation.find(truckMaintenanceSpendListQuery, MaintenanceSpendBySupplier.class);
        return truckMaintenanceSpendList;
    }

    private List<MaintenanceSpendBySupplier> getSupplierMaintenanceSpend(ServiceProvider serviceProvider, Date from, Date to) {
        Query supplierMaintenanceSpendListQuery = new Query();
        supplierMaintenanceSpendListQuery.addCriteria(
                Criteria.where("supplierId").is(serviceProvider.getId())
                .andOperator(Criteria.where("transactionDate").gte(from),
                Criteria.where("transactionDate").lte(to)));

        /*   */
        List<MaintenanceSpendBySupplier> supplierMaintenanceSpendList = mongoOperation.find(supplierMaintenanceSpendListQuery, MaintenanceSpendBySupplier.class);
//        System.out.println("supplierMaintenanceSpendList - "    + supplierMaintenanceSpendList.toString());

        for (MaintenanceSpendBySupplier maintenanceSpend : supplierMaintenanceSpendList) {
            System.out.println("SupplierMaintenanceSpendList - supplierId= " + maintenanceSpend.getSupplierId() + " | date= " + maintenanceSpend.getTransactionDate());
        }

//        return mongoOperation.find(supplierMaintenanceSpendListQuery, MaintenanceSpendBySupplier.class);
        return supplierMaintenanceSpendList;
    }
}
