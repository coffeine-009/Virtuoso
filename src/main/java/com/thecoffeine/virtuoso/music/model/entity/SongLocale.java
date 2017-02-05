/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
 * Class for reflect song_locale table from persistence layout.
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( name = "song_locale" )
public class SongLocale implements Serializable {

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
    @Length( max = 64 )
    @Column( name = "title", length = 64 )
    protected String title;

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
     * Default constructor.
     */
    public SongLocale() {

    }

    /**
     * Create composer locale.
     *
     * @param title     Title.
     * @param locale    Locale.
     */
    public SongLocale(
        String title,
        String locale
    ) {
        //- Initialization -//
        this.title = title;
        this.locale = locale;
    }

    /**
     * Create composer locale.
     *
     * @param song      Song.
     * @param title     Title.
     * @param locale    Locale.
     */
    public SongLocale(
        Song song,
        String title,
        String locale
    ) {
        //- Initialization -//
        this.song = song;
        this.title = title;
        this.locale = locale;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of composer locale data.
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get composer for this locale data.
     *
     * @return Song
     */
    public Song getSong() {
        return song;
    }

    /**
     * Get title for current locale.
     *
     * @return String
     */
    public String getTitle() {
        return title;
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
     * Get time of create.
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
    }

    //- SECTION :: SET -//
    /**
     * Set ID of composer's data.
     *
     * @param id    Id of song's localied data.
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set composer of this data.
     *
     * @param song    Song.
     */
    public void setSong( Song song ) {
        this.song = song;
    }

    /**
     * Set first name in chosen locale.
     *
     * @param title    Title.
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set locale.
     *
     * @param locale    Locale.
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }
}
