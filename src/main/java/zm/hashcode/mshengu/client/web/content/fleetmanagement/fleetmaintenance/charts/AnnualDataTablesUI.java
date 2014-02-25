/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import zm.hashcode.mshengu.app.util.panel.PanelStyled;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.AnnualMaintenanceCostHeadingTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.AnnualMaintenanceCostTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.AnnualMileageTable;

/**
 *
 * @author Colin
 */
public class AnnualDataTablesUI extends VerticalLayout {
// Panel to contain layouts for All Tables

    public PanelStyled mainPanel = new PanelStyled(); // new PanelStyled("Month Spend");
    public VerticalLayout mainLayout = new VerticalLayout();
    // Title bar
    private HorizontalLayout costTitleHorizontalLayout = new HorizontalLayout();
    private HorizontalLayout mileageTitleHorizontalLayout = new HorizontalLayout();
    //
    Label costTitle = new Label("Maintenance Cost");
    Label mileageTitle = new Label("Mileage");
    // Horizontal layouts to contain Tables
    public VerticalLayout costHeadingLayout = new VerticalLayout();
    public VerticalLayout costTableLayout = new VerticalLayout();
    public VerticalLayout mileageHeadingLayout = new VerticalLayout();
    public VerticalLayout mileageTableLayout = new VerticalLayout();

    public void addTables(AnnualMaintenanceCostHeadingTable annualMaintenanceCostHeadingTable, AnnualMaintenanceCostTable annualMaintenanceCostTable, AnnualMileageTable annualMileageTable) {
        addStyleName("chartLayout");
        setMargin(false);
        //
        costTitle.addStyleName("title");
        mileageTitle.addStyleName("title");


        costTitleHorizontalLayout.addComponent(costTitle);
        mileageTitleHorizontalLayout.addComponent(mileageTitle);

        costHeadingLayout.addComponent(annualMaintenanceCostHeadingTable);
        costHeadingLayout.setSpacing(false);
        costTableLayout.addComponent(annualMaintenanceCostTable);
        costTableLayout.setSpacing(false);
//        mileageHeadingLayout.addComponent();
        mileageTableLayout.addComponent(annualMileageTable);
        mileageTableLayout.setSpacing(false);

        mainLayout.addComponent(costTitleHorizontalLayout);
        mainLayout.addComponent(costHeadingLayout);
        mainLayout.addComponent(costTableLayout);
        mainLayout.addComponent(mileageTitleHorizontalLayout); //mileageHeadingLayout
//        mainLayout.addComponent(mileageHeadingLayout);
        mainLayout.addComponent(mileageTableLayout);



// Flexible sizing column widths using Column headerss (Vehicle Numbers)
// Match the column widths of Maintenance Cost and Mileage table to that of Heading table

        String[] headingTableHeaders = annualMaintenanceCostHeadingTable.getColumnHeaders();
        for (int i = 0; i < headingTableHeaders.length; i++) {
//            System.out.println("Column Header: " + headingTableHeaders[i]);
//            annualMaintenanceCostTable.setColumnWidth(headingTableHeaders[i], annualMaintenanceCostHeadingTable.getColumnWidth(headingTableHeaders[i]));
//            annualMileageTable.setColumnWidth(headingTableHeaders[i], annualMaintenanceCostHeadingTable.getColumnWidth(headingTableHeaders[i]));
            annualMaintenanceCostHeadingTable.setColumnWidth(headingTableHeaders[i], 75);
            annualMaintenanceCostTable.setColumnWidth(headingTableHeaders[i], 75);
            annualMileageTable.setColumnWidth(headingTableHeaders[i], 75);
        }
        annualMaintenanceCostTable.setColumnWidth("Total", 75);
        annualMileageTable.setColumnWidth("Total", 75);

        annualMaintenanceCostTable.setColumnWidth("RowHead", 80); // "" for RowHead
        annualMileageTable.setColumnWidth("RowHead", 80);
        annualMaintenanceCostHeadingTable.setColumnWidth("RowHead", 80); // "" for RowHead


// Now Hide the Column Headers
//        annualMaintenanceCostHeadingTable.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
//        annualMaintenanceCostTable.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
//        annualMileageTable.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);


        costTitleHorizontalLayout.setWidth("100%");  // inherit the size of mainLayout
        mileageTitleHorizontalLayout.setWidth("100%");
        costHeadingLayout.setWidth(annualMaintenanceCostTable.getWidth(), Unit.PIXELS);  // inherit the size of annualMaintenanceCostHeadingTable
        costTableLayout.setWidth(annualMaintenanceCostTable.getWidth(), Unit.PIXELS);
        mainLayout.setWidth(costHeadingLayout.getWidth(), Unit.PIXELS); // inherit the size of costHeadingLayout
        mainLayout.setSpacing(false);
        //
        mainPanel.setWidth(1045, Unit.PIXELS);
        mainPanel.setContent(mainLayout);

        addComponent(mainPanel);
    }
}
