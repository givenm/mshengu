/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.tables.SiteUnitServiceLogTable;

/**
 *
 * @author Ferox
 */
public class SiteUnitServiceLogTab extends VerticalLayout{

    private final MshenguMain main;
    private final SiteUnitServiceLogTable table;

    public SiteUnitServiceLogTab(MshenguMain app) {
        main = app;
        table = new SiteUnitServiceLogTable(main);
        setSizeFull();
        addComponent(table);
    }


    private void getHome() {
        main.content.setSecondComponent(new SiteSiteUnitTab(main, "SERVICE_LOGS"));
    }


    public void loadServiceLogs(String siteUnitId){
        table.loadServiceLogs(siteUnitId);
    }
}