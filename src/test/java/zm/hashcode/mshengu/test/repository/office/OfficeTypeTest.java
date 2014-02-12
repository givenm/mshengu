/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.office;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.office.OfficeType;
import zm.hashcode.mshengu.repository.office.OfficeTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class OfficeTypeTest extends AppTest {

    private OfficeTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(OfficeTypeRepository.class);
        OfficeType name = new OfficeType.Builder("name1").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(OfficeTypeRepository.class);
        OfficeType name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "name1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(OfficeTypeRepository.class);
        OfficeType name = repository.findOne(id);
        OfficeType newName = new OfficeType.Builder("name2").id(name.getId()).build();
        repository.save(newName);
        OfficeType upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "name2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(OfficeTypeRepository.class);
        OfficeType firstname = repository.findOne(id);
        repository.delete(firstname);
        OfficeType deletedFirstname = repository.findOne(id);
        Assert.assertNull(deletedFirstname);
    }
}
