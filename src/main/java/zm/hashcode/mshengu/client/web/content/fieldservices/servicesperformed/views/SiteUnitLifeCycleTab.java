/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.tables.SiteUnitLyfeCycleTable;



 /*
 * @author Ferox
 */
public class SiteUnitLifeCycleTab extends VerticalLayout  {

    private final MshenguMain main;
    private final SiteUnitLyfeCycleTable table;

    public SiteUnitLifeCycleTab(MshenguMain app) {
        main = app;
        table = new SiteUnitLyfeCycleTable(main);
        setSizeFull();
        addComponent(table);
    }


    private void getHome() {
        main.content.setSecondComponent(new SiteSiteUnitTab(main, "LIFE_CYCLE"));
    }
    
    public void loadUnitLifeCycle(String siteUnitId){
        table.loadUnitLifeCycle(siteUnitId);
    }
}

