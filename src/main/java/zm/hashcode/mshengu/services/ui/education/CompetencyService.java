/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.education;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.education.Competency;

/**
 *
 * @author lucky
 */
public interface CompetencyService {
    public List<Competency> findAll();
    public void persist(Competency competency);
    public void merge(Competency competency);
    public Competency findById(String id);
    public void delete(Competency competency);
}
