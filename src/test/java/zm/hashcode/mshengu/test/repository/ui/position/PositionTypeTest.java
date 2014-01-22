/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.position;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.position.PositionType;
import zm.hashcode.mshengu.repository.ui.position.PositionTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class PositionTypeTest extends AppTest {

    private PositionTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(PositionTypeRepository.class);
        PositionType name = new PositionType.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(PositionTypeRepository.class);
        PositionType name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(PositionTypeRepository.class);
        PositionType name = repository.findOne(id);
        PositionType newName = new PositionType.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        PositionType upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(PositionTypeRepository.class);
        PositionType name = repository.findOne(id);
        repository.delete(name);
        PositionType deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
