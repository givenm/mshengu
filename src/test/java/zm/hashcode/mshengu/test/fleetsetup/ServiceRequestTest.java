/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup;

import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.customer.ServiceRequestService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author Ferox
 */
public class ServiceRequestTest extends AppTest {

    private ServiceRequestService serviceRequestService;

//    @Test
    public void setUpTitles() {

            serviceRequestService = ctx.getBean(ServiceRequestService.class);
            serviceRequestService.findAll();
            System.out.println("pass");
//            List<ServiceRequest> sreq = serviceRequestService.findAllOpen();


    }
}
