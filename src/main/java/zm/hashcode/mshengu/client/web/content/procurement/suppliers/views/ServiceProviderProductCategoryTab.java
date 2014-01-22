
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.suppliers.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderProductCategoryFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.SupplierMenu;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.form.ServiceProviderProductCategoryForm;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.models.ServiceProviderProductCategoryBean;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.table.ServiceProviderProductCategoryTable;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProductCategory;

/**
 *
 * @author Ferox
 */
public class ServiceProviderProductCategoryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ServiceProviderProductCategoryForm form;
    private final ServiceProviderProductCategoryTable table;

    public ServiceProviderProductCategoryTab(MshenguMain app) {
        main = app;
        form = new ServiceProviderProductCategoryForm();
        table = new ServiceProviderProductCategoryTable(main);
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
            final ServiceProviderProductCategory ServiceProviderProductCategory = ServiceProviderProductCategoryFacade.getServiceProviderProductCategoryService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(ServiceProviderProductCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            ServiceProviderProductCategoryFacade.getServiceProviderProductCategoryService().persist(getEntity(binder));
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
            ServiceProviderProductCategoryFacade.getServiceProviderProductCategoryService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        ServiceProviderProductCategoryFacade.getServiceProviderProductCategoryService().delete(getEntity(binder));
        getHome();
    }

    private ServiceProviderProductCategory getEntity(FieldGroup binder) {
        final ServiceProviderProductCategoryBean ServiceProviderProductCategoryBean = ((BeanItem<ServiceProviderProductCategoryBean>) binder.getItemDataSource()).getBean();
        final ServiceProviderProductCategory ServiceProviderProductCategory = new ServiceProviderProductCategory.Builder(ServiceProviderProductCategoryBean.getCategoryName())
                .id(ServiceProviderProductCategoryBean.getId())
                .build();

        return ServiceProviderProductCategory;
    }

    private void getHome() {
        main.content.setSecondComponent(new SupplierMenu(main, "PRODUCT_CATEGORY"));
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

    private ServiceProviderProductCategoryBean getBean(ServiceProviderProductCategory ServiceProviderProductCategory) {
        ServiceProviderProductCategoryBean bean = new ServiceProviderProductCategoryBean();
        bean.setCategoryName(ServiceProviderProductCategory.getCategoryName());
        bean.setId(ServiceProviderProductCategory.getId());
        return bean;
    }
}
