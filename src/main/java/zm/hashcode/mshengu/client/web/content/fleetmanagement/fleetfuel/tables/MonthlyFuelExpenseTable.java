/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.tables;

import com.vaadin.ui.Table;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author Colin
 */
public class MonthlyFuelExpenseTable extends Table {

    private final MshenguMain main;
    // Use a specific locale for formatting decimal numbers
    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));

    public MonthlyFuelExpenseTable(MshenguMain main) {
        this.main = main;
//////        // Hide Column Header
//////       //        setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
//////        setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        setStyleName("panelTable");
        addContainerProperty("", String.class, null);
        // Allow selecting items from the table.
//        setNullSelectionAllowed(false);
        setSelectable(false);
//        // Send changes in selection immediately to server.
//        setImmediate(false);
        // Alignments
        setColumnAlignment("", Table.Align.RIGHT);
        addItem(new Object[]{"0.00"}, new Integer("1"));

    }
}
