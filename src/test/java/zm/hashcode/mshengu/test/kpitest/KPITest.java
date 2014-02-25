/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.kpitest;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.kpianalysis.KPA;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;
import zm.hashcode.mshengu.domain.kpianalysis.KPIValues;
import zm.hashcode.mshengu.services.kpianalysis.KPIItemService;
import zm.hashcode.mshengu.services.kpianalysis.KPAService;
import zm.hashcode.mshengu.services.kpianalysis.KPIValuesService;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPIOneService;
import zm.hashcode.mshengu.services.kpianalysis.LoadKPITwoService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class KPITest extends AppTest {

    @Autowired
    private KPAService service;
    @Autowired
    private KPIItemService itemService;
    @Autowired
    private KPIValuesService valuesService;
    @Autowired
    private LoadKPIOneService oneService;
    @Autowired
    private LoadKPITwoService twoService;

//    @Test
    public void testAddEvent() {
        service = ctx.getBean(KPAService.class);
        itemService = ctx.getBean(KPIItemService.class);
        valuesService = ctx.getBean(KPIValuesService.class);
        oneService = ctx.getBean(LoadKPIOneService.class);
        twoService = ctx.getBean(LoadKPITwoService.class);

        List<KPA> kpis = service.findAll();
        for (KPA kpa : kpis) {
            getKPA(kpa.getTab());
        }

    }

    private void getKPA(String tabValue) {
        KPA kpi = service.findByTab(tabValue);
        List<KPIItem> items = kpi.getItems();
        for (KPIItem item : items) {
            List<KPIValues> values = new ArrayList<>(); //if values do not exist in KPIValues
            for (int i = getValueYear(new Date(2014, 01, 01)); i <= getValueYear(new Date(2014, 02, 01)); i++) {
                for (int j = 0; j < 12; j++) {
                    String month = getMonth(j);
                    //Creating kpivalues from start of services to current date ()
                    if (tabValue.equalsIgnoreCase("one") && initiallyInsertKPAOneValuesInDatabase(item, month, j, i) != null) {
                        values.add(initiallyInsertKPAOneValuesInDatabase(item, month, j, i));
                    } else if (tabValue.equalsIgnoreCase("two") && initiallyInsertKPATwoValuesInDatabase(item, month, j, i) != null) {
                        values.add(initiallyInsertKPATwoValuesInDatabase(item, month, j, i));
                    }
                }
            }
            //Merge current item with new KPIValues
            KPIItem newIItem = new KPIItem.Builder(item.getShortDescription())
                    .kpiitem(item)
                    .values(values)
                    .build();
            itemService.merge(newIItem);
        }
    }

    private KPIValues initiallyInsertKPAOneValuesInDatabase(KPIItem item, String month, int j, int year) {
        if (item.getKpiNumber() == 1) {
            KPIValues v = new KPIValues.Builder(new Date(year, getMonth(month), 1))
                    .value(oneService.getNoServicesPerformed(month, j))
                    .build();
            valuesService.persist(v);
            return v;
        } else if (item.getKpiNumber() == 2) {
            KPIValues v = new KPIValues.Builder(new Date(year, getMonth(month), 1))
                    .value(oneService.getCompletedPercentage(month, j))
                    .build();
            valuesService.persist(v);
            return v;
        } else if (item.getKpiNumber() == 3) {
            KPIValues v = new KPIValues.Builder(new Date(year, getMonth(month), 1))
                    .value(oneService.getNoServicesNotCompleted(month, j))
                    .build();
            valuesService.persist(v);
            return v;
        } else if (item.getKpiNumber() == 4) {
            KPIValues v = new KPIValues.Builder(new Date(year, getMonth(month), 1))
                    .value(oneService.getUncompletedPercentage(month, j))
                    .build();
            valuesService.persist(v);
            return v;
        } else if (item.getKpiNumber() == 5) {
            KPIValues v = new KPIValues.Builder(new Date(year, getMonth(month), 1))
                    .value(oneService.getUnitDeployment(month, j))
                    .build();
            valuesService.persist(v);
            return v;
        }
        return null;
    }

    private KPIValues initiallyInsertKPATwoValuesInDatabase(KPIItem item, String month, int j, int year) {
        if (item.getKpiNumber() == 1) {
            KPIValues v = new KPIValues.Builder(new Date(year, getMonth(month), 1))
                    .value(twoService.getNoServicesPerformed(month, j))
                    .build();
            valuesService.persist(v);
            return v;
        } else if (item.getKpiNumber() == 2) {
            KPIValues v = new KPIValues.Builder(new Date(year, getMonth(month), 1))
                    .value(twoService.getCompletedPercentage(month, j))
                    .build();
            valuesService.persist(v);
            return v;
        } else if (item.getKpiNumber() == 3) {
            KPIValues v = new KPIValues.Builder(new Date(year, getMonth(month), 1))
                    .value(twoService.getNoServicesNotCompleted(month, j))
                    .build();
            valuesService.persist(v);
            return v;
        } else if (item.getKpiNumber() == 4) {
            KPIValues v = new KPIValues.Builder(new Date(year, getMonth(month), 1))
                    .value(twoService.getUncompletedPercentage(month, j))
                    .build();
            valuesService.persist(v);
            return v;
        } else if (item.getKpiNumber() == 5) {
            KPIValues v = new KPIValues.Builder(new Date(year, getMonth(month), 1))
                    .value(twoService.getPrivateContribution(month, j))
                    .build();
            valuesService.persist(v);
            return v;
        }
        return null;
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
