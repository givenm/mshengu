/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.UnitMenu;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.models.SiteUnitSiteUnitLocationLifeCycleBean;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.tables.SiteUnitSiteUnitLyfeCycleTable;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;

/*
 * @author Ferox
 */
public class SiteUniUnitLifeCycleTab extends VerticalLayout {

    private final MshenguMain main;
    private final SiteUnitSiteUnitLyfeCycleTable table;

    public SiteUniUnitLifeCycleTab(MshenguMain app) {
        main = app;
        table = new SiteUnitSiteUnitLyfeCycleTable(main);
        setSizeFull();
//        addComponent(form);
        addComponent(table);
    }



    private void getHome() {
        main.content.setSecondComponent(new UnitMenu(main, "LIFE_CYCLE"));
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

    public void loadUnitLifeCycle(String siteUnitId) {
        table.loadUnitLifeCycle(siteUnitId);
    }
}