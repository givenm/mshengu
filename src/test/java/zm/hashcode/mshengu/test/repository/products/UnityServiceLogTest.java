/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.products;

import java.util.Date;
import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.products.UnitServiceLog;
import zm.hashcode.mshengu.repository.products.UnitServiceLogRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class UnityServiceLogTest extends AppTest {

    private UnitServiceLogRepository repository;
    private String id;
    private Date firstDate = new Date();
    private Date upDateDate = new Date();

//    @Test
    public void testCreate() {
        repository = ctx.getBean(UnitServiceLogRepository.class);
        UnitServiceLog name = new UnitServiceLog.Builder(firstDate).build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(UnitServiceLogRepository.class);
        UnitServiceLog name = repository.findOne(id);
        Assert.assertEquals(name.getServiceDate(), firstDate);
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(UnitServiceLogRepository.class);
        UnitServiceLog name = repository.findOne(id);
        UnitServiceLog newName = new UnitServiceLog.Builder(upDateDate).id(name.getId()).build();
        repository.save(newName);
        UnitServiceLog upName = repository.findOne(id);
        Assert.assertEquals(upName.getServiceDate(), upDateDate);
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(UnitServiceLogRepository.class);
        UnitServiceLog firstname = repository.findOne(id);
        repository.delete(firstname);
        UnitServiceLog deletedFirstname = repository.findOne(id);
        Assert.assertNull(deletedFirstname);
    }
}
