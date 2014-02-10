/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.clientlogin.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.customer.CustomerFacade;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.app.facade.products.UnitLocationLifeCycleFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.UnitMenu;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.forms.CustomerSiteUnitForm;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.forms.SiteUnitSiteUnitLyfeCycleForm;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.models.SiteUnitSiteUnitLocationLifeCycleBean;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.tables.SiteUnitSiteUnitLyfeCycleTable;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;

 /*
 * @author Ferox
 */
public class UnitLifeCycleTab2 extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final SiteUnitSiteUnitLyfeCycleForm form;
    private final CustomerSiteUnitForm customerSiteUnitForm;
    private final SiteUnitSiteUnitLyfeCycleTable table;

    public UnitLifeCycleTab2(MshenguMain app) {
        main = app;
        customerSiteUnitForm = new CustomerSiteUnitForm();
        form = new SiteUnitSiteUnitLyfeCycleForm();
        table = new SiteUnitSiteUnitLyfeCycleTable(main);
        setSizeFull();
//        addComponent(customerSiteUnitForm);
//        addComponent(form);
//        addComponent(table);//
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
///            final UnitLocationLifeCycle unitLocationLifeCycle = UnitLocationLifeCycleFacade.getUnitLocationLifeCycleService().findById(table.getValue().toString());
           // form.binder.setItemDataSource(new BeanItem<>(getBean(unitLocationLifeCycle)));
            setReadFormProperties();
        }else if (property == customerSiteUnitForm.comboBoxSelectCustomer) {
//            String custId = getCustomerId();
            if (customerSiteUnitForm.comboBoxSelectCustomer.getValue().toString() != null) {

                Customer customer  = CustomerFacade.getCustomerService().findById(customerSiteUnitForm.comboBoxSelectCustomer.getValue().toString());
                customerSiteUnitForm.comboBoxSelectSite.removeValueChangeListener((Property.ValueChangeListener) this);
                customerSiteUnitForm.loadCustomerSites(customer.getSites());
                customerSiteUnitForm.comboBoxSelectSite.addValueChangeListener((Property.ValueChangeListener) this);
            }
        }else if (property == customerSiteUnitForm.comboBoxSelectSite) {
//            String custId = getCustomerId();
            if (customerSiteUnitForm.comboBoxSelectSite.getValue().toString() != null) {

                Site site  = SiteFacade.getSiteService().findById(customerSiteUnitForm.comboBoxSelectSite.getValue().toString());
                customerSiteUnitForm.comboBoxSelectUnit.removeValueChangeListener((Property.ValueChangeListener) this);
//                customerSiteUnitForm.loadSiteUnits(site.getSiteUnit());
                customerSiteUnitForm.comboBoxSelectUnit.addValueChangeListener((Property.ValueChangeListener) this);
            }
        }else if (property == customerSiteUnitForm.comboBoxSelectUnit) {
//            String custId = getCustomerId();
            if (customerSiteUnitForm.comboBoxSelectUnit.getValue().toString() != null) {

//                table.removeValueChangeListener((Property.ValueChangeListener) this);
//                SiteUnit siteUnit  = SiteUnitFacade.getSiteUnitService().findById(customerSiteUnitForm.comboBoxSelectUnit.getValue().toString());
//                table.loadUnitLifeCycle(siteUnit.getUnitLocationLifeCycle());
////                table.addValueChangeListener((Property.ValueChangeListener) this);
            }
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            UnitLocationLifeCycleFacade.getUnitLocationLifeCycleService().persist(getEntity(binder));
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
            UnitLocationLifeCycleFacade.getUnitLocationLifeCycleService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        UnitLocationLifeCycleFacade.getUnitLocationLifeCycleService().delete(getEntity(binder));
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new UnitMenu(main, "LANDING"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(true);
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
        form.edit.setVisible(false);
        form.cancel.setVisible(false);
        form.delete.setVisible(false);
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
//        table.addValueChangeListener((Property.ValueChangeListener) this);
        
        customerSiteUnitForm.comboBoxSelectCustomer.addValueChangeListener((Property.ValueChangeListener) this);        
        customerSiteUnitForm.comboBoxSelectSite.addValueChangeListener((Property.ValueChangeListener) this);        
        customerSiteUnitForm.comboBoxSelectUnit.addValueChangeListener((Property.ValueChangeListener) this);
    }
      private UnitLocationLifeCycle getEntity(FieldGroup binder) {


        final SiteUnitSiteUnitLocationLifeCycleBean unitLocationLifeCycleBean = ((BeanItem<SiteUnitSiteUnitLocationLifeCycleBean>) binder.getItemDataSource()).getBean();
//       

        final UnitLocationLifeCycle unitLocationLifeCycle = new UnitLocationLifeCycle.Builder(unitLocationLifeCycleBean.getDateofAction())
                .latitude(unitLocationLifeCycleBean.getLatitude())
                .longitude(unitLocationLifeCycleBean.getLatitude())
                .id(unitLocationLifeCycleBean.getId())
                .build();


        return unitLocationLifeCycle;

    }
        private SiteUnitSiteUnitLocationLifeCycleBean getBean(UnitLocationLifeCycle unitLocationLifeCycle) {
        SiteUnitSiteUnitLocationLifeCycleBean bean = new SiteUnitSiteUnitLocationLifeCycleBean();
        bean.setDateofAction(unitLocationLifeCycle.getDateofAction());
        bean.setLongitude(unitLocationLifeCycle.getLongitude());
        bean.setLatitude(unitLocationLifeCycle.getLatitude());
        bean.setId(unitLocationLifeCycle.getId());
        return bean;
    }

}