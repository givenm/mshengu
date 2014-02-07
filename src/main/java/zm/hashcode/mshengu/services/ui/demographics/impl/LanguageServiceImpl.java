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
import zm.hashcode.mshengu.domain.ui.demographics.Language;
import zm.hashcode.mshengu.repository.ui.demographics.LanguageRepository;
import zm.hashcode.mshengu.services.ui.demographics.LanguageService;

/**
 *
 * @author lucky
 */
@Service
public class LanguageServiceImpl implements LanguageService{

    @Autowired
    private LanguageRepository repository;

    @Override
    public List<Language> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(Language language) {

        repository.save(language);

    }

    @Override
    public void merge(Language language) {
        if (language.getId() != null) {
            repository.save(language);
        }
    }

    @Override
    public Language findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Language language) {
        repository.delete(language);
    }
    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }
}
