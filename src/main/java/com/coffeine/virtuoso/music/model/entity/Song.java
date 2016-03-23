/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Class for reflect table from persistence layout.
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

    @JsonManagedReference
    @NotNull
    @Valid
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
        name = "song_composers",
        uniqueConstraints = {
            @UniqueConstraint(
                columnNames = {
                    "id_composer",
                    "id_song"
                }
            )
        },
        joinColumns = {
            @JoinColumn(
                name = "id_song",
                unique = false,
                nullable = false,
                updatable = false
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name = "id_composer",
                unique = false,
                nullable = false,
                updatable = false
            )
        }
    )
    protected List<Composer> composers;

    @JsonManagedReference
    @NotNull
    @Valid
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
        name = "song_poets",
        uniqueConstraints = {
            @UniqueConstraint(
                columnNames = {
                    "id_poet",
                    "id_song"
                }
            )
        },
        joinColumns = {
            @JoinColumn(
                name = "id_song",
                unique = false,
                nullable = false,
                updatable = false
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name = "id_poet",
                unique = false,
                nullable = false,
                updatable = false
            )
        }
    )
    protected List<Poet> poets = new ArrayList<>();

    @JsonProperty( "title" )
    @Transient
    protected String title;

    @JsonManagedReference
    @NotNull
    @NotEmpty
    @OneToMany(
        mappedBy = "song",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    protected List<SongLocale> data = new ArrayList<>();

    @JsonManagedReference
    @NotNull
    @NotEmpty
    @OneToMany(
        mappedBy = "song",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    protected List<Staff> staffs = new ArrayList<>();

    @JsonManagedReference
    @NotNull
    @NotEmpty
    @OneToMany(
        mappedBy = "song",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    protected List<Text> texts = new ArrayList<>();

    @JsonManagedReference
    @NotNull
    @OneToMany(
        mappedBy = "song",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    protected List<Video> videos = new ArrayList<>();

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
     * Default constructor.
     */
    public Song() {
        //- Initialization -//
    }

    /**
     * Constructor.
     *
     * @param poets
     * @param title
     * @param texts
     * @param videos
     * @param locale
     */
    public Song(
        List<Poet> poets,
        String title,
        List<Text> texts,
        List<Video> videos,
        String locale
    ) {
        //- Initialization -//
        this();

        this.poets = poets;
        this.title = title;
        this.texts = texts;
        this.videos = videos;
        this.locale = locale;
    }

    /**
     * Constructor for create new song.
     *
     * @param composers
     * @param poets
     * @param data
     * @param texts
     * @param videos
     * @param locale
     */
    public Song(
        List<Composer> composers,
        List<Poet> poets,
        List<SongLocale> data,
        List<Text> texts,
        List<Video> videos,
        String locale

    ) {
        //- Initialization -//
        this();

        this.composers = composers;
        this.poets = poets;

        for ( SongLocale songLocale : data ) {
            this.addSongLocale( songLocale );
        }

        for ( Text text : texts ) {
            this.addText( text );
        }

        for( Video video : videos ) {
            this.addVideo( video );
        }

        this.locale = locale;
    }

    /**
     * Constructor for create new song.
     *
     * @param composers
     * @param poets
     * @param data
     * @param staffs
     * @param texts
     * @param videos
     * @param locale
     */
    public Song(
        List<Composer> composers,
        List<Poet> poets,
        List<SongLocale> data,
        List<Staff> staffs,
        List<Text> texts,
        List<Video> videos,
        String locale

    ) {
        //- Initialization -//
        this();

        this.composers = composers;
        this.poets = poets;

        for ( SongLocale songLocale : data ) {
            this.addSongLocale( songLocale );
        }

        for( Staff staff : staffs ) {
            staff.setSong( this );
            this.addStaff( staff );
        }

        for ( Text text : texts ) {
            this.addText( text );
        }

        for( Video video : videos ) {
            this.addVideo( video );
        }

        this.locale = locale;
    }

    /**
     * Constructor for create new song.
     *
     * @param composers
     * @param poets
     * @param locale
     */
    public Song(
        List<Composer> composers,
        List<Poet> poets,
        String locale
    ) {
        this.composers = composers;
        poets.forEach( (poet) -> {
            this.poets.add( poet );
        });
        this.data = null;
        this.staffs = null;
        this.texts = null;
        this.locale = locale;
    }

    //- SECTION :: GET -//
    /**
     * Get ID of song.
     *
     * @return Long ID of song
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Get composer of song.
     *
     * @return Composer
     */
    public List<Composer> getComposers() {
        return composers;
    }

    /**
     * Get poet of song.
     *
     * @return Poet
     */
    public List<Poet> getPoets() {
        return poets;
    }

    /**
     * Get title of song.
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }
    /**
     * Get data for locale.
     *
     * @return List of SongLocale.
     */
    public List<SongLocale> getData() {
        return data;
    }

    /**
     * Get musical staffs.
     *
     * @return List of SongStaff.
     */
    public List<Staff> getStaffs() {
        return staffs;
    }

    /**
     * Get text.
     *
     * @return List of SongText.
     */
    public List<Text> getTexts() {
        return texts;
    }

    /**
     * Get video.
     *
     * @return List of Video.
     */
    public List<Video> getVideos() {
        return videos;
    }

    /**
     * Get locate of song.
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get song's date of write.
     *
     * @return Calendar
     */
    public Calendar getWriteDate() {
        return writeDate;
    }

    /**
     * Get time of create record.
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
     * @param id ID of song.
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set composer of song.
     *
     * @param composers Composer of song
     */
    public void setComposers( List<Composer> composers ) {
        this.composers = composers;
    }

    /**
     * Set poet of this song.
     *
     * @param poets Poet of song
     */
    public void setPoets( List<Poet> poets ) {
        this.poets = poets;
    }

    /**
     * Set data for current locale.
     *
     * @param data Localized data
     */
    public void setData( List<SongLocale> data ) {
        this.data = data;
    }

    /**
     * Set musical staffs.
     *
     * @param staffs List of staffs
     */
    public void setStaffs( List<Staff> staffs ) {
        this.staffs = staffs;
    }

    /**
     * Set text.
     *
     * @param texts List of texts
     */
    public void setTexts( List<Text> texts ) {
        this.texts = texts;
    }

    /**
     * Set video.
     *
     * @param videos List of videos
     */
    public void setVideos( List<Video> videos ) {
        this.videos = videos;
    }

    /**
     * Set locale of song.
     *
     * @param locale Locale of song
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set song's date of write.
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
