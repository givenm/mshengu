/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.documents;

import java.util.List;
import zm.hashcode.mshengu.domain.documents.Documents;

/**
 *
 * @author Luckbliss
 */
public interface DocumentsService {
    public List<Documents> findAll();
    public void persist(Documents documents);
    public void merge(Documents documents);
    public Documents findById(String id);
    public void delete(Documents documents);
}
