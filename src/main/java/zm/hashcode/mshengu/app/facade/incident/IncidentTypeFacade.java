/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.incident;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.incident.IncidentTypeService;

/**
 *
 * @author Luckbliss
 */
public class IncidentTypeFacade {

    private final static SpringContext ctx = new SpringContext();

    public static IncidentTypeService getIncidentTypeService() {
        return ctx.getBean(IncidentTypeService.class);
    }
}
