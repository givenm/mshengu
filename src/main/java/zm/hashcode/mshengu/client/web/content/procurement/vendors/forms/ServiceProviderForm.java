/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.vendors.forms;

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
        TextField regNum = UIComponent.getTextField("Company Registration Number:", "registrationNum", ServiceProviderBean.class, binder);
        ComboBox legalForm = UIComboBox.getLegalFormComboBox("Legal form of the company:", "legalForm", ServiceProviderBean.class, binder);
        TextField yearOfBus = UIComponent.getTextField("Year of business establishment:", "yearsOfBus", ServiceProviderBean.class, binder);
        TextField firstNameChiefExec = UIComponent.getTextField("First Name (Chief Executive/Proprietor):", "firstNameChiefExec", ServiceProviderBean.class, binder);
        TextField lastNameChiefExec = UIComponent.getTextField("Last Name (Chief Executive/Proprietor):", "lastNameChiefExec", ServiceProviderBean.class, binder);
        TextField vatNum = UIComponent.getTextField("Vat Registration Number:", "vatNum", ServiceProviderBean.class, binder);
        TextField website = UIComponent.getTextField("Website:", "website", ServiceProviderBean.class, binder);
        CheckBox active = UIComponent.getCheckBox("Vendor Status (Preferred / Non-preferred)", "active", ServiceProviderBean.class, binder);
        CheckBox vehicleMaintenance = UIComponent.getCheckBox("Vehicle Maintenance", "vehicleMaintenance", ServiceProviderBean.class, binder);
        //Contact Information
        Label contactInfo = new Label("Contact Information");
        TextArea address1 = UIComponent.getTextArea("Address Line 1:", "address1", ServiceProviderBean.class, binder);
        address1.addValidator(new BeanValidator(ServiceProviderBean.class, "address1"));
        TextArea address2 = UIComponent.getTextArea("Address Line 2:", "address2", ServiceProviderBean.class, binder);
        address1.addValidator(new BeanValidator(ServiceProviderBean.class, "address2"));
        TextField city = UIComponent.getTextField("City:", "city", ServiceProviderBean.class, binder);
        TextField code = UIComponent.getTextField("Postal Code: ", "code", ServiceProviderBean.class, binder);
        TextField firstName = UIComponent.getTextField("Contact Person Name:", "firstName", ServiceProviderBean.class, binder);
        TextField lastName = UIComponent.getTextField("Contact Person Surname:", "lastName", ServiceProviderBean.class, binder);
        TextField mainNumber = UIComponent.getTextField("Telephone Number:", "mainNumber", ServiceProviderBean.class, binder);
        TextField faxNumber = UIComponent.getTextField("Fax Number:", "faxNumber", ServiceProviderBean.class, binder);
        TextField otherNumber = UIComponent.getTextField("Mobile Number :", "otherNumber", ServiceProviderBean.class, binder);
        TextField emailAddress = UIComponent.getTextField("Email :", "emailAddress", ServiceProviderBean.class, binder);

        //Organizational Information
        Label orgInfo = new Label("Organizational Information");
        ComboBox serviceProviderCategoryId = UIComboBox.getServiceProviderCategoryComboBox("Vendor Category", "serviceProviderCategoryId", ServiceProviderBean.class, binder);
        TextField bankName = UIComponent.getTextField("Bank Name: ", "bankName", ServiceProviderBean.class, binder);
        TextField accountNumber = UIComponent.getTextField("Account Number:", "accountNumber", ServiceProviderBean.class, binder);
        TextField branchCode = UIComponent.getTextField("Branch Code:", "branchCode", ServiceProviderBean.class, binder);

        GridLayout grid = new GridLayout(3, 20);
        grid.setSizeFull();


        grid.addComponent(generalInfo, 0, 0);
        grid.addComponent(new Label("<br>", ContentMode.HTML), 0, 1, 2, 1);
//vendorNumber
        grid.addComponent(name, 0, 2);
        grid.addComponent(vendorNumber, 1, 2);
        grid.addComponent(regNum, 2, 2);

        grid.addComponent(firstNameChiefExec, 0, 3);
        grid.addComponent(lastNameChiefExec, 1, 3);
        grid.addComponent(vatNum, 2, 3);

        grid.addComponent(legalForm, 0, 4);
        grid.addComponent(yearOfBus, 1, 4);
        grid.addComponent(website, 2, 4);

        grid.addComponent(active, 0, 5);
        grid.addComponent(vehicleMaintenance, 1, 5);

        grid.addComponent(new Label("<br>", ContentMode.HTML), 0, 6, 2, 6);
        grid.addComponent(contactInfo, 0, 7);
        grid.addComponent(new Label("<br>", ContentMode.HTML), 0, 8, 2, 8);

        grid.addComponent(address1, 0, 9);
        grid.addComponent(address2, 1, 9);
        grid.addComponent(city, 2, 9);

        grid.addComponent(code, 0, 10);
        grid.addComponent(firstName, 1, 10);
        grid.addComponent(lastName, 2, 10);

        grid.addComponent(mainNumber, 0, 11);
        grid.addComponent(faxNumber, 1, 11);
        grid.addComponent(otherNumber, 2, 11);

        grid.addComponent(emailAddress, 0, 12);

        grid.addComponent(new Label("<br>", ContentMode.HTML), 0, 13, 2, 13);
        grid.addComponent(orgInfo, 0, 14);
        grid.addComponent(new Label("<br>", ContentMode.HTML), 0, 15, 2, 15);

        grid.addComponent(serviceProviderCategoryId, 0, 16);
        grid.addComponent(bankName, 1, 16);
        grid.addComponent(accountNumber, 2, 16);

        grid.addComponent(branchCode, 0, 17);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 18, 2, 18);
        grid.addComponent(buttons, 0, 19, 2, 19);

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
