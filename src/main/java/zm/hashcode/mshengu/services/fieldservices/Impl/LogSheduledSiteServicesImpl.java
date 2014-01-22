/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices.Impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
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
    public void createTodaysSiteServicesLogs(Date date) {
        List<Site> todaysSitesList  = siteService.findAllWithVisitToday(date);
        ForkJoinPool pool = new ForkJoinPool();
        CreateServiceLogTask task = new CreateServiceLogTask(todaysSitesList, createSiteServiceLogsService, date);
        pool.execute(task);
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
