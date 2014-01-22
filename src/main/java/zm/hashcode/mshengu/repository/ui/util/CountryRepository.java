/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.util;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.util.Country;
/**
 *
 * @author lucky
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, String> {
     public Country findByName(String name);
}
