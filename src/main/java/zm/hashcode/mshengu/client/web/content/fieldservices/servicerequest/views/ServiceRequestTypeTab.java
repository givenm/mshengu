/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.customer.ServiceRequestTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.ServiceRequestMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.forms.ServiceRequestTypeForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.models.ServiceRequestTypeBean;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.tables.ServiceRequestTypeTable;
import zm.hashcode.mshengu.domain.customer.ServiceRequestType;


/**
 *
 * @author Ferox
 */
public class ServiceRequestTypeTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ServiceRequestTypeForm form;
    private final ServiceRequestTypeTable table;

    public ServiceRequestTypeTab(MshenguMain app) {
        main = app;
        form = new ServiceRequestTypeForm();
        table = new ServiceRequestTypeTable(main);
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
            final ServiceRequestType truckCategory = ServiceRequestTypeFacade.getServiceRequestTypeService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(truckCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            ServiceRequestTypeFacade.getServiceRequestTypeService().persist(getEntity(binder));
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
            ServiceRequestTypeFacade.getServiceRequestTypeService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        ServiceRequestTypeFacade.getServiceRequestTypeService().delete(getEntity(binder));
        getHome();
    }

    private ServiceRequestType getEntity(FieldGroup binder) {
        final ServiceRequestTypeBean serviceRequestTypeBean = ((BeanItem<ServiceRequestTypeBean>) binder.getItemDataSource()).getBean();
        final ServiceRequestType paymentMethod = new ServiceRequestType.Builder(serviceRequestTypeBean.getName())
                .id(serviceRequestTypeBean.getId())
                .build();

        return paymentMethod;
    }

    private void getHome() {
        main.content.setSecondComponent(new ServiceRequestMenu(main, "REQUEST_TYPE"));
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

    private ServiceRequestTypeBean getBean(ServiceRequestType serviceRequestType) {
        ServiceRequestTypeBean bean = new ServiceRequestTypeBean();
        bean.setName(serviceRequestType.getName());
        bean.setId(serviceRequestType.getId());
        return bean;
    }
}
