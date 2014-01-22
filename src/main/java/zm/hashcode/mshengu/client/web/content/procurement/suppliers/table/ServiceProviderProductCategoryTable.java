/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.suppliers.table;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderProductCategoryFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProductCategory;

/**
 *
 * @author Ferox
 */
public class ServiceProviderProductCategoryTable extends Table {

    private final MshenguMain main;

    public ServiceProviderProductCategoryTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Product Category", String.class, null);


        // Add Data Columns
        List<ServiceProviderProductCategory> ServiceProviderProductCategoryList = ServiceProviderProductCategoryFacade.getServiceProviderProductCategoryService().findAll();
        for (ServiceProviderProductCategory ServiceProviderProductCategory : ServiceProviderProductCategoryList) {
            addItem(new Object[]{ServiceProviderProductCategory.getCategoryName(),}, ServiceProviderProductCategory.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}
