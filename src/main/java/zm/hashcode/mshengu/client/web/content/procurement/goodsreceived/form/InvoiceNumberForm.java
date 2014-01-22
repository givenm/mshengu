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
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.GoodsReceivedMenu;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.models.GoodsBean;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class InvoiceNumberForm extends FormLayout implements
        Button.ClickListener {

    private String requestId;
    public UIComponentHelper UIComponent = new UIComponentHelper();
    private TextField invoiceNumber;
    private DateField deliveryDate;
    public final GoodsBean bean = new GoodsBean();
    public final BeanItem<GoodsBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    private Button save;
    private MshenguMain main;

    public InvoiceNumberForm(MshenguMain main, String id) {
        setSizeFull();
        this.main = main;
        requestId = id;
        invoiceNumber = UIComponent.getTextField("Enter The Invoice Number: ", "invoiceNumber", GoodsBean.class, binder);
        deliveryDate = UIComponent.getDateField("Delivery Date:", "deliveryDate", GoodsBean.class, binder);
        save = new Button("Save");
        save.setSizeFull();
        save.addClickListener((Button.ClickListener) this);

        GridLayout gridlayout = new GridLayout(3, 4);
        gridlayout.setSizeFull();
        gridlayout.addComponent(invoiceNumber, 0, 0);
        gridlayout.addComponent(deliveryDate, 1, 0);
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        gridlayout.addComponent(save, 0, 2, 2, 2);

        addComponent(gridlayout);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (invoiceNumber.getValue() != null && deliveryDate.getValue() != null) {
            Request request = RequestFacade.getRequestService().findById(requestId);
            Request newRequest = new Request.Builder(request.getPerson())
                    .request(request)
                    .invoiceNumber(invoiceNumber.getValue().toString())
                    .deliveryDate(deliveryDate.getValue())
                    .matchStatus("match")
                    .build();
            RequestFacade.getRequestService().merge(newRequest);
            getHome();
        } else {
            Notification.show("Please Enter All The Values");
        }
    }

    private void getHome() {
        Notification.show("Invoice Approved");
        main.content.setSecondComponent(new GoodsReceivedMenu(main, "LANDING"));
    }
}
