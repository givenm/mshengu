/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.incident;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.incident.IncidentActionStatusService;

/**
 *
 * @author Luckbliss
 */
public class IncidentActionStatusFacade {

    private final static SpringContext ctx = new SpringContext();

    public static IncidentActionStatusService getIncidentActionStatusService() {
        return ctx.getBean(IncidentActionStatusService.class);
    }
}
