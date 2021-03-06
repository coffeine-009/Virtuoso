/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Class for reflect composer_locale table from persistence layout.
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( name = "composer_locale" )
public class ComposerLocale implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @JsonBackReference
    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_composer" )
    protected Composer composer;

    @NotNull
    @NotEmpty
    @Length( max = 16 )
    @Column( name = "first_name", length = 16 )
    protected String firstName;

    @Length( max = 16 )
    @Column( name = "last_name", length = 16 )
    protected String lastName;

    @Length( max = 32 )
    @Column( name = "middle_name", length = 32 )
    protected String middleName;

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    @Column( name = "locale", length = 5 )
    protected String locale;

    @Column( 
        name = "creation", 
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" 
    )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Default constructor.
     */
    public ComposerLocale() {
        //- Default constructor -//
    }

    /**
     * Create composer locale.
     *
     * @param firstName    Localized first name.
     * @param lastName     Localized last name.
     * @param locale       Locale.
     */
    public ComposerLocale(
        String firstName,
        String lastName,
        String locale
    ) {
        //- Initialization -//
        this.firstName = firstName;
        this.lastName = lastName;
        this.locale = locale;
    }

    /**
     * Create composer locale.
     *
     * @param firstName     First name.
     * @param lastName      Last name.
     * @param middleName    Father's name.
     * @param locale        Locale.
     */
    public ComposerLocale(
        String firstName, 
        String lastName, 
        String middleName, 
        String locale
    ) {
        //- Initialization -//
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.locale = locale;
    }

    /**
     * Constructor for create a composer locale.
     *
     * @param composer      Composer.
     * @param firstName     First name.
     * @param lastName      Last name.
     * @param middleName    Father's name.
     * @param locale        Locale.
     */
    public ComposerLocale(
        Composer composer,
        String firstName,
        String lastName,
        String middleName,
        String locale
    ) {
        //- Initialization -//
        this.composer = composer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.locale = locale;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of composer locale data.
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get composer for this locale data.
     *
     * @return Composer
     */
    public Composer getComposer() {
        return composer;
    }

    /**
     * Get first name for current locale.
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get last name for current locale.
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get middle name for current locale.
     *
     * @return String
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Get locale.
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get time of create.
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
    }

    //- SECTION :: SET -//
    /**
     * Set ID of composer's data.
     *
     * @param id    Id of composer's data.
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set composer of this data.
     *
     * @param composer    Composer.
     */
    public void setComposer( Composer composer ) {
        this.composer = composer;
    }

    /**
     * Set first name in chosen locale.
     *
     * @param firstName    First name.
     */
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    /**
     * Set last name in choosen locale.
     *
     * @param lastName    Last name.
     */
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    /**
     * Set middle name in chosen locale.
     *
     * @param middleName    Father's name.
     */
    public void setMiddleName( String middleName ) {
        this.middleName = middleName;
    }

    /**
     * Set locale.
     *
     * @param locale    Locale.
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }
}
