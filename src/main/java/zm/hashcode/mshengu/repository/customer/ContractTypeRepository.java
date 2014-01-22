/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.customer.ContractType;

/**
 *
 * @author Ferox
 */
public interface ContractTypeRepository extends PagingAndSortingRepository<ContractType, String> {
             public ContractType findByType(String type);
}
