/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.setup.locations.model.LocationBean;

/**
 *
 * @author boniface
 */
public class ChildLocationForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final LocationBean bean;
    public final BeanItem<LocationBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public ChildLocationForm(String locationType, String parentLocationType) {
        bean = new LocationBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        
        
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);
        
        
        ComboBox locationTypes = UIComboBox.getLocationTypeComboBox("Location Type :", "locationType", LocationBean.class, binder);
        TextField name = UIComponent.getTextField("Location Name", "name", LocationBean.class, binder);

//        TextField code = UIComponent.getTextField("Location  Code", "code", LocationBean.class, binder);
        TextField latitude = UIComponent.getTextField("Latitude", "latitude", LocationBean.class, binder);
        TextField longitude = UIComponent.getTextField("Longitude", "longitude", LocationBean.class, binder);
        
        
             // Create a field group and use it to bind the fields in the layout


        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();


         grid.addComponent(name, 0, 0);

//        grid.addComponent(code, 1, 0);
        
         grid.addComponent(locationTypes,1, 0);
         
         grid.addComponent(longitude, 0, 1);

         grid.addComponent(latitude, 1, 1);
        



        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);


        addComponent(grid);
        addComponent(buttons);

    }

    // Add the bean validator
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
