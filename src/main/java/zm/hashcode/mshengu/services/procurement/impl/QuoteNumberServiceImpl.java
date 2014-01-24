/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.procurement.QuoteNumber;
import zm.hashcode.mshengu.repository.procurement.QuoteNumberRepository;
import zm.hashcode.mshengu.services.procurement.QuoteNumberService;

/**
 *
 * @author Luckbliss
 */
@Service
public class QuoteNumberServiceImpl implements QuoteNumberService {

    @Autowired
    private QuoteNumberRepository repository;

    @Override
    public List<QuoteNumber> findAll() {
        return ImmutableList.copyOf(repository.findAll());
    }

    @Override
    public void persist(QuoteNumber quoteNumber) {
        repository.save(quoteNumber);

    }

    @Override
    public void merge(QuoteNumber quoteNumber) {
        if (quoteNumber.getId() != null) {
            repository.save(quoteNumber);
        }
    }

    @Override
    public QuoteNumber findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(QuoteNumber quoteNumber) {
        repository.delete(quoteNumber);
    }
}
