/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util.Impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.util.ItemCategoryType;
import zm.hashcode.mshengu.repository.ui.util.ItemCategoryTypeRepository;
import zm.hashcode.mshengu.services.ui.util.ItemCategoryTypeService;

/**
 *
 * @author Luckbliss
 */
@Service
public class ItemCategoryTypeServiceImpl implements ItemCategoryTypeService {

    @Autowired
    private ItemCategoryTypeRepository repository;

    @Override
    public List<ItemCategoryType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(ItemCategoryType itemCategoryType) {
        repository.save(itemCategoryType);
    }

    @Override
    public void merge(ItemCategoryType itemCategoryType) {
        if (itemCategoryType.getId() != null) {
            repository.save(itemCategoryType);
        }
    }

    @Override
    public ItemCategoryType findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(ItemCategoryType itemCategoryType) {
        repository.delete(itemCategoryType);
    }
}
