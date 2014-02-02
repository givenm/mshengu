/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.procurement;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.procurement.MaintenanceSpendBySupplierService;

/**
 *
 * @author Colin
 */
public class MaintenanceSpendBySupplierFacade {

    private final static SpringContext ctx = new SpringContext();

    public static MaintenanceSpendBySupplierService getMaintenanceSpendBySupplierService() {
        return ctx.getBean(MaintenanceSpendBySupplierService.class);
    }
}
