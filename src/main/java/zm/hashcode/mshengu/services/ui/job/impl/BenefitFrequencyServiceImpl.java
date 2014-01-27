/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.job.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.job.BenefitFrequency;
import zm.hashcode.mshengu.repository.ui.job.BenefitFrequencyRepository;
import zm.hashcode.mshengu.services.ui.job.BenefitFrequencyService;

/**
 *
 * @author lucky
 */
@Service
public class BenefitFrequencyServiceImpl implements BenefitFrequencyService {

    @Autowired
    private BenefitFrequencyRepository repository;

    @Override
    public List<BenefitFrequency> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByFrequency()));
    }

    private Sort sortByFrequency() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "frequency"));
    }

    @Override
    public void persist(BenefitFrequency benefitFrequency) {

        repository.save(benefitFrequency);

    }

    @Override
    public void merge(BenefitFrequency benefitFrequency) {
        if (benefitFrequency.getId() != null) {
            repository.save(benefitFrequency);
        }
    }

    @Override
    public BenefitFrequency findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(BenefitFrequency benefitFrequency) {
        repository.delete(benefitFrequency);
    }
}
