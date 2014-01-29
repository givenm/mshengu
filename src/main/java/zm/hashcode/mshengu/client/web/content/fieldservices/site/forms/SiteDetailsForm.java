/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.site.forms;

import com.google.common.collect.Collections2;
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
import java.util.Collection;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.location.LocationFacade;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.app.util.predicates.location.ChildLocationIdPredicate;
import zm.hashcode.mshengu.app.util.validation.UIValidatorHelper;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.models.SiteDetailsBean;
import zm.hashcode.mshengu.domain.ui.location.Location;

/**
 *
 * @author Ferox
 */
public class SiteDetailsForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final SiteDetailsBean bean;
    public final BeanItem<SiteDetailsBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");
    public ComboBox locationId;
    public ComboBox regionId;
    private Label lblWeeklyService;
    private Label lblMonthlyService;
    private Label lblCustomerSites;
    private Label lblTotalUnits;
    private Label lblInvisible;
    public Label errorMessage;

    public SiteDetailsForm() {
        bean = new SiteDetailsBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);
        lblWeeklyService = createNewLabel("Total Weekly Services : ");
        lblMonthlyService = createNewLabel("Total Monthly Services : ");
        lblCustomerSites = createNewLabel("Total Number of  Sites : ");
        lblTotalUnits = createNewLabel("Total Number of  Units : ");
        lblInvisible = createNewLabel("");



        TextField name = UIComponent.getTextField("Site Name", "name", SiteDetailsBean.class, binder);
        name = UIValidatorHelper.setRequiredTextField(name, "Site Name");
        
        TextField streetAddress = UIComponent.getTextField("Street Address", "streetAddress", SiteDetailsBean.class, binder);
        regionId = UIComboBox.getRegionsLocationComboBox("Region ", "regionId", SiteDetailsBean.class, binder);
        regionId = UIValidatorHelper.setRequiredComboBox(regionId, "Region");
        locationId = UIComboBox.getEmptyComboBox("Site Suburb", "locationId", SiteDetailsBean.class, binder);
        locationId = UIValidatorHelper.setRequiredComboBox(locationId, "Site Suburb");
        
        TextField expectedNumberOfUnits = UIComponent.getTextField("Expected number of units", "expectedNumberOfUnits", SiteDetailsBean.class, binder);
        expectedNumberOfUnits = UIValidatorHelper.setRequiredTextField(expectedNumberOfUnits, "Expected number of units");
        CheckBox monday = UIComponent.getCheckBox("Monday", "monday", SiteDetailsBean.class, binder);
        CheckBox tuesday = UIComponent.getCheckBox("Tuesday", "tuesday", SiteDetailsBean.class, binder);
        CheckBox wednesday = UIComponent.getCheckBox("Wednesday", "wednesday", SiteDetailsBean.class, binder);
        CheckBox thursday = UIComponent.getCheckBox("Thursday", "thursday", SiteDetailsBean.class, binder);
        CheckBox friday = UIComponent.getCheckBox("Friday", "friday", SiteDetailsBean.class, binder);
        CheckBox saturday = UIComponent.getCheckBox("Saturday", "saturday", SiteDetailsBean.class, binder);
        CheckBox sunday = UIComponent.getCheckBox("Sunday", "sunday", SiteDetailsBean.class, binder);
        CheckBox active = UIComponent.getCheckBox("Is Active", "active", SiteDetailsBean.class, binder);
//        ComboBox customerId = UIComboBox.getCustomerComboBox("Customer", "customerId", SiteBean.class, binder);
        // UIComponent

        errorMessage = UIComponent.getErrorLabel();
        
        GridLayout grid = new GridLayout(4, 11);
        grid.setSizeFull();

        grid.addComponent(errorMessage, 1, 0, 2, 0);
        
        grid.addComponent(name, 0, 1);
        grid.addComponent(expectedNumberOfUnits, 1, 1);


        grid.addComponent(regionId, 0, 2);
        grid.addComponent(locationId, 1, 2);
        grid.addComponent(streetAddress, 2, 2);

        grid.addComponent(monday, 0, 3);
        grid.addComponent(tuesday, 0, 4);
        grid.addComponent(wednesday, 0, 5);

        grid.addComponent(thursday, 1, 3);
        grid.addComponent(friday, 1, 4);
        grid.addComponent(saturday, 1, 5);


        grid.addComponent(sunday, 2, 3);
        grid.addComponent(active, 2, 4);


        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 6, 2, 6);
        grid.addComponent(buttons, 0, 7, 2, 7);



        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 8, 2, 8);
        grid.addComponent(lblCustomerSites, 0, 10);
        grid.addComponent(lblTotalUnits, 1, 10);
        grid.addComponent(lblWeeklyService, 2, 10);
        grid.addComponent(lblMonthlyService, 3, 10);

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

    public void loadSuburbs(String parentId, boolean isReadOnly) {
        List<Location> locations = LocationFacade.getLocationService().findAll();
        setReadOnly(false);
        locationId.setReadOnly(false);
        locationId.removeAllItems();
        Collection<Location> childLocations = Collections2.filter(locations, new ChildLocationIdPredicate(parentId));
        for (Location location : childLocations) {
            locationId.addItem(location.getId());
            locationId.setItemCaption(location.getId(), location.getName());
        }
        setReadOnly(isReadOnly);
        locationId.setReadOnly(isReadOnly);
    }

    public void setTotalNumberOfServices(int customerSites, int totalUnits, int weeklyServices, int monthlyServices) {
        lblWeeklyService.setValue("Total Weekly Services : " + weeklyServices);
        lblMonthlyService.setValue("Total Monthly Services : " + monthlyServices);
        lblCustomerSites.setValue("Total Number of  Sites : " + customerSites);
        lblTotalUnits.setValue("Total Number of  Units : " + totalUnits);
    }

    private Label createNewLabel(String value) {
        Label label = new Label(value);
        label.setSizeUndefined();
        label.addStyleName("h4");
        return label;
    }
}
