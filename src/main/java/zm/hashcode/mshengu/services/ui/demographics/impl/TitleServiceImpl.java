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
import zm.hashcode.mshengu.domain.ui.demographics.Title;
import zm.hashcode.mshengu.repository.ui.demographics.TitleRepository;
import zm.hashcode.mshengu.services.ui.demographics.TitleService;

/**
 *
 * @author lucky
 */
@Service
public class TitleServiceImpl implements TitleService{

    @Autowired
    private TitleRepository repository;

    @Override
    public List<Title> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByTitle()));
    }

    @Override
    public void persist(Title title) {

        repository.save(title);

    }

    @Override
    public void merge(Title title) {
        if (title.getId() != null) {
            repository.save(title);
        }
    }

    @Override
    public Title findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Title title) {
        repository.delete(title);
    }
     private Sort sortByTitle() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "title"));
    }
}
