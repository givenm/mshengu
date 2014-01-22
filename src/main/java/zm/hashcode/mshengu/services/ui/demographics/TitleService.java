/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.demographics.Title;

/**
 *
 * @author lucky
 */
public interface TitleService {
    public List<Title> findAll();
    public void persist(Title titleList);
    public void merge(Title titleList);
    public Title findById(String id);
    public void delete(Title titleList);
}
