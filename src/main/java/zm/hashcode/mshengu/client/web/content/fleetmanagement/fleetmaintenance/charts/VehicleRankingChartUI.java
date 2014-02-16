/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.panel.PanelStyled;

/**
 *
 * @author geek
 */
public class VehicleRankingChartUI extends FormLayout {
// Create the root content layout

    public VerticalLayout chartRootVerticalLayout = new VerticalLayout();

    public VehicleRankingChartUI(MshenguMain main) {
//        addComponent(chartRootVerticalLayout);
    }

    public void defineAndAddLayouts(PanelStyled kmTravelledVehicleChartPanel, PanelStyled vehicleNumberPanel, PanelStyled totalMaintenanceCostPanel,
            PanelStyled totalMileagePanel, PanelStyled randsPerKmPanel, PanelStyled ratingPanel, PanelStyled monthSpendPanel) {
        chartRootVerticalLayout.addStyleName("chartLayout");
        chartRootVerticalLayout.setMargin(true);
//        chartRootVerticalLayout.setSizeFull();

        // Title bar
        HorizontalLayout titleBarHorizontalLayout = new HorizontalLayout();
        // Horizontal layout to contain All Panels for Charts and Tables
        HorizontalLayout detailsSectionHorizontalLayout = new HorizontalLayout();
        // Layouts to contain individual panels
        VerticalLayout graphLayout = new VerticalLayout();
        VerticalLayout vehicleNumberLayout = new VerticalLayout();
        VerticalLayout maintenanceLayout = new VerticalLayout();
        VerticalLayout mileageLayout = new VerticalLayout();
        VerticalLayout RperKmLayout = new VerticalLayout();
        VerticalLayout ratingLayout = new VerticalLayout();
        VerticalLayout monthSpendLayout = new VerticalLayout();

///////////////////////////////////////////////////////////////////////////////
        Label title = new Label("Current 12-Monthly Vehicle Ranking ");
        title.addStyleName("title");

        titleBarHorizontalLayout.setWidth("100%");
        titleBarHorizontalLayout.addComponent(title);

        detailsSectionHorizontalLayout.setWidth(null); // Undefined width
//        detailsSectionHorizontalLayout.setSpacing(true);

        chartRootVerticalLayout.addComponent(titleBarHorizontalLayout);
        chartRootVerticalLayout.addComponent(detailsSectionHorizontalLayout);

        graphLayout.setWidth(null); // Undefined width
//        graphLayout.setHeight("100%");
        graphLayout.setMargin(true);
        graphLayout.addComponent(kmTravelledVehicleChartPanel);
        graphLayout.addStyleName("detailLayout");

        vehicleNumberLayout.setWidth(null); // Undefined width
        vehicleNumberLayout.setMargin(true);
        vehicleNumberLayout.addComponent(vehicleNumberPanel);
        vehicleNumberLayout.addStyleName("detailLayout");

        maintenanceLayout.setWidth(null); // Undefined width
        maintenanceLayout.setMargin(true);
        maintenanceLayout.addComponent(totalMaintenanceCostPanel);
        maintenanceLayout.addStyleName("detailLayout");

        mileageLayout.setWidth(null); // Undefined width
        mileageLayout.setMargin(true);
        mileageLayout.addComponent(totalMileagePanel);
        mileageLayout.addStyleName("detailLayout");

        RperKmLayout.setWidth(null); // Undefined width
        RperKmLayout.setMargin(true);
        RperKmLayout.addComponent(randsPerKmPanel);
        RperKmLayout.addStyleName("detailLayout");

        ratingLayout.setWidth(null); // Undefined width
        ratingLayout.setMargin(true);
        ratingLayout.addComponent(ratingPanel);
        ratingLayout.addStyleName("detailLayout");

        monthSpendLayout.setWidth(null); // Undefined width
        monthSpendLayout.setMargin(true);
        monthSpendLayout.addComponent(monthSpendPanel);
        monthSpendLayout.addStyleName("detailLayout");

        detailsSectionHorizontalLayout.addComponent(graphLayout);
        detailsSectionHorizontalLayout.addComponent(vehicleNumberLayout);
        detailsSectionHorizontalLayout.addComponent(maintenanceLayout);
        detailsSectionHorizontalLayout.addComponent(mileageLayout);
        detailsSectionHorizontalLayout.addComponent(RperKmLayout);
        detailsSectionHorizontalLayout.addComponent(ratingLayout);
        detailsSectionHorizontalLayout.addComponent(monthSpendLayout);

        addComponent(chartRootVerticalLayout);
    }
}
