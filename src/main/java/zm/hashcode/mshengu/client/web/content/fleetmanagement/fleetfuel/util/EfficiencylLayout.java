/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;

/**
 *
 * @author Colin
 */
public class EfficiencylLayout extends VerticalLayout {

    public VerticalLayout getEfficiencyLayout(BigDecimal monthEfficiency, Embedded monthEfficiencyFlag) {
        //=====================
        HorizontalLayout randHorizontalLayout = new HorizontalLayout();
//                randHorizontalLayout.setSpacing(true);
        Label rLabel = new Label("R");
        rLabel.setStyleName("dashboardLabel");
        randHorizontalLayout.addComponent(rLabel);
        randHorizontalLayout.setComponentAlignment(rLabel, Alignment.MIDDLE_LEFT);
        randHorizontalLayout.setSizeUndefined();
        //=====================
        HorizontalLayout amountAndFlagHorizontalLayout = new HorizontalLayout();
        Label amountLabel = new Label(monthEfficiency + "");
        amountLabel.setWidth(120, Unit.PIXELS);
        amountLabel.setStyleName("dashboardAmountLabel");
        amountAndFlagHorizontalLayout.addComponent(amountLabel);
        amountAndFlagHorizontalLayout.addComponent(monthEfficiencyFlag);
        amountAndFlagHorizontalLayout.setComponentAlignment(amountLabel, Alignment.TOP_LEFT);
        amountAndFlagHorizontalLayout.setComponentAlignment(monthEfficiencyFlag, Alignment.TOP_RIGHT);
//        amountAndFlagHorizontalLayout.setHeight(30, Unit.PIXELS);
//        amountAndFlagHorizontalLayout.setSizeUndefined();
        //=====================
        HorizontalLayout perKmHorizontalLayout = new HorizontalLayout();
        Label perKmLabel = new Label("per Km");
        perKmLabel.setWidth(110, Unit.PIXELS);
        perKmLabel.setStyleName("dashboardLabel");
        perKmHorizontalLayout.addComponent(perKmLabel);
        perKmHorizontalLayout.setComponentAlignment(perKmLabel, Alignment.MIDDLE_RIGHT);
        perKmHorizontalLayout.setSizeUndefined();
        //====================
        addComponent(randHorizontalLayout);
        addComponent(amountAndFlagHorizontalLayout);
        addComponent(perKmHorizontalLayout);
        // These are IMPORTANT, else Panels will have Scrollbars
        setMargin(true);
        setSizeUndefined();

        return this;
    }
}
