/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.demographics.LanguageProficiency;

/**
 *
 * @author lucky
 */
public interface LanguageProficiencyService {
    public List<LanguageProficiency> findAll();
    public void persist(LanguageProficiency languageProficiency);
    public void merge(LanguageProficiency languageProficiency);
    public LanguageProficiency findById(String id);
    public void delete(LanguageProficiency languageProficiency);
}
