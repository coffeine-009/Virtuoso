/// *** User :: Model :: Entity :: Song *** *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-03-25 14:26:32 :: 2014-04-29 21:57:53
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
    @GeneratedValue
    @Column( name = "id", columnDefinition = "BIGINT( 20 )" )
    protected Long id;

    @NotNull
    @Valid
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_composer", columnDefinition = "BIGINT( 20 )" )
    protected Composer composer;

    @NotNull
    @Valid
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_poet", columnDefinition = "BIGINT( 20 )" )
    protected Poet poet;

    @JsonProperty( "title" )
    @Transient
    protected String title;

    @JsonProperty( "text" )
    @Transient
    protected String text;

    @JsonIgnore
    @NotNull
    @NotEmpty
    @OneToMany( mappedBy = "song" )
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List < SongLocale > data;

    @JsonIgnore
    @NotNull
    @NotEmpty
    @OneToMany( mappedBy = "song" )
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List < SongNotes > notes;

    @JsonIgnore
    @NotNull
    @NotEmpty
    @OneToMany( mappedBy = "song" )
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List < SongText > texts;

    @JsonIgnore
    @OneToMany( mappedBy = "song" )
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List < Video > videos;

    @NotNull
    @NotEmpty
    @Size( max = 5 )
    @Column( name = "locale", columnDefinition = "VARCHAR( 5 )" )
    protected String locale;

    @Column( name = "write_date", columnDefinition = "TIMESTAMP" )
    protected Calendar writeDate;

    @Column(
        name = "creation",
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Default constructor
     */
    public Song() {

    }

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
     * Get title of song
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }
    /**
     * Get data for locale
     *
     * @return List<SongLocale>
     */
    public List < SongLocale > getData() {
        return data;
    }

    /**
     * Get musical notes
     *
     * @return List<SongNotes>
     */
    public List < SongNotes > getNotes() {
        return notes;
    }

    /**
     * Get text of song
     *
     * @return String
     */
    public String getText() {
        return text;
    }
    /**
     * Get text
     *
     * @return List<SongText>
     */
    public List < SongText > getTexts() {
        return texts;
    }

    /**
     * Get video
     *
     * @return List<Video>
     */
    public List < Video > getVideos() {
        return videos;
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
     * Set composer of song
     *
     * @param composer
     */
    public void setComposer( Composer composer ) {
        this.composer = composer;
    }

    /**
     * Set poet of this song
     *
     * @param poet
     */
    public void setPoet( Poet poet ) {
        this.poet = poet;
    }

    /**
     * Set data for current locale
     *
     * @param data
     */
    public void setData( List < SongLocale > data ) {
        this.data = data;

        //- Initialization json property -//
        this.title = data.get( 0 ).getTitle();
    }

    /**
     * Set musical notes
     *
     * @param notes
     */
    public void setNotes( List < SongNotes > notes ) {
        this.notes = notes;
    }

    /**
     * Set text
     *
     * @param texts
     */
    public void setTexts( List < SongText > texts ) {
        this.texts = texts;
    }

    /**
     * Set video
     *
     * @param videos
     */
    public void setVideos( List < Video > videos ) {
        this.videos = videos;
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
     * Set song's date of write
     *
     * @param writeDate
     */
    public void setWriteDate( Calendar writeDate ) {
        this.writeDate = writeDate;
    }
}
