/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.vendors.views;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.ServiceProviderMenu;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.tables.ServiceProviderDatabaseTable;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Ferox
 */
public class ServiceProviderDatabaseTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private final MshenguMain main;
    private final ServiceProviderDatabaseTable table;

    public ServiceProviderDatabaseTab(MshenguMain app) {
        main = app;
        table = new ServiceProviderDatabaseTable(main);
        setSizeFull();
        addComponent(table);
        addListeners();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final ServiceProvider serviceProvider = ServiceProviderFacade.getServiceProviderService().findById(table.getValue().toString());
//            setReadFormProperties();
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new ServiceProviderMenu(main, "LANDING"));
    }

    private void addListeners() {
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }
}
