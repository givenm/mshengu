/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products;

import java.util.List;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;

/**
 *
 * @author Ferox
 */
public interface UnitLocationLifeCycleService {

    public List<UnitLocationLifeCycle> findAll();

    public void persist(UnitLocationLifeCycle unitLocationLifeCycle);

    public void merge(UnitLocationLifeCycle unitLocationLifeCycle);

    public UnitLocationLifeCycle findById(String id);

    public void delete(UnitLocationLifeCycle unitLocationLifeCycle);
    public List<UnitLocationLifeCycle> findAllSorted();
    
}
