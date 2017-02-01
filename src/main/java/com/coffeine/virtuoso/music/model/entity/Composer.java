/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

import com.coffeine.virtuoso.main.model.serializer.JsonDateSerializer;
import com.coffeine.virtuoso.security.model.entity.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.util.Assert.notNull;

/**
 * Class for reflect table Composer from persistence layout.
 *
 * @version 1.0
 */
@JsonIgnoreProperties( ignoreUnknown = true )
@Entity
@Table(
    name = "composer",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "id_user"
            }
        )
    }
)
public class Composer implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @JsonBackReference
    @Valid
    @OneToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_user", nullable = true )
    protected User user;

    @NotNull
    @NotEmpty
    @Valid
    @JsonManagedReference
    @OneToMany(
        mappedBy = "composer",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    protected Set<ComposerLocale> data;

    @Transient
    protected String firstName;

    @Transient
    protected String lastName;

    @Transient
    protected String fatherName;

    /**
     * Original locale of composer.
     */
    @NotNull
    @NotEmpty
    @Length( max = 5 )
    @Column( name = "locale", length = 5 )
    protected String locale;

    @Column( name = "gender" )
    protected Boolean gender;

    @JsonSerialize( using = JsonDateSerializer.class )
    @Column( name = "birthday", columnDefinition = "DATE" )
    protected LocalDate birthday;

    //TODO: add validation
    @JsonSerialize( using = JsonDateSerializer.class )
    @Column( name = "death_date", columnDefinition = "DATE" )
    protected LocalDate deathDate;

    @Column(
        name = "creation",
        columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Construct default.
     */
    public Composer() {
        //- Initialization -//
        this.data = new HashSet<>();
    }

    /**
     * Construct for create new composer.
     *
     * @param locale       Locale.
     * @param gender       Gender.
     * @param birthday     Birthday.
     * @param deathDate    Date of death.
     */
    public Composer(
        String locale,
        Boolean gender,
        LocalDate birthday,
        LocalDate deathDate
    ) {
        //- Initialisation -//
        this();
        this.locale = locale;
        this.gender = gender;
        this.birthday = birthday;
        this.deathDate = deathDate;
    }

    /**
     * Construct for create new composer.
     *
     * @param locale       Locale.
     * @param gender       Gender.
     * @param birthday     Birthday.
     * @param deathDate    Date of death
     * @param data         Localized data.
     */
    public Composer(
        final String locale,
        Boolean gender,
        LocalDate birthday,
        LocalDate deathDate,
        Set<ComposerLocale> data
    ) {
        //- Check params -//
        notNull( data );

        //- Initialisation -//
        this.locale = locale;
        this.gender = gender;
        this.birthday = birthday;
        this.deathDate = deathDate;
        this.data = data;

        for ( ComposerLocale composerLocale: this.data ) {
            composerLocale.setComposer( this );
        }
    }

    /**
     * Constructor for create new composer.
     *
     * @param data      Localized data.
     * @param locale    Locale.
     */
    public Composer(
        List<ComposerLocale> data,
        String locale
    ) {
        this();

        //- Set locales -//
        for ( ComposerLocale composerLocale: data ) {
            this.addComposerLocale( composerLocale );
        }
        this.locale = locale;
    }

    /**
     * Create composer with locale.
     *
     * @param locale    Locale.
     */
    public Composer(
        String locale
    ) {
        this();
        this.locale = locale;
    }

    //- SECTION :: GET -//
    /**
     * Get ID of composer.
     *
     * @return Id of composer.
     */
    public Long getId() {
        return id;
    }

    /**
     * Get user-composer.
     *
     * @return User.
     */
    public User getUser() {
        return user;
    }

    /**
     * Get composer's data.
     *
     * @return List of ComposerLocales.
     */
    public Set<ComposerLocale> getData() {
        return data;
    }

    /**
     * Get first name in original locale.
     *
     * @return First name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get last name in origin locale.
     *
     * @return Last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get father name in origin locale.
     *
     * @return Father's name.
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * Get locale of this composer.
     *
     * @return Locale.
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get gender of this composer.
     *
     * @return true - man, false - woman.
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * Get date of birthday.
     *
     * @return Birthday.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Get date of death.
     *
     * @return Date of death.
     */
    public LocalDate getDeathDate() {
        return deathDate;
    }

    /**
     * Get time of create record.
     *
     * @return Time of creating.
     */
    public Calendar getCreation() {
        return creation;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of composer.
     *
     * @param id    Id of composer.
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set user-composer.
     *
     * @param user    User.
     */
    public void setUser( User user ) {
        this.user = user;
    }

    /**
     * Set composer's data.
     *
     * @param data    Localized data.
     */
    public void setData( Set<ComposerLocale> data ) {
        this.data = data;
    }

    /**
     * Set locale of this composer.
     *
     * @param locale    Locale.
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set gender of this composer.
     *
     * @param gender    true - man, false - woman.
     */
    public void setGender( Boolean gender ) {
        this.gender = gender;
    }

    /**
     * Set date of birthday.
     *
     * @param birthday    Birthday.
     */
    public void setBirthday( LocalDate birthday ) {
        this.birthday = birthday;
    }

    /**
     * Set date of death.
     *
     * @param deathDate    Date of death.
     */
    public void setDeathDate( LocalDate deathDate ) {
        this.deathDate = deathDate;
    }

    /**
     * Add unique composer locale.
     *
     * @param composerLocale    Localized data.
     */
    public void addComposerLocale( ComposerLocale composerLocale ) {
        //- Set composer-//
        composerLocale.setComposer( this );

        //- Add Composer locale-//
        if ( !this.data.contains( composerLocale ) ) {
            this.data.add( composerLocale );
        }
    }


    //- SECTION :: HELPER -//
    /**
     * Populate not stored data.
     */
    @PostLoad
    private void postRead() {
        //- Search origin data about composer -//
        this.data.forEach( (composerLocale) -> {
            //- Check origin locale -//
            if (this.locale.equals( composerLocale.getLocale() )) {
                //- Populate data -//
                this.firstName = composerLocale.getFirstName();
                this.lastName = composerLocale.getLastName();
                this.fatherName = composerLocale.getMiddleName();
            }
        });
    }
}
