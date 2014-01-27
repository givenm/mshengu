/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer.impl;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Ordering;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.predicates.customer.ContractTypePredicate;
import zm.hashcode.mshengu.domain.customer.Contract;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.repository.customer.CustomerRepository;
import zm.hashcode.mshengu.services.customer.CustomerService;

/**
 *
 * @author Luckbliss
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(Customer customer) {

        repository.save(customer);

    }

    @Override
    public void merge(Customer customer) {
        if (customer.getId() != null) {
            repository.save(customer);
        }
    }

    @Override
    public Customer findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Customer customer) {
        repository.delete(customer);
    }
    
         @Override
    public Customer findByName(String name) {
        return repository.findByName(name);
    }
         
             @Override
    public Contract getSitetCurrentContract(String customerId) {
         Customer customer = findById(customerId);
        Set<Contract> contractList = customer.getContracts();

        return getLatestLifeCycle(contractList);
    }

    private Contract getLatestLifeCycle(Set<Contract> contractList) {

        Ordering<Contract> ordering;
        ordering = Ordering.natural().nullsLast().onResultOf(new Function<Contract, Long>() {
            @Override
            public Long apply(Contract contract) {
                return contract.getDateofAction().getTime();
            }
        });

        List<Contract> sortedList = ordering.immutableSortedCopy(contractList).reverse();
        if (sortedList.isEmpty()) {
            Contract contractNull = null;
            return contractNull;
        } else {
            return sortedList.get(0);
        }
    }
    
    @Override
    public List<Customer> findByContractType(String contractTypeName){
        List<Customer> customerList = (List<Customer>)repository.findAll();
        Collection<Customer> customerFilteredList = Collections2.filter(customerList, new ContractTypePredicate(contractTypeName));
        return ImmutableList.copyOf(customerFilteredList);
//        return ImmutableList.copyOf();
    }
}
