/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import zm.hashcode.mshengu.app.facade.fleet.OperationalAllowanceFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.DailyDieselTrackerMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.forms.OperationalAllowanceForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.models.OperationalAllowanceBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.tables.OperationalAllowanceTable;
import zm.hashcode.mshengu.domain.fleet.OperationalAllowance;

/**
 *
 * @author geek
 */
public class OperationalAllowanceTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final OperationalAllowanceForm form;
    private final OperationalAllowanceTable table;

    public OperationalAllowanceTab(MshenguMain app) {
        main = app;
        form = new OperationalAllowanceForm();
        table = new OperationalAllowanceTable(main);
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
            final OperationalAllowance operationalAllowance = OperationalAllowanceFacade.getOperationalAllowanceService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(operationalAllowance)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            OperationalAllowance operationalAllowance = getNewEntity(binder);
            OperationalAllowanceFacade.getOperationalAllowanceService().persist(operationalAllowance);
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
            OperationalAllowance operationalAllowance = getUpdatedEntity(binder);
            OperationalAllowanceFacade.getOperationalAllowanceService().merge(operationalAllowance);

            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        OperationalAllowanceFacade.getOperationalAllowanceService().delete(getUpdatedEntity(binder));
        getHome();
    }

    private OperationalAllowance getNewEntity(FieldGroup binder) {
        final OperationalAllowanceBean entityBean = ((BeanItem<OperationalAllowanceBean>) binder.getItemDataSource()).getBean();

        final OperationalAllowance operationalAllowance = new OperationalAllowance.Builder(entityBean.getOperationalAllowance())
                .date(new Date())
                .build();
        return operationalAllowance;
    }

    private OperationalAllowance getUpdatedEntity(FieldGroup binder) {
        final OperationalAllowanceBean entityBean = ((BeanItem<OperationalAllowanceBean>) binder.getItemDataSource()).getBean();

        final OperationalAllowance operationalAllowance = new OperationalAllowance.Builder(entityBean.getOperationalAllowance())
                .date(new Date())
                .id(entityBean.getId())
                .build();
        return operationalAllowance;
    }

    private void getHome() {
        main.content.setSecondComponent(new DailyDieselTrackerMenu(main, "OPERATIONAL_ALLOW"));
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

    private OperationalAllowanceBean getBean(OperationalAllowance entity) {
        OperationalAllowanceBean bean = new OperationalAllowanceBean();
        bean.setId(entity.getId());
        bean.setOperationalAllowance(entity.getOperationalAllowance());
        return bean;
    }
}
