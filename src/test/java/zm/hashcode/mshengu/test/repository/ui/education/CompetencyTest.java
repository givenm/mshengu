/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.education;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.education.Competency;
import zm.hashcode.mshengu.repository.ui.education.CompetencyRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class CompetencyTest extends AppTest {

    private CompetencyRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(CompetencyRepository.class);
        Competency name = new Competency.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(CompetencyRepository.class);
        Competency name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(CompetencyRepository.class);
        Competency name = repository.findOne(id);
        Competency newName = new Competency.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        Competency upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(CompetencyRepository.class);
        Competency name = repository.findOne(id);
        repository.delete(name);
        Competency deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
