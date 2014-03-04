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
public class FuelSpendLayout extends VerticalLayout {

    public VerticalLayout getFuelSpendLayout(BigDecimal fuelSpendPerUnit, BigDecimal fuelSpendPerService, Embedded fuelSpendUnitFlag, Embedded fuelSpendServiceFlag) {
        //=====================
        HorizontalLayout randHorizontalLayout = new HorizontalLayout();
//                randHorizontalLayout.setSpacing(true);
        Label rLabel = new Label("R");
        rLabel.setStyleName("dashboardLabel");
        randHorizontalLayout.addComponent(rLabel);
        randHorizontalLayout.setComponentAlignment(rLabel, Alignment.MIDDLE_LEFT);
        randHorizontalLayout.setSizeUndefined();
        //=====================
        HorizontalLayout amountAndFlagUnitHorizontalLayout = new HorizontalLayout();
//        amountAndFlagUnitHorizontalLayout.setSizeFull();
        Label amountLabel = new Label(fuelSpendPerUnit + "");
        amountLabel.setWidth(170, Unit.PIXELS);
        amountLabel.setStyleName("dashboardAmountLabel");
        Label perUnitLabel = new Label("per Unit");
        perUnitLabel.setWidth(70, Unit.PIXELS);
        perUnitLabel.setStyleName("dashboardAmountLabel");

        amountAndFlagUnitHorizontalLayout.addComponent(amountLabel);
        amountAndFlagUnitHorizontalLayout.addComponent(perUnitLabel);
        amountAndFlagUnitHorizontalLayout.addComponent(fuelSpendUnitFlag);
        amountAndFlagUnitHorizontalLayout.setComponentAlignment(amountLabel, Alignment.MIDDLE_LEFT);
        amountAndFlagUnitHorizontalLayout.setComponentAlignment(perUnitLabel, Alignment.MIDDLE_CENTER);
        amountAndFlagUnitHorizontalLayout.setComponentAlignment(fuelSpendUnitFlag, Alignment.MIDDLE_RIGHT);
        amountAndFlagUnitHorizontalLayout.setSizeUndefined();
        //=====================
        HorizontalLayout rand2HorizontalLayout = new HorizontalLayout();
//                randHorizontalLayout.setSpacing(true);
        Label r2Label = new Label("R");
        r2Label.setStyleName("dashboardLabel");
        rand2HorizontalLayout.addComponent(r2Label);
        rand2HorizontalLayout.setComponentAlignment(r2Label, Alignment.MIDDLE_LEFT);
        rand2HorizontalLayout.setSizeUndefined();

        //=====================
        HorizontalLayout amountAndFlagServiceHorizontalLayout = new HorizontalLayout();
//        amountAndFlagServiceHorizontalLayout.setSizeFull();
        Label amount2Label = new Label(fuelSpendPerService + "");
        amount2Label.setWidth(170, Unit.PIXELS);
        amount2Label.setStyleName("dashboardAmountLabel");
        Label perServiceLabel = new Label("per Service");
        perServiceLabel.setWidth(70, Unit.PIXELS);
        perServiceLabel.setStyleName("dashboardAmountLabel");

        amountAndFlagServiceHorizontalLayout.addComponent(amount2Label);
        amountAndFlagServiceHorizontalLayout.addComponent(perServiceLabel);
        amountAndFlagServiceHorizontalLayout.addComponent(fuelSpendServiceFlag);
        amountAndFlagServiceHorizontalLayout.setComponentAlignment(amount2Label, Alignment.MIDDLE_LEFT);
        amountAndFlagServiceHorizontalLayout.setComponentAlignment(perServiceLabel, Alignment.MIDDLE_CENTER);
        amountAndFlagServiceHorizontalLayout.setComponentAlignment(fuelSpendServiceFlag, Alignment.MIDDLE_RIGHT);
        amountAndFlagServiceHorizontalLayout.setSizeUndefined();
        //====================
        addComponent(randHorizontalLayout);
        addComponent(amountAndFlagUnitHorizontalLayout);
        addComponent(rand2HorizontalLayout);
        addComponent(amountAndFlagServiceHorizontalLayout);
        // These are IMPORTANT, else Panels will have Scrollbars
        setMargin(true);
        setSizeUndefined();

        return this;
    }
}
