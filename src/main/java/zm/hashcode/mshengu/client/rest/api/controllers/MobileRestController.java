/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.rest.api.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zm.hashcode.mshengu.client.rest.api.MobileResponseMessage;
import zm.hashcode.mshengu.client.rest.api.resources.GeoplotResource;
import zm.hashcode.mshengu.client.rest.api.resources.SiteReource;
import zm.hashcode.mshengu.client.rest.api.resources.TruckResources;
import zm.hashcode.mshengu.client.rest.api.resources.UnitDeliveryResource;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.UnitServiceLog;
import zm.hashcode.mshengu.services.fieldservices.ServiceSiteUnits;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.products.SiteUnitService;
import zm.hashcode.mshengu.services.products.UnitServiceLogService;
import zm.hashcode.mshengu.services.products.impl.MobileAppServiceImpl;

/**
 *
 * @author boniface
 */
@Controller
@RequestMapping("api")
public class MobileRestController {

    @Autowired
    private TruckService truckService;
    @Autowired
    private MobileAppServiceImpl mobileAppServices;
    @Autowired
    private SiteService siteService;
    @Autowired
    private SiteUnitService siteUnitService;
    @Autowired
    private UnitServiceLogService unitServiceLogService;
    @Autowired
    private ServiceSiteUnits serviceSiteUnits;

    @RequestMapping("sites/{size}")
    @ResponseBody
    public List<SiteReource> getSites(@PathVariable String size) {

        int siteSize = Integer.parseInt(size);

        List<SiteReource> sitesReources = new ArrayList<>();
        List<Site> sites = siteService.findAll();
        if (sites.size() != siteSize) {
            for (Site site : sites) {
                SiteReource sr = new SiteReource();
                sr.setId(site.getId());
                sr.setName(site.getName());
                sitesReources.add(sr);
            }
        }
        return sitesReources;
    }

    @RequestMapping("trucks")
    @ResponseBody
    public List<TruckResources> getTrucks() {

        List<TruckResources> trucksResources = new ArrayList<>();
        System.out.println("Test truckService: " + truckService);

        List<Truck> trucks = truckService.findAll();

        for (Truck truck : trucks) {
            TruckResources tr = new TruckResources();
            tr.setId(truck.getId());
            tr.setNumberPlate(truck.getNumberPlate());
            tr.setVehicleNumber(truck.getVehicleNumber());
            trucksResources.add(tr);
        }

        return trucksResources;
    }

    @RequestMapping(value = "tagunit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public MobileResponseMessage deployUnit(@RequestBody UnitDeliveryResource unitDelivery) {
        final MobileResponseMessage mobileResponseMessage = new MobileResponseMessage();
        String message = mobileAppServices.deployUnitToSite(unitDelivery);
        mobileResponseMessage.setMessage(message);
        return mobileResponseMessage;
    }

    @RequestMapping(value = "serviceunit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public MobileResponseMessage submitService(@RequestBody UnitServiceResource unitService) {
        final MobileResponseMessage mobileResponseMessage = new MobileResponseMessage();
        String isValid = mobileAppServices.checkCoordinates(unitService);
        System.out.println("THE DISTANCE IS !!!!!! "+isValid);
        String message;
        if ("WITHIN".equalsIgnoreCase(isValid)) {
            unitService.setStatusMessage(isValid);
            if ("START".equals(unitService.getServiceType())) {
                message = isValid + " " + serviceSiteUnits.serviceFirstUnit(unitService);
                mobileResponseMessage.setMessage(message);
            } else {
                message = isValid + " " + serviceSiteUnits.serviceLastUnit(unitService);
                mobileResponseMessage.setMessage(message);
            }
        } else {
            message = isValid;
            UnitServiceLog unitServiceLog = new UnitServiceLog.Builder(new Date())
                    .serviceTime(new Date())
                    .statusMessage("AWAY FROM UNIT")
                    .siteName("NOT ON SITE")
                    .build();
            unitService.setStatusMessage(isValid);
            unitServiceLogService.persist(unitServiceLog);
            mobileAppServices.createUnitServiceLog(unitService, isValid);
            mobileResponseMessage.setMessage(message);
        }
        return mobileResponseMessage;
    }

    @RequestMapping(value = "geoplot", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public MobileResponseMessage submitGeoPlot(@RequestBody GeoplotResource geoPlotResource) {
        final MobileResponseMessage mobileResponseMessage = new MobileResponseMessage();
        mobileResponseMessage.setMessage("OK");
        return mobileResponseMessage;
    }
}
