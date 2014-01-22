/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement;

import java.util.List;
import zm.hashcode.mshengu.domain.procurement.RequestForQuote;

/**
 *
 * @author Luckbliss
 */
public interface RequestForQuoteService {

    public List<RequestForQuote> findAll();

    public void persist(RequestForQuote request);

    public void merge(RequestForQuote request);

    public RequestForQuote findById(String id);

    public RequestForQuote findByRfqNumber(String rfqNumber);

    public void delete(RequestForQuote request);
}
