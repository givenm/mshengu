/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.suppliers.table;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;

/**
 *
 * @author Ferox
 */
public class ServiceProviderProductTable extends Table {

    private final MshenguMain main;

    public ServiceProviderProductTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

//        addContainerProperty("Supplier Name", String.class, null);
        addContainerProperty("Product Name", String.class, null);
        addContainerProperty("Price", BigDecimal.class, null);
        addContainerProperty("Product Category", String.class, null);
    }

    public void loadProducts(String supplierId) {
        removeAllItems();

        ServiceProvider supplier = ServiceProviderFacade.getServiceProviderService().findById(supplierId);

        if (supplier != null) {
            // Add Data Columns
            if (supplier.getServiceProviderProduct() != null) {

                for (ServiceProviderProduct ServiceProviderProduct : supplier.getServiceProviderProduct()) {
                    addItem(new Object[]{ServiceProviderProduct.getProductName(),
                                ServiceProviderProduct.getPrice(),
                                getCategory(ServiceProviderProduct),}, ServiceProviderProduct.getId());
                }
                // Allow selecting items from the table.
                setNullSelectionAllowed(false);

                setSelectable(true);
                // Send changes in selection immediately to server.
                setImmediate(true);
            }
        }
    }

    private String getCategory(ServiceProviderProduct product) {
        if (product.getProductCategory() != null) {
            return product.getServiceProviderProductCategoryName();
        } else {
            return null;
        }

    }
}
