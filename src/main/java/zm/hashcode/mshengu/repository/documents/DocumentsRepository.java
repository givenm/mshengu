/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.documents;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import zm.hashcode.mshengu.domain.documents.Documents;

/**
 *
 * @author Luckbliss
 */
@Repository
public interface DocumentsRepository extends PagingAndSortingRepository<Documents, String>{
    
}
