/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.kpianalysis;

import java.util.List;
import zm.hashcode.mshengu.domain.kpianalysis.KPA;

/**
 *
 * @author Luckbliss
 */
public interface KPAService {

    public List<KPA> findAll();

    public void persist(KPA kpi);

    public void merge(KPA kpi);

    public KPA findById(String id);

    public void delete(KPA kpi);

    public KPA findByTab(String name);
}
