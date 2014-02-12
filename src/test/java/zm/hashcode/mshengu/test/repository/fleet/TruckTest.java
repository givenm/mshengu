/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.fleet;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.repository.fleet.TruckRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class TruckTest extends AppTest {

    private TruckRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(TruckRepository.class);
        Truck name = new Truck.Builder("numberplate1").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(TruckRepository.class);
        Truck name = repository.findOne(id);
        Assert.assertEquals(name.getNumberPlate(), "numberplate1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(TruckRepository.class);
        Truck name = repository.findOne(id);
        Truck newName = new Truck.Builder("numberplate2").id(name.getId()).build();
        repository.save(newName);
        Truck upName = repository.findOne(id);
        Assert.assertEquals(upName.getNumberPlate(), "numberplate2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(TruckRepository.class);
        Truck firstname = repository.findOne(id);
//        repository.delete(firstname);
        Truck deletedFirstname = repository.findOne(id);
        Assert.assertNull(deletedFirstname);
    }
}
