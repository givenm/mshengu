/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.incident;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.incident.IncidentType;

/**
 *
 * @author Ferox
 */
public interface IncidentTypeRepository extends PagingAndSortingRepository<IncidentType, String> {
     public IncidentType findByName(String name);

   
}
