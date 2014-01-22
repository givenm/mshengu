/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.products;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.repository.products.SiteUnitRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class SiteUnitTest extends AppTest{
    private SiteUnitRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(SiteUnitRepository.class);
        //SiteUnit siteUnit = new SiteUnit.Builder("siteunit1").build();
        //repository.save(siteUnit);
        //id = siteUnit.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(SiteUnitRepository.class);
        SiteUnit siteUnit = repository.findOne(id);
        Assert.assertEquals(siteUnit.getDescription(), "siteunit1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(SiteUnitRepository.class);
        SiteUnit siteUnit = repository.findOne(id);
   // SiteUnit newSiteUnit = new SiteUnit.Builder("name2").id(siteUnit.getId()).build();
     //   repository.save(newSiteUnit);
        SiteUnit upSiteUnit = repository.findOne(id);
        Assert.assertEquals(upSiteUnit.getDescription(), "name2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(SiteUnitRepository.class);
        SiteUnit siteUnit = repository.findOne(id);
        repository.delete(siteUnit);
        SiteUnit deletedSiteUnit = repository.findOne(id);
        Assert.assertNull(deletedSiteUnit);
    }
}
