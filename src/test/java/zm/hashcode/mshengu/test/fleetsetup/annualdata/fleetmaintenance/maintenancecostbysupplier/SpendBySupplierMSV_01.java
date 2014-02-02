/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.annualdata.fleetmaintenance.maintenancecostbysupplier;

import org.springframework.beans.factory.annotation.Autowired;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.procurement.MaintenanceSpendBySupplierService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Colin
 */
public class SpendBySupplierMSV_01 extends AppTest {

    @Autowired
    private MaintenanceSpendBySupplierService maintenanceSpendBySupplierService;
    @Autowired
    private ServiceProviderService serviceProviderService;
    @Autowired
    private TruckService truckService; //
    DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private static Truck truck = null;
    private static ServiceProvider serviceProvider = null;
}
