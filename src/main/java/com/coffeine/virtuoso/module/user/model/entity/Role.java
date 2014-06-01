/// *** User :: Model :: Entity :: Role *** *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-09 20:58:43 :: 2014-04-09 21:59:21
     *
     * @address /Ukraine/Ivano-Frankivsk/Chornovola/104
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for reflect table role from persistence layout
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( 
    name = "role", 
    uniqueConstraints = @UniqueConstraint( 
        columnNames = {
            "code"
        } 
    ) 
)
public class Role implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @NotNull
    @NotEmpty
    @Size( max = 8 )
    @Column( name = "code", columnDefinition = "VARCHAR( 8 )" )
    protected String code;

    @NotNull
    @NotEmpty
    @Size( max = 32 )
    @Column( name = "title", columnDefinition = "VARCHAR( 32 )" )
    protected String title;

    @Column( name = "description", columnDefinition = "TEXT" )
    protected String description;


    /// *** Methods     *** ///
    /**
     * Default constructor
     */
    public Role() {
        
    }

    /**
     * Constructor
     *
     * @param code
     * @param title
     * @param description 
     */
    public Role(
        String code, 
        String title, 
        String description
    ) {
        //- Initialization -//
        this.code = code;
        this.title = title;
        this.description = description;
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
     * Get code of role
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Get title of role
     *
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get description about role
     *
     * @return String
     */
    public String getDescription() {
        return description;
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
     * Set code of role
     * Ex. ADMIN, MANAGER, USER
     *
     * @param code
     */
    public void setCode( String code ) {
        this.code = code;
    }

    /**
     * Set title of role
     *
     * @param title
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set description of role
     *
     * @param description 
     */
    public void setDescription( String description ) {
        this.description = description;
    }
}
