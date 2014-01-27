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
import zm.hashcode.mshengu.domain.kpianalysis.KPI;
import zm.hashcode.mshengu.repository.kpianalysis.KPIRepository;
import zm.hashcode.mshengu.services.kpianalysis.KPIService;

/**
 *
 * @author Luckbliss
 */
@Service
public class KPIServiceImpl implements KPIService {

    @Autowired
    private KPIRepository repository;

    @Override
    public List<KPI> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(KPI kpi) {

        repository.save(kpi);

    }

    @Override
    public void merge(KPI kpi) {
        if (kpi.getId() != null) {
            repository.save(kpi);
        }
    }

    @Override
    public KPI findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(KPI kpi) {
        repository.delete(kpi);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public KPI findByTab(String name) {
        return repository.findByTab(name);
    }
}
