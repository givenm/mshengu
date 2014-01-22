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
import java.util.Set;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.client.web.content.setup.user.util.SelectSiteUnitBean;
import zm.hashcode.mshengu.domain.products.Site;

/**
 * I
 *
 * @author Ferox
 */
public class SelectSiteUnitForm extends FormLayout {

    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final SelectSiteUnitBean bean;
    public final BeanItem<SelectSiteUnitBean> item;
    public final FieldGroup binder;
    public ComboBox comboBoxSelectSiteUnit;

    public SelectSiteUnitForm() {
        bean = new SelectSiteUnitBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
//        comboBoxSelectSiteUnit = UIComboBox.getSiteUnitComboBox("SelectSiteUnit", "customerId", SelectSiteUnitBean.class, binder);


        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(comboBoxSelectSiteUnit, 0, 0);
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        addComponent(grid);

    }
    
    public void loadSiteUnitSites(Set<Site> siteList) {
//        ComboBox customerSiteComboBox = new ComboBox(fieldText);
//        List<Site> siteList = SiteFacade.getSiteService().findAll();

//        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
//        for (Site site : siteList) {
//            customerSiteComboBox.addItem(site.getId());
//            customerSiteComboBox.setItemCaption(site.getId(), site.getName());
//        }
//        return comboBox;
    }
}
