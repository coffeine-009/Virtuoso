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

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for reflect table from persistence layout
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( name = "user" )
public class User implements Serializable {

    /// *** Properties  *** ///
    @Id
    @Column( name = "id" )
    protected Long id;

    @Valid
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_role", columnDefinition = "BIGINT( 20 ) NOT NULL" )
    protected Role role;

    @Valid
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "user" )
    protected List < Composer > composers;

    @Valid
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "user" )
    protected List < Poet > poets;

    @NotNull
    @NotEmpty
    @Size( max = 16 )
    @Column( name = "first_name", columnDefinition = "VARCHAR( 16 ) NOT NULL" )
    protected String firstName;

    @Size( max = 16 )
    @Column( name = "last_name", columnDefinition = "VARCHAR( 16 )" )
    protected String lastName;

    @Size( max = 32 )
    @Column( name = "middle_name", columnDefinition = "VARCHAR( 32 )" )
    protected String middleName;

    @Column( name = "gender" )
    protected Boolean gender;

    @NotNull
    @NotEmpty
    @Size( max = 5 )
    @Column( name = "locale", columnDefinition = "VARCHAR( 5 ) NOT NULL" )
    protected String locale;

    @Column( 
        name = "creation", 
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" 
    )
    protected Calendar creation;


    /// *** Methods     *** ///

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
    public Role getRole() {
        return this.role;
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
     * @param role
     */
    public void setRole( Role role ) {
        this.role = role;
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
}
