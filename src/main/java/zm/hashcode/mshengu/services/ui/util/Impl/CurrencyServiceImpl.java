/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util.Impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.util.Currency;
import zm.hashcode.mshengu.repository.ui.util.CurrencyRepository;
import zm.hashcode.mshengu.services.ui.util.CurrencyService;

/**
 *
 * @author lucky
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository repository;

    @Override
    public List<Currency> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(Currency currency) {
        repository.save(currency);
    }

    @Override
    public void merge(Currency currency) {
        if (currency.getId() != null) {
            repository.save(currency);
        }
    }

    @Override
    public Currency findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Currency currency) {
        repository.delete(currency);
    }
}
