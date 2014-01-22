/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.util.DisciplineActionType;

/**
 *
 * @author lucky
 */
public interface DisciplineActionTypeService {
    public List<DisciplineActionType> findAll();
    public void persist(DisciplineActionType disciplineActionTypeList);
    public void merge(DisciplineActionType disciplineActionTypeList);
    public DisciplineActionType findById(String id);
    public void delete(DisciplineActionType disciplineActionTypeList);
}
