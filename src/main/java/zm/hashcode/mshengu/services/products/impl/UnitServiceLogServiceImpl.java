/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.products.UnitServiceLog;
import zm.hashcode.mshengu.repository.products.UnitServiceLogRepository;
import zm.hashcode.mshengu.services.products.UnitServiceLogService;

/**
 *
 * @author Luckbliss
 */
@Service
public class UnitServiceLogServiceImpl implements UnitServiceLogService {

    @Autowired
    private UnitServiceLogRepository repository;

    @Override
    public List<UnitServiceLog> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByServiceDate()));
    }

    @Override
    public void persist(UnitServiceLog unityServiceLog) {

        repository.save(unityServiceLog);

    }

    @Override
    public void merge(UnitServiceLog unityServiceLog) {
        if (unityServiceLog.getId() != null) {
            repository.save(unityServiceLog);
        }
    }

    @Override
    public UnitServiceLog findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(UnitServiceLog unityServiceLog) {
        repository.delete(unityServiceLog);
    }

    private Sort sortByServiceDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "serviceDate"));
    }
}
