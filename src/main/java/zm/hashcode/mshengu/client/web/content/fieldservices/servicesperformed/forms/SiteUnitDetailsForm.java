/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.app.util.validation.UIValidatorHelper;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.models.SiteUnitsDetailsBean;

/**
 *
 * @author Ferox
 */
public class SiteUnitDetailsForm  extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper uIComboBoxHelper = new UIComboBoxHelper();
    private final SiteUnitsDetailsBean bean;
    public final BeanItem<SiteUnitsDetailsBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");
    public Label errorMessage;

    public SiteUnitDetailsForm() {
        super();
        bean = new SiteUnitsDetailsBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        TextField unitId = UIComponent.getTextField("Unit ID:", "unitId", SiteUnitsDetailsBean.class, binder);
        TextField description = UIComponent.getTextField("Description :", "description", SiteUnitsDetailsBean.class, binder);
        TextField operationalStatus = UIComponent.getTextField("Operational Status:", "operationalStatus", SiteUnitsDetailsBean.class, binder);
        ComboBox unitTypeId = uIComboBoxHelper.getUnitTypeComboBox("Unit Type :", "unitTypeId", SiteUnitsDetailsBean.class, binder);
        unitTypeId = UIValidatorHelper.setRequiredComboBox(unitTypeId, "Unit Type");
       /* DateField dateofAction = UIComponent.getDateField("Contact Person Adress :", "dateofAction", SiteUnitDetailsBean.class, binder);
        TextField latitude = UIComponent.getTextField("Latitude", "latitude", SiteUnitDetailsBean.class, binder);
        TextField longitude = UIComponent.getTextField("Longitude", "longitude", SiteUnitDetailsBean.class, binder);
*/
        errorMessage = UIComponent.getErrorLabel();

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();
        
        grid.addComponent(errorMessage, 1, 0, 2, 0);

        grid.addComponent(unitId, 0, 1);
        grid.addComponent(unitTypeId, 1, 1);

        grid.addComponent(description, 0, 2);
        grid.addComponent(operationalStatus, 1, 2);
//        grid.addComponent(dateofAction, 2, 1);
//
//        grid.addComponent(latitude, 0, 2);
//        grid.addComponent(longitude, 1, 2, 2, 2);
        //grid.addComponent(position, 2, 2);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 5, 2, 5);
       // grid.addComponent(buttons, 0, 5, 2, 5);

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
//.setStyleName("default");
        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);
        return buttons;
    }
}
