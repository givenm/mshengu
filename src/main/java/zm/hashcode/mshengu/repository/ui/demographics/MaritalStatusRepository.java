/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.demographics;

import java.util.List;
import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.demographics.MaritalStatus;

/**
 *
 * @author lucky
 */
public interface MaritalStatusRepository extends PagingAndSortingRepository< MaritalStatus, String>{
    
}
