/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.customers;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.customer.OrderType;
import zm.hashcode.mshengu.repository.customer.OrderTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class OrderTypeTest extends AppTest {

    private OrderTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(OrderTypeRepository.class);
        OrderType orderTypeId = new OrderType.Builder("id").build();
        repository.save(orderTypeId);
        id = orderTypeId.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(OrderTypeRepository.class);
        OrderType orderTypeId = repository.findOne(id);
        Assert.assertEquals(orderTypeId.getId(), "id");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(OrderTypeRepository.class);
        OrderType orderTypeId = repository.findOne(id);
        OrderType newOrderTypeId = new OrderType.Builder("id2").id(orderTypeId.getId()).build();
        repository.save(newOrderTypeId);
        OrderType upOrderTypeId = repository.findOne(id);
        Assert.assertEquals(upOrderTypeId.getId(), newOrderTypeId.getId());
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(OrderTypeRepository.class);
        OrderType orderTypeId = repository.findOne(id);
        repository.delete(orderTypeId);
        OrderType deletedOrderTypeId = repository.findOne(id);
        Assert.assertNull(deletedOrderTypeId);
    }
}
