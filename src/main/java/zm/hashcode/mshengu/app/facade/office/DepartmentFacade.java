/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.office;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.office.DepartmentService;

/**
 *
 * @author lucky
 */
public class DepartmentFacade {

    private final static SpringContext ctx = new SpringContext();

    public static DepartmentService getDepartmentService() {
        return ctx.getBean(DepartmentService.class);
    }
}
