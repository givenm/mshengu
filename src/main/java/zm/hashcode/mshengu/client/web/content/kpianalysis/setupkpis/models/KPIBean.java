/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.models;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Luckbliss
 */
public class KPIBean  implements Serializable {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
