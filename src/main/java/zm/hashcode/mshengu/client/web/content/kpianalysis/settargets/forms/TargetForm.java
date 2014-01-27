/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.forms;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.tables.TargetTable;
import zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.views.TargetsTab;
import zm.hashcode.mshengu.domain.kpianalysis.KPI;

/**
 *
 * @author Luckbliss
 */
public class TargetForm extends FormLayout {

    private MshenguMain main;
    private final TargetsTab tab;

    public TargetForm(MshenguMain main, TargetsTab tab) {
        setSizeFull();
        this.tab = tab;
        this.main = main;

        KPI oneKpa = KPIFacade.getKPIService().findByTab("one");
        Label oneLabel = new Label();
        oneLabel.setValue(oneKpa.getName());
        
        TargetTable oneTable = new TargetTable(main, tab);
        oneTable.loadTable(oneKpa.getItems());
        
        KPI twoKpa = KPIFacade.getKPIService().findByTab("two");
        Label twoLabel = new Label();
        twoLabel.setValue(twoKpa.getName());
        
        TargetTable twoTable = new TargetTable(main, tab);
        twoTable.loadTable(twoKpa.getItems());
        
        KPI threeKpa = KPIFacade.getKPIService().findByTab("three");
        Label threeLabel = new Label();
        threeLabel.setValue(threeKpa.getName());
        
        TargetTable threeTable = new TargetTable(main, tab);
        threeTable.loadTable(threeKpa.getItems());
        
        KPI fourKpa = KPIFacade.getKPIService().findByTab("four");
        Label fourLabel = new Label();
        fourLabel.setValue(fourKpa.getName());
        
        TargetTable fourTable = new TargetTable(main, tab);
        fourTable.loadTable(fourKpa.getItems());
        
        KPI fiveKpa = KPIFacade.getKPIService().findByTab("five");
        Label fiveLabel = new Label();
        fiveLabel.setValue(fiveKpa.getName());
        
        TargetTable fiveTable = new TargetTable(main, tab);
        fiveTable.loadTable(fiveKpa.getItems());
        
        GridLayout layout = new GridLayout(3, 20);
        layout.setSizeFull();
        
        layout.addComponent(oneLabel, 0, 0);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1, 2, 1);
        layout.addComponent(oneTable, 0, 2, 2, 2); 
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 3, 2, 3);
        
        layout.addComponent(twoLabel, 0, 4);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 5, 2, 5);
        layout.addComponent(twoTable, 0, 6, 2, 6);  
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 7, 2, 7);
        
        layout.addComponent(threeLabel, 0, 8);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 9, 2, 9);
        layout.addComponent(threeTable, 0, 10, 2, 10);  
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 11, 2, 11);
        
        layout.addComponent(fourLabel, 0, 12);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 13, 2, 13);
        layout.addComponent(fourTable, 0, 14, 2, 14);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 15, 2, 15);
        
        layout.addComponent(fiveLabel, 0, 16);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 17, 2, 17);
        layout.addComponent(fiveTable, 0, 18, 2, 18); 
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 19, 2, 19);
        
        addComponent(layout);
    }
}
