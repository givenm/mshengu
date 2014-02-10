/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.kpianalysis.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.kpianalysis.KPA;
import zm.hashcode.mshengu.repository.kpianalysis.KPARepository;
import zm.hashcode.mshengu.services.kpianalysis.KPAService;

/**
 *
 * @author Luckbliss
 */
@Service
public class KPAServiceImpl implements KPAService {

    @Autowired
    private KPARepository repository;

    @Override
    public List<KPA> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(KPA kpi) {

        repository.save(kpi);

    }

    @Override
    public void merge(KPA kpi) {
        if (kpi.getId() != null) {
            repository.save(kpi);
        }
    }

    @Override
    public KPA findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(KPA kpi) {
        repository.delete(kpi);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public KPA findByTab(String name) {
        return repository.findByTab(name);
    }
}
