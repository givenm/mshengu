/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer;

import java.util.List;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;

/**
 *
 * @author Ferox
 */
public interface EmployeeDetailService {

    public List<EmployeeDetail> findAll();

    public void persist(EmployeeDetail contract);

    public void merge(EmployeeDetail contract);

    public EmployeeDetail findById(String id);

    public void delete(EmployeeDetail contract);
}
