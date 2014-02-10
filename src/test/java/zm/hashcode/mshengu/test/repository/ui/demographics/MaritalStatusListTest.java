/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.demographics;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.demographics.MaritalStatus;
import zm.hashcode.mshengu.repository.ui.demographics.MaritalStatusRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class MaritalStatusListTest extends AppTest {

    private MaritalStatusRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(MaritalStatusRepository.class);
        MaritalStatus statusName = new MaritalStatus.Builder("statusName1").build();
        repository.save(statusName);
        id = statusName.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(MaritalStatusRepository.class);
        MaritalStatus name = repository.findOne(id);
        Assert.assertEquals(name.getStatusName(), "statusName1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(MaritalStatusRepository.class);
        MaritalStatus statusName = repository.findOne(id);
        MaritalStatus newStatusName = new MaritalStatus.Builder("statusName2").id(statusName.getId()).build();
        repository.save(newStatusName);
        MaritalStatus upStatusName = repository.findOne(id);
        Assert.assertEquals(upStatusName.getStatusName(), "statusName2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(MaritalStatusRepository.class);
        MaritalStatus statusName = repository.findOne(id);
        repository.delete(statusName);
        MaritalStatus deleteStatusName = repository.findOne(id);
        Assert.assertNull(deleteStatusName);
    }
}
