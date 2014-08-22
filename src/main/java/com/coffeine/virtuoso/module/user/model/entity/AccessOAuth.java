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
    name = "access_oauth",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {
            "secret_key"
        }
    )
)
public class AccessOAuth implements Serializable {

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
    @Length( min = 256, max = 256 )
    @Column( name = "secret_key")
    protected String secretKey;

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
     * @return User user @link com.coffeine.virtuoso.module.user.model.entity.User
     */
    public User getUser() {
        return user;
    }

    /**
     * Get secret key for access
     *
     * @return String Secret key 256 bytes
     */
    public String getSecretKey() {
        return secretKey;
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
     * @param secretKey Secret key 256 bytes
     */
    public void setSecretKey( String secretKey ) {
        this.secretKey = secretKey;
    }
}
