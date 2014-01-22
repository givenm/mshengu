/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.demographics.LanguageProficiency;
import zm.hashcode.mshengu.repository.ui.demographics.LanguageProficiencyRepository;
import zm.hashcode.mshengu.services.ui.demographics.LanguageProficiencyService;

/**
 *
 * @author lucky
 */
@Service
public class LanguageProficiencyServiceImpl implements LanguageProficiencyService{

   @Autowired
    private LanguageProficiencyRepository repository;

    @Override
    public List<LanguageProficiency> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByProficiency()));
    }

    @Override
    public void persist(LanguageProficiency languageProficiency) {

        repository.save(languageProficiency);

    }

    @Override
    public void merge(LanguageProficiency languageProficiency) {
        if (languageProficiency.getId() != null) {
            repository.save(languageProficiency);
        }
    }

    @Override
    public LanguageProficiency findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(LanguageProficiency languageProficiency) {
        repository.delete(languageProficiency);
    }
    private Sort sortByProficiency() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "proficiency"));
    }
}
