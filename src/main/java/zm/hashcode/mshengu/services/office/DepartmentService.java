/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.office;

import java.util.List;
import zm.hashcode.mshengu.domain.office.Department;

/**
 *
 * @author lucky
 */
public interface DepartmentService {
    public List<Department> findAll();
    public void persist(Department department);
    public void merge(Department department);
    public Department findById(String id);
    public void delete(Department department);
}
