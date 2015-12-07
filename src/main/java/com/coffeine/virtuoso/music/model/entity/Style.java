/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Model :: Entity :: Style    *** *** *** *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.music.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Class for reflect table style from persistence layout
 *
 * @version 1.0
 */
@Entity
@Table( 
    name = "style", 
    uniqueConstraints = @UniqueConstraint( 
        columnNames = {
            "code"
        } 
    ) 
)
public class Style implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @NotNull
    @NotEmpty
    @Length( max = 32 )
    @Column( name = "code", length = 32 )
    protected String code;

    @NotNull
    @NotEmpty
    @Length( max = 32 )
    @Column( name = "title", length = 32 )
    protected String title;

    @Column( name = "description", columnDefinition = "TEXT" )
    protected String description;


    /// *** Methods     *** ///
    /**
     * Default constructor
     */
    public Style() {
        
    }

    /**
     * Constructor
     *
     * @param code
     * @param title
     * @param description 
     */
    public Style(
        String code, 
        String title, 
        String description
    ) {
        //- Initialization -//
        this.code = code;
        this.title = title;
        this.description = description;
    }

    /**
     * Constructor
     * @param code
     * @param title
     */
    public Style(
        String code,
        String title
    ) {
        this.code = code;
        this.title = title;
    }

    /**
     * Constructor for create new song
     *
     * @param id
     * @param code
     * @param title
     * @param description
     */
    public Style(
        Long id,
        String code,
        String title,
        String description
    ) {
        //- Initialization -//
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
    }

    //- SECTION :: GET -//
    /**
     * Get ID of style
     *
     * @return Long ID of style
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Get code of style
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Get title of style
     *
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get description about style
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of style
     *
     * @param id ID of style
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set code of style
     * Ex. POLKA, WALTZ, DISCO
     *
     * @param code
     */
    public void setCode( String code ) {
        this.code = code;
    }

    /**
     * Set title of style
     *
     * @param title
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set description of style
     *
     * @param description 
     */
    public void setDescription( String description ) {
        this.description = description;
    }
}
