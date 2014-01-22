/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.rest.api.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zm.hashcode.mshengu.client.rest.api.resources.GeoplotResource;
import zm.hashcode.mshengu.client.rest.api.resources.SiteReource;
import zm.hashcode.mshengu.client.rest.api.resources.UnitDeliveryResource;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.products.impl.MobileAppServiceImpl;
import zm.hashcode.mshengu.services.ui.util.RoleService;

/**
 *
 * @author Ferox
 */
@Controller
@RequestMapping("mobi")
public class ApiRestControllerMobi {

    @Autowired
    private RoleService roleService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private MobileAppServiceImpl mobileAppServices;
//SiteServiceContractLifeCycle

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

    /**
     * Unit Deployment
     *
     * @param unitDelivery
     * @return
     */
    @RequestMapping(value = "tagunit", method = RequestMethod.POST)
    @ResponseBody
    public String tagUnitLocation(@Valid @RequestBody UnitDeliveryResource unitDelivery) {
        return mobileAppServices.deployUnitToSite(unitDelivery);
    }

    /**
     * Unit Service
     *
     * @param unitService
     * @return
     */
    @RequestMapping(value = "serviceunit", method = RequestMethod.POST)
    @ResponseBody
    public String submitService(@Valid @RequestBody UnitServiceResource unitService) {
        String isValid = mobileAppServices.checkCoordinates(unitService);
        mobileAppServices.createUnitServiceLog(unitService, isValid);
        unitService.setStatusMessage(isValid);
        return unitService.getStatusMessage();
    }

    @RequestMapping(value = "geoplot", method = RequestMethod.POST)
    @ResponseBody
    public String submitGeoPlot(@Valid @RequestBody GeoplotResource geoPlotResource) {
        System.out.println(" THE SITE NAME SENT IS " + geoPlotResource.getSitename());
        Site site = siteService.findByName(geoPlotResource.getSitename());
        System.out.println("THE SITE NAME RETRIEVED FROM SERVER IS " + site);
        return siteService.geoPlotSite(site, geoPlotResource.getLatitude(), geoPlotResource.getLongitude());
    }
}
