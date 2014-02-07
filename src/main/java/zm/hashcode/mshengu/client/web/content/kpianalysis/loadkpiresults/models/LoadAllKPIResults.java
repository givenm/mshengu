/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.models;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIFacade;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIItemFacade;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIValuesFacade;
import zm.hashcode.mshengu.domain.kpianalysis.KPA;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;
import zm.hashcode.mshengu.domain.kpianalysis.KPIValues;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPIFiveService;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPIFourService;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPIOneService;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPIThreeService;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPITwoService;

/**
 *
 * @author Luckbliss
 */
public class LoadAllKPIResults implements Serializable {

    private final Date fromDate;
    private final Date toDate;
    private LoadKPIOneService oneService;
    private LoadKPITwoService twoService;
    private LoadKPIThreeService threeServiceService;
    private LoadKPIFourService fourService;
    private LoadKPIFiveService fiveService;
    private List<KPIValues> values = new ArrayList<>();

    public LoadAllKPIResults(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    private List<KPIItem> getKPA(String tabValue) {
        KPA kpi = KPIFacade.getKPIService().findByTab(tabValue);
        List<KPIItem> items = kpi.getItems();
        List<KPIItem> foundItems = new ArrayList<>();
        for (KPIItem item : items) {            
            values = item.getValues();
            if (values.size() < 1) { //if values do not exist in KPIValues
                for (int i = getFromYear(); i <= getToYear(); i++) {
                    for (int j = 0; j < 12; j++) {
                        String month = getMonth(j);
                        //Creating kpivalues from start of services to current date ()
                        initiallyInsertKPAOneValuesInDatabase(item, month, j);
                        initiallyInsertKPATwoValuesInDatabase(item, month, j);
                    }
                }
                //Merge current item with new KPIValues
                KPIItem newIItem = new KPIItem.Builder(item.getShortDescription())
                        .kpiitem(item)
                        .values(values)
                        .build();
                KPIItemFacade.getKPIItemService().merge(newIItem);
                values = null;
            } else {
                List<KPIValues> foundValues = new ArrayList<>();
                for (KPIValues value : values) {
                    if (getValueYear(value.getDate()) >= getFromYear()
                            && getValueYear(value.getDate()) <= getToYear()
                            && getValueMonth(value.getDate()) >= getFromMonth()
                            && getValueMonth(value.getDate()) <= getToMonth()) { //if the date is in range then add value to foundValues List
                        foundValues.add(value);
                    } else { //if date not in range then look for the value from ... to see if they exist.

                        int startYear = getFromYear();
                        int endYear = getToYear();
                        int startMonth = getFromMonth();
                        int endMonth = getToMonth();
                        int currentMonth = getFromMonth();
                        while (startYear <= endYear) {

                            for (int i = currentMonth; 1 <= 12; i++) {
                            }
//                        currentMonth = 1;
//                        startYear++;
                        }
                    }
                }
                KPIItem newItem = new KPIItem.Builder(item.getShortDescription())
                        .values(foundValues)
                        .build();
                foundItems.add(newItem);
                newItem = null;
            }

        }
        return foundItems;
    }

    private void initiallyInsertKPAOneValuesInDatabase(KPIItem item, String month, int j) {
        if (item.getKpiNumber() == 1) {
            KPIValues v = new KPIValues.Builder(toDate)
                    .value(oneService.getNoServicesPerformed(month, j))
                    .build();
            KPIValuesFacade.getKPIValuesService().persist(v);
            values.add(v);
        } else if (item.getKpiNumber() == 2) {
            KPIValues v = new KPIValues.Builder(toDate)
                    .value(oneService.getCompletedPercentage(month, j))
                    .build();
            KPIValuesFacade.getKPIValuesService().persist(v);
            values.add(v);
        } else if (item.getKpiNumber() == 3) {
            KPIValues v = new KPIValues.Builder(toDate)
                    .value(oneService.getNoServicesNotCompleted(month, j))
                    .build();
            KPIValuesFacade.getKPIValuesService().persist(v);
            values.add(v);
        } else if (item.getKpiNumber() == 4) {
            KPIValues v = new KPIValues.Builder(toDate)
                    .value(oneService.getUncompletedPercentage(month, j))
                    .build();
            KPIValuesFacade.getKPIValuesService().persist(v);
            values.add(v);
        } else if (item.getKpiNumber() == 5) {
            KPIValues v = new KPIValues.Builder(toDate)
                    .value(oneService.getUnitDeployment(month, j))
                    .build();
            KPIValuesFacade.getKPIValuesService().persist(v);
            values.add(v);
        }
    }
    
    private void initiallyInsertKPATwoValuesInDatabase(KPIItem item, String month, int j) {
        if (item.getKpiNumber() == 1) {
            KPIValues v = new KPIValues.Builder(toDate)
                    .value(twoService.getNoServicesPerformed(month, j))
                    .build();
            KPIValuesFacade.getKPIValuesService().persist(v);
            values.add(v);
        } else if (item.getKpiNumber() == 2) {
            KPIValues v = new KPIValues.Builder(toDate)
                    .value(twoService.getCompletedPercentage(month, j))
                    .build();
            KPIValuesFacade.getKPIValuesService().persist(v);
            values.add(v);
        } else if (item.getKpiNumber() == 3) {
            KPIValues v = new KPIValues.Builder(toDate)
                    .value(twoService.getNoServicesNotCompleted(month, j))
                    .build();
            KPIValuesFacade.getKPIValuesService().persist(v);
            values.add(v);
        } else if (item.getKpiNumber() == 4) {
            KPIValues v = new KPIValues.Builder(toDate)
                    .value(twoService.getUncompletedPercentage(month, j))
                    .build();
            KPIValuesFacade.getKPIValuesService().persist(v);
            values.add(v);
        } else if (item.getKpiNumber() == 5) {
            KPIValues v = new KPIValues.Builder(toDate)
                    .value(twoService.getPrivateContribution(month, j))
                    .build();
            KPIValuesFacade.getKPIValuesService().persist(v);
            values.add(v);
        }
    }

    public List<KPIItem> getAllKPIItems() {
        List<KPIItem> items = new ArrayList<>();
        items.addAll(getKPA("one"));
        items.addAll(getKPA("two"));
        items.addAll(getKPA("three"));
        items.addAll(getKPA("four"));
        items.addAll(getKPA("five"));
        return items;
    }

    public int getFromMonth() {
        return getMonth(new SimpleDateFormat("MMMM").format(fromDate));
    }

    public int getFromYear() {
        return Integer.parseInt(new SimpleDateFormat("YYYY").format(fromDate));
    }

    public int getToMonth() {
        return getMonth(new SimpleDateFormat("MMMM").format(toDate));
    }

    public int getToYear() {
        return Integer.parseInt(new SimpleDateFormat("YYYY").format(toDate));
    }

    public int getValueYear(Date date) {
        return Integer.parseInt(new SimpleDateFormat("YYYY").format(date));
    }

    public int getValueMonth(Date date) {
        return getMonth(new SimpleDateFormat("MMMM").format(date));
    }

    private int getMonth(String month) {
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i]));
            return i + 1;
        }
        return 0;
    }

    private String getMonth(int j) {
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            if (j == i);
            return months[i];
        }
        return null;
    }
}
