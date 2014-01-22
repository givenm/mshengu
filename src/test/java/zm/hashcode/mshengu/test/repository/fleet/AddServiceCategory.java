/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.fleet;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceCategory;
import zm.hashcode.mshengu.repository.serviceprovider.ServiceCategoryRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class AddServiceCategory extends AppTest {

    private ServiceCategoryRepository categoryRepository;

//    @Test
    public void testCreate() {
        categoryRepository = ctx.getBean(ServiceCategoryRepository.class);
        ServiceCategory category1 = new ServiceCategory.Builder("servicecategory1").build();
        categoryRepository.save(category1);
        ServiceCategory category2 = new ServiceCategory.Builder("servicecategory2").build();
        categoryRepository.save(category2);
        ServiceCategory category3 = new ServiceCategory.Builder("servicecategory3").build();
        categoryRepository.save(category3);
        ServiceCategory category4 = new ServiceCategory.Builder("servicecategory4").build();
        categoryRepository.save(category4);
        Assert.assertEquals(category1.getName(), "servicecategory1");
    }
}
