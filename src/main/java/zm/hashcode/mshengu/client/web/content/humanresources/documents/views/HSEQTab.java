/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.documents.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.documents.DocumentsFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.HSEQMenu;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.form.HSEQForm;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.models.DocumentBean;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.tables.HSEQTable;
import zm.hashcode.mshengu.domain.documents.Documents;

/**
 *
 * @author Luckbliss
 */
public class HSEQTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final HSEQForm form;
    private final HSEQTable table;

    public HSEQTab(MshenguMain app) {
        main = app;
        form = new HSEQForm();
        table = new HSEQTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    private void addListeners() {
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
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

    private void deleteForm(FieldGroup binder) {
        Notification.show("Deleting documents is not allowed!", Notification.Type.TRAY_NOTIFICATION);
        getHome();
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            DocumentsFacade.getDocumentsService().persist(getEntity(binder));
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
            DocumentsFacade.getDocumentsService().merge(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private Documents getUpdateEntity(FieldGroup binder) {
        final DocumentBean bean = ((BeanItem<DocumentBean>) binder.getItemDataSource()).getBean();
        Documents documents = new Documents.Builder(bean.getName())
                .id(bean.getId())
                .category(bean.getCategory())
                .description(bean.getDescription())
                .uploads(null)
                .url(bean.getUrl())
                .build();
        return documents;
    }

    private Documents getEntity(FieldGroup binder) {
        final DocumentBean bean = ((BeanItem<DocumentBean>) binder.getItemDataSource()).getBean();
        final Documents documents = new Documents.Builder(bean.getName())
                .id(bean.getId())
                .category(bean.getCategory())
                .description(bean.getDescription())
                .uploads(null)
                .url(bean.getUrl())
                .build();
        return documents;
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Documents documents = DocumentsFacade.getDocumentsService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(documents)));
            setReadFormProperties();
        }
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

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private DocumentBean getBean(Documents documents) {
        DocumentBean bean = new DocumentBean();
        bean.setName(documents.getName());
        bean.setId(documents.getId());
        bean.setCategory(documents.getCategory());
        bean.setDescription(documents.getDescription());
        bean.setUpload(null);
        bean.setUrl(documents.getUrl());
        return bean;
    }

    private void getHome() {
        main.content.setSecondComponent(new HSEQMenu(main, "LANDING"));
    }
}
