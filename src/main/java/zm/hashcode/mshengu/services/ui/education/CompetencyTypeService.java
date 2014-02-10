/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.education;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.education.CompetencyType;

/**
 *
 * @author lucky
 */
public interface CompetencyTypeService {
    public List<CompetencyType> findAll();
    public void persist(CompetencyType competencyType);
    public void merge(CompetencyType competencyType);
    public CompetencyType findById(String id);
    public void delete(CompetencyType competencyType);
}
