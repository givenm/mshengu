/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.office;

import java.util.List;
import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.office.Department;

/**
 *
 * @author lucky
 */
public interface DepartmentRepository  extends PagingAndSortingRepository<Department, String>{
    
}
