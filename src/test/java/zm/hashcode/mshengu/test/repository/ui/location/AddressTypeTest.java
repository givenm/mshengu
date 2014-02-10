/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.location;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.location.AddressType;
import zm.hashcode.mshengu.repository.ui.location.AddressTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class AddressTypeTest extends AppTest {

    private AddressTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(AddressTypeRepository.class);
        AddressType name = new AddressType.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(AddressTypeRepository.class);
        AddressType name = repository.findOne(id);
        Assert.assertEquals(name.getAddressTypeName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(AddressTypeRepository.class);
        AddressType name = repository.findOne(id);
        AddressType newName = new AddressType.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        AddressType upName = repository.findOne(id);
        Assert.assertEquals(upName.getAddressTypeName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(AddressTypeRepository.class);
        AddressType name = repository.findOne(id);
        repository.delete(name);
        AddressType deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
