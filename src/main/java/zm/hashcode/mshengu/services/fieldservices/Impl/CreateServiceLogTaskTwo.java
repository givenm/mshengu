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
public class CreateServiceLogTaskTwo extends RecursiveAction {

    private final CreateSiteServiceLogsService siteServiceScheduleLogsService;
    private final Site site;
    private final Date date;
//    private final UnitServiceResource unitService;
//    private final String message;

    public CreateServiceLogTaskTwo(Site site, CreateSiteServiceLogsService siteServiceScheduleLogsService, Date date) {
        this.siteServiceScheduleLogsService = siteServiceScheduleLogsService;
        this.site = site;
        this.date = date;

    }

    @Override
    protected void compute() {

            siteServiceScheduleLogsService.createSiteServiceLog(site, date);
        
    }
}
