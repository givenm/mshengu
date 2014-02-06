/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.procurement;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Colin
 */
public interface RequestRepositoryCustom {

    public List<Request> getTransactedRequestsBtnTwoDates(Date start, Date end);

    public List<Request> getTransactedRequestsByTruckBtnTwoDates(Truck truck, Date start, Date end);

    public List<Request> getTransactedRequestsByTruckByMonth(Truck truck, Date month);

    public List<Request> getTransactedRequestsByServiceProviderBtnTwoDates(ServiceProvider serviceProvider, Date start, Date end);

    public List<Request> getTransactedRequestsByServiceProviderByMonth(ServiceProvider serviceProvider, Date month);

    public List<Request> getTransactedRequestsByServiceProvider(ServiceProvider serviceProvider);
}
