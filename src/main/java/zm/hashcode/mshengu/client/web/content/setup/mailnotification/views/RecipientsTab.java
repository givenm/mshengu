/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.mailnotification.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.external.MailNotificationsFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.mailnotification.MailNotificationMenu;
import zm.hashcode.mshengu.client.web.content.setup.mailnotification.form.RecipientForm;
import zm.hashcode.mshengu.client.web.content.setup.mailnotification.models.RecipientBean;
import zm.hashcode.mshengu.client.web.content.setup.mailnotification.tables.RecipientTable;
import zm.hashcode.mshengu.domain.external.MailNotifications;

/**
 *
 * @author Ferox
 */
public class RecipientsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final RecipientForm form;
    private final RecipientTable table;
    private String notificationId;

    public RecipientsTab(MshenguMain app) {
        main = app;
        form = new RecipientForm();
        table = new RecipientTable(main);
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
//            final MailNotifications mailNotifications = MailNotificationsFacade.getMailNotificationsService().findById(table.getValue().toString());
            form.parentId.removeValueChangeListener((Property.ValueChangeListener) this);
            form.binder.setItemDataSource(new BeanItem<>(getBean(form.binder, table.getValue().toString())));
            form.parentId.addValueChangeListener((Property.ValueChangeListener) this);
            setReadFormProperties();
        } else if (property == form.parentId) {
            if (!StringUtils.isEmpty(form.parentId.getValue().toString())) {
                setNotificationId(form.parentId.getValue().toString());
                table.removeValueChangeListener((Property.ValueChangeListener) this);
                table.loadRecipients(form.parentId.getValue().toString());
                table.addValueChangeListener((Property.ValueChangeListener) this);
            }
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            MailNotificationsFacade.getMailNotificationsService().persist(getUpdatedADDEntity(binder,"ADD"));
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
            MailNotificationsFacade.getMailNotificationsService().merge(getUpdatedADDEntity(binder, "UPDATE"));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        try {
            binder.commit();
            MailNotificationsFacade.getMailNotificationsService().merge(getUpdatedADDEntity(binder, "REMOVE"));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private MailNotifications getUpdatedADDEntity(FieldGroup binder, String action) {

        final RecipientBean recipientBean = ((BeanItem<RecipientBean>) binder.getItemDataSource()).getBean();

        final MailNotifications existingMailNotifications = MailNotificationsFacade.getMailNotificationsService().findById(recipientBean.getParentId());
        List<String> emailList = new ArrayList<>();
        emailList.addAll(existingMailNotifications.getEmailList());
        if (action.equalsIgnoreCase("ADD")) {
            emailList.add(recipientBean.getEmail());
        } else if (action.equalsIgnoreCase("REMOVE")) {
            emailList.remove(recipientBean.getId());
            emailList.remove(null);
        } else if (action.equalsIgnoreCase("UPDATE")) {
            emailList.remove(recipientBean.getId());
            emailList.add(recipientBean.getEmail());
            emailList.remove(null);
        }
        
        final MailNotifications mailNotifications = new MailNotifications.Builder(existingMailNotifications.getName())
                .emailList(emailList)
                .sequence(existingMailNotifications.getSequence())
                .id(recipientBean.getParentId())
                .build();

        return mailNotifications;


    }


    private void getHome() {
        main.content.setSecondComponent(new MailNotificationMenu(main, "LANDING"));
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
        form.parentId.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private RecipientBean getBean(FieldGroup binder, String email) {
//        final RecipientBean recipientBean = ((BeanItem<RecipientBean>) binder.getItemDataSource()).getBean();

        RecipientBean bean = new RecipientBean();
        bean.setId(email);
        bean.setEmail(email);
        bean.setParentId(getNotificationId());

        return bean;
    }

    /**
     * @return the notificationId
     */
    public String getNotificationId() {
        return notificationId;
    }

    /**
     * @param notificationId the notificationId to set
     */
    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }
}
