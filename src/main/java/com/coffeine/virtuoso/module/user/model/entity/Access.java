/// *** User :: Model :: Entity :: AccessOAuth  *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-08-22 15:47:43 :: 2014-08-22 16:12:47
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @version 1.0
 */
@Entity
@Table(
    name = "access"
)
public class Access implements Serializable {

    /// *** Properties  *** ///
    /**
     * Unique identificator of access for user
     */
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected  Long id;

    /**
     * User, owner of this access
     */
    @Valid
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_user" )
    protected User user;

    /**
     * Secret key for get access
     */
    @NotNull
    @NotEmpty
    @Length( max = 256 )
    @Column( name = "password" )
    protected String password;

    /**
     * Date and time of last modification
     */
    @Column( name = "modification" )
    protected Timestamp modification;

    /**
     * Date and time of create this access
     */
    @Column( name = "creation" )
    protected Timestamp creation;


    /// *** Methods     *** ///

    /**
     * Default constructor
     */
    public Access() {

    }

    /**
     * Constructor for create access
     *
     * @param password
     */
    public Access( String password ) {
        //- Initialization -//
        this.password = password;
    }


    //- SECTION :: GET -//
    /**
     * Get ID
     *
     * @return Long ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Get user
     *
     * @return User user @see com.coffeine.virtuoso.module.user.model.entity.User
     */
    public User getUser() {
        return user;
    }

    /**
     * Get secret key for access
     *
     * @return String Secret key 256 bytes
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get last modification
     *
     * @return Timestamp
     */
    public Timestamp getModification() {
        return modification;
    }

    /**
     * Get creation time
     *
     * @return Timestamp
     */
    public Timestamp getCreation() {
        return creation;
    }


    //- SECTION :: SET -//
    /**
     * Set ID
     *
     * @param id Unique identificator
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set user. Owner of this access key
     *
     * @param user User is owner of this access
     */
    public void setUser( User user ) {
        this.user = user;
    }

    /**
     * Set secret key
     *
     * @param password Secret key 256 bytes
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
