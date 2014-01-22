/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.util;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author boniface
 */
public interface StatusRepository extends PagingAndSortingRepository<Status, String> {
     Status findByName(String name);
}