/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.external;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.external.MailNotificationsService;

/**
 *
 * @author Ferox
 */
public class MailNotificationsFacade {

    private final static SpringContext ctx = new SpringContext();

    public static MailNotificationsService getMailNotificationsService() {
        return ctx.getBean(MailNotificationsService.class);
    }
}
