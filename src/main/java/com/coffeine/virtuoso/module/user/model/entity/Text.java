/// *** User :: Model :: Entity :: SongText *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-28 21:21:55 :: 2014-04-28 23:07:30
     *
     * @address /Ukraine/Ivano-Frankivsk/Chornovola/104
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Class for reflect song_text table from persistence layout
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( name = "song_text" )
public class Text implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @JsonIgnore
    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_song" )
    protected Song song;

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    @Column( name = "locale", length = 5 )
    protected String locale;

    @Column(
        name = "creation",
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Default constructor
     */
    public Text() {

    }

    /**
     * Create text for song
     *
     * @param locale
     */
    public Text(
        String locale
    ) {
        //- Initialization -//
        this.locale = locale;
    }

    public Text(
        Song song,
        String locale
    ) {
        //- Initialization -//
        this.song = song;
        this.locale = locale;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of song text
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get composer for this song text
     *
     * @return Song
     */
    public Song getSong() {
        return song;
    }

    /**
     * Get locale
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get time of create
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
    }

    //- SECTION :: SET -//
    /**
     * Set ID of song text
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set song of text
     *
     * @param song
     */
    public void setSong( Song song ) {
        this.song = song;
    }

    /**
     * Set locale
     *
     * @param locale
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }
}
