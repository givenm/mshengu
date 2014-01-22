/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.vendors.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderCategoryFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderCategory;

/**
 *
 * @author Ferox
 */
public class ServiceProviderCategoryTable extends Table {

    private final MshenguMain main;

    public ServiceProviderCategoryTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Vendors Category", String.class, null);


        // Add Data Columns
        List<ServiceProviderCategory> serviceProviderCategoryList = ServiceProviderCategoryFacade.getServiceProviderCategoryService().findAll();
        for (ServiceProviderCategory serviceProviderCategory : serviceProviderCategoryList) {
            addItem(new Object[]{serviceProviderCategory.getCategoryName(),}, serviceProviderCategory.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}
