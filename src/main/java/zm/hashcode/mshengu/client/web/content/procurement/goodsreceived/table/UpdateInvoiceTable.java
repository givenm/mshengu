/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.GoodsReceivedMenu;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.views.UpdateInvoiceTab;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public final class UpdateInvoiceTable extends Table {

    private MshenguMain main;

    public UpdateInvoiceTable(MshenguMain tab) {
        this.main = tab;
        setSizeFull();

        addContainerProperty("Date", String.class, null);
        addContainerProperty("Order Number", String.class, null);
        addContainerProperty("Supplier", String.class, null);
        addContainerProperty("Total", String.class, null);
        addContainerProperty("Update", Button.class, null);
        loadTables();
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    public void loadTables() {
        List<Request> list = RequestFacade.getRequestService().findByMisMatchStatus();
        if (list != null) {
            DecimalFormat f = new DecimalFormat("### ###.00");
            for (Request request : list) {
                Button update = new Button("Update");
                update.setData(request.getId());
                update.setStyleName(Reindeer.BUTTON_LINK);
                update.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        String itemId = event.getButton().getData().toString();
                        Request request = RequestFacade.getRequestService().findById(itemId);
                        Request newRequest = new Request.Builder(request.getPerson())
                                .request(request)
                                .matchStatus(null)
                                .build();
                        RequestFacade.getRequestService().merge(newRequest);
                        getHome();
                    }
                });
                addItem(new Object[]{
                    getDelivery(request.getMisMatchDate()),
                    request.getOrderNumber(),
                    request.getServiceProviderName(),
                    f.format(request.getTotal()),
                    update,}, request.getId());
            }
        }
    }

    private String getDelivery(Date date) {
        if (date != null) {
            return new DateTimeFormatHelper().getDayMonthYear(date);
        }
        return null;
    }

    private void getHome() {
        Notification.show("Invoice Updated");
        main.content.setSecondComponent(new GoodsReceivedMenu(main, "LANDING"));
    }
}
