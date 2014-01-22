/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.util;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.util.StatusService;

/**
 *
 * @author boniface
 */
public class StatusFacade {

    private final static SpringContext ctx = new SpringContext();

    public static StatusService getStatusService() {
        return ctx.getBean(StatusService.class);
    }
}
