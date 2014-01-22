package zm.hashcode.mshengu.app.util.predicates.customer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.customer.ServiceRequest;
import zm.hashcode.mshengu.domain.incident.UserAction;

/**
 *
 * @author boniface
 */
public class ServiceRequestStatusPredicate implements Predicate<ServiceRequest> {

    private String serviceRequestStatus;
    
    public ServiceRequestStatusPredicate(String serviceRequestStatus){
        this.serviceRequestStatus =  serviceRequestStatus;
    }
    
    @Override
    public boolean apply(ServiceRequest serviceRequest) {
        if (serviceRequestStatus.equalsIgnoreCase(getServiceRequest(serviceRequest))) {
            return true;
        }
        return false;
    }

    private String getServiceRequest(ServiceRequest serviceRequest) {
        if (serviceRequest != null) {
            return getUserActionStatus(serviceRequest.getLastUserAction());
        }
        return null;

    }
    
        private String getUserActionStatus(UserAction  userAction) {
        if (userAction != null) {
            return userAction.getUserActionStatusName();
        }
        return null;

    }
    
}
