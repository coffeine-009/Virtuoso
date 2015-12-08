/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Model :: Entity :: NotesType    *** *** *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.music.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class for reflect table staffs type from persistence layout
 *
 * @version 1.0
 */
@Entity
@Table( 
    name = "staff_type",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {
            "code"
        }
    )
)
public class StaffType implements Serializable {

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
    public StaffType() {
        
    }

    /**
     * Constructor
     *
     * @param code
     * @param title
     * @param description 
     */
    public StaffType(
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
     * Constructor for create new Staff type
     *
     * @param id
     * @param code
     * @param title
     * @param description
     */
    public StaffType(
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
     * Get ID of staffs type
     *
     * @return Long ID of staffs type
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Get code of staffs type
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Get title of staffs type
     *
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get description about staffs type
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of staffs type
     *
     * @param id ID of staffs type
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set code of staffs type
     * Ex. TABULATRE
     *
     * @param code
     */
    public void setCode( String code ) {
        this.code = code;
    }

    /**
     * Set title of staffs type
     *
     * @param title
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set description of staffs type
     *
     * @param description 
     */
    public void setDescription( String description ) {
        this.description = description;
    }
}
