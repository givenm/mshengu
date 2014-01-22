/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.GoodsReceivedMenu;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.models.GoodsBean;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.table.ItemTable;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class GoodsReceivedForm extends FormLayout implements
        Button.ClickListener {

    public UIComponentHelper UIComponent = new UIComponentHelper();
    public final GoodsBean bean = new GoodsBean();
    public final BeanItem<GoodsBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    private TextField total;
    private TextField orderNumber;
    private Button check;
    private Label vendor;
    private Label deliveryDate;
    private Label person;
    private ItemTable table;
    private BigDecimal ordertotal = new BigDecimal("0");
    private String vendorname;
    private String vendordate;
    private String personname;
    private BigDecimal requesttotal;
    private int keep = 0;
    private String requestId;
    private MshenguMain main;

    public GoodsReceivedForm(MshenguMain main) {
        setSizeFull();
        this.main = main;
        GridLayout gridlayout = new GridLayout(3, 4);
        gridlayout.setSizeFull();
        orderNumber = UIComponent.getTextField("Enter Purchase Order Number: ", "orderNumber", GoodsBean.class, binder);
        total = UIComponent.getBigDecimalTextField("Enter The Total: (e.g 10000.00) ", "total", GoodsBean.class, binder);

        check = new Button("Compare Amounts");
        check.addClickListener((Button.ClickListener) this);
        check.setWidth("250px");

        gridlayout.addComponent(orderNumber, 0, 0);
        gridlayout.addComponent(total, 1, 0);

        vendor = new Label("Vendor: ");
        vendorname = vendor.getValue();
        vendor.setWidth("435px");
        deliveryDate = new Label("Delivery Date: ");
        vendordate = deliveryDate.getValue();
        person = new Label("Requesting Person: ");
        personname = person.getValue();

        GridLayout layout = new GridLayout(3, 6);
        layout.setSizeFull();
        layout.addComponent(vendor, 0, 0, 1, 0);
        layout.addComponent(check, 2, 0);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        layout.addComponent(deliveryDate, 0, 2);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 3);
        layout.addComponent(person, 0, 4, 2, 4);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 5);

        table = new ItemTable();

        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        gridlayout.addComponent(layout, 0, 2);
        gridlayout.addComponent(table, 0, 3, 2, 3);

        addComponent(gridlayout);

        //Hiding Components
        check.setVisible(false);
        total.setVisible(false);

        getOrderNumber();
    }

    public final void getOrderNumber() {
        orderNumber.addShortcutListener(new ShortcutListener("Enter Order Number:", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                if (target == orderNumber) {
                    if (orderNumber.getValue() != null) {
                        String order = orderNumber.getValue();
                        try {
                            Request request = RequestFacade.getRequestService().findByOrderNumber(order);
                            if (request != null) {
                                if (request.getStatus() != null) {
                                    Notification.show("Invoice already processed");
                                    check.setVisible(false);
                                    total.setVisible(false);
                                    table.removeAllItems();
                                    vendor.setValue(vendorname);
                                    deliveryDate.setValue(vendordate);
                                    person.setValue(personname);
                                } else {
                                    requestId = request.getId();
                                    table.removeAllItems();
                                    table.loadTable(request);
                                    check.setVisible(true);
                                    total.setVisible(true);
                                    total.setValue("");
                                    requesttotal = request.getTotal();
                                    vendor.setValue(vendorname + request.getServiceProviderName());
                                    deliveryDate.setValue(vendordate + getDelivery(request.getDeliveryDate()));
                                    person.setValue(personname + request.getPersonName());
                                }
                            } else {
                                Notification.show("Order Number Not Found");
                                check.setVisible(false);
                                total.setVisible(false);
                                vendor.setValue(vendorname);
                                deliveryDate.setValue(vendordate);
                                person.setValue(personname);
                            }


                        } catch (NumberFormatException e) {
                            Notification.show("Please enter a number");
                            check.setVisible(false);
                            total.setVisible(false);
                            table.removeAllItems();
                            vendor.setValue(vendorname);
                            deliveryDate.setValue(vendordate);
                            person.setValue(personname);
                        }
                    } else {
                        check.setVisible(false);
                        total.setVisible(false);
                        table.removeAllItems();
                        total.setValue("");
                        vendor.setValue(vendorname);
                        deliveryDate.setValue(vendordate);
                        person.setValue(personname);
                    }
                }
            }
        });
    }

    private String getDelivery(Date date) {
        if (date != null) {
            return new DateTimeFormatHelper().getDayMonthYear(date);
        }
        return null;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == check) {
            if (!total.getValue().isEmpty()) {
                DecimalFormat f = new DecimalFormat("### ###.00");
                if (requesttotal.compareTo(new BigDecimal(total.getValue())) == 0 && keep < 2) {
                    Request request = RequestFacade.getRequestService().findById(requestId);
                    if (request.getStatus() != null) {
                        Notification.show("Invoice already processed");
                    } else {
                        Notification.show("Match!");
                        InvoiceNumberForm form = new InvoiceNumberForm(main, requestId);
                        this.removeAllComponents();
                        this.addComponent(form);
                    }
                    check.setVisible(false);
                    total.setVisible(false);
                    table.removeAllItems();
                    vendor.setValue(vendorname);
                    deliveryDate.setValue(vendordate);
                    person.setValue(personname);
                } else {
                    if (keep <= 1) {
                        Notification.show("Error! Mismatch of totals - Try Again");
                        keep += 1;
                    } else {
                        Notification.show("Invoice has been moved to mismatched invoices");
                        Request request = RequestFacade.getRequestService().findById(requestId);
                        Request newRequest = new Request.Builder(request.getPerson())
                                .request(request)
                                .misMatchDate(new Date())
                                .matchStatus("mismatch")
                                .build();
                        RequestFacade.getRequestService().merge(newRequest);
                        main.content.setSecondComponent(new GoodsReceivedMenu(main, "LANDING"));
                    }
                }
            } else {
                Notification.show("Please enter the total");
            }
        }
    }
}
