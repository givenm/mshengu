/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer;

import java.util.List;
import zm.hashcode.mshengu.domain.customer.Contract;
import zm.hashcode.mshengu.domain.customer.Customer;

/**
 *
 * @author Luckbliss
 */
public interface CustomerService {

    public List<Customer> findAll();

    public void persist(Customer customer);

    public void merge(Customer customer);

    public Customer findById(String id);

    public void delete(Customer customer);
    
    public Customer findByName(String name);
    Contract getSitetCurrentContract(String id);
    public List<Customer> findByContractType(String contractTypeName);
    

}
