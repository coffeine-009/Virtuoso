/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Model :: Entity :: SongText *** *** *** *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.music.model.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;
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
