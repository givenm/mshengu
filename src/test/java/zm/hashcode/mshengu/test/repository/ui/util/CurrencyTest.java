/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.util;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.util.Currency;
import zm.hashcode.mshengu.repository.ui.util.CurrencyRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class CurrencyTest extends AppTest {

    private CurrencyRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(CurrencyRepository.class);
        Currency code = new Currency.Builder("Code1").build();
        repository.save(code);
        id = code.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(CurrencyRepository.class);
        Currency code = repository.findOne(id);
        Assert.assertEquals(code.getCode(), "Code1");
    }
    
//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(CurrencyRepository.class);
        Currency code = repository.findOne(id);
        Currency newCode = new Currency.Builder("Code2").id(code.getId()).build();
        repository.save(newCode);
        Currency upCode = repository.findOne(id);
        Assert.assertEquals(upCode.getCode(), "Code2");
    }
    
//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(CurrencyRepository.class);
        Currency code = repository.findOne(id);
        repository.delete(code);
        Currency deletedCode = repository.findOne(id);
        Assert.assertNull(deletedCode);
    }
}
