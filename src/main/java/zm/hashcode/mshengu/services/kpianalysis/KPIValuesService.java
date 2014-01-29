/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.kpianalysis;

import java.util.List;
import zm.hashcode.mshengu.domain.kpianalysis.KPIValues;

/**
 *
 * @author Luckbliss
 */
public interface KPIValuesService {
    public List<KPIValues> findAll();
    public void persist(KPIValues item);
    public void merge(KPIValues item);
    public KPIValues findById(String id);
    public void delete(KPIValues item);
}
