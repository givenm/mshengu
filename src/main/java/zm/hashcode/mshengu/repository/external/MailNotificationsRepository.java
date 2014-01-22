/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.external;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.external.MailNotifications;

/**
 *
 * @author Ferox
 */
public interface MailNotificationsRepository extends PagingAndSortingRepository<MailNotifications, String> {
   public MailNotifications findByName(String name);
}
