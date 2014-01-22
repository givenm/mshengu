/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.demographics.IdentificationType;

/**
 *
 * @author lucky
 */
public interface IdentificationTypeService {
    public List<IdentificationType> findAll();
    public void persist(IdentificationType identificationType);
    public void merge(IdentificationType identificationType);
    public IdentificationType findById(String id);
    public void delete(IdentificationType identificationType);
}
