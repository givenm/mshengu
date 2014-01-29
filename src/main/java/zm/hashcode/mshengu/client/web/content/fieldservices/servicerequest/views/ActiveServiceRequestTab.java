/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.customer.ContractTypeFacade;
import zm.hashcode.mshengu.app.facade.customer.ServiceRequestFacade;
import zm.hashcode.mshengu.app.facade.customer.ServiceRequestTypeFacade;
import zm.hashcode.mshengu.app.facade.external.MailNotificationsFacade;
import zm.hashcode.mshengu.app.facade.people.ContactPersonFacade;
import zm.hashcode.mshengu.app.facade.ui.util.PaymentMethodFacade;
import zm.hashcode.mshengu.app.util.SendEmailHelper;
import zm.hashcode.mshengu.app.util.UtilMethods;
import zm.hashcode.mshengu.app.util.validation.OnSubmitValidationHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.ServiceRequestMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.forms.ActiveServiceRequestForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.models.ServiceRequestBean;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.tables.ActiveServiceRequestTable;
import zm.hashcode.mshengu.domain.customer.ContractType;
import zm.hashcode.mshengu.domain.customer.ServiceRequest;
import zm.hashcode.mshengu.domain.customer.ServiceRequestType;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.domain.incident.UserAction;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.ui.util.PaymentMethod;

/**
 *
 * @author Ferox
 */
