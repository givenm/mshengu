/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.chemical.ChemicalCostService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class ChemicalPackagesTest extends AppTest {

    /**
     * Chemical : ChemicalCostService,
     */
    @Autowired
    private ChemicalCostService domainClassToTest01;

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(ChemicalCostService.class);
        domainClassToTest01.findAll();
    }
}
