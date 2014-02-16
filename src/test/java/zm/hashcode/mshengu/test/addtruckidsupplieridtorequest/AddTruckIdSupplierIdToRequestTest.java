/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.addtruckidsupplieridtorequest;

import com.vaadin.ui.Notification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.services.procurement.RequestService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Colin
 */
public class AddTruckIdSupplierIdToRequestTest extends AppTest {

    @Autowired
    private RequestService requestService; //

//    @Test
    public void testAddTruckIdSupplierIdToRequest() {
        requestService = ctx.getBean(RequestService.class);
        List<Request> requests = requestService.findAll();
        for (Request request : requests) {
//    if ("fleet maintenance".equalsIgnoreCase(centreType.getName()) && bean.getItemCategory() != null)
            //            if(request.getCostCentreType().getName().equalsIgnoreCase("fleet maintenance"))
            Truck truck = request.getTruck();
            if (truck != null) {
                updateEntity(request);
            }
        }
    }

//    @Test(dependsOnMethods = {"testAddTruckIdSupplierIdToRequest"})
    public void testDb() {
        requestService = ctx.getBean(RequestService.class);
        List<Request> requests = requestService.findAll();
        for (Request request : requests) {
//            Truck truck = request.getTruck();
//            if (truck != null) {
            System.out.println("Request Check: TruckId= " + request.getTruckId() + " | ServiceProviderId= " + request.getServiceProviderId() + " | getId()= " + request.getId() + " | OrderDate= " + request.getOrderDate() + " | DeliveryDate= " + request.getDeliveryDate() + " | OrderNumber= " + request.getOrderNumber() + " | Payment Amt= " + request.getPaymentAmount() + " | Total= " + request.getTotal() + " | CostCenter Name= " + request.getCostCentreType().getName());
//            }
        }
    }

    private void updateEntity(Request request) {
        final Request unUpdatedRequest = new Request.Builder(request.getPerson())
                .request(request)
                .serviceProviderSupplierId(request.getServiceProvider().getId())
                .truckId(request.getTruck().getId())
                .build();
        try {
            requestService.merge(unUpdatedRequest);
        } catch (Exception e) {
            Notification.show("Could not Update Request with TruckId n ServicEProviderId: \n" + e, Notification.Type.TRAY_NOTIFICATION);
        }
    }
}
