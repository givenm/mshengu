/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.customers;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.customer.Invoice;
import zm.hashcode.mshengu.repository.customer.InvoiceRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class InvoiceTest extends AppTest {

    private InvoiceRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(InvoiceRepository.class);
        Invoice invoiceId = new Invoice.Builder("id").build();
        repository.save(invoiceId);
        id = invoiceId.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(InvoiceRepository.class);
        Invoice invoiceId = repository.findOne(id);
        Assert.assertEquals(invoiceId.getId(), "id");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(InvoiceRepository.class);
        Invoice invoiceId = repository.findOne(id);
        Invoice newInvoiceId = new Invoice.Builder("id2").id(invoiceId.getId()).build();
        repository.save(newInvoiceId);
        Invoice upInvoiceId = repository.findOne(id);
        Assert.assertEquals(upInvoiceId.getId(), newInvoiceId.getId());
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(InvoiceRepository.class);
        Invoice firstname = repository.findOne(id);
        repository.delete(firstname);
        Invoice deletedFirstname = repository.findOne(id);
        Assert.assertNull(deletedFirstname);
    }
}
