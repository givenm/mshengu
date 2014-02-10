/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.fleet;

import junit.framework.Assert;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.repository.people.PersonRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class AddDrivers extends AppTest {

    private PersonRepository personRepository;

//    @Test
    public void testCreate() {
        personRepository = ctx.getBean(PersonRepository.class);
        Person p1 = new Person.Builder("moyo").build();
        Person p2 = new Person.Builder("murau").build();
        Person p3 = new Person.Builder("nyau").build();
        Person p4 = new Person.Builder("forensic").build();
        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);
        personRepository.save(p4);
        Assert.assertEquals(p1.getLastname(), "moyo");
    }
}
