/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.facade.customer.CustomerFacade;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.models.ServiceRequestBean;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author Ferox
 */
public class InnactiveServiceRequestForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final ServiceRequestBean bean;
    public final BeanItem<ServiceRequestBean> item;
    public final FieldGroup binder;
   public ComboBox customerId;
  public  ComboBox siteId;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public InnactiveServiceRequestForm() {
        bean = new ServiceRequestBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        TextField refNumber = UIComponent.getTextField("Reference Number :", "refNumber", ServiceRequestBean.class, binder);
        customerId = UIComboBox.getCustomerComboBox("Select Customer:", "customerId", ServiceRequestBean.class, binder);
        siteId = UIComboBox.getEmptyComboBox("Site ", "siteId", ServiceRequestBean.class, binder);

        DateField requestDate = UIComponent.getDateField("Request Date:", "requestDate", ServiceRequestBean.class, binder);
        TextField firstName = UIComponent.getTextField("First Name:", "firstName", ServiceRequestBean.class, binder);
        TextField lastName = UIComponent.getTextField("Last Name:", "lastName", ServiceRequestBean.class, binder);

        TextField mainNumber = UIComponent.getTextField("Telephone Number:", "mainNumber", ServiceRequestBean.class, binder);
        TextField otherNumber = UIComponent.getTextField("Cell Number:", "otherNumber", ServiceRequestBean.class, binder);
        TextField emailAddress = UIComponent.getTextField("Email:", "emailAddress", ServiceRequestBean.class, binder);

        DateField deliveryDate = UIComponent.getDateField("Delivery Date :", "deliveryDate", ServiceRequestBean.class, binder);
        TextField deliveryTime = UIComponent.getTextField("Service Request Time :", "deliveryTime", ServiceRequestBean.class, binder);
        DateField collectionDate = UIComponent.getDateField("Collection Date :", "collectionDate", ServiceRequestBean.class, binder);

        CheckBox indefinitePeriod = UIComponent.getCheckBox("Indefinite Hire Period :", "indefinitePeriod", ServiceRequestBean.class, binder);
        ComboBox paymentMethodId = UIComboBox.getPaymentMethodComboBox("Payment Method :", "paymentMethodId", ServiceRequestBean.class, binder);
        TextField paymentAmout = UIComponent.getBigDecimalTextField("Payment Amount :", "paymentAmout", ServiceRequestBean.class, binder);

        ComboBox contractTypeId = UIComboBox.getContractTypeComboBox("Toilet Hire Terms :", "contractTypeId", ServiceRequestBean.class, binder);
        ComboBox mailNotificationsId = UIComboBox.getMailNotificationComboBox("Email Notification :", "mailNotificationsId", ServiceRequestBean.class, binder);
        CheckBox closed = UIComponent.getCheckBox("Closed :", "closed", ServiceRequestBean.class, binder);

        TextArea deliveryAddress = UIComponent.getTextArea("Service Request Address : ", "deliveryAddress", ServiceRequestBean.class, binder);
        deliveryAddress.addValidator(new BeanValidator(ServiceRequestBean.class, "deliveryAddress"));
        TextArea deliveryInstruction = UIComponent.getTextArea("Service Request Instruction :", "deliveryInstruction", ServiceRequestBean.class, binder);
        deliveryInstruction.addValidator(new BeanValidator(ServiceRequestBean.class, "deliveryInstruction"));

//            TextField basicAtlas = UIComponent.getTextField("Basic Atlas :", "basicAtlas", ServiceRequestBean.class, binder);
//            TextField executiveFlsuh = UIComponent.getTextField("Executive Flush:", "executiveFlsuh", ServiceRequestBean.class, binder);
//            TextField excPlusHandBasin = UIComponent.getTextField("Executive Plus & Hand Basin:", "excPlusHandBasin", ServiceRequestBean.class, binder);
//            TextField standardNonFlush = UIComponent.getTextField("Standard Non Flush:", "standardNonFlush", ServiceRequestBean.class, binder);
//            TextField wheelChair = UIComponent.getTextField("Wheel Chair:", "wheelChair", ServiceRequestBean.class, binder);
//            TextField builderAtlas = UIComponent.getTextField("Contact Person:", "builderAtlas", ServiceRequestBean.class, binder);


        TextField basicAtlasQty = UIComponent.getTextField("Basic Atlas : ", "basicAtlasQty", ServiceRequestBean.class, binder);
        TextField executiveFlsuhQty = UIComponent.getTextField("Executive Flush : ", "executiveFlsuhQty", ServiceRequestBean.class, binder);
        TextField excPlusHandBasinQty = UIComponent.getTextField("Executive Flush & Hand Basin:", "excPlusHandBasinQty", ServiceRequestBean.class, binder);
        TextField standardNonFlushQty = UIComponent.getTextField("Standard Non-Flush :", "standardNonFlushQty", ServiceRequestBean.class, binder);
        TextField wheelChairQty = UIComponent.getTextField("Wheel Chair Accessible : ", "wheelChairQty", ServiceRequestBean.class, binder);
        ComboBox serviceRequestType = UIComboBox.getServiceRequestTypeComboBox("Service Request Type : ", "serviceRequestTypeId", ServiceRequestBean.class, binder);

//        TextField builderAtlasQty = UIComponent.getTextField("Builder Atlas : ", "builderAtlasQty", ServiceRequestBean.class, binder);





        GridLayout grid = new GridLayout(4, 16);
        grid.setSizeFull();

        grid.addComponent(refNumber, 0, 0);
        grid.addComponent(customerId, 1, 0);
        grid.addComponent(siteId, 2, 0);

        grid.addComponent(requestDate, 0, 1);
        grid.addComponent(firstName, 1, 1);
        grid.addComponent(lastName, 2, 1);

        grid.addComponent(mainNumber, 0, 2);
        grid.addComponent(otherNumber, 1, 2);
        grid.addComponent(emailAddress, 2, 2);

        grid.addComponent(deliveryDate, 0, 3);
        grid.addComponent(deliveryTime, 1, 3);
        grid.addComponent(collectionDate, 2, 3);

        grid.addComponent(indefinitePeriod, 0, 4);
        grid.addComponent(paymentMethodId, 1, 4);
        grid.addComponent(paymentAmout, 2, 4);

        grid.addComponent(contractTypeId, 0, 5);
        grid.addComponent(mailNotificationsId, 1, 5);
        grid.addComponent(serviceRequestType , 2, 5);


        grid.addComponent(basicAtlasQty, 0, 6);
        grid.addComponent(standardNonFlushQty , 1, 6);
        grid.addComponent(wheelChairQty , 2, 6);


        grid.addComponent(executiveFlsuhQty, 0, 7);
        grid.addComponent(excPlusHandBasinQty, 1, 7);
        grid.addComponent(closed, 2, 7);

        grid.addComponent(deliveryAddress, 0, 8);
        grid.addComponent(deliveryInstruction, 1, 8);


        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 9, 2, 9);
        grid.addComponent(buttons, 0, 10, 2, 10);

        addComponent(grid);
    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        save.setSizeFull();
        edit.setSizeFull();
        cancel.setSizeFull();
        update.setSizeFull();
        delete.setSizeFull();

        save.setStyleName("default");
        edit.setStyleName("default");
        cancel.setStyleName("default");
        update.setStyleName("default");
        delete.setStyleName("default");


        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);
        return buttons;
    }

    public void loadCustomerSites(String customerId, boolean isReadOnly) {
        Customer customer = CustomerFacade.getCustomerService().findById(customerId);
        
        siteId.removeAllItems();
        if (customer != null) {
            if (customer.getSites() != null) {
                for (Site site : customer.getSites()) {
                    siteId.addItem(site.getId());
                    siteId.setItemCaption(site.getId(), site.getName());
                }
                siteId.addValidator(new BeanValidator(ServiceRequestBean.class, "siteId"));
                siteId.setImmediate(true);
                siteId.setNullSelectionAllowed(false);
                siteId.setWidth(250, Sizeable.Unit.PIXELS);
//                binder.bind(siteId, "siteId");
            }
        setReadOnly(isReadOnly);
        siteId.setReadOnly(isReadOnly);
        }
    }
}
