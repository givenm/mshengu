/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.job;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.job.JobClassification;
import zm.hashcode.mshengu.repository.ui.job.JobClassificationRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class JobClassificationTest extends AppTest{
    private JobClassificationRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(JobClassificationRepository.class);
        JobClassification frequency = new JobClassification.Builder("Title1").build();
        repository.save(frequency);
        id = frequency.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(JobClassificationRepository.class);
        JobClassification frequency = repository.findOne(id);
        Assert.assertEquals(frequency.getCurrentTitle(), "Title1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(JobClassificationRepository.class);
        JobClassification frequency = repository.findOne(id);
        JobClassification newFrequency = new JobClassification.Builder("Title2").id(frequency.getId()).build();
        repository.save(newFrequency);
        JobClassification upName = repository.findOne(id);
        Assert.assertEquals(upName.getCurrentTitle(), "Title2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(JobClassificationRepository.class);
        JobClassification frequency = repository.findOne(id);
        repository.delete(frequency);
        JobClassification deletedFrequency = repository.findOne(id);
        Assert.assertNull(deletedFrequency);
    }
}
