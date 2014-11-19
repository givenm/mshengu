/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.procurement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author Luckbliss
 */
@Document
public class RequestForQuote implements Serializable, Comparable<RequestForQuote> {

    @Id
    private String id;
    private String rfqNumber;
    @DBRef
    private Person person;
    private String account;
    @DBRef (lazy = true)
    private Set<RequestPurchaseItem> items;
    private Date deliveryDate;
    private Date closingDate;
    private String total;
    private String deliveryInstructions;
    @DBRef(lazy = true)
    private List<ResponseToRFQ> responseToRFQ;

    @Override
    public int compareTo(RequestForQuote o) {
        return rfqNumber.compareToIgnoreCase(o.rfqNumber);
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
        final RequestForQuote other = (RequestForQuote) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    private RequestForQuote() {
    }

    private RequestForQuote(RequestForQuote.Builder builder) {
        this.id = builder.id;
        this.person = builder.person;
        this.account = builder.account;
        this.items = builder.items;
        this.deliveryDate = builder.deliveryDate;
        this.rfqNumber = builder.rfqNumber;
        this.total = builder.total;
        this.deliveryInstructions = builder.deliveryInstructions;
        this.responseToRFQ = builder.responseToRFQ;
        this.closingDate = builder.closingDate;
    }

    public static class Builder {

        private String id;
        private String rfqNumber;
        private final Person person;
        private String account;
        private Set<RequestPurchaseItem> items;
        private Date deliveryDate;
        private String total;
        private String deliveryInstructions;
        private List<ResponseToRFQ> responseToRFQ;
        private Date closingDate;

        public Builder(Person value) {
            this.person = value;
        }

        public RequestForQuote.Builder request(RequestForQuote request) {
            this.id = request.getId();
            this.account = request.getAccount();
            this.rfqNumber = request.getRfqNumber();
            this.items = request.getItems();
            this.deliveryDate = request.getDeliveryDate();
            this.closingDate = request.getClosingDate();
            this.deliveryInstructions = request.getDeliveryInstructions();
            this.responseToRFQ = request.getResponseToRFQ();
            return this;
        }

        public Builder closingDate(Date value) {
            this.closingDate = value;
            return this;
        }

        public RequestForQuote.Builder responseToRFQ(List<ResponseToRFQ> value) {
            this.responseToRFQ = value;
            return this;
        }

        public RequestForQuote.Builder id(String value) {
            this.id = value;
            return this;
        }

        public RequestForQuote.Builder deliveryInstructions(String value) {
            this.deliveryInstructions = value;
            return this;
        }

        public RequestForQuote.Builder total(String value) {
            this.total = value;
            return this;
        }

        public RequestForQuote.Builder rfqNumber(String value) {
            this.rfqNumber = value;
            return this;
        }

        public RequestForQuote.Builder deliveryDate(Date value) {
            this.deliveryDate = value;
            return this;
        }

        public RequestForQuote.Builder items(Set<RequestPurchaseItem> value) {
            this.items = value;
            return this;
        }

        public RequestForQuote.Builder account(String value) {
            this.account = value;
            return this;
        }

        public RequestForQuote build() {
            return new RequestForQuote(this);
        }
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public List<ResponseToRFQ> getResponseToRFQ() {
        return responseToRFQ;
    }

    public String getId() {
        return id;
    }

    public String getRfqNumber() {
        return rfqNumber;
    }

    public Person getPerson() {
        return person;
    }

    public String getAccount() {
        return account;
    }

    public Set<RequestPurchaseItem> getItems() {
        return items;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public String getTotal() {
        return total;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }
}
