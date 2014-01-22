/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer;

import java.util.List;
import zm.hashcode.mshengu.domain.customer.Contract;

/**
 *
 * @author Ferox
 */
public interface ContractService {

    public List<Contract> findAll();

    public void persist(Contract contract);

    public void merge(Contract contract);

    public Contract findById(String id);

    public void delete(Contract contract);
    
    public Contract findByParentId(String parentId);
}
