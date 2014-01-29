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
import java.util.HashSet;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.external.MailNotificationsFacade;
import zm.hashcode.mshengu.app.facade.people.ContactPersonFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderCategoryFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.app.util.SendEmailHelper;
import zm.hashcode.mshengu.app.util.UtilMethods;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.ServiceProviderMenu;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.forms.ServiceProviderForm;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.models.ServiceProviderBean;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.tables.ServiceProviderTable;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderCategory;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;

/**
 *
 * @author Ferox
 */
public class ServiceProviderDetailsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ServiceProviderForm form;
    private final ServiceProviderTable table;
    private SendEmailHelper sendEmailHelper = new SendEmailHelper();
    private UtilMethods utilMethods = new UtilMethods();

    public ServiceProviderDetailsTab(MshenguMain app) {
        main = app;
        form = new ServiceProviderForm();
        table = new ServiceProviderTable(main);
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
            final ServiceProvider serviceProvider = ServiceProviderFacade.getServiceProviderService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(serviceProvider)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
//            ServiceProviderFacade.getServiceProviderService().persist(getEntity(binder));
            getEntity(binder);
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            ServiceProviderFacade.getServiceProviderService().merge(getUpdatedEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void deleteForm(FieldGroup binder) {
        ServiceProviderFacade.getServiceProviderService().delete(getUpdatedEntity(binder));
        getHome();
    }

    private ServiceProvider getEntity(FieldGroup binder) {

        MailNotifications mailNotifications = MailNotificationsFacade.getMailNotificationsService().findByName("VENDOR_NOTIFICATION");

        final ServiceProviderBean serviceProviderBean = ((BeanItem<ServiceProviderBean>) binder.getItemDataSource()).getBean();
        final Set<ServiceProviderProduct> serviceProviderProductList = new HashSet<>();
        final ServiceProviderCategory serviceProviderCategory = ServiceProviderCategoryFacade.getServiceProviderCategoryService().findById(serviceProviderBean.getServiceProviderCategoryId());

        final ContactPerson contactPerson = new ContactPerson.Builder(serviceProviderBean.getFirstName(), serviceProviderBean.getLastName())
                .address1(serviceProviderBean.getAddress1())
                .address2(serviceProviderBean.getAddress2())
                .emailAddress(serviceProviderBean.getEmailAddress())
                .code(serviceProviderBean.getCode())
                .mainNumber(serviceProviderBean.getMainNumber())
                .otherNumber(serviceProviderBean.getOtherNumber())
                .faxNumber(serviceProviderBean.getFaxNumber())
                .city(serviceProviderBean.getCity())
                .build();

        ContactPersonFacade.getContactPersonService().persist(contactPerson);
        final ServiceProvider serviceProvider = new ServiceProvider.Builder(serviceProviderBean.getName())
                .id(serviceProviderBean.getId())
                .bankName(serviceProviderBean.getBankName())
                .accountNumber(serviceProviderBean.getAccountNumber())
                .branchCode(serviceProviderBean.getBranchCode())
                .serviceProviderCategory(serviceProviderCategory)
                .contactPerson(contactPerson)
                .serviceProviderProduct(serviceProviderProductList)
                .active(serviceProviderBean.isActive())
                .preferedVendor(serviceProviderBean.isPreferedVendor())
                .vehicleMaintenance(serviceProviderBean.isVehicleMaintenance())
                .firstNameChiefExec(serviceProviderBean.getFirstNameChiefExec())
                .lastNameChiefExec(serviceProviderBean.getLastNameChiefExec())
                .website(serviceProviderBean.getWebsite())
                .legalForm(serviceProviderBean.getLegalForm())
                .yearsOfBusiness(serviceProviderBean.getYearsOfBus())
                .registrationNum(serviceProviderBean.getRegistrationNum())
                .vatNum(serviceProviderBean.getVatNum())
                .vendorNumber(utilMethods.getRefNumber(mailNotifications))
                .build();
        ServiceProviderFacade.getServiceProviderService().persist(serviceProvider);

        sendEmailHelper.vendoRegistration(serviceProvider, mailNotifications);
        return serviceProvider;
    }

    private ServiceProvider getUpdatedEntity(FieldGroup binder) {

        final Set<ServiceProviderProduct> serviceProviderProductList = new HashSet<>();

        final ServiceProviderBean serviceProviderBean = ((BeanItem<ServiceProviderBean>) binder.getItemDataSource()).getBean();

        final ServiceProviderCategory serviceProviderCategory = ServiceProviderCategoryFacade.getServiceProviderCategoryService().findById(serviceProviderBean.getServiceProviderCategoryId());

        ServiceProvider serviceProviderUpdate = ServiceProviderFacade.getServiceProviderService().findById(serviceProviderBean.getId());
        if (serviceProviderUpdate != null) {
            if (serviceProviderUpdate.getServiceProviderProduct() != null) {
                serviceProviderProductList.addAll(serviceProviderUpdate.getServiceProviderProduct());
            }
        }

        final ContactPerson person = serviceProviderUpdate.getContactPerson();
        ContactPerson contactPerson = getContactPerson(person, serviceProviderBean);

        final ServiceProvider serviceProvider = new ServiceProvider.Builder(serviceProviderBean.getName())
                .id(serviceProviderBean.getId())
                .bankName(serviceProviderBean.getBankName())
                .accountNumber(serviceProviderBean.getAccountNumber())
                .branchCode(serviceProviderBean.getBranchCode())
                .serviceProviderCategory(serviceProviderCategory)
                .contactPerson(contactPerson)
                .serviceProviderProduct(serviceProviderProductList)
                .active(serviceProviderBean.isActive())
                .preferedVendor(serviceProviderBean.isPreferedVendor())
                .vehicleMaintenance(serviceProviderBean.isVehicleMaintenance())
                .firstNameChiefExec(serviceProviderBean.getFirstNameChiefExec())
                .lastNameChiefExec(serviceProviderBean.getLastNameChiefExec())
                .website(serviceProviderBean.getWebsite())
                .legalForm(serviceProviderBean.getLegalForm())
                .yearsOfBusiness(serviceProviderBean.getYearsOfBus())
                .registrationNum(serviceProviderBean.getRegistrationNum())
                .vatNum(serviceProviderBean.getVatNum())
                .vendorNumber(serviceProviderUpdate.getVendorNumber())
                .build();

        return serviceProvider;
    }

    private ContactPerson getContactPerson(ContactPerson person, ServiceProviderBean serviceProviderBean) {
        ContactPerson contactPerson;
        if (person != null) {
            contactPerson = new ContactPerson.Builder(serviceProviderBean.getFirstName(), serviceProviderBean.getLastName())
                    .id(person.getId())
                    .address1(serviceProviderBean.getAddress1())
                    .address2(serviceProviderBean.getAddress2())
                    .emailAddress(serviceProviderBean.getEmailAddress())
                    .code(serviceProviderBean.getCode())
                    .mainNumber(serviceProviderBean.getMainNumber())
                    .otherNumber(serviceProviderBean.getOtherNumber())
                    .faxNumber(serviceProviderBean.getFaxNumber())
                    .city(serviceProviderBean.getCity())
                    .build();

            ContactPersonFacade.getContactPersonService().merge(contactPerson);
        } else {
            contactPerson = new ContactPerson.Builder(serviceProviderBean.getFirstName(), serviceProviderBean.getLastName())
                    .address1(serviceProviderBean.getAddress1())
                    .address2(serviceProviderBean.getAddress2())
                    .emailAddress(serviceProviderBean.getEmailAddress())
                    .code(serviceProviderBean.getCode())
                    .mainNumber(serviceProviderBean.getMainNumber())
                    .otherNumber(serviceProviderBean.getOtherNumber())
                    .faxNumber(serviceProviderBean.getFaxNumber())
                    .city(serviceProviderBean.getCity())
                    .build();

            ContactPersonFacade.getContactPersonService().persist(contactPerson);
        }
        return contactPerson;
    }

    private void getHome() {
        main.content.setSecondComponent(new ServiceProviderMenu(main, "VENDOR"));
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

    private ServiceProviderBean getBean(ServiceProvider serviceProvider) {
        ServiceProviderBean bean = new ServiceProviderBean();
        bean.setName(serviceProvider.getName());
        bean.setId(serviceProvider.getId());
        bean.setContactPersonId(serviceProvider.getContactPersonId());
        if (serviceProvider.getContactPerson() != null) {
            ContactPerson contactPerson = serviceProvider.getContactPerson();
            bean.setAddress1(contactPerson.getAddress1());
            bean.setAddress2(contactPerson.getAddress2());
            bean.setEmailAddress(contactPerson.getEmailAddress());
            bean.setFirstName(contactPerson.getFirstName());
            bean.setLastName(contactPerson.getLastName());
            bean.setMainNumber(contactPerson.getMainNumber());
            bean.setOtherNumber(contactPerson.getOtherNumber());
            bean.setFaxNumber(contactPerson.getFaxNumber());
            bean.setCity(contactPerson.getCity());
            bean.setCode(contactPerson.getCode());
        }
        bean.setServiceProviderCategoryId(serviceProvider.getServiceProvideCategoyId());
        bean.setActive(serviceProvider.isActive());
        bean.setPreferedVendor(serviceProvider.isPreferedVendor());
        bean.setVehicleMaintenance(serviceProvider.isVehicleMaintenance());
        bean.setBankName(serviceProvider.getBankName());
        bean.setAccountNumber(serviceProvider.getAccountNumber());
        bean.setBranchCode(serviceProvider.getBranchCode());
        bean.setFirstNameChiefExec(serviceProvider.getFirstNameChiefExec());
        bean.setLastNameChiefExec(serviceProvider.getLastNameChiefExec());
        bean.setLegalForm(serviceProvider.getLegalForm());
        bean.setRegistrationNum(serviceProvider.getRegistrationNum());
        bean.setYearsOfBus(serviceProvider.getYearsOfBusiness());
        bean.setWebsite(serviceProvider.getWebsite());
        bean.setVatNum(serviceProvider.getVatNum());
        bean.setVendorNumber(serviceProvider.getVendorNumber());
        return bean;
    }
}
