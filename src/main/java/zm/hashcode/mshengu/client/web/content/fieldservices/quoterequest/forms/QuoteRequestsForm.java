/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.quoterequest.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
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
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.fieldservices.quoterequest.models.QuoteRequestBean;

/**
 *
 * @author Luckbliss
 */
public class QuoteRequestsForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final QuoteRequestBean bean;
    public final BeanItem<QuoteRequestBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public QuoteRequestsForm() {
        bean = new QuoteRequestBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);

        update.setVisible(false);
        delete.setVisible(false);
        edit.setVisible(false);

        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        DateField actionDate = UIComponent.getDateField("Action Date:", "actionDate", QuoteRequestBean.class, binder);
        TextField companyNameNonRequired = UIComponent.getTextField("Company Name:", "companyNameNonRequired", QuoteRequestBean.class, binder);
        TextField vatRegistrationNumberUnrequired = UIComponent.getTextField("VAT Registration Number:", "vatRegistrationNumberUnrequired", QuoteRequestBean.class, binder);
        TextField contactPersonFirstname = UIComponent.getTextField("Contact Person Firstname:", "contactPersonFirstname", QuoteRequestBean.class, binder);
        TextField contactPersonLastname = UIComponent.getTextField("Contact Person Lastname:", "contactPersonLastname", QuoteRequestBean.class, binder);
        TextField telephoneNumberNonRequired = UIComponent.getTextField("Company Tel Number:", "telephoneNumberNonRequired", QuoteRequestBean.class, binder);
        TextField contactNumber = UIComponent.getTextField("Contact Number:", "contactNumber", QuoteRequestBean.class, binder);
        TextField faxNumber = UIComponent.getTextField("Fax Number:", "faxNumber", QuoteRequestBean.class, binder);
        TextField email = UIComponent.getTextField("Email :", "email", QuoteRequestBean.class, binder);
        TextArea billingAddress = UIComponent.getTextArea("Billing Address", "billingAddress", QuoteRequestBean.class, binder);
        billingAddress.addValidator(new BeanValidator(QuoteRequestBean.class, "billingAddress"));
        TextArea deliveryAddress = UIComponent.getTextArea("Delivery Address", "deliveryAddress", QuoteRequestBean.class, binder);
        deliveryAddress.addValidator(new BeanValidator(QuoteRequestBean.class, "deliveryAddress"));
        ComboBox eventType = UIComboBox.getEventTypeComboBox("Event Type:", "eventType", QuoteRequestBean.class, binder);
        TextField eventName = UIComponent.getTextField("Event Name:", "eventName", QuoteRequestBean.class, binder);
        DateField eventDate = UIComponent.getDateField("Event Date:", "eventDate", QuoteRequestBean.class, binder);
        ComboBox toiletsRequired1 = UIComboBox.getUnitTypeComboBox("Toilets Required:", "toiletsRequired1", QuoteRequestBean.class, binder);
        TextField quantityRequired1 = UIComponent.getTextField("Quantity Required:", "quantityRequired1", QuoteRequestBean.class, binder);
        ComboBox toiletsRequired2 = UIComboBox.getUnitTypeComboBox("Additional Toilets Required 1:", "toiletsRequired2", QuoteRequestBean.class, binder);
        TextField quantityRequired2 = UIComponent.getTextField(" Additional Quantity Required 1:", "quantityRequired2", QuoteRequestBean.class, binder);
        ComboBox toiletsRequired3 = UIComboBox.getUnitTypeComboBox("Additional Toilets Required 2:", "toiletsRequired3", QuoteRequestBean.class, binder);
        TextField quantityRequired3 = UIComponent.getTextField("Additional Quantity Required 2:", "quantityRequired3", QuoteRequestBean.class, binder);
        TextField numberOfJanitors = UIComponent.getTextField("Number Of Janitors:", "numberOfJanitors", QuoteRequestBean.class, binder);
        TextField numberOfToiletRolls = UIComponent.getTextField("Number Of Toilet Rolls:", "numberOfToiletRolls", QuoteRequestBean.class, binder);
        DateField deliveryDate = UIComponent.getDateField("Delivery Date:", "deliveryDate", QuoteRequestBean.class, binder);
        DateField collectionDate = UIComponent.getDateField("Collection Date:", "collectionDate", QuoteRequestBean.class, binder);
        TextField daysRental = UIComponent.getTextField("Days Rental:", "daysRental", QuoteRequestBean.class, binder);
        TextArea comment = UIComponent.getTextArea("Comment", "comment", QuoteRequestBean.class, binder);
        comment.addValidator(new BeanValidator(QuoteRequestBean.class, "comment"));
        CheckBox serviceFrequencyMon = UIComponent.getCheckBox("Monday", "serviceFrequencyMon", QuoteRequestBean.class, binder);
        CheckBox serviceFrequencyTue = UIComponent.getCheckBox("Tuesday", "serviceFrequencyTue", QuoteRequestBean.class, binder);
        CheckBox serviceFrequencyWed = UIComponent.getCheckBox("Wednesday", "serviceFrequencyWed", QuoteRequestBean.class, binder);
        CheckBox serviceFrequencyThur = UIComponent.getCheckBox("Thursday", "serviceFrequencyThur", QuoteRequestBean.class, binder);
        CheckBox serviceFrequencyFri = UIComponent.getCheckBox("Friday", "serviceFrequencyFri", QuoteRequestBean.class, binder);
        CheckBox serviceFrequencySat = UIComponent.getCheckBox("Saturday", "serviceFrequencySat", QuoteRequestBean.class, binder);
        CheckBox serviceFrequencySun = UIComponent.getCheckBox("Sunday", "serviceFrequencySun", QuoteRequestBean.class, binder);


        GridLayout grid = new GridLayout(3, 15);
        grid.setSizeFull();


        grid.addComponent(actionDate, 0, 0);
        grid.addComponent(companyNameNonRequired, 1, 0);
        grid.addComponent(vatRegistrationNumberUnrequired, 2, 0);

        grid.addComponent(contactPersonFirstname, 0, 1);
        grid.addComponent(contactPersonLastname, 1, 1);
        grid.addComponent(telephoneNumberNonRequired, 2, 1);

        grid.addComponent(email, 0, 2);
        grid.addComponent(faxNumber, 1, 2);
        grid.addComponent(contactNumber, 2, 2);

        grid.addComponent(billingAddress, 0, 3);
        grid.addComponent(deliveryAddress, 1, 3);

        grid.addComponent(eventDate, 0, 4);
        grid.addComponent(eventType, 1, 4);
        grid.addComponent(eventName, 2, 4);

        grid.addComponent(toiletsRequired1, 0, 5);
        grid.addComponent(quantityRequired1, 1, 5);
        grid.addComponent(numberOfJanitors, 2, 5);

        grid.addComponent(toiletsRequired2, 0, 6);
        grid.addComponent(quantityRequired2, 1, 6);
        grid.addComponent(numberOfToiletRolls, 2, 6);

        grid.addComponent(toiletsRequired3, 0, 7);
        grid.addComponent(quantityRequired3, 1, 7);
        grid.addComponent(comment, 2, 7);

        grid.addComponent(deliveryDate, 0, 8);
        grid.addComponent(collectionDate, 1, 8);
        grid.addComponent(daysRental, 2, 8);

        grid.addComponent(serviceFrequencyMon, 0, 9);
        grid.addComponent(serviceFrequencyTue, 1, 9);
        grid.addComponent(serviceFrequencyWed, 2, 9);

        grid.addComponent(serviceFrequencyThur, 0, 10);
        grid.addComponent(serviceFrequencyFri, 1, 10);
        grid.addComponent(serviceFrequencySat, 2, 10);

        grid.addComponent(serviceFrequencySun, 0, 11);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 12, 2, 12);
        grid.addComponent(buttons, 0, 13, 2, 13);

        addComponent(grid);
    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        save.setSizeFull();
        edit.setSizeFull();
        cancel.setSizeFull();
        update.setSizeFull();
        delete.setSizeFull();

        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);
        return buttons;
    }
}
