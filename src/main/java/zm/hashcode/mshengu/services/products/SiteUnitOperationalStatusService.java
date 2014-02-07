/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products;

import java.util.List;
import zm.hashcode.mshengu.domain.products.SiteUnitOperationalStatus;

/**
 *
 * @author Ferox
 */
public interface SiteUnitOperationalStatusService {

    public List<SiteUnitOperationalStatus> findAll();

    public void persist(SiteUnitOperationalStatus siteUnitOperationalStatus);

    public void merge(SiteUnitOperationalStatus siteUnitOperationalStatus);

    public SiteUnitOperationalStatus findById(String id);

    public void delete(SiteUnitOperationalStatus siteUnitOperationalStatus);
    
    public SiteUnitOperationalStatus findByName(String name);
}
