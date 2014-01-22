/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.location;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.location.LocationType;
import zm.hashcode.mshengu.repository.ui.location.LocationTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class LocationTypeTest extends AppTest {

    private LocationTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(LocationTypeRepository.class);
        LocationType name = new LocationType.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(LocationTypeRepository.class);
        LocationType name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(LocationTypeRepository.class);
        LocationType name = repository.findOne(id);
        LocationType newName = new LocationType.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        LocationType upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(LocationTypeRepository.class);
        LocationType name = repository.findOne(id);
        repository.delete(name);
        LocationType deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
