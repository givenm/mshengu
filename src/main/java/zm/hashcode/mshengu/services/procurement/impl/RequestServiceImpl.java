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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.SequenceHelper;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;
import zm.hashcode.mshengu.repository.procurement.RequestRepository;
import zm.hashcode.mshengu.services.procurement.RequestService;

/**
 *
 * @author Luckbliss
 */
@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository repository;
    private SequenceHelper helper = new SequenceHelper();

    @Override
    @Cacheable("requests")
    public List<Request> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByOrderNumber()));
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "requests", allEntries = true),
        @CacheEvict(value = "pendingRequests", allEntries = true),
        @CacheEvict(value = "disApprovedRequests", allEntries = true),
        @CacheEvict(value = "approvedRequestsBySupplier", allEntries = true),
        @CacheEvict(value = "misMatchStatus", allEntries = true),
        @CacheEvict(value = "processedRequestsWithInvoiceNumber", allEntries = true),
        @CacheEvict(value = "serviceProviderProcessedRequestsWithInvoiceNumber", allEntries = true),
        @CacheEvict(value = "processedRequestsWithPaymentDate", allEntries = true),
        @CacheEvict(value = "processedRequestsByCostCentreType", allEntries = true),
        @CacheEvict(value = "serviceProviderProcessedRequestsWithPaymentDate", allEntries = true),
        @CacheEvict(value = "approvedRequests", allEntries = true)})
    public void persist(Request request) {
        repository.save(request);

    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "requests", allEntries = true),
        @CacheEvict(value = "pendingRequests", allEntries = true),
        @CacheEvict(value = "disApprovedRequests", allEntries = true),
        @CacheEvict(value = "approvedRequestsBySupplier", allEntries = true),
        @CacheEvict(value = "misMatchStatus", allEntries = true),
        @CacheEvict(value = "processedRequestsWithInvoiceNumber", allEntries = true),
        @CacheEvict(value = "serviceProviderProcessedRequestsWithInvoiceNumber", allEntries = true),
        @CacheEvict(value = "processedRequestsWithPaymentDate", allEntries = true),
        @CacheEvict(value = "processedRequestsByCostCentreType", allEntries = true),
        @CacheEvict(value = "serviceProviderProcessedRequestsWithPaymentDate", allEntries = true),
        @CacheEvict(value = "approvedRequests", allEntries = true)})
    public void merge(Request request) {
        if (request.getId() != null) {
            repository.save(request);
        }
    }

    @Override
    public Request findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "requests", allEntries = true),
        @CacheEvict(value = "pendingRequests", allEntries = true),
        @CacheEvict(value = "disApprovedRequests", allEntries = true),
        @CacheEvict(value = "approvedRequestsBySupplier", allEntries = true),
        @CacheEvict(value = "misMatchStatus", allEntries = true),
        @CacheEvict(value = "processedRequestsWithInvoiceNumber", allEntries = true),
        @CacheEvict(value = "serviceProviderProcessedRequestsWithInvoiceNumber", allEntries = true),
        @CacheEvict(value = "processedRequestsWithPaymentDate", allEntries = true),
        @CacheEvict(value = "processedRequestsByCostCentreType", allEntries = true),
        @CacheEvict(value = "serviceProviderProcessedRequestsWithPaymentDate", allEntries = true),
        @CacheEvict(value = "approvedRequests", allEntries = true)})
    public void delete(Request request) {
        repository.delete(request);
    }

    private Sort sortByOrderNumber() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "orderNumber"));
    }

    @Override
    public Request findByOrderNumber(String id) {
        Request request = repository.findByOrderNumber(id);
        return request;
    }

    @Override
    @Cacheable("misMatchStatus")
    public List<Request> findByMisMatchStatus() {
        return repository.findByMisMatchStatus();
    }

    @Override
    public List<Request> getTransactedRequestsBtnTwoDates(Date start, Date end) {
        return repository.getTransactedRequestsBtnTwoDates(start, end);

//        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
//        Date resetStartDate = dateTimeFormatHelper.resetTimeAndMonthStart(start);
//        Date resetEndDate = dateTimeFormatHelper.resetTimeAndMonthEnd(end);
//
//        List<Request> requestList = new ArrayList<>();
//        List<Request> allRequestList = findAll();
//
//        for (Request request : allRequestList) {
//            if (request.getDeliveryDate() != null && request.getOrderNumber() != null) { // Uncompleted Request have DeliveryDate and OrderNumber with null
//                Date transactionDate = dateTimeFormatHelper.resetTimeOfDate(request.getDeliveryDate());
//                if (transactionDate.compareTo(resetStartDate) == 0 || transactionDate.compareTo(resetEndDate) == 0 || (transactionDate.after(resetStartDate) && transactionDate.before(resetEndDate))) {
//                    requestList.add(request);
//                }
//            }
//        }
//        return requestList;
    }

    @Override
    public List<Request> findByServiceProvider(String id) {
        List<Request> requestList = new ArrayList<>();
        List<Request> allRequestList = findAll();
        for (Request request : allRequestList) {
            if (getServiceProvider(request.getServiceProvider()).equalsIgnoreCase(id)) {
                requestList.add(request);
            }
        }
        return requestList;
    }

    private String getServiceProvider(ServiceProvider serviceProvider) {
        if (serviceProvider != null) {
            return serviceProvider.getId();
        }
        return null;
    }

    @Override
    public List<Request> getTransactedRequestsByTruckBtnTwoDates(Truck truck, Date start, Date end) {
        return repository.getTransactedRequestsByTruckBtnTwoDates(truck, start, end);
    }

    @Override
    public List<Request> getTransactedRequestsByTruckByMonth(Truck truck, Date month) {
        return repository.getTransactedRequestsByTruckByMonth(truck, month);
    }

    @Override
    public List<Request> getTransactedRequestsByServiceProviderBtnTwoDates(ServiceProvider sericeProvider, Date start, Date end) {
        return repository.getTransactedRequestsByServiceProviderBtnTwoDates(sericeProvider, start, end);
    }

    @Override
    public List<Request> getTransactedRequestsByServiceProviderByMonth(ServiceProvider serviceProvider, Date month) {
        return repository.getTransactedRequestsByServiceProviderByMonth(serviceProvider, month);
    }

    @Override
    public List<Request> getTransactedRequestsByServiceProvider(ServiceProvider serviceProvider) {
        return repository.getTransactedRequestsByServiceProvider(serviceProvider);
    }

    @Override
    @Cacheable("processedRequestsWithInvoiceNumber")
    public List<Request> getProcessedRequestsWithInvoiceNumber() {
        return repository.getProcessedRequestsWithInvoiceNumber();
    }

    @Override
    @Cacheable("serviceProviderProcessedRequestsWithInvoiceNumber")
    public List<Request> getServiceProviderProcessedRequestsWithInvoiceNumber(String serviceProviderId) {
        return repository.getServiceProviderProcessedRequestsWithInvoiceNumber(serviceProviderId);
    }

    @Override
    @Cacheable("processedRequestsWithPaymentDate")
    public List<Request> getProcessedRequestsWithPaymentDate(Date month) {
        return repository.getProcessedRequestsWithPaymentDate(month);
    }

    @Override
    @Cacheable("serviceProviderProcessedRequestsWithPaymentDate")
    public List<Request> getServiceProviderProcessedRequestsWithPaymentDate(String serviceProviderId, Date month) {
        return repository.getServiceProviderProcessedRequestsWithPaymentDate(serviceProviderId, month);
    }

    @Override
    @Cacheable("processedRequestsByCostCentreType")
    public List<Request> getProcessedRequestsByCostCentreType(CostCentreType costCentreType, Date month) {
        return repository.getProcessedRequestsByCostCentreType(costCentreType, month);
    }

    @Override
    @Cacheable("pendingRequests")
    public List<Request> getPendingRequests() {
        return repository.getPendingRequests();
    }

    @Override
    @Cacheable("disApprovedRequests")
    public List<Request> getDisApprovedRequests() {
        return repository.getDisApprovedRequests();
    }

    @Override
    @Cacheable("approvedRequests")
    public List<Request> getApprovedRequests(Date month) {
        return repository.getApprovedRequests(month);
    }

    @Override
    @Cacheable("approvedRequestsBySupplier")
    public List<Request> getApprovedRequestsBySupplier(String serviceProviderId, Date month) {
        return repository.getApprovedRequestsBySupplier(serviceProviderId, month);
    }
}
