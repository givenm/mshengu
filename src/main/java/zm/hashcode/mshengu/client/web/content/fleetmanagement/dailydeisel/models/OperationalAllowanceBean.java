/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author geek
 */
public class OperationalAllowanceBean implements Serializable {

    private String id;
    private BigDecimal operationalAllowance = BigDecimal.ZERO;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the OperationalAllowance
     */
    public BigDecimal getOperationalAllowance() {
        return operationalAllowance;
    }

    /**
     * @param OperationalAllowance the OperationalAllowance to set
     */
    public void setOperationalAllowance(BigDecimal OperationalAllowance) {
        this.operationalAllowance = OperationalAllowance;
    }
}
