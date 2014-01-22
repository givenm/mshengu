/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.demographics;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.demographics.Race;
import zm.hashcode.mshengu.repository.ui.demographics.RaceRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class RaceListTest extends AppTest {

    private RaceRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(RaceRepository.class);
        Race raceName = new Race.Builder("raceName1").build();
        repository.save(raceName);
        id = raceName.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(RaceRepository.class);
        Race raceName = repository.findOne(id);
        Assert.assertEquals(raceName.getRaceName(), "raceName1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(RaceRepository.class);
        Race raceName = repository.findOne(id);
        Race newRaceName = new Race.Builder("raceName2").id(raceName.getId()).build();
        repository.save(newRaceName);
        Race upRaceName = repository.findOne(id);
        Assert.assertEquals(upRaceName.getRaceName(), "raceName2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(RaceRepository.class);
        Race raceName = repository.findOne(id);
        repository.delete(raceName);
        Race deleteRaceName = repository.findOne(id);
        Assert.assertNull(deleteRaceName);
    }
}
