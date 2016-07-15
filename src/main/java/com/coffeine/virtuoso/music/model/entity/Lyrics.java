/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Class for reflect song_text table from persistence layout.
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table
public class Lyrics implements Serializable {

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

    @JsonManagedReference
    @NotNull
    @Valid
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
        name = "lyrics_poets",
        uniqueConstraints = {
            @UniqueConstraint(
                columnNames = {
                    "id_poet",
                    "id_lyrics"
                }
            )
        },
        joinColumns = {
            @JoinColumn(
                name = "id_lyrics",
                nullable = false,
                updatable = false
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name = "id_poet",
                nullable = false,
                updatable = false
            )
        }
    )
    protected Set<Poet> poets = new HashSet<>();

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
    public Lyrics() {
        //- Initialization -//
    }

    /**
     * Create text for song.
     *
     * @param poets     Poets.
     * @param song      Song.
     * @param locale    Locale.
     * @param lyrics    Lyrics.
     */
    public Lyrics(
        Set<Poet> poets,
        Song song,
        String locale,
        String lyrics
    ) {
        //- Initialization -//
        poets.forEach( poet -> this.poets.add( poet ) );
        this.song = song;
        this.locale = locale;
        this.lyrics = lyrics;
    }

    /**
     * Create a new text.
     *
     * @param poets     Poets.
     * @param locale    Locale.
     * @param lyrics    Lyrics.
     */
    public Lyrics(
        Set<Poet> poets,
        String locale,
        String lyrics
    ) {
        //- Initialization -//
        poets.forEach( poet -> this.poets.add( poet ) );
        this.locale = locale;
        this.lyrics = lyrics;
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
     * Get poet of song.
     *
     * @return Poet.
     */
    public Set<Poet> getPoets() {
        return poets;
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
     * Set poet of this song.
     *
     * @param poets Poet of song.
     */
    public void setPoets( Set<Poet> poets ) {
        this.poets = poets;
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
     * @param lyrics    Lyrics of the song.
     */
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
