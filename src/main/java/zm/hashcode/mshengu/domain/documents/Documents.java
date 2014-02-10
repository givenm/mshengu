/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.documents;

import com.vaadin.ui.Upload;
import java.io.Serializable;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Luckbliss
 */
@Document
public class Documents implements Serializable, Comparable<Documents> {

    @Id
    private String id;
    private String name;
    private String category;
    private String description;
    private String url;
    private Set<Upload> uploads;

    private Documents() {
    }

    private Documents(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.category = builder.category;
        this.description = builder.description;
        this.uploads = builder.uploads;
        this.url = builder.url;
    }

    @Override
    public int compareTo(Documents o) {
        return name.compareToIgnoreCase(o.name);
    }

    public static class Builder {

        private String id;
        private final String name;
        private String category;
        private String description;
        private Set<Upload> uploads;
        private String url;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }
        
        public Builder url(String value) {
            this.url = value;
            return this;
        }


        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public Builder category(String value) {
            this.category = value;
            return this;
        }

        public Builder uploads(Set<Upload> value) {
            this.uploads = value;
            return this;
        }

        public Documents build() {
            return new Documents(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Set<Upload> getUpload() {
        return uploads;
    }

    public String getUrl() {
        return url;
    }    
}
