/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.entity;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


/**
 * Class for reflect table style from persistence layout.
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

    @Column( name = "description" )
    @Type( type = "text" )
    protected String description;


    /// *** Methods     *** ///
    /**
     * Default constructor.
     */
    public Style() {
        
    }

    /**
     * Constructor.
     *
     * @param code           Code.
     * @param title          Title.
     * @param description    Description.
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
     * Constructor.
     *
     * @param code     Code.
     * @param title    Title.
     */
    public Style(
        String code,
        String title
    ) {
        this.code = code;
        this.title = title;
    }

    /**
     * Constructor for create new song.
     *
     * @param id             Id.
     * @param code           Code.
     * @param title          Title.
     * @param description    Description.
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
     * Get ID of style.
     *
     * @return Long ID of style.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Get code of style.
     *
     * @return String.
     */
    public String getCode() {
        return code;
    }

    /**
     * Get title of style.
     *
     * @return String.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get description about style.
     *
     * @return String.
     */
    public String getDescription() {
        return description;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of style.
     *
     * @param id ID of style.
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set code of style.
     * Ex. POLKA, WALTZ, DISCO
     *
     * @param code    Code.
     */
    public void setCode( String code ) {
        this.code = code;
    }

    /**
     * Set title of style.
     *
     * @param title    Title.
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set description of style.
     *
     * @param description Description.
     */
    public void setDescription( String description ) {
        this.description = description;
    }
}
