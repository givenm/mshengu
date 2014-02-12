/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.kpianalysis;

import java.util.List;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;

/**
 *
 * @author Luckbliss
 */
public interface KPIItemService {
    public List<KPIItem> findAll();
    public void persist(KPIItem item);
    public void merge(KPIItem item);
    public KPIItem findById(String id);
    public void delete(KPIItem item);
}
