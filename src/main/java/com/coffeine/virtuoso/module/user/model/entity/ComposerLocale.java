/// *** User :: Model :: Entity :: ComposerLocale   *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-26 09:41:34 :: 2014-04-26 10:24:20
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
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for reflect composer_locale table from persistence layout
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

    @NotNull
    @Valid
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_composer", columnDefinition = "BIGINT( 20 )" )
    protected Composer composer;

    @NotNull
    @NotEmpty
    @Size( max = 16 )
    @Column( name = "first_name", columnDefinition = "VARCHAR( 16 )" )
    protected String firstName;

    @Size( max = 16 )
    @Column( name = "last_name", columnDefinition = "VARCHAR( 16 )" )
    protected String lastName;

    @Size( max = 32 )
    @Column( name = "middle_name", columnDefinition = "VARCHAR( 32 )" )
    protected String middleName;

    @NotNull
    @NotEmpty
    @Size( max = 5 )
    @Column( name = "locale", columnDefinition = "VARCHAR( 5 )" )
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
    public ComposerLocale() {
        
    }

    /**
     * Create composer locale
     *
     * @param firstName
     * @param lastName
     * @param middleName
     * @param locale 
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
    public Composer getComposer() {
        return composer;
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
     * @param composer 
     */
    public void setComposer( Composer composer ) {
        this.composer = composer;
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
