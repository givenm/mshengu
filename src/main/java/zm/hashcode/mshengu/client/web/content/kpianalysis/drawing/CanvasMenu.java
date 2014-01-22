/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.drawing;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.Menu;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.views.CanvasTab;

/**
 *
 * @author Luckbliss
 */
public class CanvasMenu extends Menu {

    private final MshenguMain main;
    private final TabSheet tab;

    public CanvasMenu(MshenguMain app, String selectedTab) {
        main = app;

        final VerticalLayout kpiAnalysis = new VerticalLayout();
        kpiAnalysis.setMargin(true);
        kpiAnalysis.addComponent(new CanvasTab(main));
        
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(kpiAnalysis, "KPI Analysis", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(kpiAnalysis);
        } 
        addComponent(tab);
    }
}
