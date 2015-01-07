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

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    @Column( name = "id" )
    protected Long id;

    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_composer" )
    protected Composer composer;

    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_poet" )
    protected Poet poet;

    @JsonProperty( "title" )
    @Transient
    protected String title;

    @NotNull
    @NotEmpty
    @OneToMany(
        mappedBy = "song",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    protected List < SongLocale > data;

    @NotNull
    @NotEmpty
    @OneToMany(
        mappedBy = "song",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    protected List <Staff > staffs;

    @NotNull
    @NotEmpty
    @OneToMany(
            mappedBy = "song",
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    protected List < Text > texts;

    @OneToMany(
            mappedBy = "song",
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    protected List < Video > videos;

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    @Column( name = "locale", length = 5 )
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

        //- Initialization -//
        this.data = new ArrayList < SongLocale >();
        this.texts = new ArrayList < Text >();
        this.staffs = new ArrayList < Staff >();
        this.videos = new ArrayList < Video >();
    }

    /**
     * Constructor
     *
     * @param poet
     * @param title
     * @param texts
     * @param videos
     * @param locale
     */
    public Song(
        Poet poet,
        String title,
        List < Text > texts,
        List < Video > videos,
        String locale
    ) {
        //- Initialization -//
        this();

        this.poet = poet;
        this.title = title;
        this.texts = texts;
        this.videos = videos;
    }

    /**
     * Constructor for create new song
     *
     * @param composer
     * @param poet
     * @param data
     * @param texts
     * @param videos
     * @param locale
     */
    public Song(
        Composer composer,
        Poet poet,
        List < SongLocale > data,
        List < Text > texts,
        List < Video > videos,
        String locale

    ) {
        //- Initialization -//
        this();

        this.composer = composer;
        this.poet = poet;

        for ( SongLocale songLocale : data ) {
            this.addSongLocale( songLocale );
        }

        for ( Text text : texts) {
            this.addText( text );
        }

        for( Video video : videos) {
            this.addVideo( video );
        }

        this.locale = locale;
    }

    /**
     * Constructor for create new song
     *
     * @param composer
     * @param poet
     * @param data
     * @param staffs
     * @param texts
     * @param videos
     * @param locale
     */
    public Song(
        Composer composer,
        Poet poet,
        List < SongLocale > data,
        List < Staff > staffs,
        List < Text > texts,
        List < Video > videos,
        String locale

    ) {
        //- Initialization -//
        this();

        this.composer = composer;
        this.poet = poet;

        for ( SongLocale songLocale : data ) {
            this.addSongLocale( songLocale );
        }

        for( Staff staff : staffs) {
            this.addStaff( staff );
        }

        for ( Text text : texts) {
            this.addText( text );
        }

        for( Video video : videos) {
            this.addVideo( video );
        }

        this.locale = locale;
    }

    /**
     * Constructor for create new song
     *
     * @param composer
     * @param poet
     * @param locale
     */
    public Song(
        Composer composer,
        Poet poet,
        String locale
    ) {
        this.composer = composer;
        this.poet = poet;
        this.data = null;
        this.staffs = null;
        this.texts = null;
        this.locale = locale;
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
     * Get musical staffs
     *
     * @return List<SongStaff>
     */
    public List <Staff > getStaffs() {
        return staffs;
    }

    /**
     * Get text
     *
     * @return List<SongText>
     */
    public List < Text > getTexts() {
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
     * @param composer Composer of song
     */
    public void setComposer( Composer composer ) {
        this.composer = composer;
    }

    /**
     * Set poet of this song
     *
     * @param poet Poet of song
     */
    public void setPoet( Poet poet ) {
        this.poet = poet;
    }

    /**
     * Set data for current locale
     *
     * @param data Localized data
     */
    public void setData( List < SongLocale > data ) {
        this.data = data;
    }

    /**
     * Set musical staffs
     *
     * @param staffs List of staffs
     */
    public void setStaffs(List<Staff > staffs) {
        this.staffs = staffs;
    }

    /**
     * Set text
     *
     * @param texts List of texts
     */
    public void setTexts( List < Text > texts ) {
        this.texts = texts;
    }

    /**
     * Set video
     *
     * @param videos List of videos
     */
    public void setVideos( List < Video > videos ) {
        this.videos = videos;
    }

    /**
     * Set locale of song
     *
     * @param locale Locale of song
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set song's date of write
     *
     * @param writeDate Date of write
     */
    public void setWriteDate( Calendar writeDate ) {
        this.writeDate = writeDate;
    }


    @PostLoad
    public void reinit() {
        this.title = this.data.get(0).getTitle();
    }

    /**
     *
     *
     * @param text
     */
    public void addText( Text text) {
        //- Set Song-//
        text.setSong( this );

        //- Add Text-//
        if( !this.texts.contains( text) ) {
            this.texts.add( text );
        }
    }

    public void addSongLocale( SongLocale songLocale ) {
        //- Set Song-//
        songLocale.setSong( this );

        //- Add song locale -//
        if( !this.data.contains( songLocale )) {
            this.data.add( songLocale );
        }
    }

    public void addStaff( Staff staff) {
        //- Set Song-//
        staff.setSong( this );

        //- Add staff -//
        if( !this.staffs.contains( staff )) {
            this.staffs.add( staff );
        }
    }

    public void addVideo( Video video ) {
        //- Set Song-//
        video.setSong( this );

        //- Add video-//
        if( !this.videos.contains( video )) {
            this.videos.add( video );
        }
    }
}
