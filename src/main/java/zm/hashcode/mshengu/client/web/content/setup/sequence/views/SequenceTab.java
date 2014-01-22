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
import zm.hashcode.mshengu.app.facade.ui.util.SequenceFacade;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.OfficeUtilsMenu;
import zm.hashcode.mshengu.client.web.content.setup.sequence.forms.SequenceForm;
import zm.hashcode.mshengu.client.web.content.setup.sequence.models.SequenceBean;
import zm.hashcode.mshengu.client.web.content.setup.sequence.tables.SequenceTable;
import zm.hashcode.mshengu.domain.ui.util.Sequence;
import zm.hashcode.mshengu.domain.ui.util.SequenceType;

/**
 *
 * @author Ferox
 */
public class SequenceTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final SequenceForm form;
    private final SequenceTable table;

    public SequenceTab(MshenguMain app) {
        main = app;
        form = new SequenceForm();
        table = new SequenceTable(main);
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
            final Sequence truckCategory = SequenceFacade.getSequenceListService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(truckCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            SequenceFacade.getSequenceListService().persist(getEntity(binder));
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
            SequenceFacade.getSequenceListService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        SequenceFacade.getSequenceListService().delete(getEntity(binder));
        getHome();
    }

    private Sequence getEntity(FieldGroup binder) {
        //final  Sequence cust = new Sequence.Builder(binder.getItemDataSource().getItemProperty("name")).
        
        final SequenceBean sequenceBean = ((BeanItem<SequenceBean>) binder.getItemDataSource()).getBean();

        final SequenceType sequenceType =  SequenceTypeFacade.getSequenceTypeListService().findById(sequenceBean.getSequenceType());
        
        final Sequence sequence = new Sequence.Builder(sequenceBean.getName())
                .sequenceType(sequenceType)
                .namingCode(sequenceBean.getNamingCode())
                .value(sequenceBean.getValue())
                .id(sequenceBean.getId())
                .build();

        return sequence;


    }

    private void getHome() {
        main.content.setSecondComponent(new OfficeUtilsMenu(main, "SEQUENCE"));
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

    private SequenceBean getBean(Sequence sequence) {
        SequenceBean bean = new SequenceBean();
        bean.setName(sequence.getName());
        bean.setValue(sequence.getValue());
        bean.setNamingCode(sequence.getNamingCode());
        bean.setSequenceType(sequence.getSequenceTypeId());
        bean.setId(sequence.getId());
        return bean;
    }

}
