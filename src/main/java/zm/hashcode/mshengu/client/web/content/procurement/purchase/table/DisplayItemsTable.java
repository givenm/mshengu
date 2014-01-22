/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.table;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.util.Set;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;

/**
 *
 * @author Luckbliss
 */
public class DisplayItemsTable extends Table {

    public DisplayItemsTable(Set<RequestPurchaseItem> items) {
        setSizeFull();

        addContainerProperty("Item Description", String.class, null);
        addContainerProperty("Item Number", String.class, null);
        addContainerProperty("Quantity", String.class, null);
        addContainerProperty("Unit", String.class, null);
        addContainerProperty("Volume", String.class, null);
        addContainerProperty("Unit Price", BigDecimal.class, null);
        addContainerProperty("Total", BigDecimal.class, null);

        for (RequestPurchaseItem item : items) {
            if (item.getProduct() != null) {
                addItem(new Object[]{
                    item.getProduct().getProductName(),
                    item.getProduct().getItemNumber(),
                    item.getQuantity(),
                    item.getProduct().getUnit(),
                    item.getProduct().getVolume(),
                    item.getProduct().getPrice(),
                    item.getSubTotal()}, item.getId());
            } else {
                addItem(new Object[]{
                    item.getItemDescription(),
                    item.getItemNumber(),
                    item.getQuantity(),
                    item.getUnit(),
                    item.getVolume(),
                    item.getUnitPrice(),
                    item.getSubTotal()}, item.getId());
            }
        }
    }
}
