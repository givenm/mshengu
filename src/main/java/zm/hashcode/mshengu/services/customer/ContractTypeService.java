/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer;

import java.util.List;
import zm.hashcode.mshengu.domain.customer.ContractType;

/**
 *
 * @author Ferox
 */
public interface ContractTypeService {

    public List<ContractType> findAll();

    public void persist(ContractType contractType);

    public void merge(ContractType contractType);

    public ContractType findById(String id);

    public void delete(ContractType contractType);
    
    public ContractType findByType(String type);
}
