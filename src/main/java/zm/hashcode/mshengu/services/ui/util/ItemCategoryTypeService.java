/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.ItemCategoryType;

/**
 *
 * @author Luckbliss
 */
public interface ItemCategoryTypeService {

    public List<ItemCategoryType> findAll();

    public void persist(ItemCategoryType itemCategoryType);

    public void merge(ItemCategoryType itemCategoryType);

    public ItemCategoryType findById(String id);

    public void delete(ItemCategoryType itemCategoryType);
}
