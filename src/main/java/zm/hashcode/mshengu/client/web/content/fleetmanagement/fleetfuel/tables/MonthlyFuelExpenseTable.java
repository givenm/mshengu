/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.tables;

import com.vaadin.ui.Table;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.monthlyfuelexpense.MonthlyFuelExpenseBean;

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
        setStyleName("panelTable");
        //
        addContainerProperty("Month", String.class, null);
        addContainerProperty("Non-Operational", String.class, null);
        addContainerProperty("Operational", String.class, null);
        addContainerProperty("Service Fleet", String.class, null);
        addContainerProperty("Total", String.class, null);
        // Allow selecting items from the table.
//        setNullSelectionAllowed(false);
        setSelectable(false);
//        // Send changes in selection immediately to server.
//        setImmediate(false);
        // Alignments
//        setColumnAlignment("", Table.Align.RIGHT);
////        addItem(new Object[]{"0.00"}, new Integer("1"));

    }

    public Table createTable(List<MonthlyFuelExpenseBean> monthlyFuelExpenseBeanList) {



        return this;
    }
}
