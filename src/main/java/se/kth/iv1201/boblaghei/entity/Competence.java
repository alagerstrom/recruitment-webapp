package se.kth.iv1201.boblaghei.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * O/R Mapping of the table Competence in the database.
 */
@Entity
public class Competence {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Translation> translations = new HashSet<>();

    @Column
    @NotNull
    private String name;

    public Competence() {
    }

    public Competence(String name) {
        addTranslation(Translation.DEFAULT_LOCALE, name);
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Translation> getTranslations() {
        return translations;
    }

    public Competence addTranslation(String locale, String value) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competence that = (Competence) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
