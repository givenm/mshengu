/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.procurement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class InvoicesTest extends AppTest {

    @Autowired
    private ServiceProviderService providerService;

//    @Test
    public void getTruckUnitsTests() {

        providerService = ctx.getBean(ServiceProviderService.class);
        List<ServiceProvider> serviceProviders = providerService.findAll();

        for (ServiceProvider provider : serviceProviders) {
            System.out.println("Id: " + provider.getId() + " Name: " + provider.getName());
        }
    }

    public void getOrder31(Request request) {
        if (request.getOrderNumber().equals("MSH_PO-0000031")) {
            Request newRequest = new Request.Builder(request.getPerson())
                    .request(request)
                    .invoiceNumber("00257034")
                    .deliveryDate(new Date(2014, 01, 31))
                    .total(new BigDecimal("5164.49"))
                    .build();
//            requestService.merge(newRequest);
        }
    }

    public void getOrder08(Request request) {
        if (request.getOrderNumber().equals("MSH_PO-0000031")) {
            Request newRequest = new Request.Builder(request.getPerson())
                    .request(request)
                    .truckId("5267af26e4b0417002e0342d")
                    .serviceProviderSupplierId("52da02fee4b01596318b5a2c")
                    .orderDate(new Date(2014, 01, 03))
                    .build();
//            requestService.merge(newRequest);
        }
    }

    public void getOrder43(Request request) {
        if (request.getOrderNumber().equals("MSH_PO-0000043")) {
            Request newRequest = new Request.Builder(request.getPerson())
                    .request(request)
                    .truckId("5267b8b5e4b0417002e03430")
                    .serviceProviderSupplierId("52da02fee4b01596318b5a2c ")
                    .orderDate(new Date(2014, 02, 07))
                    .total(new BigDecimal("1672.38"))
                    .build();
//            requestService.merge(newRequest);
        }
    }

    public void getOrder29(Request request) {
        if (request.getOrderNumber().equals("MSH_PO-0000043")) {
            Request newRequest = new Request.Builder(request.getPerson())
                    .request(request)
                    .truckId("5267b8b5e4b0417002e03430")
                    .serviceProviderSupplierId("52da02fee4b01596318b5a2c ")
                    .orderDate(new Date(2014, 02, 07))
                    .total(new BigDecimal("1672.38"))
                    .build();
//            requestService.merge(newRequest);
        }
    }
}
