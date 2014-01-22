/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.chemical.Impl;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.chemical.ChemicalCost;
import zm.hashcode.mshengu.repository.chemical.ChemicalCostRepository;
import zm.hashcode.mshengu.services.chemical.ChemicalCostService;

/**
 *
 * @author geek
 */
@Service
public class ChemicalCostServiceImpl implements ChemicalCostService {

    @Autowired
    private ChemicalCostRepository repository;

    @Override
    public List<ChemicalCost> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByStartDate()));
    }

    private Sort sortByStartDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "transactionDate"));
    }

    @Override
    public void persist(ChemicalCost entity) {
        repository.save(entity);
    }

    @Override
    public void merge(ChemicalCost entity) {
        if (entity.getId() != null) {
            repository.save(entity);
        }
    }

    @Override
    public ChemicalCost findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(ChemicalCost entity) {
        repository.delete(entity);
    }
}
