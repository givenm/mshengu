/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.customer.Contract;

/**
 *
 * @author Ferox
 */
public interface ContractRepository extends PagingAndSortingRepository<Contract, String> {
        public Contract findByParentId(String parentId);
}
