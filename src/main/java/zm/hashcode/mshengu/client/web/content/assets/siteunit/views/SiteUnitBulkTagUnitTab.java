/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.products.MobileAppFacade;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.app.facade.products.SiteUnitFacade;
import zm.hashcode.mshengu.app.facade.products.UnitTypeFacade;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceFacade;
import zm.hashcode.mshengu.app.facade.ui.util.StatusFacade;
import zm.hashcode.mshengu.app.util.DialobBoxWindow;
import zm.hashcode.mshengu.app.util.UIDialogBoxHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.UnitMenu;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.forms.SiteUnitBulkTagForm;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.models.SiteUnitUnitBulkTagBean;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.tables.SiteUnitSiteUnitTable;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.domain.ui.util.Sequence;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author Ferox
 */
public class SiteUnitBulkTagUnitTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final SiteUnitBulkTagForm form;
    private final SiteUnitSiteUnitTable table;
    private final UIDialogBoxHelper UIDialogBox = new UIDialogBoxHelper();
    private final DialobBoxWindow dialogWindow;
//    private final MobileAppService mobileAppService =  new MobileAppServiceImpl();

    public SiteUnitBulkTagUnitTab(MshenguMain app) {
        super();
        main = app;
        form = new SiteUnitBulkTagForm();
        table = new SiteUnitSiteUnitTable(main);
        dialogWindow = UIDialogBox.getYesNoDialogBox("Attention", "Are you sure you want to add all this toilet units to inventory?");
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            UI.getCurrent().addWindow(dialogWindow);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        } else if (source == dialogWindow.btnYesDialog) {
            dialogWindow.close();
            saveForm(form.binder);
        } else if (source == dialogWindow.btnNoDialog) {
            dialogWindow.close();
            Notification.show("Toilet units not added!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final SiteUnit siteUnit = SiteUnitFacade.getSiteUnitService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(siteUnit)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            String displayMessage = getBulkTagUnits(binder);
            Notification.show(displayMessage, Notification.Type.TRAY_NOTIFICATION);
            getHome();
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            // SiteUnitFacade.getSiteUnitService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        // SiteUnitFacade.getSiteUnitService().delete(getEntity(binder));
        getHome();
    }

    private void getHome() {
        main.content.setSecondComponent(new UnitMenu(main, "BULK"));
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
        dialogWindow.btnYesDialog.addClickListener((Button.ClickListener) this);
        dialogWindow.btnNoDialog.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private String getBulkTagUnits(FieldGroup binder) {

        String displayMessage;
        final SiteUnitUnitBulkTagBean unitUnitBulkTagBean = ((BeanItem<SiteUnitUnitBulkTagBean>) binder.getItemDataSource()).getBean();
        final Site site = SiteFacade.getSiteService().findByName("MSHENGU_WAREHOUSE");

        if (site != null) {
            final UnitType unitType = UnitTypeFacade.getUnitTypeService().findById(unitUnitBulkTagBean.getSiteUnitTypeId());
            int quantity = unitUnitBulkTagBean.getQuantity();

            if (!StringUtils.isEmpty(unitType)) {
                final Sequence unitIdSequence = SequenceFacade.getSequenceListService().findById(unitUnitBulkTagBean.getSequenceId());
                if (!StringUtils.isEmpty(unitIdSequence)) {
                    final Status status = StatusFacade.getStatusService().findByName("STOCK");
                    if (!StringUtils.isEmpty(status)) {
                        MobileAppFacade.getMobileService().bulkTagUnitsToWharehouse(unitType, unitIdSequence, quantity, site);
                        displayMessage = "Record ADDED!";
                    } else {
                        displayMessage = "Operational Status not found !";
                    }
                } else {
                    displayMessage = "Sequence not found !";
                }
            } else {
                displayMessage = "Unit Type not found !";
            }
        } else {
            displayMessage = "Site MSHENGU_WAREHOUSE Is Not Set-Up Yet!";
        }

        return displayMessage;
    }

    private SiteUnitUnitBulkTagBean getBean(SiteUnit siteUnit) {
        SiteUnitUnitBulkTagBean bean = new SiteUnitUnitBulkTagBean();

//        bean.setUnitTypeId(siteUnit.getUnitType().getId());
//        bean.setDeployed(siteUnit.isDeployed());
//        bean.setUnitId(siteUnit.getUnitId());
//        bean.setDescription(siteUnit.getDescription());
//        bean.setOperationalStatus(siteUnit.getOperationalStatus());
//        bean.setUnitId(siteUnit.getUnitId());
//
//        UnitLocationLifeCycle unitLocationLifeCycle = SiteUnitFacade.getSiteUnitService().getUnitCurrentLocation(siteUnit.getId());
//        if (!StringUtils.isEmpty(unitLocationLifeCycle)) {
//            bean.setDateofAction(unitLocationLifeCycle.getDateofAction());
//            bean.setLatitude(unitLocationLifeCycle.getLatitude());
//            bean.setLongitude(unitLocationLifeCycle.getLongitude());
//        }

//      
        bean.setId(siteUnit.getId());
        return bean;
    }
}