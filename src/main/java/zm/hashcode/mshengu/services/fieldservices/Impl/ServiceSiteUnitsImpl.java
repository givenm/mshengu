/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices.Impl;

import com.google.common.collect.Collections2;
import com.google.common.collect.Range;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.LogEventsEnum;
import zm.hashcode.mshengu.domain.products.LogSiteEvents;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.services.fieldservices.LogSiteEventService;
import zm.hashcode.mshengu.services.fieldservices.ServiceSiteUnits;
import zm.hashcode.mshengu.services.fieldservices.ServiceUnit;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.products.SiteUnitService;

/**
 *
 * @author boniface
 */
@Service
public class ServiceSiteUnitsImpl implements ServiceSiteUnits {

    @Autowired
    private SiteUnitService siteUnitService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private ServiceUnit serviceUnit;
    @Autowired
    private LogSiteEventService logSiteEventService;

    @Override
    public String serviceFirstUnit(UnitServiceResource unitService) {

        SiteUnit unit = siteUnitService.findByUnitId(unitService.getUnitId());
        Site site = getSiteForUnit(unit.getId());
        LogSiteEvents event = new LogSiteEvents.Builder(new Date())
                .action(LogEventsEnum.START.name())
                .siteId(site.getId())
                .unitId(unit.getId())
                .truckId(unitService.getTruckId())
                .build();
        String eventMessage = logSiteEventService.firstUnitLogEvent(event);

        if (LogEventsEnum.ACCEPT.name().equalsIgnoreCase(eventMessage)) {
            serviceUnit.serviceunit(unitService, unit, unitService.getStatusMessage());
        }
        return eventMessage;
    }

    @Override
    public List<SiteUnit> getUnitsFromSite(String unitId) {
        SiteUnit siteUnit = siteUnitService.findById(unitId);
        Site site = siteService.findByName(siteUnit.getSiteName());
        Set<SiteUnit> units = site.getLastSiteServiceContractLifeCycle().getSiteUnit(); //REFACTOR
        return ImmutableList.copyOf(units);
    }

    @Override
    public String serviceLastUnit(UnitServiceResource unitService) {
        SiteUnit unit = siteUnitService.findByUnitId(unitService.getUnitId());
        Site site = getSiteForUnit(unit.getId());
        LogSiteEvents event = new LogSiteEvents.Builder(new Date())
                .action(LogEventsEnum.FINISH.name())
                .siteId(site.getId())
                .unitId(unit.getId())
                .truckId(unitService.getTruckId())
                .build();
        String eventMessage = logSiteEventService.lastUnitLogEvent(event);
        if (LogEventsEnum.ACCEPT.name().equalsIgnoreCase(eventMessage)) {
            serviceUnit.serviceunit(unitService, unit, unitService.getStatusMessage());
            SiteUnit first = getFirstUnit(unitService.getTruckId(), site.getId());
            List<SiteUnit> units = getSiteUnitsRange(first, unit);
            serviceBulkUnits(units, unitService, unitService.getStatusMessage());
        }
        return eventMessage;
    }

    @Override
    public Site getSiteForUnit(String unitID) {
        SiteUnit siteUnit = siteUnitService.findById(unitID);
        return siteService.findByName(siteUnit.getSiteName());
    }

    @Override
    public SiteUnit getFirstUnit(String truckId, String siteId) {
        String unitId = logSiteEventService.getLatestUnitIDWithStartAction(truckId, siteId);
        return siteUnitService.findById(unitId);
    }

    @Override
    public List<SiteUnit> getSiteUnitsRange(SiteUnit first, SiteUnit finish) {
        List<SiteUnit> unitRange = new ArrayList<>();
        List<SiteUnit> units = getUnitsFromSite(first.getId());
        Collection<SiteUnit> filteredUnits = Collections2.filter(units, Range.open(first, finish));
        unitRange.addAll(filteredUnits);
        return unitRange;
    }

    @Override
    public void serviceBulkUnits(List<SiteUnit> units, UnitServiceResource resource, String message) {
        ForkJoinPool pool = new ForkJoinPool();
        UnitServcingTask task = new UnitServcingTask(units, resource, message, serviceUnit);
        pool.execute(task);
        pool.shutdown();

    }
}
