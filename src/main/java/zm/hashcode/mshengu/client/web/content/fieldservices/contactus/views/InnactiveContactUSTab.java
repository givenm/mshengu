/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.contactus.views;

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
import zm.hashcode.mshengu.app.facade.external.ContactUSFacade;
import zm.hashcode.mshengu.app.facade.external.MailNotificationsFacade;
import zm.hashcode.mshengu.app.util.SendEmailHelper;
import zm.hashcode.mshengu.app.util.UtilMethods;
import zm.hashcode.mshengu.app.util.validation.OnSubmitValidationHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.contactus.ContactUSMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.contactus.form.InnactiveContactUSForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.contactus.models.ContactUSBean;
import zm.hashcode.mshengu.client.web.content.fieldservices.contactus.tables.InnactiveContactUSTable;
import zm.hashcode.mshengu.domain.external.ContactUS;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.domain.incident.UserAction;

/**
 *
 * @author Ferox
 */
public class InnactiveContactUSTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private SendEmailHelper sendEmailHelper = new SendEmailHelper();
    private final MshenguMain main;
    private final InnactiveContactUSForm form;
    private final InnactiveContactUSTable table;
    private UtilMethods utilMethods = new UtilMethods();

    public InnactiveContactUSTab(MshenguMain app) {
        main = app;
        form = new InnactiveContactUSForm();
        table = new InnactiveContactUSTable(main, this);
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
            final ContactUS contactUS = ContactUSFacade.getContactUSService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(contactUS)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            ContactUS contactUS = getEntity(binder, "ADD");
            ContactUSFacade.getContactUSService().persist(contactUS);
            sendEmailHelper.sedContactUsEmail(contactUS);
            sendEmailHelper.feedbackEmail(new Date(), contactUS.getEmail(), contactUS.getRefNumber(), contactUS.getMessage());
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
            ContactUSFacade.getContactUSService().merge(getUpdateEntity(binder));
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
        ContactUSFacade.getContactUSService().delete(getEntity(binder, "DELETE"));
        getHome();
    }

    private ContactUS getUpdateEntity(FieldGroup binder) {
        final ContactUSBean contactUSBean = ((BeanItem<ContactUSBean>) binder.getItemDataSource()).getBean();

        final MailNotifications mailNotifications = MailNotificationsFacade.getMailNotificationsService().findById(contactUSBean.getMailNotifications());

        ContactUS contactUs = ContactUSFacade.getContactUSService().findById(contactUSBean.getId());
        Set<UserAction> userActionsList = new HashSet<>();
        userActionsList.addAll(contactUs.getUserAction());


        final ContactUS contactUS = new ContactUS.Builder(contactUSBean.getDateOfAction())
                .refNumber(contactUSBean.getRefNumber())
                .closed(contactUSBean.isClosed())
                .company(contactUSBean.getCompany())
                .email(contactUSBean.getEmail())
                .faxNumber(contactUSBean.getFaxNumber())
                .hearAboutUs(contactUSBean.getHearAboutUs())
                .mailNotifications(mailNotifications)
                .message(contactUSBean.getMessage())
                .contactPersonFirstname(contactUSBean.getContactPersonFirstname())
                .contactPersonLastname(contactUSBean.getContactPersonLastname())
                .phone(contactUSBean.getPhone())
                .reason(contactUSBean.getReason())
                .userAction(userActionsList)
                .id(contactUSBean.getId())
                .build();

        return contactUS;


    }

    private ContactUS getEntity(FieldGroup binder, String action) {
        //final  ContactUS cust = new ContactUS.Builder(binder.getItemDataSource().getItemProperty("name")).

        final ContactUSBean contactUSBean = ((BeanItem<ContactUSBean>) binder.getItemDataSource()).getBean();

        final MailNotifications mailNotifications = MailNotificationsFacade.getMailNotificationsService().findById(contactUSBean.getMailNotifications());
        Set<UserAction> userActionsList = new HashSet<>();

        String refNumber = null;
        if (action.equalsIgnoreCase("ADD")) {
            refNumber = utilMethods.getRefNumber(mailNotifications);
        } else {
            refNumber = contactUSBean.getRefNumber();
        }

        final ContactUS contactUS = new ContactUS.Builder(new Date())
                .refNumber(refNumber)
                .closed(contactUSBean.isClosed())
                .company(contactUSBean.getCompany())
                .email(contactUSBean.getEmail())
                .faxNumber(contactUSBean.getFaxNumber())
                .hearAboutUs(contactUSBean.getHearAboutUs())
                .mailNotifications(mailNotifications)
                .message(contactUSBean.getMessage())
                .contactPersonFirstname(contactUSBean.getContactPersonFirstname())
                .contactPersonLastname(contactUSBean.getContactPersonLastname())
                .phone(contactUSBean.getPhone())
                .reason(contactUSBean.getReason())
                .userAction(userActionsList)
                .id(contactUSBean.getId())
                .build();

        return contactUS;


    }

    private void getHome() {
        main.content.setSecondComponent(new ContactUSMenu(main, "CLOSED"));
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

    private ContactUSBean getBean(ContactUS contactUS) {
        ContactUSBean bean = new ContactUSBean();
        bean.setDateOfAction(contactUS.getDateOfAction());
        bean.setClosed(contactUS.isClosed());
        bean.setCompany(contactUS.getCompany());
        bean.setFaxNumber(contactUS.getFaxNumber());
        bean.setHearAboutUs(contactUS.getHearAboutUs());
        bean.setMailNotifications(contactUS.getMailNotificationsId());
        bean.setEmail(contactUS.getEmail());
        bean.setMessage(contactUS.getMessage());
        bean.setContactPersonFirstname(contactUS.getContactPersonFirstname());
        bean.setContactPersonLastname(contactUS.getContactPersonLastname());
        bean.setPhone(contactUS.getPhone());
        bean.setStatus(contactUS.getLastUserActionStatusId());
        bean.setId(contactUS.getId());
        bean.setRefNumber(contactUS.getRefNumber());
        return bean;
    }
}