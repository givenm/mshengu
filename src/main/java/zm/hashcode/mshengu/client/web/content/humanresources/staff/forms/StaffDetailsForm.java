/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.forms;

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
import zm.hashcode.mshengu.app.util.validation.UIValidatorHelper;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.models.StaffDetailsBean;

/**
 * I
 *
 * @author Ferox
 */
public class StaffDetailsForm extends FormLayout {

    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private UIComponentHelper UIComponent = new UIComponentHelper();
    private final StaffDetailsBean bean;
    public final BeanItem<StaffDetailsBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button terminate = new Button("Terminate");
    public TextField terminateReason;
    public TextField terminateCode;
    public TextField terminateDate;
    public Label errorMessage;

    public StaffDetailsForm() {
        bean = new StaffDetailsBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
            terminate.setVisible(false);



        CheckBox drivesCompanyCar = UIComponent.getCheckBox("Drives Company Car", "drivesCompanyCar", StaffDetailsBean.class, binder);
        
        ComboBox employementStatusId = UIComboBox.getEmploymentStatusomboBox("Employment Status", "employementStatusId", StaffDetailsBean.class, binder);
        employementStatusId = UIValidatorHelper.setRequiredComboBox(employementStatusId, "Employment Status");
        
        DateField leaveEndDate = UIComponent.getDateField("Leave End Date :", "leaveEndDate", StaffDetailsBean.class, binder);
                
        DateField leaveStartDate = UIComponent.getDateField("Leave Start Date :", "leaveStartDate", StaffDetailsBean.class, binder);

        TextField firstName = UIComponent.getTextField("Firstname :", "firstname", StaffDetailsBean.class, binder);
        firstName = UIValidatorHelper.setRequiredTextField(firstName, "Firstname");
        
        TextField lastName = UIComponent.getTextField("Lastname :", "lastname", StaffDetailsBean.class, binder);
        lastName = UIValidatorHelper.setRequiredTextField(lastName, "Lastname");
        
        TextField othername = UIComponent.getTextField("Other Name :", "othername", StaffDetailsBean.class, binder);
        
        TextField employeeNumber = UIComponent.getTextField("Employee Number :", "employeeNumber", StaffDetailsBean.class, binder);
        employeeNumber = UIValidatorHelper.setRequiredTextField(employeeNumber, "Employee Number ");
        
        TextField emailAddress = UIComponent.getTextField("Email :", "email", StaffDetailsBean.class, binder);
        emailAddress.addValidator(UIValidatorHelper.emailValidator());
        
        TextField mainNumber = UIComponent.getTextField("Mobile Number :", "mainNumber", StaffDetailsBean.class, binder);
        mainNumber.addValidator(UIValidatorHelper.mobileNumberValidator()); 
        mainNumber = UIValidatorHelper.setRequiredTextField(mainNumber, "Mobile Number");
        
        TextField otherNumber = UIComponent.getTextField("Telephone Number :", "otherNumber", StaffDetailsBean.class, binder);
        otherNumber.addValidator(UIValidatorHelper.phoneNumberValidator());
        
        TextArea streetAddress = UIComponent.getTextArea("Address :", "streetAddress", StaffDetailsBean.class, binder);
        //streetAddress.addValidator(new BeanValidator(StaffDetailsBean.class, "streetAddress"));

        TextField postalCode = UIComponent.getTextField("Postal Code :", "postalCode", StaffDetailsBean.class, binder);
//        postalCode.addValidator(UIValidatorHelper.postalCodeValidator());
        
        CheckBox requestor = UIComponent.getCheckBox("Allow To Request Purchase", "requestor", StaffDetailsBean.class, binder);
        
        TextField idNumber = UIComponent.getTextField("ID/Passport Number :", "idNumber", StaffDetailsBean.class, binder);
        idNumber = UIValidatorHelper.setRequiredTextField(idNumber, "ID/Passport Number");
        
        DateField dateofbirth = UIComponent.getDateField("Date of Birth :", "dateofbirth", StaffDetailsBean.class, binder);
        
        DateField permitExpire = UIComponent.getDateField("Permit Expiry :", "permitExpire", StaffDetailsBean.class, binder);
        
        ComboBox countryId = UIComboBox.getNationalityComboBox("Nationality :", "countryId", StaffDetailsBean.class, binder);
        countryId = UIValidatorHelper.setRequiredComboBox(countryId, "Nationality");
        
        ComboBox jobPositionId = UIComboBox.getJobPositionComboBox("Occupation :", "jobPositionId", StaffDetailsBean.class, binder);
        jobPositionId = UIValidatorHelper.setRequiredComboBox(jobPositionId, "Occupation");
        
        TextField driversLicenceNo = UIComponent.getTextField("Drivers Licence Number :", "driversLicenceNo", StaffDetailsBean.class, binder);
        
        DateField driversLicenceExpireDate = UIComponent.getDateField("Drivers Licence Expire Date :", "driversLicenceExpireDate", StaffDetailsBean.class, binder);
        DateField pdpExpireDate = UIComponent.getDateField("PDP Expire :", "pdpExpireDate", StaffDetailsBean.class, binder);
        
        DateField endDate = UIComponent.getDateField("Leave End Date :", "endDate", StaffDetailsBean.class, binder);
//        endDate = UIValidatorHelper.setRequiredDateField(endDate, "Leave End Date");
        
        DateField startDate = UIComponent.getDateField("Comencement Date :", "startDate", StaffDetailsBean.class, binder);        

        terminateReason = UIComponent.getTextField("Terminate Reason :", "terminateReason", StaffDetailsBean.class, binder);       
        
        terminateCode = UIComponent.getTextField("Terminate Code :", "terminateCode", StaffDetailsBean.class, binder);
        
        terminateDate = UIComponent.getTextField("Terminate Date :", "terminateDate", StaffDetailsBean.class, binder);
        
        errorMessage = UIComponent.getErrorLabel(); 
        
        GridLayout grid = new GridLayout(4, 16);
        
        grid.addComponent(errorMessage, 1, 0, 2, 0);
        
        grid.setSizeFull();

        grid.addComponent(firstName, 0, 1);
        grid.addComponent(othername, 1, 1);
        grid.addComponent(lastName, 2, 1);


        grid.addComponent(dateofbirth, 0, 2);
        grid.addComponent(mainNumber, 1, 2);
        grid.addComponent(otherNumber, 2, 2);


        grid.addComponent(countryId, 0, 3);
        grid.addComponent(idNumber, 1, 3);
        grid.addComponent(permitExpire, 2, 3);



        grid.addComponent(employeeNumber, 0, 4);
        grid.addComponent(employementStatusId, 1, 4);
        grid.addComponent(startDate, 2, 4);

        grid.addComponent(jobPositionId, 0, 5);
        grid.addComponent(requestor, 1, 5);
        grid.addComponent(drivesCompanyCar, 2, 5);

        grid.addComponent(driversLicenceNo, 0, 6);
        grid.addComponent(driversLicenceExpireDate, 1, 6);
        grid.addComponent(pdpExpireDate, 2, 6);


        grid.addComponent(leaveStartDate, 0, 7);
        grid.addComponent(leaveEndDate, 1, 7);
        grid.addComponent(streetAddress, 2, 7);

        grid.addComponent(terminateReason, 0, 8);
        grid.addComponent(terminateCode, 1, 8);
        grid.addComponent(terminateDate, 2, 8);
        setVisibleFalse();

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
        terminate.setSizeFull();

        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(terminate);
        return buttons;
    }

    private void setVisibleFalse() {
        terminateReason.setVisible(false);
        terminateCode.setVisible(false);
        terminateDate.setVisible(false);
    }
    
    public void setButtonsVisibleFalse(){
        edit.setVisible(false);
        terminate.setVisible(false);
    }
}
