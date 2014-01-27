/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.kpianalysis.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import zm.hashcode.mshengu.domain.kpianalysis.KPIValues;
import zm.hashcode.mshengu.repository.kpianalysis.KPIValuesRepository;
import zm.hashcode.mshengu.services.kpianalysis.KPIValuesService;

/**
 *
 * @author Luckbliss
 */
public class KPIValuesServiceImpl implements KPIValuesService {

    @Autowired
    private KPIValuesRepository repository;

    @Override
    public List<KPIValues> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(KPIValues item) {

        repository.save(item);

    }

    @Override
    public void merge(KPIValues item) {
        if (item.getId() != null) {
            repository.save(item);
        }
    }

    @Override
    public KPIValues findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(KPIValues item) {
        repository.delete(item);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "month"));
    }
}
