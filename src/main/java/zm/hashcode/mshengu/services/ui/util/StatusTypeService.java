/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.StatusType;

/**
 *
 * @author boniface
 */
public interface StatusTypeService {
    public List<StatusType> findAll();
    public void persist(StatusType statusType);
    public void merge(StatusType staus);
    public StatusType findById(String id);
    public void delete(StatusType statusType);  
    
    public StatusType findByName(String name);
}
