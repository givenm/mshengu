/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.education;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class CompetencyType implements Serializable, Comparable<CompetencyType> {

    @Id
    private String id;
    // Computer Skills, Client Interaction and Accounting based on Company Competeancy Model
    private String name;
 

    private CompetencyType() {
    }

    private CompetencyType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        
    }

    public static class Builder {

        private String id;
        // Computer Skills, Client Interaction and Accounting based on Company Competeancy Model
        private final String name;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public CompetencyType build() {
            return new CompetencyType(this);
        }
    }

    @Override
    public int compareTo(CompetencyType o) {
        return name.compareToIgnoreCase(o.name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final CompetencyType other = (CompetencyType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    
}
