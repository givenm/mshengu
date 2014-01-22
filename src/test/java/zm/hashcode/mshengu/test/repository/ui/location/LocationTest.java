/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.location;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.location.Location;
import zm.hashcode.mshengu.repository.ui.location.LocationRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class LocationTest extends AppTest{
    private LocationRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(LocationRepository.class);
        Location name = new Location.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(LocationRepository.class);
        Location name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(LocationRepository.class);
        Location name = repository.findOne(id);
        Location newName = new Location.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        Location upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(LocationRepository.class);
        Location name = repository.findOne(id);
        repository.delete(name);
        Location deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
