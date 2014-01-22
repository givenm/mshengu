/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.util;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.util.SequenceType;

/**
 *
 * @author Ferox
 */
public interface SequenceTypeRepository extends PagingAndSortingRepository<SequenceType, String> {
     public SequenceType findByName(String name);
}
