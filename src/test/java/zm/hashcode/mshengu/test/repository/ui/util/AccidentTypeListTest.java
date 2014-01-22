/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.util;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.util.AccidentType;
import zm.hashcode.mshengu.repository.ui.util.AccidentTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class AccidentTypeListTest extends AppTest{
    
    private AccidentTypeRepository repository;
    private String id;
    
//    @Test
    public void testCreate() {
        repository = ctx.getBean(AccidentTypeRepository.class);
        AccidentType name = new AccidentType.Builder("Lucky").build();
        repository.save(name);
        id = name.getId();
    }
    
//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(AccidentTypeRepository.class);
        AccidentType name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "Lucky");
    }
    
//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(AccidentTypeRepository.class);
        AccidentType name = repository.findOne(id);
        AccidentType newName = new AccidentType.Builder("Colin").id(name.getId()).build();
        repository.save(newName);
        AccidentType upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "Colin");
    }
    
//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(AccidentTypeRepository.class);
        AccidentType name = repository.findOne(id);
        repository.delete(name);
        AccidentType deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
