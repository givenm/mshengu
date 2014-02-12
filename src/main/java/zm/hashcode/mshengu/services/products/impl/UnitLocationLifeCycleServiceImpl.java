/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products.impl;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.repository.products.UnitLocationLifeCycleRepository;
import zm.hashcode.mshengu.services.products.UnitLocationLifeCycleService;

/**
 *
 * @author Luckbliss
 */
@Service
public class UnitLocationLifeCycleServiceImpl implements UnitLocationLifeCycleService {

    @Autowired
    private UnitLocationLifeCycleRepository repository;

    @Override 
    @Cacheable("unitLocationLifeCycle")
    public List<UnitLocationLifeCycle> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByServiceDate()));
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "unitLocationLifeCycle", allEntries = true)
    })
    public void persist(UnitLocationLifeCycle unitLocationLifeCycle) {

        repository.save(unitLocationLifeCycle);

    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "unitLocationLifeCycle", allEntries = true)
    })
    public void merge(UnitLocationLifeCycle unitLocationLifeCycle) {
        if (unitLocationLifeCycle.getId() != null) {
            repository.save(unitLocationLifeCycle);
        }
    }

    @Override
    public UnitLocationLifeCycle findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "unitLocationLifeCycle", allEntries = true)
    })
    public void delete(UnitLocationLifeCycle unitLocationLifeCycle) {
        repository.delete(unitLocationLifeCycle);
    }

    private Sort sortByServiceDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "dateofAction"));
    }

    @Override
    public List<UnitLocationLifeCycle> findAllSorted() {
        List<UnitLocationLifeCycle> sortedList = ImmutableList.copyOf(repository.findAll());
        Ordering<UnitLocationLifeCycle> ordering = Ordering.natural().nullsLast().onResultOf(new Function<UnitLocationLifeCycle, Long>() {
            public Long apply(UnitLocationLifeCycle unitLocationLifeCycle) {
                return unitLocationLifeCycle.getDateofAction().getTime();
            }
        });

        return ordering.immutableSortedCopy(sortedList).reverse();
    }
    
    public boolean compareGeoLocation(UnitType unitType, Map<String, String> values){
        
        return false;
    }
}
