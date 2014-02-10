/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet;

import java.util.List;
import zm.hashcode.mshengu.domain.fleet.TruckCategory;

/**
 *
 * @author Ferox
 */
public interface TruckCategoryService {
    public List<TruckCategory> findAll();
    public void persist(TruckCategory truck);
    public void merge(TruckCategory truck);
    public TruckCategory findById(String id);
    public void delete(TruckCategory truck);
    public TruckCategory findByCategoryName(String categoryName);
}
