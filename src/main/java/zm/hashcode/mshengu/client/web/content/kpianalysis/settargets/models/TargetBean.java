/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.models;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Luckbliss
 */
public class TargetBean {

    private String id;
    @NotNull
    private String editTargets;
    @NotNull
    private String weighting;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEditTargets() {
        return editTargets;
    }

    public void setEditTargets(String editTargets) {
        this.editTargets = editTargets;
    }

    public String getWeighting() {
        return weighting;
    }

    public void setWeighting(String weighting) {
        this.weighting = weighting;
    }
}
