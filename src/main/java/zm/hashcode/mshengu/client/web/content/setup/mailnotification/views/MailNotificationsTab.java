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
import zm.hashcode.mshengu.app.facade.external.MailNotificationsFacade;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.mailnotification.MailNotificationMenu;
import zm.hashcode.mshengu.client.web.content.setup.mailnotification.form.MailNotificationsForm;
import zm.hashcode.mshengu.client.web.content.setup.mailnotification.models.MailNotificationsBean;
import zm.hashcode.mshengu.client.web.content.setup.mailnotification.tables.MailNotificationsTable;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.domain.ui.util.Sequence;

/**
 *
 * @author Ferox
 */
public class MailNotificationsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final MailNotificationsForm form;
    private final MailNotificationsTable table;

    public MailNotificationsTab(MshenguMain app) {
        main = app;
        form = new MailNotificationsForm();
        table = new MailNotificationsTable(main);
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
            final MailNotifications mailNotifications = MailNotificationsFacade.getMailNotificationsService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(mailNotifications)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            MailNotificationsFacade.getMailNotificationsService().persist(getEntity(binder));
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
            MailNotificationsFacade.getMailNotificationsService().merge(getUpdatedEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        MailNotificationsFacade.getMailNotificationsService().delete(getEntity(binder));
        getHome();
    }

    private MailNotifications getEntity(FieldGroup binder) {

        final MailNotificationsBean mailNotificationsBean = ((BeanItem<MailNotificationsBean>) binder.getItemDataSource()).getBean();

        final Sequence sequence = SequenceFacade.getSequenceListService().findById(mailNotificationsBean.getSequenceId());
        List<String> emailList = new ArrayList<>();


        final MailNotifications mailNotifications = new MailNotifications.Builder(mailNotificationsBean.getName())
                .emailList(emailList)
                .id(mailNotificationsBean.getId())
                .sequence(sequence)
                .build();

        return mailNotifications;


    }

    private MailNotifications getUpdatedEntity(FieldGroup binder) {

        final MailNotificationsBean mailNotificationsBean = ((BeanItem<MailNotificationsBean>) binder.getItemDataSource()).getBean();

        final MailNotifications existingMailNotifications = MailNotificationsFacade.getMailNotificationsService().findById(mailNotificationsBean.getId());

        final Sequence sequence = SequenceFacade.getSequenceListService().findById(mailNotificationsBean.getSequenceId());
        
        List<String> emailList = new ArrayList<>();
        emailList.addAll(existingMailNotifications.getEmailList());


        final MailNotifications mailNotifications = new MailNotifications.Builder(mailNotificationsBean.getName())
                .emailList(emailList)
                .id(mailNotificationsBean.getId())
                .sequence(sequence)
                .build();

        return mailNotifications;


    }

    private void getHome() {
        main.content.setSecondComponent(new MailNotificationMenu(main, "NOTIFICATIONS"));
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

    private MailNotificationsBean getBean(MailNotifications mailNotifications) {
        MailNotificationsBean bean = new MailNotificationsBean();
        bean.setId(mailNotifications.getId());
        bean.setName(mailNotifications.getName());
        bean.setSequenceId(mailNotifications.getSequenceId());
        return bean;
    }
}
