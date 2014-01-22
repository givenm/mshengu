/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.util.Currency;

/**
 *
 * @author lucky
 */
public interface CurrencyService {
    public List<Currency> findAll();
    public void persist(Currency currency);
    public void merge(Currency currency);
    public Currency findById(String id);
    public void delete(Currency currency);
}
