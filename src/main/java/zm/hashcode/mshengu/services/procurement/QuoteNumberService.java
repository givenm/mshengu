/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement;

import java.util.List;
import zm.hashcode.mshengu.domain.procurement.QuoteNumber;

/**
 *
 * @author Luckbliss
 */
public interface QuoteNumberService {

    public List<QuoteNumber> findAll();

    public void persist(QuoteNumber quoteNumber);

    public void merge(QuoteNumber quoteNumber);

    public QuoteNumber findById(String id);

    public void delete(QuoteNumber quoteNumber);
}
