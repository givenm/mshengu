/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.vendors.forms;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.app.util.validation.UIValidatorHelper;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.models.ServiceProviderBean;

/**
 *
 * @author Ferox
 */
public class ServiceProviderForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final ServiceProviderBean bean;
    public final BeanItem<ServiceProviderBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");
    public Label errorMessage;

    public ServiceProviderForm() {
        bean = new ServiceProviderBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        //General Information
        Label generalInfo = new Label("General Information");
        TextField vendorNumber = UIComponent.getTextField("Vendor Number:", "vendorNumber", ServiceProviderBean.class, binder);

        TextField name = UIComponent.getTextField("Name of Company:", "name", ServiceProviderBean.class, binder);
        name = UIValidatorHelper.setRequiredTextField(name, "Name of Company");

        TextField regNum = UIComponent.getTextField("Company Registration Number:", "registrationNum", ServiceProviderBean.class, binder);
        regNum = UIValidatorHelper.setRequiredTextField(regNum, "Company Registration Number");

        ComboBox legalForm = UIComboBox.getLegalFormComboBox("Legal form of the company:", "legalForm", ServiceProviderBean.class, binder);
        legalForm = UIValidatorHelper.setRequiredComboBox(legalForm, "Legal form of the company");

        TextField yearOfBus = UIComponent.getTextField("Year of business establishment:", "yearsOfBus", ServiceProviderBean.class, binder);
        yearOfBus = UIValidatorHelper.setRequiredTextField(yearOfBus, "Year of business establishment");
        yearOfBus.addValidator(UIValidatorHelper.yearValidator());

        TextField firstNameChiefExec = UIComponent.getTextField("First Name (Chief Executive/Proprietor):", "firstNameChiefExec", ServiceProviderBean.class, binder);
        firstNameChiefExec = UIValidatorHelper.setRequiredTextField(firstNameChiefExec, "First Name (Chief Executive/Proprietor)");

        TextField lastNameChiefExec = UIComponent.getTextField("Last Name (Chief Executive/Proprietor):", "lastNameChiefExec", ServiceProviderBean.class, binder);
        lastNameChiefExec = UIValidatorHelper.setRequiredTextField(lastNameChiefExec, "Last Name (Chief Executive/Proprietor)");

        final TextField vatNum = UIComponent.getTextField("Vat Registration Number:", "vatNum", ServiceProviderBean.class, binder);

        TextField website = UIComponent.getTextField("Website:", "website", ServiceProviderBean.class, binder);
        vatNum.setRequired(true);
        vatNum.setRequiredError("Vat Registration Number is required else tick 'Not Registered for VAT B/E'.");

        CheckBox active = UIComponent.getCheckBox("Vendor Status (Preferred / Non-preferred)", "active", ServiceProviderBean.class, binder);

        CheckBox vehicleMaintenance = UIComponent.getCheckBox("Vehicle Maintenance", "vehicleMaintenance", ServiceProviderBean.class, binder);
        final CheckBox registeredForVat = UIComponent.getCheckBox("Not Registered for VAT B/E", "registeredForVat", ServiceProviderBean.class, binder);
        registeredForVat.setHeight("20px"); //If clicked, then the Vat is NOT mandatory
        registeredForVat.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                if (registeredForVat.getValue()) {
                    vatNum.setRequired(false);
                    vatNum.removeStyleName("invalid");
                } else {
                    vatNum.setRequired(true);
                    vatNum.setRequiredError("Vat Registration Number is required else tick 'Not Registered for VAT B/E'.");
                }
            }
        });

