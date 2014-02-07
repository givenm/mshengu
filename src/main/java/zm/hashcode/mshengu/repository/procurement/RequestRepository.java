/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.procurement;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public interface RequestRepository extends PagingAndSortingRepository<Request, String>, RequestRepositoryCustom {

    public Request findByOrderNumber(String orderNumber);
}
