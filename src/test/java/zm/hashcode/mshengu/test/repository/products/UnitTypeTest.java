/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.products;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.repository.products.UnitTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class UnitTypeTest extends AppTest{
    private UnitTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(UnitTypeRepository.class);
        UnitType name = new UnitType.Builder("name1").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(UnitTypeRepository.class);
        UnitType name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "name1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(UnitTypeRepository.class);
        UnitType name = repository.findOne(id);
        UnitType newName = new UnitType.Builder("name2").id(name.getId()).build();
        repository.save(newName);
        UnitType upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "name2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(UnitTypeRepository.class);
        UnitType name = repository.findOne(id);
        repository.delete(name);
        UnitType deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
