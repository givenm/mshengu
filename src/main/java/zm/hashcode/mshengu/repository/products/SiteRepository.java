/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.products;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author Ferox
 */
public interface SiteRepository extends PagingAndSortingRepository<Site, String> {
     public Site findByName(String name);
     public List<Site> findByName(String name, String fieldName);
//    public SiteServiceContractLifeCycle getSitetCurrentContract(String id);

   
}
