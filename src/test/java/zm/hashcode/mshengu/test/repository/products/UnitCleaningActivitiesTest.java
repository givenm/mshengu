/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.products;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.products.UnitCleaningActivities;
import zm.hashcode.mshengu.repository.products.UnitCleaningActivitiesRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class UnitCleaningActivitiesTest extends AppTest {

    private UnitCleaningActivitiesRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(UnitCleaningActivitiesRepository.class);
        UnitCleaningActivities name = new UnitCleaningActivities.Builder("name1").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(UnitCleaningActivitiesRepository.class);
        UnitCleaningActivities name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "name1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(UnitCleaningActivitiesRepository.class);
        UnitCleaningActivities name = repository.findOne(id);
        UnitCleaningActivities newName = new UnitCleaningActivities.Builder("name2").id(name.getId()).build();
        repository.save(newName);
        UnitCleaningActivities upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "name2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(UnitCleaningActivitiesRepository.class);
        UnitCleaningActivities firstname = repository.findOne(id);
        repository.delete(firstname);
        UnitCleaningActivities deletedFirstname = repository.findOne(id);
        Assert.assertNull(deletedFirstname);
    }
}
