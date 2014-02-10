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
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.repository.procurement.RequestRepositoryCustom;

/**
 *
 * @author Colin
 */
public class RequestRepositoryImpl implements RequestRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public List<Request> getTransactedRequestsByServiceProvider(ServiceProvider serviceProvider) {
        Query transactedRequestListQuery = new Query();
        transactedRequestListQuery.addCriteria(
                Criteria.where("serviceProviderSupplierId").is(serviceProvider.getId()).and("InvoiceNumber").ne(null));
        /*
         List<Request> transactedRequestList = mongoOperation.find(transactedRequestListQuery, Request.class);

         System.out.println("RequestRepository  - SERVICE PROVIDER WITH INVOICE NUMBER ONLY QUERY");
         if (transactedRequestList.isEmpty()) {
         System.out.println("RequestRepository  - SERVICE PROVIDER WITH INVOICE NUMBER ONLY QUERY - NO MATCHING RECORDS FOUND  FOR serviceProviderId: " + serviceProvider.getId());
         }

         for (Request request : transactedRequestList) {
         System.out.println("RequestRepository - SERVICE PROVIDER WITH INVOICE NUMBER ONLY QUERY -  serviceProviderId= " + request.getServiceProviderSupplierId() + " | truckId" + request.getTruckId() + " | DeliveryDATE= " + request.getDeliveryDate() + " | iNVoice= " + request.getInvoiceNumber());
         }
         System.out.println("--==--");
         */
        return mongoOperation.find(transactedRequestListQuery, Request.class);
    }

    @Override
    public List<Request> getTransactedRequestsBtnTwoDates(Date start, Date end) {
        Query transactedRequestListQuery = new Query();
        transactedRequestListQuery.addCriteria(
                Criteria.where("deliveryDate").exists(true)
                .andOperator(Criteria.where("deliveryDate").gte(start),
                Criteria.where("deliveryDate").lte(end)));

        /*
         List<Request> transactedRequestList = mongoOperation.find(transactedRequestListQuery, Request.class);

         System.out.println("RequestRepository  - GENERAL QUERY Start= " + start + " | To= " + end);
         if (transactedRequestList.isEmpty()) {
         System.out.println("RequestRepository  - GENERAL QUERY - NO MATCHING RECORDS FOUND");
         }

         for (Request request : transactedRequestList) {
         System.out.println("RequestRepository - GENERAL QUERY -  serviceProviderId= " + request.getServiceProviderSupplierId() + " | truckId" + request.getTruckId() + " | DeliveryDATE= " + request.getDeliveryDate() + " | iNVoice= " + request.getInvoiceNumber());
         }
         //        System.out.println("--==--");
         */
        return mongoOperation.find(transactedRequestListQuery, Request.class);
    }

    @Override
    public List<Request> getTransactedRequestsByTruckBtnTwoDates(Truck truck, Date start, Date end) {
        return getTruckTransactedRequests(truck, start, end);
    }

    @Override
    public List<Request> getTransactedRequestsByTruckByMonth(Truck truck, Date month) {
        final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        Date from = dateTimeFormatHelper.resetTimeAndMonthStart(month);
        Date to = dateTimeFormatHelper.resetTimeAndMonthEnd(month);

        return getTruckTransactedRequests(truck, from, to);
    }

    @Override
    public List<Request> getTransactedRequestsByServiceProviderBtnTwoDates(ServiceProvider serviceProvider, Date start, Date end) {
        return getServiceProviderTransactedRequest(serviceProvider, start, end);
    }

    @Override
    public List<Request> getTransactedRequestsByServiceProviderByMonth(ServiceProvider serviceProvider, Date month) {
        final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        Date from = dateTimeFormatHelper.resetTimeAndMonthStart(month);
        Date to = dateTimeFormatHelper.resetTimeAndMonthEnd(month);

        return getServiceProviderTransactedRequest(serviceProvider, from, to);
    }

    private List<Request> getTruckTransactedRequests(Truck truck, Date from, Date to) {
        Query transactedRequestListQuery = new Query();
        transactedRequestListQuery.addCriteria(
                Criteria.where("truckId").is(truck.getId())
                .andOperator(Criteria.where("transactionDate").gte(from),
                Criteria.where("transactionDate").lte(to), Criteria.where("InvoiceNumber").ne(null)));

        /*
         List<Request> transactedRequestList = mongoOperation.find(transactedRequestListQuery, Request.class);

         System.out.println("RequestRepository  - TRUCK QUERY Start= " + from + " | To= " + to + "  FOR truckId: " + truck.getId());
         if (transactedRequestList.isEmpty()) {
         System.out.println("RequestRepository  - TRUCK QUERY - NO MATCHING RECORDS FOUND");
         }

         for (Request request : transactedRequestList) {
         System.out.println("RequestRepository - TRUCK QUERY -  serviceProviderId= " + request.getServiceProviderSupplierId() + " | truckId" + request.getTruckId() + " | DeliveryDATE= " + request.getDeliveryDate() + " | iNVoice= " + request.getInvoiceNumber());
         }
         //        System.out.println("--==--");
         */
        return mongoOperation.find(transactedRequestListQuery, Request.class);
    }

    private List<Request> getServiceProviderTransactedRequest(ServiceProvider serviceProvider, Date from, Date to) {
        Query transactedRequestListQuery = new Query();
        transactedRequestListQuery.addCriteria(
                Criteria.where("serviceProviderSupplierId").is(serviceProvider.getId())
                .andOperator(Criteria.where("deliveryDate").gte(from),
                Criteria.where("deliveryDate").lte(to), Criteria.where("InvoiceNumber").ne(null)));

        /*
         List<Request> transactedRequestList = mongoOperation.find(transactedRequestListQuery, Request.class);

         System.out.println("RequestRepository  - SERVICE PROVIDER QUERY Start= " + from + " | To= " + to + "  FOR serviceProviderId: " + serviceProvider.getId());
         if (transactedRequestList.isEmpty()) {
         System.out.println("RequestRepository  - SERVICE PROVIDER QUERY - NO MATCHING RECORDS FOUND");
         }

         for (Request request : transactedRequestList) {
         System.out.println("RequestRepository - SERVICE PROVIDER QUERY -  serviceProviderId= " + request.getServiceProviderSupplierId() + " | truckId" + request.getTruckId() + " | DeliveryDATE= " + request.getDeliveryDate() + " | iNVoice= " + request.getInvoiceNumber());
         }
         //        System.out.println("--==--");
         */
        return mongoOperation.find(transactedRequestListQuery, Request.class);
    }
}
