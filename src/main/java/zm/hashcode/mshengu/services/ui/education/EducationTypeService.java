/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.education;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.education.EducationType;

/**
 *
 * @author lucky
 */
public interface EducationTypeService {
    public List<EducationType> findAll();
    public void persist(EducationType educationType);
    public void merge(EducationType educationType);
    public EducationType findById(String id);
    public void delete(EducationType educationType);
}
