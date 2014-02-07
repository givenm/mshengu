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
import zm.hashcode.mshengu.domain.ui.job.SalaryGrade;
import zm.hashcode.mshengu.repository.ui.job.SalaryGradeRepository;
import zm.hashcode.mshengu.services.ui.job.SalaryGradeService;

/**
 *
 * @author lucky
 */
@Service
public class SalaryGradeServiceImpl implements SalaryGradeService {

    @Autowired
    private SalaryGradeRepository repository;

    @Override
    public List<SalaryGrade> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByGradeName()));
    }

    private Sort sortByGradeName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "gradeName"));
    }

    @Override
    public void persist(SalaryGrade salaryGrade) {

        repository.save(salaryGrade);

    }

    @Override
    public void merge(SalaryGrade salaryGrade) {
        if (salaryGrade.getId() != null) {
            repository.save(salaryGrade);
        }
    }

    @Override
    public SalaryGrade findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(SalaryGrade salaryGrade) {
        repository.delete(salaryGrade);
    }
}
