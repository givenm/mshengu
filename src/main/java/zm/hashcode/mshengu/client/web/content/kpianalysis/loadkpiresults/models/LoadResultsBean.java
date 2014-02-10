/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Luckbliss
 */
public class LoadResultsBean implements Serializable{

    private Date fromdate;
    private Date todate;

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }
}
