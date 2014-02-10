/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.demographics.MaritalStatus;

/**
 *
 * @author lucky
 */
public interface MaritalStatusService {
    public List<MaritalStatus> findAll();
    public void persist(MaritalStatus maritalStatusList);
    public void merge(MaritalStatus maritalStatusList);
    public MaritalStatus findById(String id);
    public void delete(MaritalStatus maritalStatusList);
}
