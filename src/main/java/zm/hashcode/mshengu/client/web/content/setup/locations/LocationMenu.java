/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.locations.views.AddressTypeTab;
import zm.hashcode.mshengu.client.web.content.setup.locations.views.ContactListTab;
import zm.hashcode.mshengu.client.web.content.setup.locations.views.CountryTab;
import zm.hashcode.mshengu.client.web.content.setup.locations.views.LocationTab;
import zm.hashcode.mshengu.client.web.content.setup.locations.views.LocationTypeTab;
import zm.hashcode.mshengu.client.web.content.setup.locations.views.ProvinceTab;
import zm.hashcode.mshengu.client.web.content.setup.locations.views.RegionTab;
import zm.hashcode.mshengu.client.web.content.setup.locations.views.RegistrationBodyTab;
import zm.hashcode.mshengu.client.web.content.setup.locations.views.SuburbTab;

/**
 *
 * @author boniface
 */
public class LocationMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;

    public LocationMenu(MshenguMain app, String selectedTab) {
        main = app;

        VerticalLayout locationTab = new VerticalLayout();
        locationTab.setMargin(true);
        locationTab.addComponent(new LocationTab(main));



        VerticalLayout countryTab = new VerticalLayout();
        countryTab.setMargin(true);
        countryTab.addComponent(new CountryTab(main));
        
        VerticalLayout provinceTab = new VerticalLayout();
        provinceTab.setMargin(true);
        provinceTab.addComponent(new ProvinceTab(main));    
                
        VerticalLayout regionTab = new VerticalLayout();
        regionTab.setMargin(true);
        regionTab.addComponent(new RegionTab(main));
        
        VerticalLayout suburbTab = new VerticalLayout();
        suburbTab.setMargin(true);
        suburbTab.addComponent(new SuburbTab(main));

        VerticalLayout addressTypeTab = new VerticalLayout();
        addressTypeTab.setMargin(true);
        addressTypeTab.addComponent(new AddressTypeTab(main));

        VerticalLayout contactListTab = new VerticalLayout();
        contactListTab.setMargin(true);
        contactListTab.addComponent(new ContactListTab(main));

        VerticalLayout locationTypeTab = new VerticalLayout();
        locationTypeTab.setMargin(true);
        locationTypeTab.addComponent(new LocationTypeTab(main));

        VerticalLayout registrationBodyTab = new VerticalLayout();
        registrationBodyTab.setMargin(true);
        registrationBodyTab.addComponent(new RegistrationBodyTab(main));





        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(countryTab, "Add Countries", null);
        tab.addTab(provinceTab, "Add  Provinces", null);        
        tab.addTab(regionTab, "Add Region", null);
        tab.addTab(suburbTab, "Add  Suburbs", null);
        tab.addTab(locationTypeTab, "Location Type", null);
        tab.addTab(locationTab, "Geographical Location", null);
        tab.addTab(addressTypeTab, "Address Types", null);
        tab.addTab(contactListTab, "Contact List", null);



//        tab.addTab(registrationBodyTab, "Registration Body", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(countryTab);
        }else if (selectedTab.equals("PROVINCE")) {
            tab.setSelectedTab(provinceTab);
        }  else if (selectedTab.equals("REGION")) {
            tab.setSelectedTab(regionTab);
        } else if (selectedTab.equals("SUBURB")) {
            tab.setSelectedTab(suburbTab);
        }  else if (selectedTab.equals("LOCATION")) {
            tab.setSelectedTab(locationTab);
        } else if (selectedTab.equals("LOCATION_TYPE")) {
            tab.setSelectedTab(locationTypeTab);
        } else if (selectedTab.equals("ADDRESSTYPE")) {
            tab.setSelectedTab(addressTypeTab);
        } else if (selectedTab.equals("CONTACTLIST")) {
            tab.setSelectedTab(contactListTab);
        } else if (selectedTab.equals("REGISTRATIONBODY")) {
            tab.setSelectedTab(registrationBodyTab);
        }

        addComponent(tab);
    }
}
