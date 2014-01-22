package zm.hashcode.mshengu.app.util.predicates.customer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.customer.ServiceRequest;

/**
 *
 * @author boniface
 */
public class ServiceRequestActivationStatusPredicate implements Predicate<ServiceRequest> {

    private boolean serviceRequestStatus;
    
    public ServiceRequestActivationStatusPredicate(boolean serviceRequestStatus){
        this.serviceRequestStatus =  serviceRequestStatus;
    }
    
    @Override
    public boolean apply(ServiceRequest serviceRequest) {
        if (serviceRequestStatus == getServiceRequest(serviceRequest)) {
            return true;
        }
        return false;
    }

    private boolean getServiceRequest(ServiceRequest serviceRequest) {
        if (serviceRequest != null) {
            return serviceRequest.isClosed();
        }
        return false;

    }
    
}
