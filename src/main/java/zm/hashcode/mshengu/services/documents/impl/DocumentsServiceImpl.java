/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.documents.impl;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.documents.Documents;
import zm.hashcode.mshengu.repository.documents.DocumentsRepository;
import zm.hashcode.mshengu.services.documents.DocumentsService;

/**
 *
 * @author Luckbliss
 */
@Service
public class DocumentsServiceImpl implements DocumentsService {

    @Autowired
    private DocumentsRepository repository;

    @Override
    public List<Documents> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(Documents documents) {

        repository.save(documents);

    }

    @Override
    public void merge(Documents documents) {
        if (documents.getId() != null) {
            repository.save(documents);
        }
    }

    @Override
    public Documents findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Documents documents) {
        repository.delete(documents);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "name"));
    }
}
