/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.people;

import java.io.Serializable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import zm.hashcode.mshengu.domain.ui.demographics.IdentificationType;

/**
 *
 * @author boniface
 */
public final class PersonIdentities implements Serializable {

    @DBRef
    private IdentificationType identificationType;
    @Indexed(unique = true)
    private String idValue;

    private PersonIdentities() {
    }

    private PersonIdentities(Builder builder) {
        this.identificationType = builder.identificationType;
        this.idValue = builder.idValue;

    }

    /**
     * @return the identificationType
     */
    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    /**
     * @return the idValue
     */
    public String getIdValue() {
        return idValue;
    }

    public static class Builder {

        private IdentificationType identificationType;
        private String idValue;

        public Builder(IdentificationType value) {
            this.identificationType = value;
        }

        public Builder idValue(String value) {
            this.idValue = value;
            return this;
        }

        public Builder personIdentities(PersonIdentities personIdentities) {
            this.identificationType = personIdentities.getIdentificationType();
            this.idValue = personIdentities.getIdValue();
            return this;
        }

        public PersonIdentities build() {
            return new PersonIdentities(this);
        }
    }
}
