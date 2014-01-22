/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.util;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class Currency implements Serializable, Comparable<Currency> {

    @Id
    private String id;
    private String code;
    private String name;
    private String symbol;

    private Currency() {
    }

    private Currency(Builder builder) {
        id = builder.id;
        code = builder.code;
        name = builder.name;
        symbol = builder.symbol;
    }

    @Override
    public int compareTo(Currency o) {
        return code.compareToIgnoreCase(o.code);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Currency other = (Currency) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final String code;
        private String name;
        private String symbol;

        public Builder(String value) {
            this.code = value;
        }

        public Builder id(String value) {
            id = value;
            return this;
        }

        public Builder value(String value) {
            name = value;
            return this;
        }

        public Builder symbol(String value) {
            symbol = value;
            return this;
        }

        public Currency build() {
            return new Currency(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
