/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.views;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author Luckbliss
 */
public class AllKPATab extends VerticalLayout  {

    private MshenguMain main;
    private TabSheet tab;
    private KPAOneTab kPA1Tab;
    private KPATwoTab kPA2Tab;
    private KPAThreeTab kPA3Tab;
    private KPAFourTab kPA4Tab;
    private KPAFiveTab kPA5Tab;

    public AllKPATab(MshenguMain app, String selectedTab) {
        main = app;

        kPA1Tab = new KPAOneTab(main);
        kPA1Tab.setMargin(true);
        
        kPA2Tab = new KPATwoTab(main);
        kPA2Tab.setMargin(true);
        
        kPA3Tab = new KPAThreeTab(main);
        kPA3Tab.setMargin(true);
        
        kPA4Tab = new KPAFourTab(main);
        kPA4Tab.setMargin(true);
        
        kPA5Tab = new KPAFiveTab(main);
        kPA5Tab.setMargin(true);
        
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(kPA1Tab, "KPA1", null);
        tab.addTab(kPA2Tab, "KPA2", null);
        tab.addTab(kPA3Tab, "KPA3", null);
        tab.addTab(kPA4Tab, "KPA4", null);
        tab.addTab(kPA5Tab, "KPA5", null);
        switch (selectedTab) {
            case "KPA1":
                tab.setSelectedTab(kPA1Tab);
                break;
            case "KPA2":
                tab.setSelectedTab(kPA2Tab);
                break;
            case "KPA3":
                tab.setSelectedTab(kPA3Tab);
                break;
            case "KPA4":
                tab.setSelectedTab(kPA4Tab);
                break;
            case "KPA5":
                tab.setSelectedTab(kPA5Tab);
                break;
        }
        addComponent(tab);
    }
}