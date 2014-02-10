/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.demographics;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.demographics.Gender;
import zm.hashcode.mshengu.repository.ui.demographics.GenderRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class GenderListTest extends AppTest {

    private GenderRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(GenderRepository.class);
        Gender gender = new Gender.Builder("Male").build();
        repository.save(gender);
        id = gender.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(GenderRepository.class);
        Gender gender = repository.findOne(id);
        Assert.assertEquals(gender.getGender(), "Male");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(GenderRepository.class);
        Gender gender = repository.findOne(id);
        Gender newGender = new Gender.Builder("Female").id(gender.getId()).build();
        repository.save(newGender);
        Gender upGender = repository.findOne(id);
        Assert.assertEquals(upGender.getGender(), "Female");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(GenderRepository.class);
        Gender gender = repository.findOne(id);
        repository.delete(gender);
        Gender deletedGender = repository.findOne(id);
        Assert.assertNull(deletedGender);
    }
}
