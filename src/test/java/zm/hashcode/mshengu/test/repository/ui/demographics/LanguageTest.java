/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.demographics;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.demographics.Language;
import zm.hashcode.mshengu.repository.ui.demographics.LanguageRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class LanguageTest extends AppTest {

    private LanguageRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(LanguageRepository.class);
        Language name = new Language.Builder("Name1").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(LanguageRepository.class);
        Language name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "Name1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(LanguageRepository.class);
        Language name = repository.findOne(id);
        Language newName = new Language.Builder("Name2").id(name.getId()).build();
        repository.save(newName);
        Language upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "Name2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(LanguageRepository.class);
        Language name = repository.findOne(id);
        repository.delete(name);
        Language deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
