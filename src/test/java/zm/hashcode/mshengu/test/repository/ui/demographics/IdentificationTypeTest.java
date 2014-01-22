/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.demographics;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.demographics.IdentificationType;
import zm.hashcode.mshengu.repository.ui.demographics.IdentificationTypeRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class IdentificationTypeTest extends AppTest {

    private IdentificationTypeRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(IdentificationTypeRepository.class);
        IdentificationType idvalue = new IdentificationType.Builder("idvalue1").build();
        repository.save(idvalue);
        id = idvalue.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(IdentificationTypeRepository.class);
        IdentificationType idvalue = repository.findOne(id);
        Assert.assertEquals(idvalue.getIdvalue(), "idvalue1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(IdentificationTypeRepository.class);
        IdentificationType idvalue = repository.findOne(id);
        IdentificationType newIdValue = new IdentificationType.Builder("idvalue2").id(idvalue.getId()).build();
        repository.save(newIdValue);
        IdentificationType upIdValue = repository.findOne(id);
        Assert.assertEquals(upIdValue.getIdvalue(), "idvalue2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(IdentificationTypeRepository.class);
        IdentificationType idvalue = repository.findOne(id);
        repository.delete(idvalue);
        IdentificationType deletedIdValue = repository.findOne(id);
        Assert.assertNull(deletedIdValue);
    }
}
