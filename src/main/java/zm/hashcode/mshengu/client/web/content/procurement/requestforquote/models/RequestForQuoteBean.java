/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.models;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Luckbliss
 */
public class RequestForQuoteBean implements Serializable{
     private String id;
    @NotNull
    private String account;
    private String itemNumber;
    @NotNull
    private String rfqNumber;
    @NotNull
    private String requestingPerson;
}
