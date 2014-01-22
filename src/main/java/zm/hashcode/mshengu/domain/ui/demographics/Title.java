/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.demographics;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author SongezoM
 */
@Document
public final class Title implements Serializable, Comparable<Title> {

    @Id
    private String id;
    private String title;

    private Title() {
    }

    private Title(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
    }

    @Override
    public int compareTo(Title o) {
        return title.compareToIgnoreCase(o.title);
    }

    public static class Builder {

        private String id;
        //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
        private final String title;

        public Builder(String value) {
            this.title = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Title build() {
            return new Title(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Title)) {
            return false;
        }
        Title other = (Title) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
