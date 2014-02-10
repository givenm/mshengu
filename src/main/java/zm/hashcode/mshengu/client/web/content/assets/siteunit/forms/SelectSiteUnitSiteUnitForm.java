/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import zm.hashcode.mshengu.app.facade.products.SiteUnitFacade;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.client.web.content.setup.user.util.SelectSiteBean;
import zm.hashcode.mshengu.domain.products.SiteUnit;

/**
 * I
 *
 * @author Ferox
 */
public class SelectSiteUnitSiteUnitForm extends FormLayout {

    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final SelectSiteBean bean;
    public final BeanItem<SelectSiteBean> item;
    public final FieldGroup binder;
    public ComboBox comboBoxSelectUnit;
    private final TextField siteUnitSearchBox = new TextField("Search For Site Unit");

    public SelectSiteUnitSiteUnitForm() {
        bean = new SelectSiteBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        siteUnitSearchBox.setWidth("400px");


        comboBoxSelectUnit = new ComboBox("Select Unit");
        comboBoxSelectUnit.setImmediate(true);
        GridLayout grid = new GridLayout(4, 10);

        grid.setSizeFull();

        grid.addComponent(siteUnitSearchBox, 0, 1);
        grid.addComponent(comboBoxSelectUnit, 0, 0);


        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        addComponent(grid);



        siteUnitSearchBox.addShortcutListener(new ShortcutListener("Search Site Unit", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                if (target == siteUnitSearchBox) {
                    if (!siteUnitSearchBox.getValue().isEmpty()) {
                        List<SiteUnit> units = SiteUnitFacade.getSiteUnitService().findAll();
                        List<SiteUnit> list = new ArrayList<>();
                        for (SiteUnit siteUnit : units) {
                            if (siteUnit.getUnitId().toLowerCase().contains(siteUnitSearchBox.getValue().toLowerCase())) {
                                list.add(siteUnit);
                            }
                        }
                        comboBoxSelectUnit.removeAllItems();
                        loadSiteUnits(list);
                    } else {
//                        List<SiteUnit> units = SiteUnitFacade.getSiteUnitService().findAll();
                        comboBoxSelectUnit.removeAllItems();
//                        if (siteUnitsArrayList != null) {
//                            comboBoxSelectUnit.loadSiteUnits(siteUnitsArrayList);
//                        }
                    }
                }
            }
        });

    }

    public void loadSiteUnits(List<SiteUnit> siteUnitsList) {
        comboBoxSelectUnit.removeAllItems();

        for (SiteUnit siteUnit : siteUnitsList) {
            comboBoxSelectUnit.addItem(siteUnit.getId());
            comboBoxSelectUnit.setItemCaption(siteUnit.getId(), siteUnit.getUnitId());
        }
    }
}
