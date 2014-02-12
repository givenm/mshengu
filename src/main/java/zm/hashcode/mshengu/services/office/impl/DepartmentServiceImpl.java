/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.office.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.office.Department;
import zm.hashcode.mshengu.repository.office.DepartmentRepository;
import zm.hashcode.mshengu.services.office.DepartmentService;

/**
 *
 * @author lucky
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Override
    public List<Department> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(Department department) {

        repository.save(department);

    }

    @Override
    public void merge(Department department) {
        if (department.getId() != null) {
            repository.save(department);
        }
    }

    @Override
    public Department findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Department department) {
        repository.delete(department);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }
}
