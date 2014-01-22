/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.table;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;

/**
 *
 * @author Luckbliss
 */
public class ItemTable extends Table {

    public ItemTable() {
        setSizeFull();

        addContainerProperty("Item Description", String.class, null);
        addContainerProperty("Item Number", String.class, null);
        addContainerProperty("Quantity", String.class, null);
        addContainerProperty("Unit", String.class, null);
        addContainerProperty("Volume", String.class, null);
        addContainerProperty("Unit Price", BigDecimal.class, null);
        addContainerProperty("Total", BigDecimal.class, null);
    }

    public void loadTable(Request request) {
        if (request.getServiceProvider().getServiceProviderProduct() != null) {
            if (!request.getServiceProvider().getServiceProviderProduct().isEmpty()) {
                for (RequestPurchaseItem item : request.getRequestPurchaseItems()) {
                    addItem(new Object[]{
                        item.getProduct().getProductName(),
                        item.getProduct().getItemNumber(),
                        item.getQuantity(),
                        item.getProduct().getUnit(),
                        item.getProduct().getVolume(),
                        item.getProduct().getPrice(),
                        item.getSubTotal(),}, item.getId());
                }
            } else {
                for (RequestPurchaseItem item : request.getRequestPurchaseItems()) {
                    addItem(new Object[]{
                        item.getItemDescription(),
                        item.getItemNumber(),
                        item.getQuantity(),
                        item.getUnit(),
                        item.getVolume(),
                        item.getUnitPrice(),
                        item.getSubTotal(),}, item.getId());
                }
            }
        }
    }
}