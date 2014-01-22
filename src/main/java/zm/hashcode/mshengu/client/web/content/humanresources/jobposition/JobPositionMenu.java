/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.jobposition;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.jobposition.views.JobPositionTab;

/**
 *
 * @author Ferox
 */
public class JobPositionMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;

    public JobPositionMenu(MshenguMain app, String selectedTab) {
        main = app;

         VerticalLayout jobPositionTab = new VerticalLayout();
        jobPositionTab.setMargin(true);
        jobPositionTab.addComponent(new JobPositionTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(jobPositionTab, "Job Position Type", null); 
    
        
        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(jobPositionTab);
        } 
        addComponent(tab);
    }
}