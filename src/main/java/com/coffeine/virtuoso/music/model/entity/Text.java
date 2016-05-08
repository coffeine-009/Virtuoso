/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;
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
 * Class for reflect song_text table from persistence layout.
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

    @JsonBackReference
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

    @Column( name = "lyrics" )
    @Type( type = "text" )
    protected String lyrics;

    @Column(
        name = "creation",
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Default constructor.
     */
    public Text() {
        //- Initialization -//
    }

    /**
     * Create text for song.
     *
     * @param locale    Locale.
     */
    public Text(
        String locale
    ) {
        //- Initialization -//
        this.locale = locale;
    }

    /**
     * Create a new text.
     *
     * @param song      Song.
     * @param locale    Locale.
     */
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
     * Get ID of song text.
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get composer for this song text.
     *
     * @return Song
     */
    public Song getSong() {
        return song;
    }

    /**
     * Get locale.
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get lyrics.
     *
     * @return String
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * Get time of create.
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
    }

    //- SECTION :: SET -//
    /**
     * Set ID of song text.
     *
     * @param id    Id.
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set song of text.
     *
     * @param song    Song.
     */
    public void setSong( Song song ) {
        this.song = song;
    }

    /**
     * Set locale.
     *
     * @param locale    Locale.
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set lyrics.
     *
     * @param lyrics    Text of the song.
     */
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
