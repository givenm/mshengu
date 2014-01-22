/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.util;

import org.testng.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.util.Status;
import zm.hashcode.mshengu.repository.ui.util.StatusRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author boniface
 */
public class StatusTest extends AppTest {

    private StatusRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(StatusRepository.class);
        Status role = new Status.Builder("System Adminiatrator").build();
        repository.save(role);
        id = role.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(StatusRepository.class);
        Status role = repository.findOne(id);
        Assert.assertEquals(role.getName(), "System Adminiatrator");

    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(StatusRepository.class);
        Status role = repository.findOne(id);
        Status newrole = new Status.Builder("Adminiatrator").id(role.getId()).build();
        repository.save(newrole);
        Status upDaterole = repository.findOne(id);
        Assert.assertEquals(upDaterole.getName(), "Adminiatrator");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(StatusRepository.class);
        Status role = repository.findOne(id);
        repository.delete(role);
        Status deletedRole = repository.findOne(id);
        Assert.assertNull(deletedRole);
    }
}
