/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.job;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.job.BenefitFrequency;
import zm.hashcode.mshengu.repository.ui.job.BenefitFrequencyRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class BenefitFrequencyTest extends AppTest {

    private BenefitFrequencyRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(BenefitFrequencyRepository.class);
        BenefitFrequency frequency = new BenefitFrequency.Builder("Frequency1").build();
        repository.save(frequency);
        id = frequency.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(BenefitFrequencyRepository.class);
        BenefitFrequency frequency = repository.findOne(id);
        Assert.assertEquals(frequency.getFrequency(), "Frequency1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(BenefitFrequencyRepository.class);
        BenefitFrequency frequency = repository.findOne(id);
        BenefitFrequency newFrequency = new BenefitFrequency.Builder("Frequency2").id(frequency.getId()).build();
        repository.save(newFrequency);
        BenefitFrequency upName = repository.findOne(id);
        Assert.assertEquals(upName.getFrequency(), "Frequency2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(BenefitFrequencyRepository.class);
        BenefitFrequency frequency = repository.findOne(id);
        repository.delete(frequency);
        BenefitFrequency deletedFrequency = repository.findOne(id);
        Assert.assertNull(deletedFrequency);
    }
}
