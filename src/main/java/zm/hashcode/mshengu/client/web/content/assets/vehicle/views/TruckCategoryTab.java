package zm.hashcode.mshengu.client.web.content.assets.vehicle.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.fleet.TruckCategoryFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.FleetMenu;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.forms.TruckCategoryForm;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.models.TruckCategoryBean;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.tables.TruckCategoryTable;
import zm.hashcode.mshengu.domain.fleet.TruckCategory;

/**
 *
 * @author Ferox
 */
public class TruckCategoryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final TruckCategoryForm form;
    private final TruckCategoryTable table;

    public TruckCategoryTab(MshenguMain app) {
        main = app;
        form = new TruckCategoryForm();
        table = new TruckCategoryTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final TruckCategory truckCategory = TruckCategoryFacade.getTruckCategoryService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(truckCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            TruckCategoryFacade.getTruckCategoryService().persist(getEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            TruckCategoryFacade.getTruckCategoryService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        TruckCategoryFacade.getTruckCategoryService().delete(getEntity(binder));
        getHome();
    }

    private TruckCategory getEntity(FieldGroup binder) {
        //final  TruckCategory cust = new TruckCategory.Builder(binder.getItemDataSource().getItemProperty("name")).
        final TruckCategoryBean truckCategoryBean = ((BeanItem<TruckCategoryBean>) binder.getItemDataSource()).getBean();

        final TruckCategory truckCategory = new TruckCategory.Builder(truckCategoryBean.getCategoryName())
                .id(truckCategoryBean.getId())
                .namingCode(truckCategoryBean.getNamingCode())
                .value(truckCategoryBean.getValue())
                .build();

        return truckCategory;


    }

    private void getHome() {
        main.content.setSecondComponent(new FleetMenu(main, "CATEGORY"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private TruckCategoryBean getBean(TruckCategory truckCategory) {
        TruckCategoryBean bean = new TruckCategoryBean();
        bean.setCategoryName(truckCategory.getCategoryName());
        bean.setValue(truckCategory.getValue());
        bean.setNamingCode(truckCategory.getNamingCode());
        bean.setId(truckCategory.getId());
        return bean;
    }
}
