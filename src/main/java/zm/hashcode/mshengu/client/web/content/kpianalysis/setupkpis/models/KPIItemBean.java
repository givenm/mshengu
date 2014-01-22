/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.models;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Luckbliss
 */
public class KPIItemBean {

    private String id;
    @NotNull
    private String shortDescription;
    @NotNull
    private String detailedDescription;
    @NotNull
    private String uom;
    @NotNull
    private String measureTYpe;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getMeasureTYpe() {
        return measureTYpe;
    }

    public void setMeasureTYpe(String measureTYpe) {
        this.measureTYpe = measureTYpe;
    }
}
