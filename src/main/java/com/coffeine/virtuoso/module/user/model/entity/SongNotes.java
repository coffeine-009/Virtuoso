/// *** User :: Model :: Entity :: SongNotes    *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-29 21:38:42 :: 2014-04-29 21:51:30
     *
     * @address /Ukraine/Ivano-Frankivsk/Chornovola/104
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
import javax.persistence.GeneratedValue;
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
 * Class for reflect song_text table from persistence layout
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( name = "song_notes" )
public class SongNotes implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @JsonIgnore
    @NotNull
    @Valid
    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_song", columnDefinition = "BIGINT( 20 )" )
    protected Song song;

    @NotNull
    @NotEmpty
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_notes_type", columnDefinition = "BIGINT( 20 )" )
    protected NotesType notesType;

    @NotNull
    @NotEmpty
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_style", columnDefinition = "BIGINT( 20 )" )
    protected Style style;

    @NotNull
    @NotEmpty
    @Size( max = 5 )
    @Column( name = "locale", columnDefinition = "VARCHAR( 5 )" )
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
    public SongNotes() {

    }

    /**
     * Create text for song
     *
     * @param locale
     */
    public SongNotes(
        String locale
    ) {
        //- Initialization -//
        this.locale = locale;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of song notes
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get song of this notes
     *
     * @return Song
     */
    public Song getSong() {
        return song;
    }

    /**
     * Get notes type
     *
     * @return NotesType
     */
    public NotesType getNotesType() {
        return notesType;
    }

    /**
     * Get style of song
     *
     * @return Style
     */
    public Style getStyle() {
        return style;
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
     * Set ID of song notes
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set song of this notes
     *
     * @param song
     */
    public void setSong( Song song ) {
        this.song = song;
    }

    /**
     * Set type of notes
     *
     * @param notesType
     */
    public void setNotesType( NotesType notesType ) {
        this.notesType = notesType;
    }

    /**
     * Set style of song
     *
     * @param style
     */
    public void setStyle( Style style ) {
        this.style = style;
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
