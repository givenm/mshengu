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
public class AllSitesDetailsForm extends FormLayout {

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

    public AllSitesDetailsForm() {
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
//        name = UIValidatorHelper.setRequiredTextField(name, "Site Name");
        TextField streetAddress = UIComponent.getTextField("Street Address", "streetAddress", SiteDetailsBean.class, binder);
        regionId = UIComboBox.getRegionsLocationComboBox("Region ", "regionId", SiteDetailsBean.class, binder);
        regionId = UIValidatorHelper.setRequiredComboBox(regionId, "Region");
        locationId = UIComboBox.getEmptyComboBox("Site Suburb", "locationId", SiteDetailsBean.class, binder);
        locationId = UIValidatorHelper.setRequiredComboBox(locationId, "Site Suburb");
        // UIComponent


        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();


        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 5, 2, 5);
//        grid.addComponent(buttons, 0, 6, 2, 6);


         
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 7, 2, 7);
        grid.addComponent(lblCustomerSites, 0, 9);
        grid.addComponent(lblTotalUnits, 1, 9);
        grid.addComponent(lblWeeklyService, 2, 9);
        grid.addComponent(lblMonthlyService, 3, 9);

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
    


    public void loadSuburbs(String parentId) {
        List<Location> locations = LocationFacade.getLocationService().findAll();
        locationId.removeAllItems();
        Collection<Location> childLocations = Collections2.filter(locations, new ChildLocationIdPredicate(parentId));
        for (Location location : childLocations) {
            locationId.addItem(location.getId());
            locationId.setItemCaption(location.getId(), location.getName());
        }
    }

    public void setTotalNumberOfServices(int customerSites, int totalUnits, int weeklyServices, int monthlyServices) {
        lblWeeklyService.setValue("Total Weekly Services : " + weeklyServices);
        lblMonthlyService.setValue("Total Monthly Services : " + monthlyServices);
        lblCustomerSites.setValue("Total Number of  Sites : " + customerSites);
        lblTotalUnits.setValue("Total Number of  Units : " + totalUnits);
    }

    private Label createNewLabel( String value) {
        Label label = new Label(value);
        label.setSizeUndefined();
        label.addStyleName("h4");
        return label;
    }
}