public class ActiveServiceRequestTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ActiveServiceRequestForm form;
    private final ActiveServiceRequestTable table;
    private UtilMethods utilMethods = new UtilMethods();
    private SendEmailHelper sendEmailHelper = new SendEmailHelper();

    public ActiveServiceRequestTab(MshenguMain app) {
        main = app;
        form = new ActiveServiceRequestForm();
        table = new ActiveServiceRequestTable(main, this);
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
            final ServiceRequest serviceRequest = ServiceRequestFacade.getServiceRequestService().findById(table.getValue().toString());
//            final ServiceRequestBean serviceRequestBean = getBean(serviceRequest);
            
            form.setReadOnly(false);
            form.binder.setItemDataSource(new BeanItem<>(getBean(serviceRequest)));
            setReadFormProperties();
        } else if (property == form.customerId) {
            if (form.customerId.getValue() != null) {
                String customerId = form.customerId.getValue().toString();
                boolean isreadOnly = form.isReadOnly();
                form.setReadOnly(false);
                form.loadCustomerSites(customerId, isreadOnly);

            }
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            ServiceRequest serviceRequest = getEntity(binder, "ADD");
            ServiceRequestFacade.getServiceRequestService().persist( serviceRequest);
            sendEmailHelper.serviceRequest(serviceRequest);
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Collection<Field<?>> fields = binder.getFields();
            OnSubmitValidationHelper helper = new OnSubmitValidationHelper(fields, form.errorMessage);
            helper.doValidation();
            Notification.show("Please select customer!", Notification.Type.TRAY_NOTIFICATION); 
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            ServiceRequestFacade.getServiceRequestService().merge(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Collection<Field<?>> fields = binder.getFields();
            OnSubmitValidationHelper helper = new OnSubmitValidationHelper(fields, form.errorMessage);
            helper.doValidation();
            Notification.show("Please select customer!", Notification.Type.TRAY_NOTIFICATION); 
        }
    }

    private void deleteForm(FieldGroup binder) {
        ServiceRequestFacade.getServiceRequestService().delete(getEntity(binder, "DELETE"));
        getHome();
    }

    private ServiceRequest getUpdateEntity(FieldGroup binder) {
        final ServiceRequestBean serviceRequestBean = ((BeanItem<ServiceRequestBean>) binder.getItemDataSource()).getBean();

        ContactPerson contactPerson = createContactPerson(serviceRequestBean);
        ContactPersonFacade.getContactPersonService().merge(contactPerson);

        final MailNotifications mailNotifications = MailNotificationsFacade.getMailNotificationsService().findById(serviceRequestBean.getMailNotificationsId());
        final ServiceRequest existingServiceRequest = ServiceRequestFacade.getServiceRequestService().findById(serviceRequestBean.getId());
        final ContractType contractType = ContractTypeFacade.getContractTypeService().findById(serviceRequestBean.getContractTypeId());
        final PaymentMethod paymentMethod = PaymentMethodFacade.getPaymentMethodListService().findById(serviceRequestBean.getPaymentMethodId());
        final ServiceRequestType existingServiceRequestType = ServiceRequestTypeFacade.getServiceRequestTypeService().findById(serviceRequestBean.getServiceRequestTypeId());

        Set<UserAction> userActionsList = new HashSet<>();
        userActionsList.addAll(existingServiceRequest.getUserAction());


        final ServiceRequest serviceRequest = new ServiceRequest.Builder(serviceRequestBean.getDateofAction())
                .requestDate(serviceRequestBean.getRequestDate())
                .deliveryDate(serviceRequestBean.getDeliveryDate())
                .deliveryTime(serviceRequestBean.getDeliveryTime())
                .collectionDate(serviceRequestBean.getCollectionDate())
                .indefinitePeriod(serviceRequestBean.isIndefinitePeriod())
                .refNumber(serviceRequestBean.getRefNumber())
                .closed(serviceRequestBean.isClosed())
                .userAction(userActionsList)
                .contactPerson(contactPerson)
                .paymentMethod(paymentMethod)
                .paymentAmout(serviceRequestBean.getPaymentAmout())
                .contractType(contractType)
                .mailNotifications(mailNotifications)
                .serviceRequestType(existingServiceRequestType)
                .customerId(serviceRequestBean.getCustomerId())
                .siteId(serviceRequestBean.getSiteId())
                .deliveryAddress(serviceRequestBean.getDeliveryAddress())
                .deliveryInstruction(serviceRequestBean.getDeliveryInstruction())
                .basicAtlasQty(serviceRequestBean.getBasicAtlasQty())
                .standardNonFlushQty(serviceRequestBean.getStandardNonFlushQty())
                .wheelChairQty(serviceRequestBean.getWheelChairQty())
                .excPlusHandBasinQty(serviceRequestBean.getExcPlusHandBasinQty())
                .executiveFlsuhQty(serviceRequestBean.getExecutiveFlsuhQty())
                .builderAtlasQty(serviceRequestBean.getBuilderAtlasQty())
                .id(serviceRequestBean.getId())
                .build();

        return serviceRequest;


    }

    private ServiceRequest getEntity(FieldGroup binder, String action) {
        final ServiceRequestBean serviceRequestBean = ((BeanItem<ServiceRequestBean>) binder.getItemDataSource()).getBean();

        ContactPerson contactPerson = createContactPerson(serviceRequestBean);
        ContactPersonFacade.getContactPersonService().persist(contactPerson);

        final MailNotifications mailNotifications = MailNotificationsFacade.getMailNotificationsService().findById(serviceRequestBean.getMailNotificationsId());
        final ContractType contractType = ContractTypeFacade.getContractTypeService().findById(serviceRequestBean.getContractTypeId());
        final PaymentMethod paymentMethod = PaymentMethodFacade.getPaymentMethodListService().findById(serviceRequestBean.getPaymentMethodId());
        final ServiceRequestType existingServiceRequestType = ServiceRequestTypeFacade.getServiceRequestTypeService().findById(serviceRequestBean.getServiceRequestTypeId());

        Set<UserAction> userActionsList = new HashSet<>();

        String refNumber = null;
        if (action.equalsIgnoreCase("ADD")) {
            refNumber = utilMethods.getRefNumber(mailNotifications);
        } else {
            refNumber = serviceRequestBean.getRefNumber();
        }

        final ServiceRequest serviceRequest = new ServiceRequest.Builder(new Date())
                .requestDate(serviceRequestBean.getRequestDate())
                .deliveryDate(serviceRequestBean.getDeliveryDate())
                .deliveryTime(serviceRequestBean.getDeliveryTime())
                .collectionDate(serviceRequestBean.getCollectionDate())
                .indefinitePeriod(serviceRequestBean.isIndefinitePeriod())
                .refNumber(refNumber)
                .closed(serviceRequestBean.isClosed())
                .userAction(userActionsList)
                .contactPerson(contactPerson)
                .paymentMethod(paymentMethod)
                .paymentAmout(serviceRequestBean.getPaymentAmout())
                .contractType(contractType)
                .mailNotifications(mailNotifications)
                .serviceRequestType(existingServiceRequestType)
                .customerId(serviceRequestBean.getCustomerId())
                .siteId(serviceRequestBean.getSiteId())
                .deliveryAddress(serviceRequestBean.getDeliveryAddress())
                .deliveryInstruction(serviceRequestBean.getDeliveryInstruction())
                .basicAtlasQty(serviceRequestBean.getBasicAtlasQty())
                .standardNonFlushQty(serviceRequestBean.getStandardNonFlushQty())
                .wheelChairQty(serviceRequestBean.getWheelChairQty())
                .excPlusHandBasinQty(serviceRequestBean.getExcPlusHandBasinQty())
                .executiveFlsuhQty(serviceRequestBean.getExecutiveFlsuhQty())
                .builderAtlasQty(serviceRequestBean.getBuilderAtlasQty())
                .id(serviceRequestBean.getId())
                .build();

        return serviceRequest;



    }

    private void getHome() {
        getMain().content.setSecondComponent(new ServiceRequestMenu(getMain(), "LANDING"));
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
        form.customerId.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private ServiceRequestBean getBean(ServiceRequest serviceRequest) {
        ServiceRequestBean bean = new ServiceRequestBean();
        bean.setDateofAction(serviceRequest.getDateofAction());
        bean.setRequestDate(serviceRequest.getRequestDate());
        bean.setDeliveryDate(serviceRequest.getDeliveryDate());
        bean.setDeliveryTime(serviceRequest.getDeliveryTime());
        bean.setCollectionDate(serviceRequest.getCollectionDate());
        bean.setIndefinitePeriod(serviceRequest.isIndefinitePeriod());
        bean.setRefNumber(serviceRequest.getRefNumber());
        bean.setClosed(serviceRequest.isClosed());

        bean.setPaymentMethodId(serviceRequest.getPaymentMethodId());
        bean.setPaymentAmout(serviceRequest.getPaymentAmout());
        bean.setContractTypeId(serviceRequest.getContractTypeId());
        bean.setMailNotificationsId(serviceRequest.getMailNotificationsId());
        bean.setServiceRequestTypeId(serviceRequest.getServiceRequestTypeId());
        bean.setCustomerId(serviceRequest.getCustomerId());
        bean.setSiteId(serviceRequest.getSiteId());

        bean.setDeliveryAddress(serviceRequest.getDeliveryAddress());
        bean.setDeliveryInstruction(serviceRequest.getDeliveryInstruction());

        bean.setBasicAtlasQty(serviceRequest.getBasicAtlasQty());
        bean.setStandardNonFlushQty(serviceRequest.getStandardNonFlushQty());
        bean.setWheelChairQty(serviceRequest.getWheelChairQty());
        bean.setExcPlusHandBasinQty(serviceRequest.getExcPlusHandBasinQty());
        bean.setExecutiveFlsuhQty(serviceRequest.getExecutiveFlsuhQty());
        bean.setBuilderAtlasQty(serviceRequest.getBuilderAtlasQty());

        bean.setContactPersonId(serviceRequest.getContactPersonId());
        bean.setFirstName(serviceRequest.getContactFirstName());
        bean.setLastName(serviceRequest.getContactLastName());
        bean.setMainNumber(serviceRequest.getContactPersonMainNumber());
        bean.setOtherNumber(serviceRequest.getContactPersonAlternativeNumber());
        bean.setEmailAddress(serviceRequest.getContactPersonEmail());
        bean.setId(serviceRequest.getId());

        return bean;
    }

    private ContactPerson createContactPerson(ServiceRequestBean serviceRequestBean) {

        ContactPerson contactPerson = new ContactPerson.Builder(serviceRequestBean.getFirstName(), serviceRequestBean.getLastName())
                .mainNumber(serviceRequestBean.getMainNumber())
                .otherNumber(serviceRequestBean.getOtherNumber())
                .emailAddress(serviceRequestBean.getEmailAddress())
                .id(serviceRequestBean.getContactPersonId())
                .build();
        return contactPerson;

    }

    /**
     * @return the main
     */
    public MshenguMain getMain() {
        return main;
    }
}
