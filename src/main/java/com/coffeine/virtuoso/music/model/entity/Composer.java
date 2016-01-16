/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

import com.coffeine.virtuoso.security.model.entity.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
@SuppressWarnings( "serial" )
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

    @JsonIgnore
    @JsonBackReference
    @Valid
    @OneToOne
    @JoinColumn( name = "id_user" )
    protected User user;

//    @NotNull
//    @NotEmpty
//    @Size( min = 1 )
//    @Valid
    @JsonManagedReference
    @OneToMany(
        mappedBy = "composer",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    protected List < ComposerLocale > data;

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

    @Column( name = "birthday", columnDefinition = "DATE" )
    protected LocalDate birthday;

    //TODO: add validation
    @Column( name = "deathDate", columnDefinition = "DATE" )
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
    public Composer(
    ) {
        //- Initialization -//
        this.data = new ArrayList<>();
    }

    /**
     * Construct for create new composer.
     *
     * @param birthday
     * @param deathDate
     */
    public Composer(
        String locale,
        Boolean gender,
        LocalDate birthday,
        LocalDate deathDate
    ) {
        //- Initialisation -//
        this.locale = locale;
        this.gender = gender;
        this.birthday = birthday;
        this.deathDate = deathDate;
    }

    /**
     * Construct for create new composer.
     *
     */
    public Composer(
        final String locale,
        Boolean gender,
        LocalDate birthday,
        LocalDate deathDate,
        List < ComposerLocale > data
    ) {
        //- Check params -//
        notNull( data );

        //- Initialisation -//
        this.locale = locale;
        this.gender = gender;
        this.birthday = birthday;
        this.deathDate = deathDate;
        this.data = data;

        for ( ComposerLocale composerLocale : this.data ) {
            composerLocale.setComposer( this );
        }
    }

    /**
     * Constructor for create new composer.
     *
     * @param data
     * @param locale
     */
    public Composer(
        List < ComposerLocale > data,
        String locale
    ) {
        this();

        //- Set locales -//
        for ( ComposerLocale composerLocale : data ) {
            this.addComposerLocale( composerLocale );
        }
        this.locale = locale;
    }

    public Composer(
        String locale
    ) {
        this.data = null;
        this.locale = locale;
    }
    //- SECTION :: GET -//
    /**
     * Get ID of composer.
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get user-composer.
     *
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Get composer's data.
     *
     * @return List<ComposerLocale>
     */
    public List < ComposerLocale > getData() {
        return data;
    }

    /**
     * Get first name in original locale.
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get last name in origin locale.
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get father name in origin locale.
     *
     * @return String
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * Get locale of this composer.
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get gender of this composer.
     *
     * @return Boolean
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * Get date of birthday.
     *
     * @return Calendar
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Get date of death.
     *
     * @return Calendar
     */
    public LocalDate getDeathDate() {
        return deathDate;
    }

    /**
     * Get time of create record.
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of composer.
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set user-composer.
     *
     * @param user
     */
    public void setUser( User user ) {
        this.user = user;
    }

    /**
     * Set composer's data.
     *
     * @param data
     */
    public void setData( List < ComposerLocale > data ) {
        this.data = data;
    }

    /**
     * Set locale of this composer.
     *
     * @param locale
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set gender of this composer.
     *
     * @param gender
     */
    public void setGender( Boolean gender ) {
        this.gender = gender;
    }

    /**
     * Set date of birthday.
     *
     * @param birthday
     */
    public void setBirthday( LocalDate birthday ) {
        this.birthday = birthday;
    }

    /**
     * Set date of death.
     *
     * @param deathDate
     */
    public void setDeathDate( LocalDate deathDate ) {
        this.deathDate = deathDate;
    }

    /**
     * Add unique composer locale.
     *
     * @param composerLocale
     */
    public void addComposerLocale( ComposerLocale composerLocale) {
        //- Set composer-//
        composerLocale.setComposer( this );

        //- Add Composer locale-//
        if( !this.data.contains( composerLocale ) ) {
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
        this.data.forEach( (ComposerLocale composerLocale) -> {
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
