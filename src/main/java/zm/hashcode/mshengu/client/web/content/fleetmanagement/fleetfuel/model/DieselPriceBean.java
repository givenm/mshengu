/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Colin
 */
public class DieselPriceBean implements Serializable {

    private String id;
    private Date transactionMonth;
    private String month;
    private String truckId;
    private BigDecimal amountSpend = BigDecimal.ZERO;
}
