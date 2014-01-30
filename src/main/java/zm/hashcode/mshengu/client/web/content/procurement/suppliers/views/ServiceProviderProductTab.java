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
import java.util.HashSet;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderProductCategoryFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderProductFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.SupplierMenu;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.form.ServiceProviderProductForm;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.models.ServiceProviderProductBean;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.table.ServiceProviderProductTable;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderCategory;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProductCategory;

/**
 * @author Ferox
 *
 */
public class ServiceProviderProductTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ServiceProviderProductForm form;
    private final ServiceProviderProductTable table;
    private static String supplierId;

    public ServiceProviderProductTab(MshenguMain app) {
        main = app;
        form = new ServiceProviderProductForm();
        table = new ServiceProviderProductTable(main);
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
            final ServiceProviderProduct ServiceProviderProduct = ServiceProviderProductFacade.getServiceProviderProductService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(ServiceProviderProduct)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            if (getSupplierId() != null) {
                binder.commit();
                ServiceProviderProduct serviceProviderProduct = getEntity(binder);
                ServiceProviderProductFacade.getServiceProviderProductService().persist(serviceProviderProduct);
                updateSuppiler(serviceProviderProduct);
                getHome();
                Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
            } else {
                Notification.show("Please select supplier !", Notification.Type.TRAY_NOTIFICATION);
            }
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            ServiceProviderProductFacade.getServiceProviderProductService().merge(getUpdatedEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        ServiceProviderProductFacade.getServiceProviderProductService().delete(getUpdatedEntity(binder));
        getHome();
    }

    private ServiceProviderProduct getEntity(FieldGroup binder) {
        final ServiceProviderProductBean serviceProviderProductBean = ((BeanItem<ServiceProviderProductBean>) binder.getItemDataSource()).getBean();
        final ServiceProviderProductCategory serviceProviderProductCategory = ServiceProviderProductCategoryFacade.getServiceProviderProductCategoryService().findById(serviceProviderProductBean.getProductCategoryId());

        final ServiceProviderProduct serviceProviderProduct = new ServiceProviderProduct.Builder(serviceProviderProductBean.getName())
                .productCategory(serviceProviderProductCategory)
                .price(serviceProviderProductBean.getPrice())
                .itemNumber(serviceProviderProductBean.getItemNumber())
                .unit(serviceProviderProductBean.getUnit())
                .volume(serviceProviderProductBean.getVolume())
                .build();
        return serviceProviderProduct;
    }

    private ServiceProviderProduct getUpdatedEntity(FieldGroup binder) {

        final ServiceProviderProductBean serviceProviderProductBean = ((BeanItem<ServiceProviderProductBean>) binder.getItemDataSource()).getBean();
        final ServiceProviderProductCategory serviceProviderProductCategory = ServiceProviderProductCategoryFacade.getServiceProviderProductCategoryService().findById(serviceProviderProductBean.getProductCategoryId());

        final ServiceProviderProduct serviceProviderProduct = new ServiceProviderProduct.Builder(serviceProviderProductBean.getName())
                .id(serviceProviderProductBean.getId())
                .productCategory(serviceProviderProductCategory)
                .price(serviceProviderProductBean.getPrice())
                .itemNumber(serviceProviderProductBean.getItemNumber())
                .unit(serviceProviderProductBean.getUnit())
                .volume(serviceProviderProductBean.getVolume())
                .build();

        return serviceProviderProduct;
    }

    private void updateSuppiler(ServiceProviderProduct serviceProviderProduct) {
        try {
            final Set<ServiceProviderProduct> serviceProviderProductList = new HashSet<>();

            ServiceProvider serviceProviderUpdate = ServiceProviderFacade.getServiceProviderService().findById(getSupplierId());
//        System.out.println("\n\n Service Provider id is: " + getSupplierId() + "\n\n");
            if (serviceProviderUpdate != null) {
                if (!serviceProviderUpdate.getServiceProviderProduct().isEmpty()) {
                    serviceProviderProductList.addAll(serviceProviderUpdate.getServiceProviderProduct());
                }

                serviceProviderProductList.add(serviceProviderProduct); //attaching the new product

                final ServiceProvider serviceProviderNew = new ServiceProvider.Builder(serviceProviderUpdate.getName())
                        .id(serviceProviderUpdate.getId())
                        .ServiceProvider(serviceProviderUpdate)
                        .serviceProviderCategory(serviceProviderUpdate.getServiceProviderCategory())
                        .contactPerson(serviceProviderUpdate.getContactPerson())
                        .serviceProviderProduct(serviceProviderProductList)
                        .build();

                ServiceProviderFacade.getServiceProviderService().merge(serviceProviderNew);
            } else {
                Notification.show("Error supplier not found!", Notification.Type.TRAY_NOTIFICATION);
            }


        } catch (Exception e) {
            Notification.show("Error assosiating produtc to supplier!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }

    }

    private void getHome() {
        main.content.setSecondComponent(new SupplierMenu(main, "PRODUCT"));
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

    private ServiceProviderProductBean getBean(ServiceProviderProduct serviceProviderProduct) {
        ServiceProviderProductBean bean = new ServiceProviderProductBean();
        bean.setName(serviceProviderProduct.getProductName());
        bean.setId(serviceProviderProduct.getId());
        bean.setPrice(serviceProviderProduct.getPrice());
        bean.setProductCategoryId(serviceProviderProduct.getServiceProviderProductCategoryId());
        bean.setItemNumber(serviceProviderProduct.getItemNumber());
        bean.setUnit(serviceProviderProduct.getUnit());
        bean.setVolume(serviceProviderProduct.getVolume());
        return bean;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
        table.removeValueChangeListener(this);
        table.loadProducts(supplierId);//Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    /**
     * @return the supplierId
     */
    public String getSupplierId() {
        return supplierId;
    }
}
