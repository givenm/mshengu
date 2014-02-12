/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.education;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.education.Degree;
import zm.hashcode.mshengu.repository.ui.education.DegreeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class DegreeTest extends AppTest {

    private DegreeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(DegreeRepository.class);
        Degree name = new Degree.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(DegreeRepository.class);
        Degree name = repository.findOne(id);
        Assert.assertEquals(name.getDegreeName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(DegreeRepository.class);
        Degree name = repository.findOne(id);
        Degree newName = new Degree.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        Degree upName = repository.findOne(id);
        Assert.assertEquals(upName.getDegreeName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(DegreeRepository.class);
        Degree name = repository.findOne(id);
        repository.delete(name);
        Degree deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
