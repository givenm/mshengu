/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products;

import java.util.List;
import zm.hashcode.mshengu.domain.products.UnitServiceLog;

/**
 *
 * @author Luckbliss
 */
public interface UnitServiceLogService {

    public List<UnitServiceLog> findAll();

    public void persist(UnitServiceLog unityServiceLog);

    public void merge(UnitServiceLog unityServiceLog);

    public UnitServiceLog findById(String id);

    public void delete(UnitServiceLog unityServiceLog);
}
