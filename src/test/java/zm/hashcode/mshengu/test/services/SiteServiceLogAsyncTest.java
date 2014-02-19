/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.services.asynchronous.AsyncCalls;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author Ferox
 */
public class SiteServiceLogAsyncTest extends AppTest {

    @Autowired
    private AsyncCalls asyncCalls;

    private final DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();
    Date startDate = dtfwh.getDate(17, 01, 2014);
    Date endDate = dtfwh.getDate(17, 01, 2014);

    private void setTodaysDate(Date date) {
        dtfwh.setDate(date);
    }

//    @Test
    public void testAsyncServiceLogging() {

        asyncCalls = ctx.getBean(AsyncCalls.class);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        setTodaysDate(calendar.getTime());

        while (calendar.getTime().before(endDate)) {
            System.out.println("Creating SERVICE LOGS" + calendar.getTime());
            asyncCalls.createLogsAsync(calendar.getTime());
            System.out.println("Updating SERVICE LOGS " + calendar.getTime());
            asyncCalls.updateLogsAsync(calendar.getTime());
            System.out.println("Closng  SERVICE LOGS" + calendar.getTime());
            asyncCalls.closeLogsAsync(calendar.getTime());

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
}
