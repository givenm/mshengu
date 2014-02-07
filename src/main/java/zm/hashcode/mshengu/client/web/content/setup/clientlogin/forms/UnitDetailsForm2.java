/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.clientlogin.forms;

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
import zm.hashcode.mshengu.client.web.content.setup.clientlogin.models.UnitDetailsBean2;
import zm.hashcode.mshengu.app.util.UIComponentHelper;

/**
 *
 * @author Ferox
 */
public class UnitDetailsForm2  extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper uIComboBoxHelper = new UIComboBoxHelper();
    private final UnitDetailsBean2 bean;
    public final BeanItem<UnitDetailsBean2> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public UnitDetailsForm2() {
        bean = new UnitDetailsBean2();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        TextField unitId = UIComponent.getTextField("Unit ID:", "unitId", UnitDetailsBean2.class, binder);
        CheckBox deployed = UIComponent.getCheckBox("Deployed :", "deployed", UnitDetailsBean2.class, binder);
        TextField description = UIComponent.getTextField("Description :", "description", UnitDetailsBean2.class, binder);
        TextField operationalStatus = UIComponent.getTextField("Operational Status:", "operationalStatus", UnitDetailsBean2.class, binder);
        ComboBox unitTypeId = uIComboBoxHelper.getUnitTypeComboBox("Unit Type :", "unitTypeId", UnitDetailsBean2.class, binder);
        
       /* DateField dateofAction = UIComponent.getDateField("Contact Person Adress :", "dateofAction", UnitDetailsBean.class, binder);
        TextField latitude = UIComponent.getTextField("Latitude", "latitude", UnitDetailsBean.class, binder);
        TextField longitude = UIComponent.getTextField("Longitude", "longitude", UnitDetailsBean.class, binder);
*/

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(unitId, 0, 0);
        grid.addComponent(unitTypeId, 1, 0);
        grid.addComponent(deployed, 2, 0);

        grid.addComponent(description, 0, 1);
        grid.addComponent(operationalStatus, 1, 1);
//        grid.addComponent(dateofAction, 2, 1);
//
//        grid.addComponent(latitude, 0, 2);
//        grid.addComponent(longitude, 1, 2, 2, 2);
        //grid.addComponent(position, 2, 2);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

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
