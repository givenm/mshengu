/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.mobileapi;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.products.LogEventsEnum;
import zm.hashcode.mshengu.domain.products.LogSiteEvents;
import zm.hashcode.mshengu.services.fieldservices.LogSiteEventService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author boniface
 */
public class LogSiteEventsTest extends AppTest {

    @Autowired
    private LogSiteEventService service;

//    @Test
    public void testAddEvent() {
        service = ctx.getBean(LogSiteEventService.class);
        LogSiteEvents event = new LogSiteEvents.Builder(new Date())
                .siteId("12343432")
                .action(LogEventsEnum.START.name())
                .build();
        String evt = service.lastUnitLogEvent(event);
        System.out.println(" THE EVET is " + evt);
    }

//    @Test
    void testUnitRetrieval() {
        service = ctx.getBean(LogSiteEventService.class);
        String id = service.getLatestUnitIDWithStartAction("5267b8b5e4b0417002e03430", "5282f283823063df45045eba");
        System.out.println(" THE IS IS HERE");
    }
}
