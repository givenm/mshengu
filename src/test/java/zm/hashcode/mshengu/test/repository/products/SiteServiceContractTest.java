/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.products;

import java.util.Date;
import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;
import zm.hashcode.mshengu.repository.products.SiteServiceContractLifeCycleRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class SiteServiceContractTest extends AppTest {

    private SiteServiceContractLifeCycleRepository repository;
    private String id;
    private Date firstDate = new Date();
    private Date upDateDate = new Date();

//    @Test
    public void testCreate() {
        repository = ctx.getBean(SiteServiceContractLifeCycleRepository.class);
        SiteServiceContractLifeCycle siteServiceContractLifeCycle = new SiteServiceContractLifeCycle.Builder(firstDate).build();
        repository.save(siteServiceContractLifeCycle);
        id = siteServiceContractLifeCycle.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(SiteServiceContractLifeCycleRepository.class);
        SiteServiceContractLifeCycle siteServiceContractLifeCycle = repository.findOne(id);
        Assert.assertEquals(siteServiceContractLifeCycle.getDateofAction(), firstDate);
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(SiteServiceContractLifeCycleRepository.class);
        SiteServiceContractLifeCycle siteServiceContractLifeCycle = repository.findOne(id);
        SiteServiceContractLifeCycle newName = new SiteServiceContractLifeCycle.Builder(upDateDate).id(siteServiceContractLifeCycle.getId()).build();
        repository.save(newName);
        SiteServiceContractLifeCycle upName = repository.findOne(id);
        Assert.assertEquals(upName.getDateofAction(), upDateDate);
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(SiteServiceContractLifeCycleRepository.class);
        SiteServiceContractLifeCycle firstsiteServiceContractLifeCycle = repository.findOne(id);
        repository.delete(firstsiteServiceContractLifeCycle);
        SiteServiceContractLifeCycle deletedFirstsiteServiceContractLifeCycle = repository.findOne(id);
        Assert.assertNull(deletedFirstsiteServiceContractLifeCycle);
    }
}
