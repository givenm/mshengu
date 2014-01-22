/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products;

import java.util.List;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;

/**
 *
 * @author Luckbliss
 */
public interface SiteUnitService {

    public List<SiteUnit> findAll();

    public void persist(SiteUnit siteUnit);

    public void merge(SiteUnit siteUnit);

    public SiteUnit findById(String id);
    
     public SiteUnit findByUnitId(String unitId);

    public void delete(SiteUnit siteUnit);
    
    public UnitLocationLifeCycle getUnitCurrentLocation(String id);
   
    public String checkCoordinates(UnitServiceResource unitService);
}
