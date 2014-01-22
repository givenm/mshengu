/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.util;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.util.DisciplineActionType;
import zm.hashcode.mshengu.repository.ui.util.DisciplineActionTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class DisciplineActionTypeListTest extends AppTest {

    private DisciplineActionTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(DisciplineActionTypeRepository.class);
        DisciplineActionType name = new DisciplineActionType.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(DisciplineActionTypeRepository.class);
        DisciplineActionType name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(DisciplineActionTypeRepository.class);
        DisciplineActionType name = repository.findOne(id);
        DisciplineActionType newName = new DisciplineActionType.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        DisciplineActionType upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(DisciplineActionTypeRepository.class);
        DisciplineActionType name = repository.findOne(id);
        repository.delete(name);
        DisciplineActionType deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
