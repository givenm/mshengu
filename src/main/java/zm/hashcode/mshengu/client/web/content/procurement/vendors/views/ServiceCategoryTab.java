/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.vendors.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Collection;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceCategoryFacade;
import zm.hashcode.mshengu.app.util.validation.OnSubmitValidationHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.ServiceProviderMenu;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.forms.ServiceCategoryForm;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.models.ServiceCategoryBean;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.tables.ServiceCategoryTable;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceCategory;

/**
 *
 * @author Ferox
 */
public class ServiceCategoryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ServiceCategoryForm form;
    private final ServiceCategoryTable table;

    public ServiceCategoryTab(MshenguMain app) {
        main = app;
        form = new ServiceCategoryForm();
        table = new ServiceCategoryTable(main);
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
            final ServiceCategory serviceCategory = ServiceCategoryFacade.getServiceCategoryService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(serviceCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            ServiceCategoryFacade.getServiceCategoryService().persist(getEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Collection<Field<?>> fields = binder.getFields();
            OnSubmitValidationHelper helper = new OnSubmitValidationHelper(fields, form.errorMessage);
            helper.doValidation();
            Notification.show("Please Correct Red Colored Inputs!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            ServiceCategoryFacade.getServiceCategoryService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Collection<Field<?>> fields = binder.getFields();
            OnSubmitValidationHelper helper = new OnSubmitValidationHelper(fields, form.errorMessage);
            helper.doValidation();
            Notification.show("Please Correct Red Colored Inputs!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void deleteForm(FieldGroup binder) {
        ServiceCategoryFacade.getServiceCategoryService().delete(getEntity(binder));
        getHome();
    }

    private ServiceCategory getEntity(FieldGroup binder) {
        final ServiceCategoryBean serviceCategoryBean = ((BeanItem<ServiceCategoryBean>) binder.getItemDataSource()).getBean();
        final ServiceCategory serviceCategory = new ServiceCategory.Builder(serviceCategoryBean.getName())
                .id(serviceCategoryBean.getId())
                .build();

        return serviceCategory;
    }

    private void getHome() {
        main.content.setSecondComponent(new ServiceProviderMenu(main, "SERVICE_CATEGORY"));
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

    private ServiceCategoryBean getBean(ServiceCategory serviceCategory) {
        ServiceCategoryBean bean = new ServiceCategoryBean();
        bean.setName(serviceCategory.getName());
        bean.setId(serviceCategory.getId());
        return bean;
    }
}
