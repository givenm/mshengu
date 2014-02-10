/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.job;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.job.BenefitType;
import zm.hashcode.mshengu.repository.ui.job.BenefitTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class BenefitTypeTest extends AppTest{
    private BenefitTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(BenefitTypeRepository.class);
        BenefitType frequency = new BenefitType.Builder("BenefitType1").build();
        repository.save(frequency);
        id = frequency.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(BenefitTypeRepository.class);
        BenefitType frequency = repository.findOne(id);
        Assert.assertEquals(frequency.getBenefitTypeName(), "BenefitType1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(BenefitTypeRepository.class);
        BenefitType frequency = repository.findOne(id);
        BenefitType newFrequency = new BenefitType.Builder("BenefitType2").id(frequency.getId()).build();
        repository.save(newFrequency);
        BenefitType upName = repository.findOne(id);
        Assert.assertEquals(upName.getBenefitTypeName(), "BenefitType2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(BenefitTypeRepository.class);
        BenefitType frequency = repository.findOne(id);
        repository.delete(frequency);
        BenefitType deletedFrequency = repository.findOne(id);
        Assert.assertNull(deletedFrequency);
    }
}