//Contact Information
        Label contactInfo = new Label("Contact Information");

        TextArea address1 = UIComponent.getTextArea("Address Line 1:", "address1", ServiceProviderBean.class, binder);
        address1 = UIValidatorHelper.setRequiredTextArea(address1, "Address Line 1");
        address1.addValidator(new BeanValidator(ServiceProviderBean.class, "address1"));

        TextArea address2 = UIComponent.getTextArea("Address Line 2:", "address2", ServiceProviderBean.class, binder);
        address1.addValidator(new BeanValidator(ServiceProviderBean.class, "address2"));

        TextField city = UIComponent.getTextField("City:", "city", ServiceProviderBean.class, binder);
        city = UIValidatorHelper.setRequiredTextField(city, "City");

        TextField code = UIComponent.getTextField("Postal Code: ", "code", ServiceProviderBean.class, binder);
        code = UIValidatorHelper.setRequiredTextField(code, "Postal Code");
        code.addValidator(UIValidatorHelper.postalCodeValidator());

        TextField firstName = UIComponent.getTextField("Contact Person Name:", "firstName", ServiceProviderBean.class, binder);
        firstName = UIValidatorHelper.setRequiredTextField(firstName, "Contact Person Name");

        TextField lastName = UIComponent.getTextField("Contact Person Surname:", "lastName", ServiceProviderBean.class, binder);
        lastName = UIValidatorHelper.setRequiredTextField(lastName, "Contact Person Surname");

        TextField mainNumber = UIComponent.getTextField("Telephone Number:", "mainNumber", ServiceProviderBean.class, binder);
        mainNumber = UIValidatorHelper.setRequiredTextField(mainNumber, "Telephone Number");
        mainNumber.addValidator(UIValidatorHelper.phoneNumberValidator());

        TextField faxNumber = UIComponent.getTextField("Fax Number:", "faxNumber", ServiceProviderBean.class, binder);
        faxNumber.addValidator(UIValidatorHelper.faxNumberValidator());

        TextField otherNumber = UIComponent.getTextField("Mobile Number :", "otherNumber", ServiceProviderBean.class, binder);
        otherNumber.addValidator(UIValidatorHelper.mobileNumberValidator());

        TextField emailAddress = UIComponent.getTextField("Email :", "emailAddress", ServiceProviderBean.class, binder);
        emailAddress.addValidator(UIValidatorHelper.emailValidator());
        emailAddress = UIValidatorHelper.setRequiredTextField(emailAddress, "Email");

        //Organizational Information
        Label orgInfo = new Label("Organizational Information");

        ComboBox serviceProviderCategoryId = UIComboBox.getServiceProviderCategoryComboBox("Vendor Category", "serviceProviderCategoryId", ServiceProviderBean.class, binder);
        serviceProviderCategoryId = UIValidatorHelper.setRequiredComboBox(serviceProviderCategoryId, "Vendor Category");

        TextField bankName = UIComponent.getTextField("Bank Name: ", "bankName", ServiceProviderBean.class, binder);
        TextField accountNumber = UIComponent.getTextField("Account Number:", "accountNumber", ServiceProviderBean.class, binder);
        TextField branchCode = UIComponent.getTextField("Branch Code:", "branchCode", ServiceProviderBean.class, binder);

        errorMessage = UIComponent.getErrorLabel();

        GridLayout grid = new GridLayout(3, 22);
        grid.setSizeFull();

        grid.addComponent(errorMessage, 1, 0, 2, 0);

        grid.addComponent(generalInfo, 0, 1);
        grid.addComponent(new Label("<br>", ContentMode.HTML), 0, 2, 2, 2);
//vendorNumber
        grid.addComponent(name, 0, 3);
        grid.addComponent(vendorNumber, 1, 3);
        grid.addComponent(regNum, 2, 3);

        grid.addComponent(firstNameChiefExec, 0, 4);
        grid.addComponent(lastNameChiefExec, 1, 4);
        grid.addComponent(registeredForVat, 2, 4);

        grid.addComponent(legalForm, 0, 5);
        grid.addComponent(yearOfBus, 1, 5);
        grid.addComponent(vatNum, 2, 5);
        

        grid.addComponent(active, 0, 6);
        grid.addComponent(vehicleMaintenance, 1, 6);
        grid.addComponent(website, 2, 6);
               
        
        grid.addComponent(new Label("<br>", ContentMode.HTML), 0, 8, 2, 8);
        grid.addComponent(contactInfo, 0, 9);
        grid.addComponent(new Label("<br>", ContentMode.HTML), 0, 10, 2, 10);

        grid.addComponent(address1, 0, 11);
        grid.addComponent(address2, 1, 11);
        grid.addComponent(city, 2, 11);

        grid.addComponent(code, 0, 12);
        grid.addComponent(firstName, 1, 12);
        grid.addComponent(lastName, 2, 12);

        grid.addComponent(mainNumber, 0, 13);
        grid.addComponent(faxNumber, 1, 13);
        grid.addComponent(otherNumber, 2, 13);

        grid.addComponent(emailAddress, 0, 14);

        grid.addComponent(new Label("<br>", ContentMode.HTML), 0, 15, 2, 15);
        grid.addComponent(orgInfo, 0, 16);
        grid.addComponent(new Label("<br>", ContentMode.HTML), 0, 17, 2, 17);

        grid.addComponent(serviceProviderCategoryId, 0, 18);
        grid.addComponent(bankName, 1, 18);
        grid.addComponent(accountNumber, 2, 18);

        grid.addComponent(branchCode, 0, 19);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 20, 2, 20);
        grid.addComponent(buttons, 0, 21, 2, 21);

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
