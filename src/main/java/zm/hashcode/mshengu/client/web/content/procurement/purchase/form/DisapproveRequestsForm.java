/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.PurchaseMenu;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.models.RequestBean;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class DisapproveRequestsForm extends VerticalLayout implements
        Button.ClickListener {

    private Request request;
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private MshenguMain main;
    private UIComponentHelper UIComponent = new UIComponentHelper();
    public final RequestBean bean = new RequestBean();
    public final BeanItem<RequestBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    private TextArea reason = new TextArea();

    public DisapproveRequestsForm(Request request, MshenguMain main) {
        this.main = main;
        setSizeFull();
        this.request = request;

        GridLayout layout = new GridLayout(3, 5);
        layout.setSizeFull();
        reason = UIComponent.getTextArea("Reason For Disapproval", "reason", RequestBean.class, binder);
        reason.setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSizeFull();
        save.setSizeFull();
        cancel.setSizeFull();

        buttons.addComponent(save);
        buttons.addComponent(cancel);

        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 0);
        layout.addComponent(reason, 0, 1, 1, 1);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 2);
        layout.addComponent(buttons, 0, 3, 2, 3);

        addListeners();
        addComponent(layout);
    }

    private void addListeners() {
        //Register Button Listeners
        save.addClickListener((Button.ClickListener) this);
        cancel.addClickListener((Button.ClickListener) this);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == save) {
            if (reason.getValue() != null) {
                disapprove(binder);
                getHome();
            } else{
                Notification.show("Enter The Reason!", Notification.Type.TRAY_NOTIFICATION);
            }
        } else if (source == cancel) {
            getHome();
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new PurchaseMenu(main, "DISSAPPROVED_REQUESTS"));
    }

    private void disapprove(FieldGroup binder) {
        try {
            binder.commit();
            RequestBean requestBean = ((BeanItem<RequestBean>) binder.getItemDataSource()).getBean();
            Request newRequest = new Request.Builder(request.getPerson())
                    .request(request)
                    .reasonForDisapproval(requestBean.getReason())
                    .build();
            RequestFacade.getRequestService().merge(newRequest);
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }
}
