/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;

/**
 *
 * @author Luckbliss
 */
public interface CostCentreTypeService {

    public List<CostCentreType> findAll();

    public void persist(CostCentreType costCentreType);

    public void merge(CostCentreType costCentreType);

    public CostCentreType findById(String id);

    public void delete(CostCentreType costCentreType);
}
