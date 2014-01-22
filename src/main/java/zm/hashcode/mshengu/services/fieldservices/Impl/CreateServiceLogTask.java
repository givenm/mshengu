/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices.Impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.services.fieldservices.CreateSiteServiceLogsService;

/**
 *
 * @author boniface
 */
public class CreateServiceLogTask extends RecursiveAction {

    private final CreateSiteServiceLogsService siteServiceScheduleLogsService;
    private final List<Site> siteList;
    private final Date date;
//    private final UnitServiceResource unitService;
//    private final String message;

    public CreateServiceLogTask(List<Site> siteList, CreateSiteServiceLogsService siteServiceScheduleLogsService, Date date) {
        this.siteServiceScheduleLogsService = siteServiceScheduleLogsService;
        this.siteList = siteList;
        this.date = date;

    }

    @Override
    protected void compute() {
        int count = 0;
            int size = siteList.size();
            System.out.println("\n\n================= TEST VISIT DATE : " + date);
            for (Site site : siteList) { 
                count ++;
                System.out.println("\n\n --- Site No" + count + "/" + size + "---");
                System.out.println("Site :" + site.getName());
            siteServiceScheduleLogsService.createSiteServiceLog(site, date);
        }
    }
}
