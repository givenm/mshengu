/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.annualdata.fleetmaintenance.maintenancecost;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.services.people.PersonService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Colin
 */
public class Testing_Emp_Number extends AppTest {

    @Autowired
    private PersonService personService;
    private static Truck truck = null;
    private static Person driver = null;
    private static String employeeNumber;

//    @Test
    public void testSheet() {
        personService = ctx.getBean(PersonService.class);

        // Get personId
        try {
            driver = personService.findDriverWithEmployeeNumber("IMV096");
            if (driver != null) {
                System.out.println("\n\nDriver Found: " + driver.getId());
            }
        } catch (NullPointerException ex) {
            System.out.println("\n\nNo Driver Found");
        }
    }
}
