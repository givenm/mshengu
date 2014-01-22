/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.procurement;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Luckbliss
 */
@Document
public class QuoteNumber implements Serializable, Comparable<QuoteNumber>{
    @Id
    private String id;
    private Long quoteNumber;

    private QuoteNumber() {
    }

    private QuoteNumber(QuoteNumber.Builder builder) {
        this.id = builder.id;
        this.quoteNumber = builder.orderNumber;
    }

    public static class Builder {

        private String id;
        private final Long orderNumber;

        public Builder(Long value) {
            this.orderNumber = value;
        }

        public QuoteNumber.Builder id(String value) {
            this.id = value;
            return this;
        }

        public QuoteNumber build() {
            return new QuoteNumber(this);
        }
    }

    @Override
    public int compareTo(QuoteNumber o) {
        return getQuoteNumber().compareTo(o.getQuoteNumber());
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
        final QuoteNumber other = (QuoteNumber) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public Long getQuoteNumber() {
        return quoteNumber;
    }
}
