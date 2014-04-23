/// *** User :: Model :: Entity :: Song *** *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-03-25 14:26:32 :: ....-..-.. ..:..:..
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for reflect table from persistence layout
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( name = "song" )
public class Song implements Serializable {

    /// *** Properties  *** ///
    @Id
    @Column( name = "id", columnDefinition = "BIGINT( 20 ) AUTO_INCREMENT" )
    protected Long id;

    @JsonIgnore
    @NotNull
    @Valid
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_composer", columnDefinition = "BIGINT( 20 )" )
    protected Composer composer;

    @JsonIgnore
    @NotNull
    @Valid
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_poet", columnDefinition = "BIGINT( 20 )" )
    protected Poet poet;

    @NotNull
    @NotEmpty
    @Size( max = 5 )
    @Column( name = "locale", columnDefinition = "VARCHAR( 5 )" )
    protected String locale;

    @NotNull
    @NotEmpty
    @Size( max = 64 )
    @Column( name = "title", columnDefinition = "VARCHAR( 64 )" )
    protected String title;

    @Column( name = "write_date", columnDefinition = "TIMESTAMP" )
    protected Calendar writeDate;

    @Column( 
        name = "creation", 
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" 
    )
    protected Calendar creation;

    
    /// *** Methods     *** ///

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
     * Get composer of song
     * 
     * @return Composer
     */
    public Composer getComposer() {
        return composer;
    }

    /**
     * Get poet of song
     *
     * @return Poet
     */
    public Poet getPoet() {
        return poet;
    }

    /**
     * Get locate of song
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get title of song
     *
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get song's date of write
     *
     * @return Calendar
     */
    public Calendar getWriteDate() {
        return writeDate;
    }

    /**
     * Get time of create record
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
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
     * Set locale of song
     *
     * @param locale 
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set title of song
     *
     * @param title
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set song's date of write
     *
     * @param writeDate 
     */
    public void setWriteDate( Calendar writeDate ) {
        this.writeDate = writeDate;
    }
}
