/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.products;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.repository.products.SiteRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class SiteTest extends AppTest {

    private SiteRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(SiteRepository.class);
        Site name = new Site.Builder("name1").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(SiteRepository.class);
        Site name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "name1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(SiteRepository.class);
        Site name = repository.findOne(id);
        Site newName = new Site.Builder("name2").id(name.getId()).build();
        repository.save(newName);
        Site upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "name2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(SiteRepository.class);
        Site name = repository.findOne(id);
        repository.delete(name);
        Site deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }

    public void attachUnit() {
    }
}
