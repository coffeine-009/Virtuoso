/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/5/15 2:01 PM
 */

package com.coffeine.virtuoso.security.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 * Class for reflect email table from persistence layout.
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
    /**
     * Unique id of contact: e-mail.
     */
    @Id
    @GeneratedValue
    @Column
    protected Long id;

    /**
     * Owner of this contact.
     */
    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_user" )
    protected User user;

    /**
     * E-mail address.
     */
    @NotNull
    @NotEmpty
    @Length( max = 80 )
    @Column( length = 80 )
    protected String address;

    /**
     * Time of crete this contact.
     */
    @Column( columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Default constructor.
     */
    public Email() {

    }

    /**
     * Constructor for create email.
     *
     * @param user       Owner of contact.
     * @param address    E-mail address.
     */
    public Email(
        User user,
        String address
    ) {
        //- Initialization -//
        this.user = user;
        this.address = address;
    }

    /**
     * Create e-mail with out owner.
     *
     * @param address    E-mail address.
     */
    public Email(
        String address
    ) {
        //- Initialization -//
        this.address = address;
    }

    //- SECTION :: GET -//
    /**
     * Get ID of email.
     *
     * @return Long id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Get user, owner of this email.
     *
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Get email address.
     *
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get time of create this email.
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
    }

    //- SECTION :: SET -//
    /**
     * Set ID of email.
     *
     * @param id    Unique id.
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set user, owner of email.
     *
     * @param user    Owner of contact.
     */
    public void setUser( User user ) {
        this.user = user;
    }

    /**
     * Set email address.
     *
     * @param address    E-mail address.
     */
    public void setAddress( String address ) {
        this.address = address;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Email email = ( Email ) o;
        return Objects.equals( id, email.id ) &&
            Objects.equals( address, email.address );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, address );
    }

    @Override
    public String toString() {
        return "Email{" +
            "id=" + id +
            ", user=" + user +
            ", address='" + address + '\'' +
            ", creation=" + creation +
            '}';
    }
}
