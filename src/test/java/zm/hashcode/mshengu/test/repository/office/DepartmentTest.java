/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.office;

import junit.framework.Assert;
import zm.hashcode.mshengu.domain.office.Department;
import zm.hashcode.mshengu.repository.office.DepartmentRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class DepartmentTest extends AppTest {

    private DepartmentRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(DepartmentRepository.class);
        Department name = new Department.Builder("name1").build();
        repository.save(name);
        id = name.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(DepartmentRepository.class);
        Department name = repository.findOne(id);
        Assert.assertEquals(name.getName(), "name1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(DepartmentRepository.class);
        Department name = repository.findOne(id);
        Department newName = new Department.Builder("name2").id(name.getId()).build();
        repository.save(newName);
        Department upName = repository.findOne(id);
        Assert.assertEquals(upName.getName(), "name2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(DepartmentRepository.class);
        Department firstname = repository.findOne(id);
        repository.delete(firstname);
        Department deletedFirstname = repository.findOne(id);
        Assert.assertNull(deletedFirstname);
    }
}
