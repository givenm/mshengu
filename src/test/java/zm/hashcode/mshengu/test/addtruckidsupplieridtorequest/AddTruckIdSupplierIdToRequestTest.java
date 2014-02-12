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
import zm.hashcode.mshengu.domain.ui.util.CostCentreCategoryType;
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
    @Test
    public void testDb() {
        requestService = ctx.getBean(RequestService.class);
        List<Request> requests = requestService.findAll();

        for (Request request : requests) {
            Truck truck = request.getTruck();
//            if (truck != null) {
            System.out.println("Request Check: request.getTruckId= " + getTruckId(request.getTruck()) + "  Cost Category Type " +  getCostCentreCategoryTypeName(request.getCategoryType()) + "  Request Date "+  request.getOrderDate() + "Request Check: TruckId= " + request.getTruckId() + " | ServiceProviderId= " + request.getServiceProviderId() + " | getId()= " + request.getId() + " | OrderDate= " + request.getOrderDate() + " | DeliveryDate= " + request.getDeliveryDate() + " | OrderNumber= " + request.getOrderNumber());
//            }
        }
    }
    
    public String getTruckId(Truck truck){
        if(truck != null){
           return truck.getId();
        }else{
            return "Trcuk IS null";
        }
    }
    
      public String getCostCentreCategoryTypeName(CostCentreCategoryType categoryType){
        if(categoryType != null){
           return categoryType.getName();
        }else{
            return " Category Name IS null ";
        }
    }

    private void updateEntity(Request request) {
        final Request unUpdatedRequest = new Request.Builder(request.getPerson())
                .request(request)
                .approvalStatus(request.isApprovalStatus())
                .approver(request.getApprover())
                .categoryType(request.getCategoryType())
                .costCentreType(request.getCostCentreType())
                .deliveryDate(request.getDeliveryDate())
                .deliveryInstructions(request.getDeliveryInstructions())
                .emailstatus(request.isEmailstatus())
                .invoiceNumber(request.getInvoiceNumber())
                .itemCategoryType(request.getItemCategoryType())
                .items(request.getRequestPurchaseItems())
                .matchStatus(request.getStatus())
                .misMatchDate(request.getMisMatchDate())
                .orderDate(request.getOrderDate())
                .orderNumber(request.getOrderNumber())
                .paymentAmount(request.getPaymentAmount())
                .paymentDate(request.getPaymentDate())
                .reasonForDisapproval(request.getReasonForDisapproval())
                .serviceProvider(request.getServiceProvider())
                .serviceProviderSupplierId(request.getServiceProvider().getId())
                .total(request.getTotal())
                .truck(request.getTruck())
                .truckId(request.getTruck().getId())
                .build();
        try {
            requestService.merge(unUpdatedRequest);
        } catch (Exception e) {
            Notification.show("Could not Update Request with TruckId n ServicEProviderId: \n" + e, Notification.Type.TRAY_NOTIFICATION);
        }
    }
}
