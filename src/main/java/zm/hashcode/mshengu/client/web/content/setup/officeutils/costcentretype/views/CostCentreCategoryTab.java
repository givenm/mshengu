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
import java.util.HashSet;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.ui.util.CostCentreCategoryTypeFacade;
import zm.hashcode.mshengu.app.facade.ui.util.CostCentreTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.OfficeUtilsMenu;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.form.CostCentreCategoryForm;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.models.CostCentreCategoryBean;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.tables.CostCentreCategoryTable;
import zm.hashcode.mshengu.domain.ui.util.CostCentreCategoryType;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;

/**
 *
 * @author Luckbliss
 */
public class CostCentreCategoryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final CostCentreCategoryForm form;
    private final CostCentreCategoryTable table;
    private String costCentreId;

    public CostCentreCategoryTab(MshenguMain app) {
        main = app;
        table = new CostCentreCategoryTable(main);

        form = new CostCentreCategoryForm();
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
            costCentreId = form.costCentreName.getValue().toString();
            final CostCentreCategoryType categoryType = CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(categoryType)));
            setReadFormProperties();
        } else if (property == form.costCentreName) {
            table.removeAllItems();
            table.loadTable(form.costCentreName.getValue().toString());
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            CostCentreType centreType = getEntity(binder);
            CostCentreTypeFacade.getCostCentreTypeService().merge(centreType);
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            form.costCentreName.setReadOnly(false);
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
        CostCentreTypeFacade.getCostCentreTypeService().merge(getDeleteEntity(binder));
        getHome();
    }

    private CostCentreType getEntity(FieldGroup binder) {
        final CostCentreCategoryBean bean = ((BeanItem<CostCentreCategoryBean>) binder.getItemDataSource()).getBean();

        final CostCentreType type = CostCentreTypeFacade.getCostCentreTypeService().findById(bean.getCostCentreId());

        final CostCentreCategoryType categoryType = new CostCentreCategoryType.Builder(bean.getName())
                .build();

        CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().persist(categoryType);

        Set<CostCentreCategoryType> list = new HashSet<>();

        if (type.getCategoryTypes() != null) {
            list = type.getCategoryTypes();
        }

        list.add(categoryType);

        final CostCentreType newtype = new CostCentreType.Builder(type.getName())
                .costCentreType(type)
                .categoryTypes(list)
                .build();
        return newtype;

    }

    private CostCentreType getDeleteEntity(FieldGroup binder) {
        final CostCentreCategoryBean bean = ((BeanItem<CostCentreCategoryBean>) binder.getItemDataSource()).getBean();

        final CostCentreType type = CostCentreTypeFacade.getCostCentreTypeService().findById(bean.getCostCentreId());

        final CostCentreCategoryType categoryType = CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().findById(bean.getId());

        CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().delete(categoryType);

        Set<CostCentreCategoryType> list = new HashSet<>();

        if (type.getCategoryTypes() != null) {
            list = type.getCategoryTypes();
        }

        list.remove(categoryType);

        final CostCentreType newtype = new CostCentreType.Builder(type.getName())
                .costCentreType(type)
                .categoryTypes(list)
                .build();
        return newtype;

    }

    private CostCentreType getUpdateEntity(FieldGroup binder) {
        final CostCentreCategoryBean bean = ((BeanItem<CostCentreCategoryBean>) binder.getItemDataSource()).getBean();

        final CostCentreType type = CostCentreTypeFacade.getCostCentreTypeService().findById(bean.getCostCentreId());

        final CostCentreCategoryType categoryType = CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().findById(bean.getId());

        final CostCentreCategoryType newCategoryType = new CostCentreCategoryType.Builder(bean.getName())
                .id(bean.getId())
                .build();

        CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().merge(newCategoryType);
        Set<CostCentreCategoryType> list = new HashSet<>();

        if (type.getCategoryTypes() != null) {
            list = type.getCategoryTypes();
        }

        list.remove(categoryType);
        list.add(newCategoryType);

        final CostCentreType newtype = new CostCentreType.Builder(type.getName())
                .costCentreType(type)
                .categoryTypes(list)
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
        form.costCentreName.setReadOnly(true);
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
        form.costCentreName.addValueChangeListener((Property.ValueChangeListener) this);
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private CostCentreCategoryBean getBean(CostCentreCategoryType type) {
        CostCentreCategoryBean bean = new CostCentreCategoryBean();
        bean.setName(type.getName());
        bean.setCostCentreId(costCentreId);
        bean.setId(type.getId());
        return bean;
    }
}
