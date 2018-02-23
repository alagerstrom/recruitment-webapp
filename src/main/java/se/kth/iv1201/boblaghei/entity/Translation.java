package se.kth.iv1201.boblaghei.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Translation
 *
 * Entity class that represents a string translated to a specific locale.
 * A translation has one locale, for example 'de', and a value that represents the translation of a word to that language.
 */
@Entity
public class Translation {

    public static final String DEFAULT_LOCALE = "en";

    @Id
    @GeneratedValue
    private long id;

    @Column
    @NotNull
    private String locale;

    @Column
    @NotNull
    private String value;

    public Translation() {
    }

    public Translation(String locale, String value) {
        this.locale = locale;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
