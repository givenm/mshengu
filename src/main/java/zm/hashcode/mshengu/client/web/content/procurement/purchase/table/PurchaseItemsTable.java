/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.math.BigDecimal;
import zm.hashcode.mshengu.app.facade.procurement.RequestPurchaseItemFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.form.RequestForm;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form.RFQForm;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;

/**
 *
 * @author Luckbliss
 */
public class PurchaseItemsTable extends Table {

    private final MshenguMain main;

    public PurchaseItemsTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Item Description", String.class, null);
        addContainerProperty("Item Number", String.class, null);
        addContainerProperty("Quantity", String.class, null);
        addContainerProperty("Unit", String.class, null);
        addContainerProperty("Volume", String.class, null);
        addContainerProperty("Unit Price", String.class, null);
        addContainerProperty("Total", BigDecimal.class, null);
        addContainerProperty("Delete", Button.class, null);

        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    public void loadTable(RequestPurchaseItem item, final RequestForm form) {
        Button showDetails = new Button("Remove");
        showDetails.setData(item.getId());
        showDetails.setStyleName(Reindeer.BUTTON_LINK);
        showDetails.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String itemId = event.getButton().getData().toString();
                RequestPurchaseItem item = RequestPurchaseItemFacade.getRequestPurchaseItemService().findById(itemId);
                RequestPurchaseItemFacade.getRequestPurchaseItemService().delete(item);
                removeItem((String) event.getButton().getData());
                if (getItemIds().isEmpty()) {
                    form.approval.setVisible(false);
                }
            }
        });
        if (item.getProduct() != null) {
            ServiceProviderProduct serviceProviderProduct = item.getProduct();
            addItem(new Object[]{
                serviceProviderProduct.getProductName(),
                serviceProviderProduct.getItemNumber(),
                item.getQuantity(),
                serviceProviderProduct.getUnit(),
                serviceProviderProduct.getVolume(),
                serviceProviderProduct.getPrice().toString(),
                item.getSubTotal(),
                showDetails,}, item.getId());
        } else {
            addItem(new Object[]{
                item.getItemDescription(),
                item.getItemNumber(),
                item.getQuantity(),
                item.getUnit(),
                item.getVolume(),
                item.getUnitPrice(),
                item.getSubTotal(),
                showDetails,}, item.getId());
        }
    }
}
