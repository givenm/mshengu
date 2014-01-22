/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.demographics;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.demographics.LanguageProficiency;
import zm.hashcode.mshengu.repository.ui.demographics.LanguageProficiencyRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class LanguageProficiencyTest extends AppTest {

    private LanguageProficiencyRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(LanguageProficiencyRepository.class);
        LanguageProficiency name = new LanguageProficiency.Builder("Proficiency1").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(LanguageProficiencyRepository.class);
        LanguageProficiency name = repository.findOne(id);
        Assert.assertEquals(name.getProficiency(), "Proficiency1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(LanguageProficiencyRepository.class);
        LanguageProficiency name = repository.findOne(id);
        LanguageProficiency newName = new LanguageProficiency.Builder("Proficiency2").id(name.getId()).build();
        repository.save(newName);
        LanguageProficiency upName = repository.findOne(id);
        Assert.assertEquals(upName.getProficiency(), "Proficiency2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(LanguageProficiencyRepository.class);
        LanguageProficiency name = repository.findOne(id);
        repository.delete(name);
        LanguageProficiency deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
