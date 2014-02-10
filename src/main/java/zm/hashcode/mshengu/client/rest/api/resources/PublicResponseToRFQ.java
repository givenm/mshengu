/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.rest.api.resources;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author given
 */
public class PublicResponseToRFQ {
    
    private String rfqNumber;
    private String companyName;
    private String companyType;
    private int yearEstablishment;
    private String chiefExecutive;
    private boolean hasVat;
    private String vatRegistrationNumber;
    private List<String> item;
    private List<String> itemNumber;
    private List<Long> qty;
    private List<Long> unit;
    private List<Float> unitPrice;
    private List<BigDecimal> subTotal;
    private String webSite;
    private String validityOfQuote;
    private String paymentTerms;
    private String email;
    
    public boolean hasVat() {
        return hasVat;
    }

    public void hasVat(boolean hasVat) {
        this.hasVat = hasVat;
    }    

    public String getRfqNumber() {
        return rfqNumber;
    }

    public void setRfqNumber(String rfqNumber) {
        this.rfqNumber = rfqNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public int getYearEstablishment() {
        return yearEstablishment;
    }

    public void setYearEstablishment(int yearEstablishment) {
        this.yearEstablishment = yearEstablishment;
    }


    public String getChiefExecutive() {
        return chiefExecutive;
    }

    public void setChiefExecutive(String chiefExecutive) {
        this.chiefExecutive = chiefExecutive;
    }

    public String getVatRegistrationNumber() {
        return vatRegistrationNumber;
    }

    public void setVatRegistrationNumber(String vatRegistrationNumber) {
        this.vatRegistrationNumber = vatRegistrationNumber;
    }

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }

    public List<String> getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(List<String> itemNumber) {
        this.itemNumber = itemNumber;
    }

    public List<Long> getQty() {
        return qty;
    }

    public void setQty(List<Long> qty) {
        this.qty = qty;
    }

    public List<Long> getUnit() {
        return unit;
    }

    public void setUnit(List<Long> unit) {
        this.unit = unit;
    }

    public List<Float> getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(List<Float> unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<BigDecimal> getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(List<BigDecimal> subTotal) {
        this.subTotal = subTotal;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getValidityOfQuote() {
        return validityOfQuote;
    }

    public void setValidityOfQuote(String validityOfQuote) {
        this.validityOfQuote = validityOfQuote;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
