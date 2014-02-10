/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products.impl;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;
import zm.hashcode.mshengu.repository.products.SiteServiceContractLifeCycleRepository;
import zm.hashcode.mshengu.services.products.SiteServiceContractLifeCycleService;

/**
 *
 * @author Ferox
 */
@Service
public class SiteServiceContractLifeCycleServiceServiceImpl implements SiteServiceContractLifeCycleService {

    @Autowired
    private SiteServiceContractLifeCycleRepository repository;

    @Override
    public List<SiteServiceContractLifeCycle> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByCreatedDate()));
    }

    @Override
    public void persist(SiteServiceContractLifeCycle siteServiceContract) {

        repository.save(siteServiceContract);

    }

    @Override
    public void merge(SiteServiceContractLifeCycle siteServiceContract) {
        if (siteServiceContract.getId() != null) {
            repository.save(siteServiceContract);
        }
    }

    @Override
    public SiteServiceContractLifeCycle findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(SiteServiceContractLifeCycle siteServiceContract) {
        repository.delete(siteServiceContract);
    }

    private Sort sortByCreatedDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "dateofAction"));
    }

    /*  @Override
     public Set<SiteServiceContractLifeCycle> findAllSorted() {
     Set<SiteServiceContractLifeCycle> sortedList = ImmutableSet.copyOf(repository.findAll());
     Ordering<SiteServiceContractLifeCycle> ordering = Ordering.natural().nullsLast().onResultOf(new Function<SiteServiceContractLifeCycle, Long>() {
     public Long apply(SiteServiceContractLifeCycle siteServiceContractLifeCycle) {
     return siteServiceContractLifeCycle.getDateofAction().getTime();
     }
     });

     return ordering.immutableSortedCopy(sortedList).reverse();
     //ordering.immutableSortedCopy(sortedList).reverse();
     //        ImmutableSet.copyOf
     }*/
    @Override
    public List<SiteServiceContractLifeCycle> findAllSorted() {
        List<SiteServiceContractLifeCycle> sortedList = ImmutableList.copyOf(repository.findAll());
        Ordering<SiteServiceContractLifeCycle> ordering = Ordering.natural().nullsLast().onResultOf(new Function<SiteServiceContractLifeCycle, Long>() {
            public Long apply(SiteServiceContractLifeCycle siteServiceContractLifeCycle) {
                return siteServiceContractLifeCycle.getDateofAction().getTime();
            }
        });

        return ordering.immutableSortedCopy(sortedList).reverse();
        //ordering.immutableSortedCopy(sortedList).reverse();
//        ImmutableSet.copyOf
    }
}
