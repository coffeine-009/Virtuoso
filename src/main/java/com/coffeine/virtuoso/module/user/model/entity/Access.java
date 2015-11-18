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
import java.util.Date;

/**
 * Entity for store access information.
 *
 * @version 1.0
 */
@Entity
@Table(
    name = "access"
)
public class Access implements Serializable {

    /// *** Properties  *** ///
    /**
     * Unique id of access for user
     */
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected  Long id;

    /**
     * User, owner of this access
     */
    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_user" )
    protected User user;

    /**
     * Hash of secret password for get access
     */
    @NotNull
    @NotEmpty
    @Length( max = 256 )
    @Column( name = "password" )
    protected String password;

    /**
     * Date and time of last modification
     */
    @Column(
        name = "modification",
        columnDefinition = "TIMESTAMP NULL"
    )
    protected Timestamp modification;

    /**
     * Date and time of create this access
     */
    @Column(
        name = "creation",
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected Timestamp creation;


    /// *** Methods     *** ///
    /**
     * Default constructor
     */
    public Access() {
        // Initialization
    }

    /**
     * Constructor for create access
     *
     * @param password    Hash of password
     */
    public Access( String password ) {
        //- Initialization -//
        this.password = password;
    }

    /**
     * Constructor for create access
     *
     * @param user        User, owner of access
     * @param password    Hash of password
     */
    public Access(
        User user,
        String password
    ) {
        //- Initialition -//
        this.user = user;
        this.password = password;

        //- Set relation -//
        this.user.addAccess( this );
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

    //- SECTION :: HELPER -//
    /**
     * Update modification time before save.
     */
    @PreUpdate
    protected void onUpdate() {
        this.modification = new Timestamp( new Date().getTime() );
    }
}
