/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.products.UnitType;

/**
 *
 * @author Luckbliss
 */
public interface UnitTypeService {

    public List<UnitType> findAll();

    public void persist(UnitType unitType);

    public void merge(UnitType unitType);

    public UnitType findById(String id);

    public void delete(UnitType unitType);
    
    public UnitType findByName(String name);
}
