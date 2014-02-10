/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.location;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.location.Contact;
import zm.hashcode.mshengu.repository.ui.location.ContactRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class ContactListTest extends AppTest{
    private ContactRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(ContactRepository.class);
        Contact name = new Contact.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(ContactRepository.class);
        Contact name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(ContactRepository.class);
        Contact name = repository.findOne(id);
        Contact newName = new Contact.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        Contact upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(ContactRepository.class);
        Contact name = repository.findOne(id);
        repository.delete(name);
        Contact deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
