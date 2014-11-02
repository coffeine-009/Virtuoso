/// *** User :: Model :: Entity :: User *** *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-09 22:45:38 :: 2014-04-15 22:48:41
     *
     * @address /Ukraine/Ivano-Frankivsk/Chornovola/104
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class for reflect table from persistence layout
 *
 * @version 1.0
 */
@JsonIgnoreProperties( ignoreUnknown = true )
@SuppressWarnings( "serial" )
@Entity
@Table( name = "user" )
public class User implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @NotNull
    @Valid
    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true )
    @JoinTable(
        name = "user_roles",
        joinColumns = {
            @JoinColumn(
                name = "id_user",
                nullable = false,
                updatable = false
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name = "id_role",
                nullable = false,
                updatable = false
            )
        }
    )
    protected List < Role > roles;

    @JsonIgnore
    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    protected List < Access > access;

    @JsonIgnore
    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    protected List < Email > emails;

    @JsonIgnore
    @Valid
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    protected List < Composer > composers;

    @JsonIgnore
    @Valid
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    protected List < Poet > poets;

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

    @Column( name = "gender" )
    protected Boolean gender;

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
    public User() {
        //- Initialization -//
        this.access = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.composers = new ArrayList<>();
        this.poets = new ArrayList<>();
    }

    //- SECTION :: GET -//
    /**
     * Get ID of song
     *
     * @return Long ID of song
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Get role
     *
     * @return Role
     */
    public List < Role > getRoles() {
        return this.roles;
    }

    /**
     * Get access
     *
     * @return List<Access>
     */
    public List < Access > getAccess() {
        return access;
    }

    /**
     * Get emails for this user
     *
     * @return List<Emails>
     */
    public List < Email > getEmails() {
        return emails;
    }

    /**
     * Get composer's data for this user
     *
     * @return List<Composer>
     */
    public List < Composer > getComposers() {
        return composers;
    }

    /**
     * Get poet's data for this user
     *
     * @return List<Poet>
     */
    public List < Poet > getPoets() {
        return poets;
    }

    /**
     * Get first name
     *
     * @return String
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Get last name
     *
     * @return String
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Get middle name
     *
     * @return String
     */
    public String getMiddleName() {
        return this.middleName;
    }

    /**
     * Get gender
     *
     * @return Boolean true - man, false - woman, null - undefined
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * Get locale code
     *
     * @return String
     */
    public String getLocale() {
        return this.locale;
    }

    /**
     * Get time of create this record
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return this.creation;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of song
     *
     * @param id ID of song
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set role
     *
     * @param roles
     */
    public void setRoles( List < Role > roles ) {
        this.roles = roles;
    }

    /**
     * Set access
     *
     * @param access
     */
    public void setAccess( List < Access > access ) {
        this.access = access;
    }

    /**
     * Set email of this user
     *
     * @param emails
     */
    public void setEmails( List < Email > emails ) {
        this.emails = emails;
    }

    /**
     * Set composer's data of this user
     *
     * @param composers
     */
    public void setComposers( List < Composer > composers ) {
        this.composers = composers;
    }

    /**
     * Set poet's data of this user
     *
     * @param poets
     */
    public void setPoets( List < Poet > poets ) {
        this.poets = poets;
    }

    /**
     * Set first name
     *
     * @param firstName
     */
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    /**
     * Set last name
     *
     * @param lastName
     */
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    /**
     * Set middle name
     *
     * @param middleName
     */
    public void setMiddleName( String middleName ) {
        this.middleName = middleName;
    }

    /**
     * Set gender
     *
     * @param gender true - man, false - woman, null - undefined
     */
    public void setGender( Boolean gender ) {
        this.gender = gender;
    }

    /**
     * Set locale code
     *
     * @param locale Locale code
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }


    //- SECTION :: MAIN -//
    public void addAccess( Access access ) {

        // Set user
        access.setUser( this );

        // Check exists access list
        if ( !this.access.contains( access ) ) {
            // Add a new access for user
            this.access.add( access );
        }
    }
    /**
     * Add a new email
     *
     * @param email
     */
    public void addEmail( Email email ) {

        // Set user
        email.setUser( this );

        // Check exists email list
        if ( !this.emails.contains( email ) ) {
            // Add a new email for user
            this.emails.add( email );
        }
    }

    /**
     * Add a new composer
     *
     * @param composer
     */
    public void addComposer( Composer composer ) {

        // Set user
        composer.setUser( this );

        // Check exists composer list
        if ( !this.composers.contains( composer ) ) {
            // Add a new composer
            this.composers.add( composer );
        }
    }

    /**
     * Add a new poet
     *
     * @param poet
     */
    public void addPoet( Poet poet ) {

        // Set user
        poet.setUser( this );

        // Check exists poet list
        if ( !this.poets.contains( poet ) ) {
            // Add a new poet
            this.poets.add( poet );
        }
    }
}
