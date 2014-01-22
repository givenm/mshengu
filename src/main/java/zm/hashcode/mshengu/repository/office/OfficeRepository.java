/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.office;

import java.util.List;
import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.office.Office;

/**
 *
 * @author lucky
 */
public interface OfficeRepository extends PagingAndSortingRepository<Office, String>{
   
}
