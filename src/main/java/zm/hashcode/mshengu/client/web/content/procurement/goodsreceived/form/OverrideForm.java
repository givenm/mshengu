/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.form;

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
import zm.hashcode.mshengu.app.security.GetUserCredentials;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.GoodsReceivedMenu;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.models.GoodsBean;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class OverrideForm extends FormLayout {

    public UIComponentHelper UIComponent = new UIComponentHelper();
    public final GoodsBean bean = new GoodsBean();
    public final BeanItem<GoodsBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    private TextField override = new TextField();
    private TextField invoiceNumber;
    private DateField deliveryDate;
    private Button save;
    private final MshenguMain main;
    private final Request request;

    public OverrideForm(final MshenguMain main, final Request request) {
        this.main = main;
        this.request = request;
        setSizeFull();
        GridLayout gridlayout = new GridLayout(3, 15);
        gridlayout.setSizeFull();
        override = UIComponent.getBigDecimalTextField("Enter Invoice Total: (Incl.VAT) ", "override", GoodsBean.class, binder);
        invoiceNumber = UIComponent.getTextField("Enter The Invoice Number: ", "invoiceNumber", GoodsBean.class, binder);
        deliveryDate = UIComponent.getDateField("Delivery Date:", "deliveryDate", GoodsBean.class, binder);

        save = new Button("Override");

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSizeFull();
        save.setSizeFull();
        buttons.addComponent(save);

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
        gridlayout.addComponent(override, 0, 10);
        gridlayout.addComponent(invoiceNumber, 1, 10);
        gridlayout.addComponent(deliveryDate, 2, 10);
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 11);
        gridlayout.addComponent(buttons, 0, 12, 2, 12);

        addComponent(gridlayout);
        addSave();
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

    private void addSave() {
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Person user = new GetUserCredentials().getLoggedInPerson();
                if (user.getUsername().equalsIgnoreCase("hiltonjc@iafrica.com")) {
                    if (override.getValue() != null && invoiceNumber.getValue() != null && deliveryDate.getValue() != null) {
                        Request newRequest = new Request.Builder(request.getPerson())
                                .request(request)
                                .total(new BigDecimal(override.getValue()))
                                .invoiceNumber(invoiceNumber.getValue())
                                .deliveryDate(deliveryDate.getValue())
                                .matchStatus("match")
                                .build();
                        RequestFacade.getRequestService().merge(newRequest);
                        getHome();
                    } else {
                        Notification.show("Enter all the values!", Notification.Type.TRAY_NOTIFICATION);
                    }
                } else {
                    Notification.show("Not authorized to override!", Notification.Type.TRAY_NOTIFICATION);
                }
            }
        });
    }
}
