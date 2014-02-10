/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.external;

import java.util.List;
import zm.hashcode.mshengu.domain.external.MailNotifications;

/**
 *
 * @author Ferox
 */
public interface MailNotificationsService {

    public List<MailNotifications> findAll();

    public void persist(MailNotifications incident);

    public void merge(MailNotifications incident);

    public MailNotifications findById(String id);

    public void delete(MailNotifications incident);
    
    
    public MailNotifications findByName(String name);
    
}
