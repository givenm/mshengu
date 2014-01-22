/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices.Impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;
import zm.hashcode.mshengu.services.fieldservices.CreateSiteServiceLogsService;

/**
 *
 * @author boniface
 */
public class CloseOutdatedServiceLogTask extends RecursiveAction {

    private final CreateSiteServiceLogsService createSiteServiceLogsService;
    private final List<SiteServiceLog> siteServiceLogList;
    private final Date date;
//    private final UnitServiceResource unitService;
//    private final String message;

    public CloseOutdatedServiceLogTask(List<SiteServiceLog> siteServiceLogList, CreateSiteServiceLogsService createSiteServiceLogsService, Date date) {
        this.createSiteServiceLogsService = createSiteServiceLogsService;
        this.siteServiceLogList = siteServiceLogList;
        this.date = date;

    }

    @Override
    protected void compute() {
        for (SiteServiceLog siteServiceLog : siteServiceLogList) {
            createSiteServiceLogsService.closeOutdatedSiteService(siteServiceLog, date);
        }
    }
}
