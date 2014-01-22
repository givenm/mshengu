/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.customer;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.customer.ContractTypeService;

/**
 *
 * @author Ferox
 */
public class ContractTypeFacade {

    private final static SpringContext ctx = new SpringContext();

    public static ContractTypeService getContractTypeService() {
        return ctx.getBean(ContractTypeService.class);
    }
}
