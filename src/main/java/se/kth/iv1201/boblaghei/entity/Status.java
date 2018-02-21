package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * O/R Mapping of the table Status in the database.
 */
@Entity
public class Status {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Translation> translations = new HashSet<>();

    public Status() {
    }

    public Status(String name) {
        addTranslation(Translation.DEFAULT_LOCALE, name);
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Set<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }

    public Status addTranslation(String locale, String value) {
        translations.add(new Translation(locale, value));
        return this;
    }

    public String getTranslation(String locale) {
        for (Translation translation : translations)
            if (translation.getLocale().equals(locale))
                return translation.getValue();
        for (Translation translation : translations)
            if (translation.getLocale().equals(Translation.DEFAULT_LOCALE))
                return translation.getValue();
        return "N/A";
    }


}
