/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.CostCentreCategoryType;

/**
 *
 * @author Luckbliss
 */
public interface CostCentreCategoryTypeService {
    public List<CostCentreCategoryType> findAll();
    public void persist(CostCentreCategoryType categoryType);
    public void merge(CostCentreCategoryType categoryType);
    public CostCentreCategoryType findById(String id);
    public void delete(CostCentreCategoryType categoryType);
}
