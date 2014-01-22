/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement;

import java.util.List;
import zm.hashcode.mshengu.domain.procurement.ResponseToRFQ;

/**
 *
 * @author Luckbliss
 */
public interface ResponseToRFQService {

    public List<ResponseToRFQ> findAll();

    public void persist(ResponseToRFQ request);

    public void merge(ResponseToRFQ request);

    public ResponseToRFQ findById(String id);

    public void delete(ResponseToRFQ request);
}
