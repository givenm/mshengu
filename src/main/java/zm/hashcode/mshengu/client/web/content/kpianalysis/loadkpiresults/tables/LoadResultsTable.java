/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.tables;

import com.vaadin.ui.Table;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.joda.time.LocalDate;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIFacade;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIItemFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.kpianalysis.KPA;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;

/**
 *
 * @author Luckbliss
 */
public class LoadResultsTable extends Table {

    private final MshenguMain main;

    public LoadResultsTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("KPI Name", String.class, null);
        addContainerProperty("UOM", String.class, null);
    }

    public void addColumns(Date start, Date end) {

        LocalDate startDate = LocalDate.fromDateFields(start);
        LocalDate endDate = LocalDate.fromDateFields(end);
        LocalDate stopDate = LocalDate.fromDateFields(new Date());

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (date.isBefore(stopDate) || date.isEqual(stopDate)) {
                String yearValue = date.getYear() + "";
                String viewdate = getMonth(date.getMonthOfYear() - 1).substring(0, 3) + "-" + yearValue.substring(2);
                addContainerProperty(viewdate, String.class, null);
            }
        }
    }

    private String getMonth(int j) {
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            if (j == i) {
                return months[i];
            }
        }
        return null;
    }

    public void loadTable(Date start, Date end) {
        addColumns(start, end);
    }
}
