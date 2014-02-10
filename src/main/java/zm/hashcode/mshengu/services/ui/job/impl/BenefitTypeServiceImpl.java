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
import zm.hashcode.mshengu.domain.ui.job.BenefitType;
import zm.hashcode.mshengu.repository.ui.job.BenefitTypeRepository;
import zm.hashcode.mshengu.services.ui.job.BenefitTypeService;

/**
 *
 * @author lucky
 */
@Service
public class BenefitTypeServiceImpl implements BenefitTypeService {

    @Autowired
    private BenefitTypeRepository repository;

    @Override
    public List<BenefitType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByBenefitTypeName()));
    }

    private Sort sortByBenefitTypeName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "benefitTypeName"));
    }

    @Override
    public void persist(BenefitType benefitType) {

        repository.save(benefitType);

    }

    @Override
    public void merge(BenefitType benefitType) {
        if (benefitType.getId() != null) {
            repository.save(benefitType);
        }
    }

    @Override
    public BenefitType findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(BenefitType benefitType) {
        repository.delete(benefitType);
    }
}
