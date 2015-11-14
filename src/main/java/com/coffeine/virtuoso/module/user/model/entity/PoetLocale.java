/// *** User :: Model :: Entity :: PoetLocale   *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-26 23:31:15 :: 2014-04-27 00:11:40
     *
     * @address /Ukraine/Ivano-Frankivsk/Chornovola/104
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for reflect composer_locale table from persistence layout
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( name = "poet_locale" )
public class PoetLocale implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_poet" )
    protected Poet poet;

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
     * Default constructor
     */
    public PoetLocale() {
        
    }

    /**
     * Create composer locale
     *
     * @param firstName     First name of poet
     * @param lastName      Last name of poet
     * @param locale        Default locale for poet
     */
    public PoetLocale(
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
     * Create composer locale
     *
     * @param firstName     First name of poet
     * @param lastName      Last name of poet
     * @param middleName    Father's name of poet
     * @param locale        Default locale for poet
     */
    public PoetLocale(
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
     * Constructor for create poet locale
     *
     * @param poet
     * @param firstName
     * @param lastName
     * @param middleName
     * @param locale
     */
    public PoetLocale(
        Poet poet,
        String firstName,
        String lastName,
        String middleName,
        String locale
    ) {
        //- Initialization -//
        this.poet = poet;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.locale = locale;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of composer locale data
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get composer for this locale data
     *
     * @return Composer
     */
    public Poet getPoet() {
        return poet;
    }

    /**
     * Get first name for current locale
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get last name for current locale
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get middle name for current locale
     *
     * @return String
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Get locale
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get time of create
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
    }

    //- SECTION :: SET -//
    /**
     * Set ID of composer's data
     *
     * @param id 
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set composer of this data
     *
     * @param poet 
     */
    public void setPoet( Poet poet ) {
        this.poet = poet;
    }

    /**
     * Set first name in choosen locale
     *
     * @param firstName 
     */
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    /**
     * Set last name in choosen locale
     *
     * @param lastName 
     */
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    /**
     * Set middle name in choosen locale
     *
     * @param middleName 
     */
    public void setMiddleName( String middleName ) {
        this.middleName = middleName;
    }

    /**
     * Set locale
     *
     * @param locale 
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }
}
