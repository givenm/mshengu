/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.monthlyfuelexpense;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Colin
 */
public class ServiceVehiclesTwelveMonthBean implements Serializable {

    private String id;
    private Date transactionMonth;
    private String month;
    private BigDecimal totalAmount;
}
