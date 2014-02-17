/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.view;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.WorkSchedulingMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.tables.AssignedSitesTable;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.tables.TrucksTable;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author Ferox
 */
public class ManageRoutesTab extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private Collection<String> siteIds;
    private String trucckId;
    private final TrucksTable table;
    final TwinColSelect select = new TwinColSelect();
    private final Button assignTrucksButton = new Button("Add Routes");
    private HorizontalLayout assignLayout = new HorizontalLayout();
    private AssignedSitesTable assignedSitesTable;
    private HorizontalLayout assingPanel = new HorizontalLayout();

    public ManageRoutesTab(MshenguMain app) {
        main = app;
        assignLayout.setSizeFull();
        assingPanel.setSizeFull();
        assignTrucksButton.setStyleName("default");
        assignTrucksButton.setSizeFull();
        assignTrucksButton.addClickListener((Button.ClickListener) this);
        table = new TrucksTable(main);

        select.setLeftColumnCaption("Select Routes");
        select.setRightColumnCaption(" Selected Routes");
        select.setSizeFull();
        select.setImmediate(true);
        select.setNewItemsAllowed(false);
        select.addValueChangeListener((Property.ValueChangeListener) this);
        table.addValueChangeListener((Property.ValueChangeListener) this);
        select.setNullSelectionAllowed(false);
        select.setMultiSelect(true);

        List<Site> sites = SiteFacade.getSiteService().findAll();


        for (Site site : sites) {
            select.addItem(site.getId());
            select.setItemCaption(site.getId(), site.getName() + " " + site.getAddressStreetAddress());
        }


        addComponent(table);
        assignLayout.addComponent(select);
        assignLayout.addComponent(assingPanel);
        addComponent(assignTrucksButton);
        addComponent(assignLayout);




    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == assignTrucksButton) {

            if (trucckId != null) {
                assignSitesTotruck(trucckId, siteIds);
                getHome();
            } else {
                Notification.show("Please Select Truck", Notification.Type.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == select) {
            Collection<String> ids = (Collection<String>) property.getValue();
            siteIds = ids;
        }
        if (property == table) {
            String tId = (String) property.getValue();
            trucckId = tId;
            assingPanel.removeAllComponents();
            assignedSitesTable = new AssignedSitesTable(main, trucckId);
            assignedSitesTable.setCaption("Added Routes");
            assingPanel.addComponent(assignedSitesTable);
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new WorkSchedulingMenu(main, "ROUTES"));
    }

    private void assignSitesTotruck(String trucckId, Collection<String> sitesId) {
        List<Site> routes = new ArrayList<>();
        Truck truck = TruckFacade.getTruckService().findById(trucckId);
        for (String id : sitesId) {
            Site site = SiteFacade.getSiteService().findById(id);
            routes.add(site);
        }
        routes.addAll(truck.getRoutes());
        Truck updatedTruck = new Truck.Builder(truck.getNumberPlate())
                .truck(truck)
                .routes(routes)
                .build();

        TruckFacade.getTruckService().merge(updatedTruck);
    }
}
