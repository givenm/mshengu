/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;

/**
 *
 * @author Ferox
 */
public interface EmployeeDetailRepository extends PagingAndSortingRepository<EmployeeDetail, String> {

}
