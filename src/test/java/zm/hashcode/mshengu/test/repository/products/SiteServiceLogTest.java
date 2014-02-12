/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.products;

import java.util.Date;
import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;
import zm.hashcode.mshengu.repository.products.SiteServiceLogRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class SiteServiceLogTest extends AppTest {

    private SiteServiceLogRepository repository;
    private String id;
    private Date firstDate = new Date();
    private Date upDateDate = new Date();

//    @Test
    public void testCreate() {
        repository = ctx.getBean(SiteServiceLogRepository.class);
        SiteServiceLog name = new SiteServiceLog.Builder(firstDate).build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(SiteServiceLogRepository.class);
        SiteServiceLog name = repository.findOne(id);
        Assert.assertEquals(name.getServiceDate(), firstDate);
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(SiteServiceLogRepository.class);
        SiteServiceLog name = repository.findOne(id);
        SiteServiceLog newName = new SiteServiceLog.Builder(upDateDate).id(name.getId()).build();
        repository.save(newName);
        SiteServiceLog upName = repository.findOne(id);
        Assert.assertEquals(upName.getServiceDate(), upDateDate);
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(SiteServiceLogRepository.class);
        SiteServiceLog firstname = repository.findOne(id);
        repository.delete(firstname);
        SiteServiceLog deletedFirstname = repository.findOne(id);
        Assert.assertNull(deletedFirstname);
    }
}
