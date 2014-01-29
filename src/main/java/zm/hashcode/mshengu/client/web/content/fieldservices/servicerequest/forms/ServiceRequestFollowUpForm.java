/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.app.util.validation.UIValidatorHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.models.IncidentFollowUpBean;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.models.ServiceRequestFollowUpBean;

/**
 *
 * @author Luckbliss
 */
public class ServiceRequestFollowUpForm extends FormLayout{

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final ServiceRequestFollowUpBean bean;
    public final BeanItem<ServiceRequestFollowUpBean> item;
    public final FieldGroup binder;
    private final MshenguMain main;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button back = new Button("Back");
    public Button delete = new Button("Delete");
    public Label errorMessage;

    public ServiceRequestFollowUpForm(MshenguMain app) {
        setSizeFull();
        this.main = app;
        bean = new ServiceRequestFollowUpBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent

        ComboBox status = UIComboBox.getIncidentStatusComboBox("Status :", "status", IncidentFollowUpBean.class, binder);
        status = UIValidatorHelper.setRequiredComboBox(status, "Status");
        
        DateField resolvedDate = UIComponent.getDateField("Resolved Date:", "resolvedDate", IncidentFollowUpBean.class, binder);
        resolvedDate = UIValidatorHelper.setRequiredDateField(resolvedDate, "Resolved Date");
        
        TextArea comment = UIComponent.getTextArea("Remarks:", "comment", IncidentFollowUpBean.class, binder);
        comment = UIValidatorHelper.setRequiredTextArea(comment, "Remarks");
        
        DateField actionDate = UIComponent.getDateField("Reported On:", "actionDate", IncidentFollowUpBean.class, binder);
        actionDate = UIValidatorHelper.setRequiredDateField(actionDate, "Reported On");
        
        DateField qualityAssuranceDate = UIComponent.getDateField("Quality Assurance Date:", "qualityAssuranceDate", IncidentFollowUpBean.class, binder);
        qualityAssuranceDate = UIValidatorHelper.setRequiredDateField(qualityAssuranceDate, "Quality Assurance Date");

        errorMessage = UIComponent.getErrorLabel();


        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();
        
        grid.addComponent(errorMessage, 1, 0, 2, 0);

        grid.addComponent(status, 0, 1);
        grid.addComponent(resolvedDate, 1, 1);
        grid.addComponent(comment, 0, 2);
        grid.addComponent(qualityAssuranceDate, 1, 2);


        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 6, 2, 6);
        grid.addComponent(buttons, 0, 7, 2, 7);
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 8, 2, 8);

        addComponent(grid);
    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        save.setSizeFull();
        edit.setSizeFull();
        cancel.setSizeFull();
        update.setSizeFull();
        delete.setSizeFull();
        back.setSizeFull();

        save.setStyleName("default");
        edit.setStyleName("default");
        cancel.setStyleName("default");
        update.setStyleName("default");
        delete.setStyleName("default");
        back.setStyleName("default");



        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);
        buttons.addComponent(back);
        return buttons;
    }
}
