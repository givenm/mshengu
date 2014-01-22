/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.ui.demographics;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.demographics.Title;
import zm.hashcode.mshengu.repository.ui.demographics.TitleRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class TitleListTest extends AppTest {

    private TitleRepository repository;
    private String id;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(TitleRepository.class);
        Title title = new Title.Builder("title1").build();
        repository.save(title);
        id = title.getId();
    }

//    @Test(dependsOnMethods = "testCreate")
    public void testRead() {
        repository = ctx.getBean(TitleRepository.class);
        Title title = repository.findOne(id);
        Assert.assertEquals(title.getTitle(), "title1");
    }

//    @Test(dependsOnMethods = {"testRead"})
    public void testUpdate() {
        repository = ctx.getBean(TitleRepository.class);
        Title title = repository.findOne(id);
        Title newTitle = new Title.Builder("title2").id(title.getId()).build();
        repository.save(newTitle);
        Title upTitle = repository.findOne(id);
        Assert.assertEquals(upTitle.getTitle(), "title2");
    }

//    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() {
        repository = ctx.getBean(TitleRepository.class);
        Title idvalue = repository.findOne(id);
        repository.delete(idvalue);
        Title deletedIdValue = repository.findOne(id);
        Assert.assertNull(deletedIdValue);
    }
}
