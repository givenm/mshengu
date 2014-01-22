/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products;

import java.util.List;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;

/**
 *
 * @author Ferox
 */
public interface SiteServiceContractLifeCycleService {

    public List<SiteServiceContractLifeCycle> findAll();

    public void persist(SiteServiceContractLifeCycle siteServiceContract);

    public void merge(SiteServiceContractLifeCycle siteServiceContract);

    public SiteServiceContractLifeCycle findById(String id);

    public void delete(SiteServiceContractLifeCycle siteServiceContract);
    public List<SiteServiceContractLifeCycle> findAllSorted();
}
