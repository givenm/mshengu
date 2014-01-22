/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.vendors.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderCategoryFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.ServiceProviderMenu;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.forms.ServiceProviderCategoryForm;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.models.ServiceProviderCategoryBean;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.tables.ServiceProviderCategoryTable;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderCategory;

/**
 *
 * @author Ferox
 */
public class ServiceProviderCategoryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ServiceProviderCategoryForm form;
    private final ServiceProviderCategoryTable table;

    public ServiceProviderCategoryTab(MshenguMain app) {
        main = app;
        form = new ServiceProviderCategoryForm();
        table = new ServiceProviderCategoryTable(main);
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
            final ServiceProviderCategory serviceProviderCategory = ServiceProviderCategoryFacade.getServiceProviderCategoryService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(serviceProviderCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            ServiceProviderCategoryFacade.getServiceProviderCategoryService().persist(getEntity(binder));
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
            ServiceProviderCategoryFacade.getServiceProviderCategoryService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        ServiceProviderCategoryFacade.getServiceProviderCategoryService().delete(getEntity(binder));
        getHome();
    }

    private ServiceProviderCategory getEntity(FieldGroup binder) {
        final ServiceProviderCategoryBean serviceProviderCategoryBean = ((BeanItem<ServiceProviderCategoryBean>) binder.getItemDataSource()).getBean();
        final ServiceProviderCategory serviceProviderCategory = new ServiceProviderCategory.Builder(serviceProviderCategoryBean.getCategoryName())
                .id(serviceProviderCategoryBean.getId())
                .build();
        
        return serviceProviderCategory;
    }

    private void getHome() {
        main.content.setSecondComponent(new ServiceProviderMenu(main, "PROVIDER_CATEGORY"));
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

    private ServiceProviderCategoryBean getBean(ServiceProviderCategory serviceProviderCategory) {
        ServiceProviderCategoryBean bean = new ServiceProviderCategoryBean();
        bean.setCategoryName(serviceProviderCategory.getCategoryName());
        bean.setId(serviceProviderCategory.getId());
        return bean;
    }


}
