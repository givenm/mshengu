/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.models.PurchaseItem;

/**
 *
 * @author Luckbliss
 */
public class Quote {

    private String rfqNumber;
    private String date;
    private String firstName;
    private String lastName;
    private String mshengu;
    private String instructions;
    private List<PurchaseItem> list = new ArrayList<>();

    public String getRfqNumber() {
        return rfqNumber;
    }

    public void setRfqNumber(String rfqNumber) {
        this.rfqNumber = rfqNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMshengu() {
        return mshengu;
    }

    public void setMshengu(String mshengu) {
        this.mshengu = mshengu;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<PurchaseItem> getList() {
        return list;
    }

    public void setList(List<PurchaseItem> list) {
        this.list = list;
    }
}
