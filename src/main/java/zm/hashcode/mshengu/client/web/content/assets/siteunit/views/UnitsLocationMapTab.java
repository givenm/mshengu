/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.views;

import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import zm.hashcode.mshengu.app.facade.products.SiteUnitFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;

/*
 * @author Ferox
 */
public class UnitsLocationMapTab extends VerticalLayout {

    private final MshenguMain main;
    private GoogleMap googleMap;
    private Panel panel;
    private final String apiKey = "";

    public UnitsLocationMapTab(MshenguMain app) {
        main = app;
//        List<SiteUnit> units = SiteUnitFacade.getSiteUnitService().findAll();
        setSizeFull();
//        VerticalLayout map = new VerticalLayout();
//        map.setSizeFull();
//        map.setCaption("MAP");
        googleMap = new GoogleMap(new LatLon(-33.938803, 18.591185), 10.0, apiKey);
        googleMap.setSizeFull();

//        map.addComponent(googleMap);
//        map.setSizeFull();
        panel = new Panel();
        panel.setContent(googleMap);
        panel.setHeight("800px");
        addComponent(panel);
    }

    public void showUnitsLocations(List<SiteUnit> units) {
        String unitId = "";
        String latitude = "";
        String longitude = "";
        panel.setContent(null);
        googleMap = new GoogleMap(new LatLon(-33.938803, 18.591185), 10.0, apiKey);
        googleMap.setSizeFull();
        for (SiteUnit unit : units) {
            try {
                if (unit.getUnitLocationLifeCycle().size() > 0) {
                    UnitLocationLifeCycle location = SiteUnitFacade.getSiteUnitService().getUnitCurrentLocation(unit.getId());
                    unitId = unit.getUnitId();
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    GoogleMapMarker marker = new GoogleMapMarker(unit.getUnitId(), new LatLon(Double.parseDouble(location.getLatitude()), Double.parseDouble(location.getLongitude())), false);
                    googleMap.addMarker(marker);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Catch NumberFormatException");
                System.out.println("Unit ID " + unitId);
                System.out.println("latitude " + latitude);
                System.out.println("longidude " + longitude);
                System.out.println("===========================");

            }


        }
       panel.setContent(googleMap);
    }




}
