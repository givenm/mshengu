/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.people;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.ui.demographics.Gender;
import zm.hashcode.mshengu.domain.ui.demographics.Language;
import zm.hashcode.mshengu.domain.ui.demographics.LanguageProficiency;
import zm.hashcode.mshengu.domain.ui.demographics.MaritalStatus;
import zm.hashcode.mshengu.domain.ui.demographics.Race;
import zm.hashcode.mshengu.domain.ui.demographics.Title;

/**
 *
 * @author boniface
 */
@Document
public final class PersonDemography implements Serializable {

    @DBRef
    private Gender gender;
    @DBRef
    private Language language;
    @DBRef
    private LanguageProficiency languageProficiency;
    @DBRef
    private MaritalStatus maritalStatus;
    @DBRef
    private Title title;
    @DBRef
    private Race race;

    private PersonDemography() {
    }

    private PersonDemography(Builder builder) {
        this.gender = builder.gender;
        this.language = builder.language;
        this.languageProficiency = builder.languageProficiency;
        this.maritalStatus = builder.maritalStatus;
        this.title = builder.title;
        this.race = builder.race;
    }

    public static class Builder {

        private final Gender gender;
        private Language language;
        private LanguageProficiency languageProficiency;
        private MaritalStatus maritalStatus;
        private Title title;
        private Race race;

        public Builder personDemography(PersonDemography personDemography) {
//            this.gender = personDemography.getGender();
            this.language = personDemography.getLanguage();
            this.languageProficiency = personDemography.getLanguageProficiency();
            this.maritalStatus = personDemography.getMaritalStatus();
            this.title = personDemography.getTitle();
            this.race = personDemography.getRace();
            return this;
        }

        public Builder(Gender value) {
            this.gender = value;
        }

        public Builder language(Language value) {
            this.language = value;
            return this;
        }

        public Builder languageProficiency(LanguageProficiency value) {
            this.languageProficiency = value;
            return this;
        }

        public Builder maritalStatus(MaritalStatus value) {
            this.maritalStatus = value;
            return this;
        }

        public Builder title(Title value) {
            this.title = value;
            return this;
        }

        public Builder race(Race value) {
            this.race = value;
            return this;
        }

        public PersonDemography build() {
            return new PersonDemography(this);
        }
    }

    public Gender getGender() {
        return gender;
    }

    public Language getLanguage() {
        return language;
    }

    public LanguageProficiency getLanguageProficiency() {
        return languageProficiency;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Title getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }
}
