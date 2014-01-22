/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.job;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.job.SalaryGrade;
import zm.hashcode.mshengu.repository.ui.job.SalaryGradeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class SalaryGradeTest extends AppTest {

    private SalaryGradeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(SalaryGradeRepository.class);
        SalaryGrade name = new SalaryGrade.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(SalaryGradeRepository.class);
        SalaryGrade name = repository.findOne(id);
        Assert.assertEquals(name.getGradeName(), "Lucky");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(SalaryGradeRepository.class);
        SalaryGrade name = repository.findOne(id);
        SalaryGrade newName = new SalaryGrade.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        SalaryGrade upName = repository.findOne(id);
        Assert.assertEquals(upName.getGradeName(), "Colin");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(SalaryGradeRepository.class);
        SalaryGrade name = repository.findOne(id);
        repository.delete(name);
        SalaryGrade deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
