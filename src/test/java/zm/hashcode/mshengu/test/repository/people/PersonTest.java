/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.people;

import junit.framework.Assert;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.repository.people.PersonRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class PersonTest extends AppTest {

    private PersonRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(PersonRepository.class);
        Person firstname = new Person.Builder("firstname1").build();
        repository.save(firstname);
        id = firstname.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(PersonRepository.class);
        Person firstname = repository.findOne(id);
        Assert.assertEquals(firstname.getFirstname(), "firstname1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(PersonRepository.class);
        Person firstname = repository.findOne(id);
        Person newFirstname = new Person.Builder("firstname2").id(firstname.getId()).build();
        repository.save(newFirstname);
        Person upFirstname = repository.findOne(id);
        Assert.assertEquals(upFirstname.getFirstname(), "firstname2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(PersonRepository.class);
        Person firstname = repository.findOne(id);
        repository.delete(firstname);
        Person deletedFirstname = repository.findOne(id);
        Assert.assertNull(deletedFirstname);
    }
}
