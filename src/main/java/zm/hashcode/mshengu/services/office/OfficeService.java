/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.office;

import java.util.List;
import zm.hashcode.mshengu.domain.office.Office;

/**
 *
 * @author lucky
 */
public interface OfficeService {
    public List<Office> findAll();
    public void persist(Office office);
    public void merge(Office office);
    public Office findById(String id);
    public void delete(Office office);
}
