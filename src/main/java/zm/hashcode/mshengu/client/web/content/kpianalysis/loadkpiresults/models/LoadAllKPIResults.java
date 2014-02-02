/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIFacade;
import zm.hashcode.mshengu.domain.kpianalysis.KPI;
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

    public LoadAllKPIResults(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getFromMonth() {
        return new SimpleDateFormat("MMMM").format(fromDate);
    }

    public int getFromYear() {
        return Integer.parseInt(new SimpleDateFormat("YYYY").format(fromDate));
    }

    public String getToMonth() {
        return new SimpleDateFormat("MMMM").format(toDate);
    }

    public int getToYear() {
        return Integer.parseInt(new SimpleDateFormat("YYYY").format(toDate));
    }
    
    private KPI getKPIOne(){
        KPI kpi = KPIFacade.getKPIService().findByTab("one");
        List<KPIItem> items = kpi.getItems();
        for(KPIItem item: items){
            List<KPIValues> values = item.getValues();
            for(KPIValues value: values){
                
            }
                
        }
        return kpi;
    }
}
