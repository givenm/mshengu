/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices.Impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;
import zm.hashcode.mshengu.services.fieldservices.CreateSiteServiceLogsService;
import zm.hashcode.mshengu.services.fieldservices.LogSheduledSiteServices;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.products.SiteServiceLogService;

/**
 *
 * @author Luckbliss
 */
@Service
public class LogSheduledSiteServicesImpl implements LogSheduledSiteServices {

    @Autowired
    private SiteService siteService;
    @Autowired
    SiteServiceLogService siteServiceLogService;
    @Autowired
    CreateSiteServiceLogsService createSiteServiceLogsService;

//    @Override
    @Override
    public void createTodaysSiteServicesLogs2(Date date) {
        List<Site> todaysSitesList = siteService.findAllWithVisitToday(date);
        ForkJoinPool pool = new ForkJoinPool();
//        ForkJoinTask<CreateServiceLogTaskTwo> forkJoinTask = null;
//        forkJoinTask.
        int count = 0;
        int size = todaysSitesList.size();
        System.out.println("\n\n================= TEST VISIT DATE : " + date);
        ForkJoinTask<CreateServiceLogTaskTwo> taskList = new RecursiveTask<CreateServiceLogTaskTwo>() {

            @Override
            protected CreateServiceLogTaskTwo compute() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        for (Site site : todaysSitesList) {
            try {
                count++;
                System.out.println("\n\n --- Site No" + count + "/" + size + "---");
                System.out.println("Site :" + site.getName());
                CreateServiceLogTaskTwo task = new CreateServiceLogTaskTwo(site, createSiteServiceLogsService, date);

                taskList.complete(task);
                pool.execute(task);
                pool.awaitTermination(10, TimeUnit.MINUTES);
//        forkJoinTask.(new CreateServiceLogTask(todaysSitesList, createSiteServiceLogsService, date));
            } catch (InterruptedException ex) {
                Logger.getLogger(LogSheduledSiteServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//                        pool.execute(null); kind offf
//                pool.shutdown();



    }

    @Override
    public void createTodaysSiteServicesLogs(Date date) {
        List<Site> todaysSitesList = siteService.findAllWithVisitToday(date);
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<CreateServiceLogTask> forkJoinTask = null;
//        forkJoinTask.
        CreateServiceLogTask task = new CreateServiceLogTask(todaysSitesList, createSiteServiceLogsService, date);
        pool.execute(task);
        forkJoinTask.complete(task);
//        forkJoinTask.(new CreateServiceLogTask(todaysSitesList, createSiteServiceLogsService, date));
        pool.execute(forkJoinTask);
        pool.shutdown();

    }

    @Override
    public void updateOpensSiteServicesLogs(Date date) {
        List<Site> todaysSitesList = siteService.findAllWithLastLogOpen();
        ForkJoinPool pool = new ForkJoinPool();
        UpdateServiceLogTask task = new UpdateServiceLogTask(todaysSitesList, createSiteServiceLogsService, date);
        pool.execute(task);
        pool.shutdown();

    }

    @Override
    public void closeOutdatedSiteServicesLogs(Date date) {
        List<SiteServiceLog> siteServiceLogList = siteServiceLogService.getOutdatedOpenLogs(date);
        ForkJoinPool pool = new ForkJoinPool();
        CloseOutdatedServiceLogTask task = new CloseOutdatedServiceLogTask(siteServiceLogList, createSiteServiceLogsService, date);
        pool.execute(task);
        pool.shutdown();

    }
}
