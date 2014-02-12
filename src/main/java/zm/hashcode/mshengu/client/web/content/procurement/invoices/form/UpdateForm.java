/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import java.math.BigDecimal;
import java.util.Date;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.models.GoodsBean;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.InvoicesMenu;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.views.InvoicePaidTab;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.views.InvoicesTab;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class UpdateForm extends FormLayout {

    private MshenguMain main;
    private final InvoicePaidTab tab;
    public final GoodsBean bean = new GoodsBean();
    public final BeanItem<GoodsBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    public Button save = new Button("Save");
    public Button cancel = new Button("Cancel");
    public UIComponentHelper UIComponent = new UIComponentHelper();
    public TextField amount = UIComponent.getBigDecimalTextField("Payment Amount:", "orderNumber", GoodsBean.class, binder);
    public DateField date = UIComponent.getDateField("Payment Date:", "deliveryDate", GoodsBean.class, binder);
    private final Request request;

    public UpdateForm(final MshenguMain main, final Request request, final InvoicePaidTab tab) {
        this.main = main;
        this.tab = tab;
        this.request = request;

        setSizeFull();
        GridLayout gridlayout = new GridLayout(3, 20);
        gridlayout.setSizeFull();

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSizeFull();
        save.setSizeFull();
        cancel.setSizeFull();
        buttons.addComponent(save);
        buttons.addComponent(cancel);

        Label ordernumber = new Label("Purchase Order Number: " + request.getOrderNumber());
        Label vendor = new Label("Vendor: " + request.getServiceProviderName());
        Label deliverydate = new Label("Delivery Date: " + getDelivery(request.getOrderDate()));
        Label person = new Label("Requesting Person: " + request.getPersonName());
        Label total = new Label("PO Amount (Incl.VAT): R" + request.getTotal());

        gridlayout.addComponent(ordernumber, 0, 0);
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        gridlayout.addComponent(vendor, 0, 2);
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 3);
        gridlayout.addComponent(deliverydate, 0, 4);
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 5);
        gridlayout.addComponent(person, 0, 6);
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 7);
        gridlayout.addComponent(total, 0, 8);
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 9);
        gridlayout.addComponent(amount, 0, 10);
        gridlayout.addComponent(date, 1, 10);
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 11);
        gridlayout.addComponent(buttons, 0, 12, 2, 12);

        addComponent(gridlayout);

        addSave();
        addCancel();
    }

    private void addSave() {
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (amount.getValue() != null && date.getValue() != null) {
                    Request newRequest = new Request.Builder(request.getPerson())
                            .request(request)
                            .paymentAmount(new BigDecimal(amount.getValue()))
                            .paymentDate(date.getValue())
                            .build();
                    RequestFacade.getRequestService().merge(newRequest);
                    getHome();
                } else {
                    Notification.show("Enter all values!", Notification.Type.TRAY_NOTIFICATION);
                }

            }
        });
    }

    private void addCancel() {
        cancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getHome();
            }
        });
    }

    private String getDelivery(Date date) {
        if (date != null) {
            return new DateTimeFormatHelper().getDayMonthYear(date);
        }
        return null;
    }

    private void getHome() {
        main.content.setSecondComponent(new InvoicesMenu(main, "PAID"));
    }
}
