/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.customers;

import java.util.Date;
import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.customer.Order;
import zm.hashcode.mshengu.repository.customer.OrderRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class OrderTest extends AppTest{
    private OrderRepository repository;
    private String id;
    private Date firstDate = new Date();
    private Date updateDate = new Date();

//    @Test
    public void testCreate() {
        repository = ctx.getBean(OrderRepository.class);
        Order startDate = new Order.Builder(firstDate).build();
        repository.save(startDate);
        id = startDate.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(OrderRepository.class);
        Order startDate = repository.findOne(id);
        Assert.assertEquals(startDate.getStartDate(), firstDate);
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(OrderRepository.class);
        Order startDate = repository.findOne(id);
        Order newStartDate = new Order.Builder(updateDate).id(startDate.getId()).build();
        repository.save(newStartDate);
        Order upStartDate = repository.findOne(id);
        Assert.assertEquals(upStartDate.getStartDate(), updateDate);
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(OrderRepository.class);
        Order startDate = repository.findOne(id);
        repository.delete(startDate);
        Order deletedStartDate = repository.findOne(id);
        Assert.assertNull(deletedStartDate);
    }
}
