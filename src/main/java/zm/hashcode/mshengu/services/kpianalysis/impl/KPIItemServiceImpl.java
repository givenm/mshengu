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
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;
import zm.hashcode.mshengu.repository.kpianalysis.KPIItemRepository;
import zm.hashcode.mshengu.services.kpianalysis.KPIItemService;

/**
 *
 * @author Luckbliss
 */
@Service
public class KPIItemServiceImpl implements KPIItemService {

    @Autowired
    private KPIItemRepository repository;

    @Override
    public List<KPIItem> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(KPIItem item) {

        repository.save(item);

    }

    @Override
    public void merge(KPIItem item) {
        if (item.getId() != null) {
            repository.save(item);
        }
    }

    @Override
    public KPIItem findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(KPIItem item) {
        repository.delete(item);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "shortDescription"));
    }
}
