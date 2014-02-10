/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.job;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.job.Job;
import zm.hashcode.mshengu.repository.ui.job.JobRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class JobTest extends AppTest {

    private JobRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(JobRepository.class);
        Job frequency = new Job.Builder("Title1").build();
        repository.save(frequency);
        id = frequency.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(JobRepository.class);
        Job frequency = repository.findOne(id);
        Assert.assertEquals(frequency.getTitle(), "Title1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(JobRepository.class);
        Job frequency = repository.findOne(id);
        Job newFrequency = new Job.Builder("Title2").id(frequency.getId()).build();
        repository.save(newFrequency);
        Job upName = repository.findOne(id);
        Assert.assertEquals(upName.getTitle(), "Title2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(JobRepository.class);
        Job frequency = repository.findOne(id);
        repository.delete(frequency);
        Job deletedFrequency = repository.findOne(id);
        Assert.assertNull(deletedFrequency);
    }
}
