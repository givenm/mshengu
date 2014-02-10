/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.procurement;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.external.MailNotifications;

/**
 *
 * @author Luckbliss
 */
@Document
public class ResponseToRFQ implements Serializable, Comparable<ResponseToRFQ> {

    @Id
    private String id;
    private String companyName;
    private String companyType;
    private int yearEstablishment;
    private String chiefExecutive;
    private boolean registeredForVat;
    private String vatRegistrationNumber;
    private String webSite;
    private String validityOfQuote;
    private String paymentTerms;
    private String email;
    private String legalForm;
    @DBRef
    private Set<RequestPurchaseItem> items;
    private String refNumber;
    private boolean accepted;
//    @DBRef
//    private MailNotifications mailNotifications;

    @Override
    public int compareTo(ResponseToRFQ o) {
        return companyName.compareToIgnoreCase(o.companyName);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResponseToRFQ other = (ResponseToRFQ) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    private ResponseToRFQ() {
    }

    private ResponseToRFQ(ResponseToRFQ.Builder builder) {
        this.id = builder.id;
        this.companyName = builder.companyName;
        this.companyType = builder.companyType;
        this.yearEstablishment = builder.yearEstablishment;
        this.chiefExecutive = builder.chiefExecutive;
        this.vatRegistrationNumber = builder.vatRegistrationNumber;
        this.registeredForVat = builder.registeredForVat;
        this.items = builder.items;
        this.webSite = builder.webSite;
        this.validityOfQuote = builder.validityOfQuote;
        this.paymentTerms = builder.paymentTerms;
        this.email = builder.email;
        this.legalForm = builder.legalForm;
        this.refNumber = builder.refNumber;
        this.accepted = builder.accepted;
    }

    /**
     * @return the refNumber
     */
    public String getRefNumber() {
        return refNumber;
    }

    public static class Builder {

        private String id;
        private final String companyName;
        private String companyType;
        private int yearEstablishment;
        private String chiefExecutive;
        private String vatRegistrationNumber;
        private boolean registeredForVat;
        private Set<RequestPurchaseItem> items;
        private String webSite;
        private String validityOfQuote;
        private String paymentTerms;
        private String email;
        private String legalForm;
        private String refNumber;
        private boolean accepted;

        public Builder(String value) {
            this.companyName = value;
        }

        public Builder request(ResponseToRFQ request) {
            this.id = request.getId();
            this.companyType = request.getCompanyType();
            this.yearEstablishment = request.getYearEstablishment();
            this.chiefExecutive = request.getChiefExecutive();
            this.vatRegistrationNumber = request.getVatRegistrationNumber();
            this.registeredForVat = request.getRegisteredForVat();
            this.items = request.getItems();
            this.webSite = request.getWebSite();
            this.validityOfQuote = request.getValidityOfQuote();
            this.paymentTerms = request.getPaymentTerms();
            this.email = request.getEmail();
            this.legalForm = request.getLegalForm();
            this.refNumber = request.getRefNumber();
            this.accepted = request.isAccepted();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder accepted(boolean value) {
            this.accepted = value;
            return this;
        }

        public ResponseToRFQ.Builder legalForm(String value) {
            this.legalForm = value;
            return this;
        }

        public Builder companyType(String value) {
            this.companyType = value;
            return this;
        }

        public Builder yearEstablishment(int value) {
            this.yearEstablishment = value;
            return this;
        }

        public Builder chiefExecutive(String value) {
            this.chiefExecutive = value;
            return this;
        }

        public Builder vatRegistrationNumber(String value) {
            this.vatRegistrationNumber = value;
            return this;
        }
        
        public Builder registeredForVat(boolean value) {
            this.registeredForVat = value;
            return this;
        }

        public Builder items(Set<RequestPurchaseItem> value) {
            this.items = value;
            return this;
        }

        public Builder webSite(String value) {
            this.webSite = value;
            return this;
        }

        public Builder validityOfQuote(String value) {
            this.validityOfQuote = value;
            return this;
        }

        public Builder paymentTerms(String value) {
            this.paymentTerms = value;
            return this;
        }

        public Builder email(String value) {
            this.email = value;
            return this;
        }

        public Builder refNumber(String value) {
            this.refNumber = value;
            return this;
        }

        public ResponseToRFQ build() {
            return new ResponseToRFQ(this);
        }
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getLegalForm() {
        return legalForm;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getId() {
        return id;
    }

    public String getCompanyType() {
        return companyType;
    }

    public int getYearEstablishment() {
        return yearEstablishment;
    }

    public String getChiefExecutive() {
        return chiefExecutive;
    }

    public String getVatRegistrationNumber() {
        return vatRegistrationNumber;
    }
    
    public boolean getRegisteredForVat() {
        return registeredForVat;
    }

    public Set<RequestPurchaseItem> getItems() {
        return items;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getValidityOfQuote() {
        return validityOfQuote;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public String getEmail() {
        return email;
    }
}
