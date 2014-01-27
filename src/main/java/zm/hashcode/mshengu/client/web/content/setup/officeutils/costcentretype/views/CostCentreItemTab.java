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
import zm.hashcode.mshengu.app.facade.ui.util.ItemCategoryTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.OfficeUtilsMenu;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.form.CostCentreItemForm;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.models.CostCentreCategoryBean;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.models.CostCentreItemBean;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.tables.CostCentreItemTable;
import zm.hashcode.mshengu.domain.ui.util.CostCentreCategoryType;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;
import zm.hashcode.mshengu.domain.ui.util.ItemCategoryType;

/**
 *
 * @author Luckbliss
 */
public class CostCentreItemTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final CostCentreItemForm form;
    private final CostCentreItemTable table;

    public CostCentreItemTab(MshenguMain app) {
        main = app;
        table = new CostCentreItemTable(main);

        form = new CostCentreItemForm();
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
            final ItemCategoryType itemCategoryType = ItemCategoryTypeFacade.getItemCategoryTypeService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(itemCategoryType)));
            setReadFormProperties();
        } else if (property == form.costCentreName) {
            form.costCentreCategory.removeAllItems();
            CostCentreType centreType = CostCentreTypeFacade.getCostCentreTypeService().findById(form.costCentreName.getValue().toString());
            addItemsToCostCentreCategoryCombobox(centreType);
        } else if (property == form.costCentreCategory) {
            table.removeAllItems();
            table.loadTable(form.costCentreCategory.getValue().toString());
        }
    }

    private void addItemsToCostCentreCategoryCombobox(CostCentreType centreType) {
        if (centreType.getCategoryTypes() != null) {
            for (CostCentreCategoryType categoryType : centreType.getCategoryTypes()) {
                form.costCentreCategory.addItem(categoryType.getId());
                form.costCentreCategory.setItemCaption(categoryType.getId(), categoryType.getName());
            }
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
            form.costCentreCategory.setReadOnly(false);
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
        final CostCentreItemBean bean = ((BeanItem<CostCentreItemBean>) binder.getItemDataSource()).getBean();

        final CostCentreType type = CostCentreTypeFacade.getCostCentreTypeService().findById(bean.getCostCentreId());

        final CostCentreCategoryType categoryType = CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().findById(bean.getCostCentreCategoryId());

        final ItemCategoryType itemCategoryType = new ItemCategoryType.Builder(bean.getName())
                .build();

        ItemCategoryTypeFacade.getItemCategoryTypeService().persist(itemCategoryType);

        final CostCentreCategoryType newCategoryType = mergeCostCategoryType(categoryType, itemCategoryType, bean);

        CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().merge(newCategoryType);

        Set<CostCentreCategoryType> categoryList = new HashSet<>();
        if (type.getCategoryTypes() != null) {
            categoryList = type.getCategoryTypes();
        }
        categoryList.remove(categoryType);
        categoryList.add(newCategoryType);

        final CostCentreType newtype = new CostCentreType.Builder(type.getName())
                .costCentreType(type)
                .categoryTypes(categoryList)
                .build();
        return newtype;
    }

    private CostCentreCategoryType mergeCostCategoryType(CostCentreCategoryType categoryType, ItemCategoryType itemCategoryType, CostCentreItemBean bean) {
        Set<ItemCategoryType> list = new HashSet<>();
        if (categoryType.getItemCategoryTypes() != null) {
            list = categoryType.getItemCategoryTypes();
        }
        list.add(itemCategoryType);

        final CostCentreCategoryType newCategoryType = new CostCentreCategoryType.Builder(categoryType.getName())
                .id(categoryType.getId())
                .categoryTypes(list)
                .build();

        CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().merge(newCategoryType);

        return newCategoryType;
    }

    private CostCentreType getDeleteEntity(FieldGroup binder) {
        final CostCentreItemBean bean = ((BeanItem<CostCentreItemBean>) binder.getItemDataSource()).getBean();

        final CostCentreType type = CostCentreTypeFacade.getCostCentreTypeService().findById(bean.getCostCentreId());

        final CostCentreCategoryType categoryType = CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().findById(bean.getCostCentreCategoryId());

        final ItemCategoryType itemCategoryType = ItemCategoryTypeFacade.getItemCategoryTypeService().findById(bean.getId());

        final CostCentreCategoryType newCategoryType = mergeDeleteCostCategoryType(categoryType, itemCategoryType);

        CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().merge(newCategoryType);

        Set<CostCentreCategoryType> categoryList = new HashSet<>();
        if (type.getCategoryTypes() != null) {
            categoryList = type.getCategoryTypes();
        }
        categoryList.remove(categoryType);
        categoryList.add(newCategoryType);

        final CostCentreType newtype = new CostCentreType.Builder(type.getName())
                .costCentreType(type)
                .categoryTypes(categoryList)
                .build();
        return newtype;

    }

    private CostCentreCategoryType mergeDeleteCostCategoryType(CostCentreCategoryType categoryType, ItemCategoryType itemCategoryType) {
        Set<ItemCategoryType> list = new HashSet<>();
        if (categoryType.getItemCategoryTypes() != null) {
            list = categoryType.getItemCategoryTypes();
        }
        list.remove(itemCategoryType);

        final CostCentreCategoryType newCategoryType = new CostCentreCategoryType.Builder(categoryType.getName())
                .id(categoryType.getId())
                .categoryTypes(list)
                .build();

        ItemCategoryTypeFacade.getItemCategoryTypeService().delete(itemCategoryType);

        return newCategoryType;
    }

    private CostCentreType getUpdateEntity(FieldGroup binder) {
        final CostCentreItemBean bean = ((BeanItem<CostCentreItemBean>) binder.getItemDataSource()).getBean();

        final CostCentreType type = CostCentreTypeFacade.getCostCentreTypeService().findById(bean.getCostCentreId());

        final CostCentreCategoryType categoryType = CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().findById(bean.getCostCentreCategoryId());

        final ItemCategoryType itemCategoryType = ItemCategoryTypeFacade.getItemCategoryTypeService().findById(bean.getId());

        final ItemCategoryType newItemCategoryType = new ItemCategoryType.Builder(bean.getName())
                .id(itemCategoryType.getId())
                .build();

        ItemCategoryTypeFacade.getItemCategoryTypeService().merge(newItemCategoryType);

        final CostCentreCategoryType newCategoryType = mergeUpdatedCostCategoryType(categoryType, itemCategoryType, newItemCategoryType);

        CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().merge(newCategoryType);

        Set<CostCentreCategoryType> categoryList = new HashSet<>();
        if (type.getCategoryTypes() != null) {
            categoryList = type.getCategoryTypes();
        }
        categoryList.remove(categoryType);
        categoryList.add(newCategoryType);

        final CostCentreType newtype = new CostCentreType.Builder(type.getName())
                .costCentreType(type)
                .categoryTypes(categoryList)
                .build();
        return newtype;
    }

    private CostCentreCategoryType mergeUpdatedCostCategoryType(CostCentreCategoryType categoryType, ItemCategoryType itemCategoryType, ItemCategoryType newItemCategoryType) {
        Set<ItemCategoryType> list = new HashSet<>();
        if (categoryType.getItemCategoryTypes() != null) {
            list = categoryType.getItemCategoryTypes();
        }
        list.remove(itemCategoryType);
        list.add(newItemCategoryType);

        final CostCentreCategoryType newCategoryType = new CostCentreCategoryType.Builder(categoryType.getName())
                .id(categoryType.getId())
                .categoryTypes(list)
                .build();

        CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().merge(newCategoryType);

        return newCategoryType;
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
        form.costCentreCategory.setReadOnly(true);
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
        form.costCentreCategory.addValueChangeListener((Property.ValueChangeListener) this);
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private CostCentreItemBean getBean(ItemCategoryType type) {
        CostCentreItemBean bean = new CostCentreItemBean();
        bean.setName(type.getName());
        bean.setCostCentreId(form.costCentreName.getValue().toString());
        bean.setCostCentreCategoryId(form.costCentreCategory.getValue().toString());
        bean.setId(type.getId());
        return bean;
    }
}
