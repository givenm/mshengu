/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.kpianalysis;

import java.util.List;
import zm.hashcode.mshengu.domain.kpianalysis.KPI;

/**
 *
 * @author Luckbliss
 */
public interface KPIService {

    public List<KPI> findAll();

    public void persist(KPI kpi);

    public void merge(KPI kpi);

    public KPI findById(String id);

    public void delete(KPI kpi);

    public KPI findByTab(String name);
}
