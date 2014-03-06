/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.table;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Set;
import javax.swing.JOptionPane;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;

/**
 *
 * @author Luckbliss
 */
public class DisplayItemsTable extends Table {

    private BigDecimal total = new BigDecimal("0");
    private DecimalFormat f = new DecimalFormat("###,###.00");

    public DisplayItemsTable(Set<RequestPurchaseItem> items, BigDecimal total) {

        this.total = total;
        setSizeFull();

        addContainerProperty("Item Description", String.class, null);
        addContainerProperty("Item Number", String.class, null);
        addContainerProperty("Quantity", String.class, null);
        addContainerProperty("Unit", String.class, null);
        addContainerProperty("Volume", String.class, null);
        addContainerProperty("Unit Price", BigDecimal.class, null);
        addContainerProperty("Total", BigDecimal.class, null);

        loadTable(items);
        if (total != null) {
            performRowStyling();
        }

    }

    private void loadTable(Set<RequestPurchaseItem> items) {
        addSubtotalRow();
        if (items != null) {
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

    private void addSubtotalRow() {
        if (total != null) {
            addItem(new Object[]{
                "Total",
                "",
                "",
                "",
                "",
                null,
                total,}, "subtotal");
        }
    }

    private void performRowStyling() {
        setCellStyleGenerator(new Table.CellStyleGenerator() {
            @Override
            public String getStyle(Table source, Object itemId, Object propertyId) {
                if (propertyId != null) {
                    return null;
                }
                String rowId = ((String) itemId).toString();
                if (rowId.equals("subtotal")) {
                    return "yellowrow";
                }
                return null;
            }
        });
    }
}
