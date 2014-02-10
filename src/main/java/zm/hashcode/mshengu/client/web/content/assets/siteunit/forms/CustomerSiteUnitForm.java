/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.products.SiteUnitFacade;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.client.web.content.setup.user.util.CustomerSiteUnitBean;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteUnit;

/**
 * I
 *
 * @author Ferox
 */
public class CustomerSiteUnitForm extends FormLayout {

    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final CustomerSiteUnitBean bean;
    public final BeanItem<CustomerSiteUnitBean> item;
    public final FieldGroup binder;
    public ComboBox comboBoxSelectUnit;
    public ComboBox comboBoxSelectSite;
    public ComboBox comboBoxSelectCustomer;

    public CustomerSiteUnitForm() {
        bean = new CustomerSiteUnitBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);

        comboBoxSelectCustomer = UIComboBox.getCustomerComboBox("SelectCustomer", "customerId", CustomerSiteUnitBean.class, binder);

        comboBoxSelectSite = new ComboBox("Site");
        comboBoxSelectUnit = new ComboBox("Select Toilet Unit");
        comboBoxSelectSite.setImmediate(true);
        comboBoxSelectUnit.setImmediate(true);
        GridLayout grid = new GridLayout(4, 10);

        grid.setSizeFull();

//        grid.addComponent(comboBoxSelectCustomer, 0, 0);
//        grid.addComponent(comboBoxSelectSite, 1, 0);
        grid.addComponent(comboBoxSelectUnit, 0, 0);
        loadAllToiletUnits();

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        addComponent(grid);
    }

    public void loadCustomerSites(Set<Site> siteList) {
        comboBoxSelectSite.removeAllItems();
        for (Site site : siteList) {
            comboBoxSelectSite.addItem(site.getId());
            comboBoxSelectSite.setItemCaption(site.getId(), site.getName());
        }
    }

    public void loadSiteUnits(Set<SiteUnit> siteUnitList) {
        comboBoxSelectUnit.removeAllItems();
        for (SiteUnit siteUnit : siteUnitList) {
            comboBoxSelectUnit.addItem(siteUnit.getId());
            comboBoxSelectUnit.setItemCaption(siteUnit.getId(), siteUnit.getUnitId());
        }
    }
    
    private void loadAllToiletUnits() {
        comboBoxSelectUnit.removeAllItems();
        List<SiteUnit> siteUnitList = SiteUnitFacade.getSiteUnitService().findAll();
        for (SiteUnit siteUnit : siteUnitList) {
            comboBoxSelectUnit.addItem(siteUnit.getId());
            comboBoxSelectUnit.setItemCaption(siteUnit.getId(), siteUnit.getUnitId());
        }
    }
}
