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
import zm.hashcode.mshengu.domain.products.SiteUnitOperationalStatus;
import zm.hashcode.mshengu.repository.products.SiteUnitOperationalStatusRepository;
import zm.hashcode.mshengu.services.products.SiteUnitOperationalStatusService;

/**
 *
 * @author Ferox
 */
@Service
public class SiteUnitOperationalStatusServiceImpl implements SiteUnitOperationalStatusService {

    @Autowired
    private SiteUnitOperationalStatusRepository repository;

    @Override
    public List<SiteUnitOperationalStatus> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(SiteUnitOperationalStatus siteUnitOperationalStatus) {

        repository.save(siteUnitOperationalStatus);

    }

    @Override
    public void merge(SiteUnitOperationalStatus siteUnitOperationalStatus) {
        if (siteUnitOperationalStatus.getId() != null) {
            repository.save(siteUnitOperationalStatus);
        }
    }

    @Override
    public SiteUnitOperationalStatus findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(SiteUnitOperationalStatus siteUnitOperationalStatus) {
        repository.delete(siteUnitOperationalStatus);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public SiteUnitOperationalStatus findByName(String name) {
        return repository.findByName(name);
    }
}
