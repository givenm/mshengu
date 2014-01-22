/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.external;

import java.util.List;
import zm.hashcode.mshengu.domain.external.ContactUS;

/**
 *
 * @author Ferox
 */
public interface ContactUSService {

    public List<ContactUS> findAll();

    public void persist(ContactUS incident);

    public void merge(ContactUS incident);

    public ContactUS findById(String id);

    public void delete(ContactUS incident);
    
    public List<ContactUS> findAllClosed();
    
    public List<ContactUS> findAllOpen();
        
    public List<ContactUS> findByStatus(String statusName);
    
    public ContactUS findByRefNumber(String refNumber);
}
