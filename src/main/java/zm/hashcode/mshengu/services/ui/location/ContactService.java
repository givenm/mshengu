/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.location;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.location.Contact;

/**
 *
 * @author lucky
 */
public interface ContactService {
    public List<Contact> findAll();
    public void persist(Contact contactList);
    public void merge(Contact contactList);
    public Contact findById(String id);
    public void delete(Contact contactList);
}
