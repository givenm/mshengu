/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.people;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author boniface
 */
public final class PersonEmploymentHistory implements Serializable {

    private String previousEmployer;
    private Date started;
    private Date ended;

    private PersonEmploymentHistory() {
    }

    private PersonEmploymentHistory(Builder builder) {
        this.previousEmployer = builder.previousEmployer;
        this.started = builder.started;
        this.ended = builder.ended;

    }

    public static class Builder {

        private String previousEmployer;
        private Date started;
        private Date ended;

        public Builder(String value) {
            this.previousEmployer = value;
        }

        public Builder started(Date value) {
            this.started = value;
            return this;
        }

        public Builder ended(Date value) {
            this.ended = value;
            return this;
        }

        public PersonEmploymentHistory build() {
            return new PersonEmploymentHistory(this);
        }
    }

    public String getPreviousEmployer() {
        return previousEmployer;
    }

    public Date getStarted() {
        return started;
    }

    public Date getEnded() {
        return ended;
    }
}
