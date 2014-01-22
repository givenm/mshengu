/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.education;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.education.Degree;

/**
 *
 * @author lucky
 */
public interface DegreeService {
    public List<Degree> findAll();
    public void persist(Degree degree);
    public void merge(Degree degree);
    public Degree findById(String id);
    public void delete(Degree degree);
}
