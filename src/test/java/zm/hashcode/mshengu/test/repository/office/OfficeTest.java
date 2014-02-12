/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.office;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.office.Office;
import zm.hashcode.mshengu.repository.office.OfficeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class OfficeTest extends AppTest {

    private OfficeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(OfficeRepository.class);
        Office name = new Office.Builder("name1").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(OfficeRepository.class);
        Office name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "name1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(OfficeRepository.class);
        Office name = repository.findOne(id);
        Office newName = new Office.Builder("name2").id(name.getId()).build();
        repository.save(newName);
        Office upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "name2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(OfficeRepository.class);
        Office firstname = repository.findOne(id);
        repository.delete(firstname);
        Office deletedFirstname = repository.findOne(id);
        Assert.assertNull(deletedFirstname);
    }
}
