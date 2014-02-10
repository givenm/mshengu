/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.demographics.Gender;

/**
 *
 * @author lucky
 */
public interface GenderService {
    public List<Gender> findAll();
    public void persist(Gender genderList);
    public void merge(Gender genderList);
    public Gender findById(String id);
    public void delete(Gender genderList);
}
