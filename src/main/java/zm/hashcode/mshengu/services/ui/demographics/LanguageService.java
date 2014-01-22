/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.demographics.Language;

/**
 *
 * @author lucky
 */
public interface LanguageService {
    public List<Language> findAll();
    public void persist(Language language);
    public void merge(Language language);
    public Language findById(String id);
    public void delete(Language language);
}
