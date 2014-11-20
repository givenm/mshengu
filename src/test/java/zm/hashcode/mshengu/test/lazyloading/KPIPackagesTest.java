/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.kpianalysis.KPAService;
import zm.hashcode.mshengu.services.kpianalysis.KPIItemService;
import zm.hashcode.mshengu.services.kpianalysis.KPIValuesService;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPIFiveService;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPIFourService;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPIOneService;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPIThreeService;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPITwoService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class KPIPackagesTest extends AppTest {

    /**
     * KPI : KPAService, KPIItemService, KPIValuesService, LoadKPIFiveService,
     * LoadKPIFourService, LoadKPIOneService, LoadKPIThreeService,
     * LoadKPITwoService
     */
    @Autowired
    private KPAService domainClassToTest01;   // Service
    @Autowired
    private KPIItemService domainClassToTest02;   // Service
    @Autowired
    private KPIValuesService domainClassToTest03;   // Service
    @Autowired
    private LoadKPIFiveService domainClassToTest04;   // Service
    @Autowired
    private LoadKPIFourService domainClassToTest05;   // Service  
    @Autowired
    private LoadKPIOneService domainClassToTest06;   // Service
    @Autowired
    private LoadKPIThreeService domainClassToTest07;   // Service
    @Autowired
    private LoadKPITwoService domainClassToTest08;   // Service

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(KPAService.class);
        domainClassToTest01.findAll();
    }

//    @Test
    public void testThemAll02() {
        domainClassToTest02 = ctx.getBean(KPIItemService.class);
        domainClassToTest02.findAll();
    }

//    @Test
    public void testThemAll03() {
        domainClassToTest03 = ctx.getBean(KPIValuesService.class);
        domainClassToTest03.findAll();
    }
//    @Test    public void testThemAll04(){domainClassToTest04 = ctx.getBean( LoadKPIFiveService.class); domainClassToTest04;}
//    @Test    public void testThemAll05(){domainClassToTest05 = ctx.getBean( LoadKPIFourService.class); domainClassToTest05.;   }
//    @Test    public void testThemAll06(){domainClassToTest06 = ctx.getBean( LoadKPIOneService.class);     domainClassToTest06.();  }    
//    @Test    public void testThemAll07(){domainClassToTest07 = ctx.getBean( LoadKPIThreeService.class); domainClassToTest07.();}
//    @Test    public void testThemAll08(){domainClassToTest08 = ctx.getBean( LoadKPITwoService.class); domainClassToTest08.();} 

}
