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
import zm.hashcode.mshengu.client.web.content.assets.siteunit.models.SiteUnitSiteUnitServiceLogBean;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.tables.SiteUnitSiteUnitServiceLogTable;
import zm.hashcode.mshengu.domain.products.UnitServiceLog;

/**
 *
 * @author Ferox
 */
public class SiteUnitUnitServiceLogTab extends VerticalLayout {

    private final MshenguMain main;
    private final SiteUnitSiteUnitServiceLogTable table;

    public SiteUnitUnitServiceLogTab(MshenguMain app) {
        main = app;
        table = new SiteUnitSiteUnitServiceLogTable(main);
        setSizeFull();
        addComponent(table);
    }



    private void getHome() {
        main.content.setSecondComponent(new UnitMenu(main, "SERVICE_LOGS"));
    }


    private UnitServiceLog getEntity(FieldGroup binder) {



        final SiteUnitSiteUnitServiceLogBean unitServiceLogBean = ((BeanItem<SiteUnitSiteUnitServiceLogBean>) binder.getItemDataSource()).getBean();


        final UnitServiceLog unitServiceLog = new UnitServiceLog.Builder(unitServiceLogBean.getServiceDate())
                .serviceTime(unitServiceLogBean.getServiceTime())
                .servicedBy(unitServiceLogBean.getServicedBy())
                .statusMessage(unitServiceLogBean.getStatusMessage())
                .pumpOut(unitServiceLogBean.isPumpOut())
                .washBucket(unitServiceLogBean.isWashBucket())
                .suctionOut(unitServiceLogBean.isSuctionOut())
                .scrubFloor(unitServiceLogBean.isScrubFloor())
                .rechargeBacket(unitServiceLogBean.isRechargeBacket())
                .cleanPerimeter(unitServiceLogBean.isCleanPerimeter())
                .id(unitServiceLogBean.getId())
                .build();


        return unitServiceLog;

    }

    private SiteUnitSiteUnitServiceLogBean getBean(UnitServiceLog unitServiceLog) {
        SiteUnitSiteUnitServiceLogBean bean = new SiteUnitSiteUnitServiceLogBean();
        bean.setServiceDate(unitServiceLog.getServiceDate());
        bean.setServiceTime(unitServiceLog.getServiceTime());
        bean.setServicedBy(unitServiceLog.getServicedBy());
        bean.setStatusMessage(unitServiceLog.getStatusMessage());
        bean.setPumpOut(unitServiceLog.isPumpOut());
        bean.setWashBucket(unitServiceLog.isWashBucket());
        bean.setSuctionOut(unitServiceLog.isSuctionOut());
        bean.setScrubFloor(unitServiceLog.isScrubFloor());
        bean.setRechargeBacket(unitServiceLog.isRechargeBacket());
        bean.setCleanPerimeter(unitServiceLog.isCleanPerimeter());


        bean.setPumpOutText(table.wasServicePerformed(unitServiceLog.isPumpOut()));
        bean.setWashBucketText(table.wasServicePerformed(unitServiceLog.isWashBucket()));
        bean.setSuctionOutText(table.wasServicePerformed(unitServiceLog.isSuctionOut()));
        bean.setScrubFloorText(table.wasServicePerformed(unitServiceLog.isScrubFloor()));
        bean.setRechargeBacketText(table.wasServicePerformed(unitServiceLog.isRechargeBacket()));
        bean.setCleanPerimeterText(table.wasServicePerformed(unitServiceLog.isCleanPerimeter()));

        bean.setIncident(unitServiceLog.getIncident());
        bean.setId(unitServiceLog.getId());
        return bean;
    }
    
    public void loadServiceLogs(String siteUnitId){
        table.loadServiceLogs(siteUnitId);
    }
}