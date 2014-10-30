/// *** User :: Model :: Entity :: Email    *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-24 21:52:26 :: 2014-04-24 22:12:52
     *
     * @address /Ukraine/Ivano-Frankivsk/Chornovola/104
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for reflect email table from persistence layout
 *
 * @version 1.0
 */
@JsonIgnoreProperties( ignoreUnknown = true )
@SuppressWarnings( "serial" )
@Entity
@Table(
    name = "email",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {
            "address"
        }
    )
)
public class Email implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_user" )
    protected User user;

    @NotNull
    @NotEmpty
    @Length( max = 80 )
    @Column( name = "address", length = 80 )
    protected String address;

    @Column(
        name = "creation",
        columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Default constructor
     */
    public Email() {

    }

    public Email(
        String address
    ) {
        //- Initialization -//
        this.address = address;
    }

    //- SECTION :: GET -//
    /**
     * Get ID of email
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get user, owner of this email
     *
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Get email address
     *
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get time of create this email
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
    }

    //- SECTION :: SET -//
    /**
     * Set ID of email
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set user, owner of email
     *
     * @param user
     */
    public void setUser( User user ) {
        this.user = user;
    }

    /**
     * Set email address
     *
     * @param address
     */
    public void setAddress( String address ) {
        this.address = address;
    }
}
