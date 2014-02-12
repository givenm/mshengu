/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.site.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.client.web.content.setup.user.util.SelectSiteBean;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;
import zm.hashcode.mshengu.domain.products.SiteUnit;

/**
 * I
 *
 * @author Ferox
 */
public class SelectSiteSiteUnitForm extends FormLayout {

    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final SelectSiteBean bean;
    public final BeanItem<SelectSiteBean> item;
    public final FieldGroup binder;
    public ComboBox comboBoxSelectUnit;

    public SelectSiteSiteUnitForm() {
        bean = new SelectSiteBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);

        comboBoxSelectUnit = new ComboBox("Select Unit");
        comboBoxSelectUnit.setImmediate(true);

        GridLayout grid = new GridLayout(4, 10);

        grid.setSizeFull();

        grid.addComponent(comboBoxSelectUnit, 0, 0);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        addComponent(grid);
    }

    public void loadSiteUnits(String siteId) {
        comboBoxSelectUnit.removeAllItems();
        if (!siteId.equals("All")) {
            final SiteServiceContractLifeCycle siteServiceContractLifeCycle = SiteFacade.getSiteService().getSitetCurrentContract(siteId);
            if (!StringUtils.isEmpty(siteServiceContractLifeCycle)) {
                if (!StringUtils.isEmpty(siteServiceContractLifeCycle.getSiteUnit())) {
                    for (SiteUnit siteUnit : siteServiceContractLifeCycle.getSiteUnit()) {
                        comboBoxSelectUnit.addItem(siteUnit.getId());
                        comboBoxSelectUnit.setItemCaption(siteUnit.getId(), siteUnit.getUnitId());
                    }
                }
            }
        }
    }
}
