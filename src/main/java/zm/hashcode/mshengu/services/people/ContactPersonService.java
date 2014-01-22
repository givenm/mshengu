/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.people;

import java.util.List;
import zm.hashcode.mshengu.domain.people.ContactPerson;

/**
 *
 * @author Ferox
 */
public interface ContactPersonService {
    public List<ContactPerson> findAll();
    public void persist(ContactPerson contactPerson);
    public void merge(ContactPerson contactPerson);
    public ContactPerson findById(String id);
    public void delete(ContactPerson contactPerson);
    public ContactPerson findByLastName(String lastName);
    public ContactPerson findByParentId(String parentId);
    public ContactPerson findByEmail(String email);
}