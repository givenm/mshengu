package zm.hashcode.mshengu.app.util.predicates.customer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.customer.Customer;

/**
 *
 * @author boniface
 */
public class ContractTypePredicate implements Predicate<Customer> {

    private String contractType;
    
    public ContractTypePredicate(String contractType){
        this.contractType =  contractType;
    }
    
    @Override
    public boolean apply(Customer customer) {
        if (contractType.equalsIgnoreCase(getCustomer(customer))) {
            return true;
        }
        return false;
    }

    private String getCustomer(Customer customer) {
        if (customer != null) {
            return customer.getLastContactTypeName();
        }
        return null;

    }
    
}
