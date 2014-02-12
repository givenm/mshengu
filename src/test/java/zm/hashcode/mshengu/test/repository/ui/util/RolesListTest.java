/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.util;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.util.Role;
import zm.hashcode.mshengu.repository.ui.util.RoleRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class RolesListTest extends AppTest {

    private RoleRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(RoleRepository.class);
        Role roleName = new Role.Builder("System Adminiatrator").build();
        repository.save(roleName);
        id = roleName.getId();
    }
    
//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(RoleRepository.class);
        Role roleName = repository.findOne(id);
        Assert.assertEquals(roleName.getRolename(), "System Adminiatrator");
    }
    
//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(RoleRepository.class);
        Role name = repository.findOne(id);
        Role newRoleName = new Role.Builder("Colin").id(name.getId()).build();
        repository.save(newRoleName);
        Role upRoleName = repository.findOne(id);
        Assert.assertEquals(upRoleName.getRolename(), "Colin");
    }
    
//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(RoleRepository.class);
        Role name = repository.findOne(id);
        repository.delete(name);
        Role deletedName = repository.findOne(id);
        Assert.assertNull(deletedName);
    }
}
