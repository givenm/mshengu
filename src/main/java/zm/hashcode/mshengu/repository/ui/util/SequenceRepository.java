/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.util;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.util.Sequence;

/**
 *
 * @author Ferox
 */
public interface SequenceRepository extends PagingAndSortingRepository<Sequence, String>{
    public Sequence findByName(String name);
}
