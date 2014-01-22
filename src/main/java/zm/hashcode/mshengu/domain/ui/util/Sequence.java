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
public final class Sequence implements Serializable, Comparable<Sequence> {

    @Id
    private String id;
    private String name;
    private String namingCode;
    private int value;
    private SequenceType sequenceType;

    private Sequence() {
    }

    private Sequence(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.namingCode = builder.namingCode;
        this.value = builder.value;
        this.sequenceType = builder.sequenceType;
    }

    @Override
    public int compareTo(Sequence o) {
        return name.compareToIgnoreCase(o.name);
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
        final Sequence other = (Sequence) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the namingCode
     */
    public String getNamingCode() {
        return namingCode;
    }

    /**
     * @return the sequenceType
     */
    public SequenceType getSequenceType() {
        return sequenceType;
    }

    public static class Builder {

        private String id;
        private final String name;
        private String namingCode;
        private SequenceType sequenceType;
        private int value;

        public Builder(String value) {
            this.name = value;
        }

        public Builder sequence(Sequence sequence) {
            this.id = sequence.getId();
            this.namingCode = sequence.getNamingCode();

            this.value = sequence.getValue();
            this.sequenceType = sequence.getSequenceType();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder namingCode(String value) {
            this.namingCode = value;
            return this;
        }

        public Builder sequenceType(SequenceType value) {
            this.sequenceType = value;
            return this;
        }

        public Builder value(int value) {
            this.value = value;
            return this;
        }

        public Sequence build() {
            return new Sequence(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public String getSequenceTypeName() {
        if (!isNullObject(sequenceType)) {
            return sequenceType.getName();
        } else {
            return null;
        }
    }

    public String getSequenceTypeId() {
        if (!isNullObject(sequenceType)) {
            return sequenceType.getId();
        } else {
            return null;
        }
    }


}
