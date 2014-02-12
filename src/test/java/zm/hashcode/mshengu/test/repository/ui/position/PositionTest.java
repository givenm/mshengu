/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.position;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.position.Position;
import zm.hashcode.mshengu.repository.ui.position.PositionRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class PositionTest extends AppTest {

    private PositionRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(PositionRepository.class);
        Position code = new Position.Builder("Code1").build();
        repository.save(code);
        id = code.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(PositionRepository.class);
        Position code = repository.findOne(id);
        Assert.assertEquals(code.getPositionCode(), "Code1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(PositionRepository.class);
        Position code = repository.findOne(id);
        Position newCode = new Position.Builder("Code2").id(code.getId()).build();
        repository.save(newCode);
        Position upCode = repository.findOne(id);
        Assert.assertEquals(upCode.getPositionCode(), "Code2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(PositionRepository.class);
        Position code = repository.findOne(id);
        repository.delete(code);
        Position deletedCode = repository.findOne(id);
        Assert.assertNull(deletedCode);
    }
}
