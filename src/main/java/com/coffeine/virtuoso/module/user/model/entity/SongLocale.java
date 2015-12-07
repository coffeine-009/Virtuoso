/// *** User :: Model :: Entity :: SongLocale   *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-27 12:14:11 :: 2014-04-27 12:59:38
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
 * Class for reflect song_locale table from persistence layout
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

    @JsonIgnore
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
     * Default constructor
     */
    public SongLocale() {

    }

    /**
     * Create composer locale
     *
     * @param title
     * @param locale
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
     * Create composer locale
     *
     * @param song
     * @param title
     * @param locale
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
     * Get ID of composer locale data
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get composer for this locale data
     *
     * @return Song
     */
    public Song getSong() {
        return song;
    }

    /**
     * Get title for current locale
     *
     * @return String
     */
    public String getTitle() {
        return title;
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
     * Set ID of composer's data
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set composer of this data
     *
     * @param song
     */
    public void setSong( Song song ) {
        this.song = song;
    }

    /**
     * Set first name in choosen locale
     *
     * @param title
     */
    public void setTitle( String title ) {
        this.title = title;
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
