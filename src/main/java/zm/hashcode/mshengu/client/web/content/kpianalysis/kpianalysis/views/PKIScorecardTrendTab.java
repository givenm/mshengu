/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.KPIMenu;

/**
 *
 * @author Ferox
 */
public class PKIScorecardTrendTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;

    public PKIScorecardTrendTab(MshenguMain app) {
        main = app;
        setSizeFull();

    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
    }

     private void getHome() {
        main.content.setSecondComponent(new KPIMenu(main, "TREND"));
    }
    
}