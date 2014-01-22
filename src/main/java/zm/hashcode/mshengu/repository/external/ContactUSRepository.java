/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.external;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.external.ContactUS;

/**
 *
 * @author Ferox
 */
public interface ContactUSRepository extends PagingAndSortingRepository<ContactUS, String> {
       public ContactUS findByRefNumber(String refNumber);
}
