/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.customers;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.repository.customer.CustomerRepository;
import zm.hashcode.mshengu.repository.people.PersonRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class CustomerTest extends AppTest {

    private CustomerRepository repository;
    private PersonRepository personRepository;
    private String id;
    private String personId;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(CustomerRepository.class);
        personRepository = ctx.getBean(PersonRepository.class);
        Person person = new Person.Builder("Lucky").build();
        personRepository.save(person);
        /*ContactPerson contactPerson = personRepository.findOne(person.getId());
        Customer name = new Customer.Builder(contactPerson).build();
        repository.save(name);
        id = name.getId();
        personId = person.getId();*/
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(CustomerRepository.class);
        personRepository = ctx.getBean(PersonRepository.class);
        Customer name = repository.findOne(id);
        Assert.assertEquals(name.getContactPerson(), personRepository.findOne(personId));
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(CustomerRepository.class);
        personRepository = ctx.getBean(PersonRepository.class);
        Customer name = repository.findOne(id);
        Person contactPerson = new Person.Builder("Moyo").id(personRepository.findOne(personId).getId()).build();
        personRepository.save(contactPerson);
//        Customer newName = new Customer.Builder(contactPerson).id(name.getId()).build();
      //  repository.save(newName);
        Customer upName = repository.findOne(id);
        Assert.assertEquals(upName.getContactPerson().getFirstName(), "Moyo");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(CustomerRepository.class);
        Customer firstname = repository.findOne(id);
        repository.delete(firstname);
        Customer deletedFirstname = repository.findOne(id);
        Assert.assertNull(deletedFirstname);
    }
}
