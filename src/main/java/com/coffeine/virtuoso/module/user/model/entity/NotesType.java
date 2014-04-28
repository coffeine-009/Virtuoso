/// *** User :: Model :: Entity :: NotesType    *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-29 00:21:15 :: 2014-04-29 00:24:24
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
 * Class for reflect table notes type from persistence layout
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( 
    name = "notes_type", 
    uniqueConstraints = @UniqueConstraint( 
        columnNames = {
            "code"
        } 
    ) 
)
public class NotesType implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @NotNull
    @NotEmpty
    @Size( max = 32 )
    @Column( name = "code", columnDefinition = "VARCHAR( 32 )" )
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
    public NotesType() {
        
    }

    /**
     * Constructor
     *
     * @param code
     * @param title
     * @param description 
     */
    public NotesType(
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
     * Get ID of notes type
     *
     * @return Long ID of notes type
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Get code of notes type
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Get title of notes type
     *
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get description about notes type
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of notes type
     *
     * @param id ID of notes type
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set code of notes type
     * Ex. TABULATRE
     *
     * @param code
     */
    public void setCode( String code ) {
        this.code = code;
    }

    /**
     * Set title of notes type
     *
     * @param title
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set description of notes type
     *
     * @param description 
     */
    public void setDescription( String description ) {
        this.description = description;
    }
}
