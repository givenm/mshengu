/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.external;

import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.ui.util.Sequence;

/**
 *
 * @author Ferox
 */
@Document
public class MailNotifications implements Serializable, Comparable<MailNotifications> {

    @Id
    private String id;
    private String name;
    private List<String> emailList = new ArrayList<>();
    @DBRef
    private Sequence sequence;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the emailList
     */
    public List<String> getEmailList() {
        
        return ImmutableList.copyOf(emailList);
//        return emailList;
    }

    @Override
    public int compareTo(MailNotifications o) {
        return getName().compareTo(o.getName());
    }

    public MailNotifications(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.emailList = builder.emailList;
        this.sequence = builder.sequence;
    }

    /**
     * @return the sequence
     */
    public Sequence getSequence() {
        return sequence;
    }

    private MailNotifications (){
        
    }
    
    public static class Builder {

        private String id;
        private final String name;
        private List<String> emailList = new ArrayList<>();
        private Sequence sequence;

        public Builder mailNotifications(MailNotifications mailNotifications) {
            this.id = mailNotifications.getId();
//            this.name = mailNotifications.name;
            this.emailList = mailNotifications.getEmailList();

            this.sequence = mailNotifications.getSequence();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder(String name) {
            this.name = name;
//            return this;
        }

        public Builder emailList(List<String> value) {
            this.emailList = value;
            return this;
        }

        public Builder sequence(Sequence value) {
            this.sequence = value;
            return this;
        }

        public MailNotifications build() {
            return new MailNotifications(this);
        }
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getSequenceId() {
        if (!isNullObject(sequence)) {
            return sequence.getId();
        } else {
            return null;
        }
    }

    public String getSequenceName() {
        if (!isNullObject(sequence)) {
            return sequence.getName();
        } else {
            return null;
        }
    }

    public String getSequenceNameCode() {
        if (!isNullObject(sequence)) {
            return sequence.getNamingCode();
        } else {
            return null;
        }
    }

    public int getSequenceValue() {
        if (!isNullObject(sequence)) {
            return sequence.getValue();
        } else {
            return 0;
        }
    }
}
