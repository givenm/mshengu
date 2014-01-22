/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import zm.hashcode.mshengu.app.facade.procurement.RequestPurchaseItemFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form.RFQForm;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;

/**
 *
 * @author Luckbliss
 */
public class QuoteItemsTable extends Table{
    private final MshenguMain main;

    public QuoteItemsTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Item Description", String.class, null);
        addContainerProperty("Quantity", String.class, null);
        addContainerProperty("Unit", String.class, null);
        addContainerProperty("Volume", String.class, null);
        addContainerProperty("Delete", Button.class, null);

        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    public void loadTable(RequestPurchaseItem item, final RFQForm form) {
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
        addItem(new Object[]{
            item.getItemDescription(),
            item.getQuantity(),
            item.getUnit(),
            item.getVolume(),
            showDetails,}, item.getId());
    }
}
