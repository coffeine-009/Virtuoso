/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

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
 * Class for reflect table VideoType from persistence layout.
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( 
    name = "video_type", 
    uniqueConstraints = @UniqueConstraint( 
        columnNames = {
            "code"
        } 
    ) 
)
public class VideoType implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @NotNull
    @NotEmpty
    @Length( max = 16 )
    @Column( name = "code", length = 16 )
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
     * Default constructor.
     */
    public VideoType() {
        
    }

    /**
     * Constructor.
     *
     * @param code  Const name of video type
     * @param title Title of video type
     */
    public VideoType(
        String code,
        String title
    ) {
        //- Initialization -//
        this.code = code;
        this.title = title;
    }

    /**
     * Constructor.
     *
     * @param code          Const name of video type
     * @param title         Title of video type
     * @param description   Description of this video type
     */
    public VideoType(
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
     * @param id            Unique identificator
     * @param code          Const name of video type
     * @param title         Title of video type
     * @param description   Description of this video type
     */
    public VideoType(
        Long id,
        String code,
        String title,
        String description
    ) {
        //- Initialization -//
        this(
            code,
            title,
            description
        );
        this.id = id;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of VideoType.
     *
     * @return Long ID of song
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Get code of VideoType.
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Get title of VideoType.
     *
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get description about VideoType.
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of VideoType.
     *
     * @param id ID of VideoType
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set code of VideoType.
     * Ex. ADMIN, MANAGER, USER
     *
     * @param code Const name of video type
     */
    public void setCode( String code ) {
        this.code = code;
    }

    /**
     * Set title of VideoType.
     *
     * @param title Title of video type
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set description of VideoType.
     *
     * @param description Description of this video type
     */
    public void setDescription( String description ) {
        this.description = description;
    }
}
