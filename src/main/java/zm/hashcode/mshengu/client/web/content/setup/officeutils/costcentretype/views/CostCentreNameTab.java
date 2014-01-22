/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.ui.util.CostCentreTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.OfficeUtilsMenu;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.form.CostCentreTypeForm;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.models.CostCentreTypeBean;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.tables.CostCentreTypeTable;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;

/**
 *
 * @author Luckbliss
 */
public class CostCentreNameTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final CostCentreTypeTable table;
    private final CostCentreTypeForm form;

    public CostCentreNameTab(MshenguMain app) {
        main = app;
        table = new CostCentreTypeTable(main);
        form = new CostCentreTypeForm();
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
            final CostCentreType costCentreType = CostCentreTypeFacade.getCostCentreTypeService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(costCentreType)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            CostCentreType centreType = getEntity(binder);
            CostCentreTypeFacade.getCostCentreTypeService().persist(centreType);
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            CostCentreType centreType = getUpdateEntity(binder);
            CostCentreTypeFacade.getCostCentreTypeService().merge(centreType);
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void deleteForm(FieldGroup binder) {
        CostCentreTypeFacade.getCostCentreTypeService().delete(getEntity(binder));
        getHome();
    }

    private CostCentreType getEntity(FieldGroup binder) {
        final CostCentreTypeBean bean = ((BeanItem<CostCentreTypeBean>) binder.getItemDataSource()).getBean();

        final CostCentreType type = new CostCentreType.Builder(bean.getName())
                .id(bean.getId())
                .build();
        return type;

    }

    private CostCentreType getUpdateEntity(FieldGroup binder) {
        final CostCentreTypeBean bean = ((BeanItem<CostCentreTypeBean>) binder.getItemDataSource()).getBean();
        final CostCentreType type = CostCentreTypeFacade.getCostCentreTypeService().findById(bean.getId());
        final CostCentreType newtype = new CostCentreType.Builder(bean.getName())
                .costCentreType(type)
                .build();
        return newtype;
    }

    private void getHome() {
        main.content.setSecondComponent(new OfficeUtilsMenu(main, "COSTCENTRE_TYPE"));
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

    private CostCentreTypeBean getBean(CostCentreType type) {
        CostCentreTypeBean bean = new CostCentreTypeBean();
        bean.setName(type.getName());
        bean.setCategoryTypes(type.getCategoryTypes());
        bean.setId(type.getId());
        return bean;
    }
}
