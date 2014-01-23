/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.vendors.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderCategory;

/**
 *
 * @author Ferox
 */
public class ServiceProviderDatabaseTable extends Table {

    private final MshenguMain main;

    public ServiceProviderDatabaseTable(MshenguMain main) {
        this.main = main;
        setSizeFull();


        addContainerProperty("Vendor Name", String.class, null);
        addContainerProperty("Vendor Number", String.class, null);
        addContainerProperty("Vendor Category", String.class, null);
        addContainerProperty("Contact Person Name", String.class, null);
        addContainerProperty("Main Number", String.class, null);
        addContainerProperty("Email Address", String.class, null);
        //addContainerProperty("Contact Number", String.class, null);

        // Add Data Columns
        List<ServiceProvider> serviceProviderList = ServiceProviderFacade.getServiceProviderService().findAll();
        for (ServiceProvider serviceProvider : serviceProviderList) {
            String cPersonName = "N/A";
            String cPersonMainNumber = "N/A";
            String cPersonEmailAddress = "N/A";
            if (serviceProvider.getContactPerson() != null) {
                cPersonName = serviceProvider.getContactPersonName();
                cPersonMainNumber = serviceProvider.getContactPersonMainNumber();
                cPersonEmailAddress = serviceProvider.getContactPersonEmail();
            }
            addItem(new Object[]{serviceProvider.getName(),
                serviceProvider.getVendorNumber(),
                getServiceProviderCategory(serviceProvider.getServiceProviderCategory()),
                cPersonName,
                cPersonMainNumber,
                cPersonEmailAddress,}, serviceProvider.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }

    private Object getServiceProviderCategory(ServiceProviderCategory serviceProviderCategory) {
        if (serviceProviderCategory != null) {
            return serviceProviderCategory.getCategoryName();
        }
        return null;
    }
}
