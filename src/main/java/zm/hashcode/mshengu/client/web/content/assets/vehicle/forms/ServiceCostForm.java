/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.forms;

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
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.models.ServiceCostBean;

/**
 *
 * @author Ferox
 */
public class ServiceCostForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final ServiceCostBean bean;
    public final BeanItem<ServiceCostBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public ServiceCostForm() {
        bean = new ServiceCostBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        DateField transactionDate = UIComponent.getDateField("Date of Invoice", "transactionDate", ServiceCostBean.class, binder);
        TextField slipNo = UIComponent.getTextField("Invoice Number:", "slipNo", ServiceCostBean.class, binder);
        ComboBox truckId = UIComboBox.getVehicleComboBox("Truck:", "truckId", ServiceCostBean.class, binder);
        TextArea comment = UIComponent.getTextArea("Service Comments:", "comment", ServiceCostBean.class, binder);
        comment.addValidator(new BeanValidator(ServiceCostBean.class, "comment"));

        ComboBox serviceProviderId = UIComboBox.getSuppliersComboBox("Supplier :", "serviceProviderId", ServiceCostBean.class, binder);
        ComboBox serviceCategoryId = UIComboBox.getServiceCategoryComboBox("Service Category", "serviceCategoryId", ServiceCostBean.class, binder);
        TextField serviceTotalCost = UIComponent.getBigDecimalTextField("Amount :", "serviceTotalCost", ServiceCostBean.class, binder);

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(transactionDate, 0, 0);
        grid.addComponent(slipNo, 1, 0);
        grid.addComponent(truckId, 2, 0);

        grid.addComponent(serviceTotalCost, 0, 1);
        grid.addComponent(serviceCategoryId, 1, 1);
        grid.addComponent(serviceProviderId, 2, 1);

        grid.addComponent(comment, 0, 2);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 3, 2, 3);
        grid.addComponent(buttons, 0, 4, 2, 4);

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
}
