/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables;

import com.vaadin.ui.Table;

/**
 *
 * @author Colin
 */
public class AnnualDataSuperTable extends Table {

    public AnnualDataSuperTable() {
        setStyleName("annualDataTable");
        setImmediate(true);
        // Hide Column Header
//        setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        // Showing Table Row Headers
//        setRowHeaderMode(RowHeaderMode.ID);
//        setSelectable(false);
    }

    public void performTableFontSizeStyling() {
        setCellStyleGenerator(new Table.CellStyleGenerator() {
            @Override
            public String getStyle(Table source, Object itemId, Object propertyId) {
                // Row header column which is hidden has a null for propertyId.
                // do this is you have to parse propertyId for column number
                if (propertyId == null) {
                    return "green"; // Will not actually be visible
                }

                int row = ((Integer) itemId).intValue();
                int col = Integer.parseInt((String) propertyId);
                // The first column which is OUR Row Header.
                if (col == 0) {
                    return "rowheader";
                }

                if (row % 2 == 0) {
                    return "blue";
                } else {
                    return "white";
                }
            }
        });
    }

    public void resetColumnWidths() {
        alwaysRecalculateColumnWidths = true;
        for (Object object : this.getVisibleColumns()) {
            setColumnWidth(object, -1);
        }
    }
}
