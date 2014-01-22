/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.people;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.people.ContactPerson;

/**
 *
 * @author Ferox
 */
public interface ContactPersonRepository extends PagingAndSortingRepository<ContactPerson, String> {

    public ContactPerson findByLastName(String lastName);
    public ContactPerson findByParentId(String parentId);
    public ContactPerson findByEmailAddress(String email);
}