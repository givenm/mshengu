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
import zm.hashcode.mshengu.client.web.content.setup.locations.model.CountryBean;

/**
 *
 * @author boniface
 */
public class CountryForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final CountryBean bean;
    public final BeanItem<CountryBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public CountryForm() {
        bean = new CountryBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        
        
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);
        
        
        TextField name = UIComponent.getTextField("Country Name", "name", CountryBean.class, binder);

//        TextField code = UIComponent.getTextField("Country  Code", "code", CountryBean.class, binder);
        TextField latitude = UIComponent.getTextField("Latitude", "latitude", CountryBean.class, binder);
        TextField longitude = UIComponent.getTextField("Longitude", "longitude", CountryBean.class, binder);
        
        
             // Create a field group and use it to bind the fields in the layout


        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();


         grid.addComponent(name, 0, 0);

//        grid.addComponent(code, 1, 0);
        
//         grid.addComponent(locationTypes,1, 0);
         
         grid.addComponent(longitude, 1, 0);

         grid.addComponent(latitude, 2, 0);
        



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
