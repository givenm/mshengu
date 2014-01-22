/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.views.PKILoadKPIResultsTab;
import zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.views.CanvasTab;
import zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.views.PKIScorecardTrendTab;
import zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.views.PKISetTargetTab;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.views.PKISetUpKPITab;

/**
 *
 * @author Ferox
 */
public class KPIMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private CanvasTab canvasTab;
    private PKISetUpKPITab pkiSetUpKPITab;
    private PKISetTargetTab pkiSetTargetTab;
    private PKIScorecardTrendTab pkiScorecardTrendTab;
    private PKILoadKPIResultsTab pkiLoadKPIResultsTab;

    public KPIMenu(MshenguMain app, String selectedTab) {
        main = app;

        canvasTab = new CanvasTab(main);
        pkiSetUpKPITab = new PKISetUpKPITab(main);
        pkiSetTargetTab = new PKISetTargetTab(main);
        pkiScorecardTrendTab = new PKIScorecardTrendTab(main);
        pkiLoadKPIResultsTab = new PKILoadKPIResultsTab(main);


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(canvasTab, "Scorecard", null);
        tab.addTab(pkiSetUpKPITab, "Setup KPIs", null);
        tab.addTab(pkiSetTargetTab, "Set Targets", null);
        tab.addTab(pkiScorecardTrendTab, "Scorecard Trend", null);
        tab.addTab(pkiLoadKPIResultsTab, "Load KPI Results", null);


            if (selectedTab.equals("LANDING")) {
                tab.setSelectedTab(canvasTab);
            } else if (selectedTab.equals("SETUP")) {
                tab.setSelectedTab(pkiSetUpKPITab);
            } else if (selectedTab.equals("TARGET")) {
                tab.setSelectedTab(pkiSetTargetTab);
            } else if (selectedTab.equals("TREND")) {
                tab.setSelectedTab(pkiScorecardTrendTab);
            } else if (selectedTab.equals("LOAD_KPI")) {
                tab.setSelectedTab(pkiLoadKPIResultsTab);
            }
        
        addComponent(tab);
    }

}