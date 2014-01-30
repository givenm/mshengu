/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.vendors.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceCategoryFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceCategory;

/**
 *
 * @author Ferox
 */
public class ServiceCategoryTable extends Table {

    private final MshenguMain main;

    public ServiceCategoryTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Category Name", String.class, null);


        // Add Data Columns
        List<ServiceCategory> serviceCategoryList = ServiceCategoryFacade.getServiceCategoryService().findAll();
        for (ServiceCategory serviceCategory : serviceCategoryList) {
            addItem(new Object[]{serviceCategory.getName(),}, serviceCategory.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}