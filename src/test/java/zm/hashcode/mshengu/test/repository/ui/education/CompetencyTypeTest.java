/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.education;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.education.CompetencyType;
import zm.hashcode.mshengu.repository.ui.education.CompetencyTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class CompetencyTypeTest extends AppTest {

    private CompetencyTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(CompetencyTypeRepository.class);
        CompetencyType name = new CompetencyType.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(CompetencyTypeRepository.class);
        CompetencyType name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(CompetencyTypeRepository.class);
        CompetencyType name = repository.findOne(id);
        CompetencyType newName = new CompetencyType.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        CompetencyType upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(CompetencyTypeRepository.class);
        CompetencyType name = repository.findOne(id);
        repository.delete(name);
        CompetencyType deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
