/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.kpitest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.kpianalysis.KPI;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;
import zm.hashcode.mshengu.domain.kpianalysis.KPIValues;
import zm.hashcode.mshengu.services.kpianalysis.KPIItemService;
import zm.hashcode.mshengu.services.kpianalysis.KPIService;
import zm.hashcode.mshengu.services.kpianalysis.KPIValuesService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class KPITest extends AppTest {

    @Autowired
    private KPIService service;
    @Autowired
    private KPIItemService itemService;
    @Autowired
    private KPIValuesService valuesService;

    @Test
    public void testAddEvent() {
        service = ctx.getBean(KPIService.class);
        itemService = ctx.getBean(KPIItemService.class);
        valuesService = ctx.getBean(KPIValuesService.class);

        List<KPI> kpis = service.findAll();
        Random random = new Random(5);
        for (KPI kpi : kpis) {
            List<KPIItem> items = kpi.getItems();
            List<KPIItem> kPIItems = new ArrayList<>();
            for (KPIItem item : items) {
                List<KPIValues> values = new ArrayList<>();
                for (int i = 2011; i < 2014; i++) {
                    for (int j = 0; j < 12; j++) {                        
                        int result = 0;
                        result = random.nextInt(25);
                        String month = getMonth(j);
                        KPIValues value = new KPIValues.Builder(month)
                                .value(result)
                                .year(i)
                                .build();
                        values.add(value);
                        valuesService.persist(value);
                        value = null;
                    }
                }
                KPIItem newItem = new KPIItem.Builder(item.getShortDescription())
                        .kpiitem(item)
                        .values(values)
                        .build();
                itemService.merge(newItem);
                kPIItems.add(newItem);
                newItem = null;
                values = null;
            }
            KPI newkpi = new KPI.Builder(kpi.getName())
                    .kpi(kpi)
                    .items(kPIItems)
                    .build();
            service.merge(newkpi);
            newkpi = null;
        }
    }

    private String getMonth(int j) {
        String month = "";
        if (j == 0) {
            month = "January";
        } else if (j == 1) {
            month = "February";
        } else if (j == 2) {
            month = "March";
        } else if (j == 3) {
            month = "April";
        } else if (j == 4) {
            month = "May";
        } else if (j == 5) {
            month = "June";
        } else if (j == 6) {
            month = "July";
        } else if (j == 7) {
            month = "August";
        } else if (j == 8) {
            month = "Septenber";
        } else if (j == 9) {
            month = "Octobetr";
        } else if (j == 10) {
            month = "November";
        } else if (j == 11) {
            month = "December";
        }
        return month;
    }
}
