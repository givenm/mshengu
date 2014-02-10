/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.Country;

/**
 *
 * @author Ferox
 */
public interface CountryService {
    public List<Country> findAll();
    public void persist(Country positionType);
    public void merge(Country positionType);
    public Country findById(String id);
    public void delete(Country positionType);
    public Country findByName(String name);
}
