/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.education;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.education.EducationType;
import zm.hashcode.mshengu.repository.ui.education.EducationTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class EducationTypeTest extends AppTest {

    private EducationTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(EducationTypeRepository.class);
        EducationType name = new EducationType.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(EducationTypeRepository.class);
        EducationType name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(EducationTypeRepository.class);
        EducationType name = repository.findOne(id);
        EducationType newName = new EducationType.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        EducationType upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(EducationTypeRepository.class);
        EducationType name = repository.findOne(id);
        repository.delete(name);
        EducationType deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
