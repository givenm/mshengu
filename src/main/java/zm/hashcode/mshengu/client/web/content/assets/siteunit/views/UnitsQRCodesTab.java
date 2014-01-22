/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.views;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.haijian.PdfExporter;
import zm.hashcode.mshengu.app.facade.products.SiteUnitFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.tables.QRCodesTable;
import zm.hashcode.mshengu.domain.products.SiteUnit;

/*
 * @author Ferox
 */
public class UnitsQRCodesTab extends VerticalLayout {

    public final QRCodesTable table;
    public final HorizontalLayout headerBar = new HorizontalLayout();
    private final TextField siteUnitSearchBox = new TextField("Search For Site Unit");
    public final VerticalLayout contentPanel = new VerticalLayout();
    private final MshenguMain main;
    private List<SiteUnit> siteUnitsArrayList;// = new ArrayList<>();

    public UnitsQRCodesTab(MshenguMain app) {
        main = app;
        contentPanel.setSizeFull();
        table = new QRCodesTable(main, this);
        headerBar.setSizeFull();
        headerBar.addComponent(siteUnitSearchBox);
        headerBar.setComponentAlignment(siteUnitSearchBox, Alignment.MIDDLE_LEFT);

        siteUnitSearchBox.setWidth("400px");
        addComponent(headerBar);
        addComponent(new Label("<HR/>", ContentMode.HTML));
        contentPanel.removeAllComponents();
        PdfExporter export = new PdfExporter(table);
        export.setCaption("Export PDF");        
        export.setStyleName("default");
        
        contentPanel.addComponent(table);
        contentPanel.addComponent(export);
        addComponent(contentPanel);



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
                        table.removeAllItems();
                        table.loadToiletUnits(list);
                    } else {
//                        List<SiteUnit> units = SiteUnitFacade.getSiteUnitService().findAll();
                        table.removeAllItems();
                        if (siteUnitsArrayList != null) {
                            table.loadToiletUnits(siteUnitsArrayList);
                        }
                    }
                }
            }
        });
    }

    public void loadToiletUnits(List<SiteUnit> siteUnitsList) {
//        table.removeValueChangeListener((Property.ValueChangeListener) this);
        table.loadToiletUnits(siteUnitsList);
//        table.addValueChangeListener((Property.ValueChangeListener) this);
        siteUnitsArrayList = new ArrayList<>();
        siteUnitsArrayList.addAll(siteUnitsList);

    }
}
