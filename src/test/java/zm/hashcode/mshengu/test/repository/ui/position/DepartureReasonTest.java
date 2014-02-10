/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.position;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.position.DepartureReason;
import zm.hashcode.mshengu.repository.ui.position.DepartureReasonRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class DepartureReasonTest extends AppTest{
    private DepartureReasonRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(DepartureReasonRepository.class);
        DepartureReason reasonName = new DepartureReason.Builder("Lucky").build();
        repository.save(reasonName);
        id = reasonName.getId();
    }
    
//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(DepartureReasonRepository.class);
        DepartureReason name = repository.findOne(id);
        Assert.assertEquals(name.getReasonName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(DepartureReasonRepository.class);
        DepartureReason name = repository.findOne(id);
        DepartureReason newName = new DepartureReason.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        DepartureReason upName = repository.findOne(id);
        Assert.assertEquals(upName.getReasonName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(DepartureReasonRepository.class);
        DepartureReason name = repository.findOne(id);
        repository.delete(name);
        DepartureReason deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
