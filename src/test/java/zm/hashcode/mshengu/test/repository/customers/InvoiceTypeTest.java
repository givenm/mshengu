/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.customers;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.customer.InvoiceType;
import zm.hashcode.mshengu.repository.customer.InvoiceTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class InvoiceTypeTest extends AppTest{
    private InvoiceTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(InvoiceTypeRepository.class);
        InvoiceType type = new InvoiceType.Builder("type1").build();
        repository.save(type);
        id = type.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(InvoiceTypeRepository.class);
        InvoiceType type = repository.findOne(id);
        Assert.assertEquals(type.getType(), "type1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(InvoiceTypeRepository.class);
        InvoiceType type = repository.findOne(id);
        InvoiceType newType = new InvoiceType.Builder("type2").id(type.getId()).build();
        repository.save(newType);
        InvoiceType upType = repository.findOne(id);
        Assert.assertEquals(upType.getType(), "type2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(InvoiceTypeRepository.class);
        InvoiceType firstname = repository.findOne(id);
        repository.delete(firstname);
        InvoiceType deletedFirstname = repository.findOne(id);
        Assert.assertNull(deletedFirstname);
    }
}
