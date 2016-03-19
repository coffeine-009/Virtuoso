/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/5/15 11:47 AM
 */

package com.coffeine.virtuoso.security.model.entity;

import com.coffeine.virtuoso.music.model.entity.Composer;
import com.coffeine.virtuoso.music.model.entity.Poet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.util.Assert.notNull;

/**
 * Class for reflect table from persistence layout.
 *
 * @version 1.0
 */
@JsonIgnoreProperties( ignoreUnknown = true )
@SuppressWarnings( "serial" )
@Entity
@Table( name = "user" )
public class User implements Serializable {

    /// *** Properties  *** ///
    /**
     * Unique id of user.
     * Primary key.
     */
    @Id
    @GeneratedValue
    @Column
    protected Long id;

    /**
     * List of roles assigned to user.
     */
    @NotNull
    @Valid
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
        name = "user_roles", 
        uniqueConstraints = {
            @UniqueConstraint(
                columnNames = {
                    "id_user", 
                    "id_role"
                }
            )
        }, 
        joinColumns = {
            @JoinColumn(
                name = "id_user",
                unique = false, 
                nullable = false,
                updatable = false
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name = "id_role", 
                unique = false, 
                nullable = false,
                updatable = false
            )
        }
    )
    protected List<Role> roles = new ArrayList<>();

    /**
     * List of accesses.
     */
    @JsonIgnore
    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    protected List<Access> access = new ArrayList<>();

    /**
     * List of e-mails.
     */
    @JsonIgnore
    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    protected List<Email> emails = new ArrayList<>();

    /**
     * Composer If user has role COMPOSER.
     */
    @JsonManagedReference
    @Valid
    @OneToOne(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    @PrimaryKeyJoinColumn
    protected Composer composer;

    /**
     * Poet if User has role POET.
     */
    @JsonManagedReference
    @Valid
    @OneToOne( 
        fetch = FetchType.EAGER, 
        cascade = CascadeType.ALL, 
        orphanRemoval = false
    )
    @PrimaryKeyJoinColumn
    protected Poet poet;

    /**
     * First name.
     */
    @NotNull
    @NotEmpty
    @Length( max = 16 )
    @Column( name = "first_name", length = 16 )
    protected String firstName;

    /**
     * Last name.
     */
    @Length( max = 16 )
    @Column( name = "last_name", length = 16 )
    protected String lastName;

    /**
     * Middle name.
     */
    @Length( max = 32 )
    @Column( name = "middle_name", length = 32 )
    protected String middleName;

    /**
     * Gender. True - man, false - woman, null - unknown.
     */
    @Column
    protected Boolean gender;

    /**
     * Code of user's locale.
     */
    @NotNull
    @NotEmpty
    @Length( max = 5 )
    @Column( length = 5 )
    protected String locale;

    /**
     * Time of registration.
     */
    @Column( columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Default constructor.
     */
    public User() {
        //- Initialization -//
    }

    /**
     * Constructor for create user.
     *
     * @param roles         List of roles
     * @param email         Email
     * @param firstName     First name
     * @param lastName      Last name
     * @param middleName    Father's name
     * @param locale        Default locale
     */
    public User(
        List < Role > roles,
        Email email,
        String firstName,
        String lastName,
        String middleName,
        String locale
    ) {
        //- Call default constructor -//
        this();

        //- Initialization -//
        this.roles = roles;
        this.addEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.locale = locale;
    }

    /**
     * Constructor for create user.
     *
     * @param roles         List of roles
     * @param access        List of permissions
     * @param email         Email
     * @param firstName     First name
     * @param lastName      Last name
     * @param middleName    Father's name
     * @param locale        Default locale
     */
    public User(
        List<Role> roles,
        Access access,
        Email email,
        String firstName,
        String lastName,
        String middleName,
        String locale
    ) {
        //- Call default constructor -//
        this();

        //- Initialization -//
        this.roles = roles;
        this.addAccess( access );
        this.addEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.locale = locale;
    }

    /**
     * Constructor for create user.
     *
     * @param roles         List of roles
     * @param access        List of permissions
     * @param email         Email
     * @param firstName     First name
     * @param lastName      Last name
     * @param gender        Gender
     * @param locale        Default locale
     */
    public User(
        List<Role> roles,
        Access access,
        Email email,
        String firstName,
        String lastName,
        Boolean gender,
        String locale
    ) {
        //- Call default constructor -//
        this();

        //- Initialization -//
        this.roles = roles;
        this.addAccess( access );
        this.addEmail( email );
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.locale = locale;
    }

    /**
     * Constructor for create user.
     *
     * @param roles        List of roles
     * @param firstName    First name
     * @param locale       Default locale
     */
    public User(
        List<Role> roles,
        String firstName,
        String locale
    ) {
        //- Call default constructor -//
        this();

        //- Initialization -//
        this.roles = roles;
        this.access = null;
        this.emails = null;
        this.firstName = firstName;
        this.locale = locale;
    }

    /**
     * Create user only with role list.
     *
     * @param roles    List of roles
     */
    public User( List<Role> roles ) {
        this.roles = roles;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of song.
     *
     * @return Long ID of song
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Get role.
     *
     * @return Role
     */
    public List<Role> getRoles() {
        return this.roles;
    }

    /**
     * Get access.
     *
     * @return List of access
     */
    public List<Access> getAccess() {
        return access;
    }

    /**
     * Get emails for this user.
     *
     * @return List of emails
     */
    public List<Email> getEmails() {
        return emails;
    }

    /**
     * Get composer's data if this user is composer.
     *
     * @return Composer
     */
    public Composer getComposer() {
        return composer;
    }

    /**
     * Get poet's data if this user is poet.
     *
     * @return Poet
     */
    public Poet getPoet() {
        return poet;
    }

    /**
     * Get first name.
     *
     * @return String
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Get last name.
     *
     * @return String
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Get middle name.
     *
     * @return String
     */
    public String getMiddleName() {
        return this.middleName;
    }

    /**
     * Get gender.
     *
     * @return Boolean true - man, false - woman, null - unknown.
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * Get locale code.
     *
     * @return String
     */
    public String getLocale() {
        return this.locale;
    }

    /**
     * Get time of create this record.
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return this.creation;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of song.
     *
     * @param id ID of song
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set role.
     *
     * @param roles
     */
    public void setRoles( List<Role> roles ) {
        this.roles = roles;
    }

    /**
     * Set access.
     *
     * @param access
     */
    public void setAccess( List<Access> access ) {
        this.access = access;
    }

    /**
     * Set email of this user.
     *
     * @param emails
     */
    public void setEmails( List<Email> emails ) {
        this.emails = emails;
    }

    /**
     * Set composer's data.
     *
     * @param composer
     */
    public void setComposer( Composer composer ) {
        //- Check params -//
        notNull( composer );

        //- Set composer -//
        this.composer = composer;

        //- Set link -//
        this.composer.setUser( this );
    }

    /**
     * Set poet's data.
     *
     * @param poet
     */
    public void setPoet( Poet poet ) {
        //- Check params -//
        notNull( poet );

        //- Set poet -//
        this.poet = poet;

        //- Set link -//
        this.poet.setUser( this );
    }

    /**
     * Set first name.
     *
     * @param firstName
     */
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    /**
     * Set last name.
     *
     * @param lastName
     */
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    /**
     * Set middle name.
     *
     * @param middleName
     */
    public void setMiddleName( String middleName ) {
        this.middleName = middleName;
    }

    /**
     * Set gender.
     *
     * @param gender true - man, false - woman, null - undefined
     */
    public void setGender( Boolean gender ) {
        this.gender = gender;
    }

    /**
     * Set locale code.
     *
     * @param locale Locale code
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }


    //- SECTION :: MAIN -//
    /**
     * Add access for user.
     *
     * @param access    Access for user.
     */
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
     * Add a new email.
     *
     * @param email    E-mail address.
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


    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        User user = ( User ) o;
        return Objects.equals( id, user.id ) &&
            Objects.equals( roles, user.roles ) &&
            Objects.equals( emails, user.emails ) &&
            Objects.equals( firstName, user.firstName ) &&
            Objects.equals( lastName, user.lastName ) &&
            Objects.equals( middleName, user.middleName ) &&
            Objects.equals( gender, user.gender ) &&
            Objects.equals( locale, user.locale );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, roles, emails, firstName, lastName, middleName, gender, locale );
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", roles=" + roles +
            ", access=" + access +
            ", emails=" + emails +
            ", composer=" + composer +
            ", poet=" + poet +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", gender=" + gender +
            ", locale='" + locale + '\'' +
            ", creation=" + creation +
            '}';
    }
}
