/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products;

import java.util.List;
import zm.hashcode.mshengu.domain.products.UnitCleaningActivities;

/**
 *
 * @author Luckbliss
 */
public interface UnitCleaningActivitiesService {

    public List<UnitCleaningActivities> findAll();

    public void persist(UnitCleaningActivities unitCleaningActivities);

    public void merge(UnitCleaningActivities unitCleaningActivities);

    public UnitCleaningActivities findById(String id);

    public void delete(UnitCleaningActivities unitCleaningActivities);
}
