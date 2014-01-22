/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.sequence.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.OfficeUtilsMenu;
import zm.hashcode.mshengu.client.web.content.setup.sequence.forms.SequenceTypeForm;
import zm.hashcode.mshengu.client.web.content.setup.sequence.models.SequenceTypeBean;
import zm.hashcode.mshengu.client.web.content.setup.sequence.tables.SequenceTypeTable;
import zm.hashcode.mshengu.domain.ui.util.SequenceType;

/**
 *
 * @author Ferox
 */
public class SequenceTypeTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final SequenceTypeForm form;
    private final SequenceTypeTable table;

    public SequenceTypeTab(MshenguMain app) {
        main = app;
        form = new SequenceTypeForm();
        table = new SequenceTypeTable(main);
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
            final SequenceType truckCategory = SequenceTypeFacade.getSequenceTypeListService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(truckCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            SequenceTypeFacade.getSequenceTypeListService().persist(getEntity(binder));
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
            SequenceTypeFacade.getSequenceTypeListService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        SequenceTypeFacade.getSequenceTypeListService().delete(getEntity(binder));
        getHome();
    }

    private SequenceType getEntity(FieldGroup binder) {
        //final  SequenceType cust = new SequenceType.Builder(binder.getItemDataSource().getItemProperty("name")).
        
        final SequenceTypeBean sequenceTypeBean = ((BeanItem<SequenceTypeBean>) binder.getItemDataSource()).getBean();


        final SequenceType sequenceType = new SequenceType.Builder(sequenceTypeBean.getName())
                .description(sequenceTypeBean.getDescription())
                .id(sequenceTypeBean.getId())
                .build();

        return sequenceType;


    }

    private void getHome() {
        main.content.setSecondComponent(new OfficeUtilsMenu(main, "SEQUENCE_TYPE"));
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

    private SequenceTypeBean getBean(SequenceType sequenceType) {
        SequenceTypeBean bean = new SequenceTypeBean();
        bean.setName(sequenceType.getName());
        bean.setDescription(sequenceType.getDescription());
        bean.setId(sequenceType.getId());
        return bean;
    }


}
