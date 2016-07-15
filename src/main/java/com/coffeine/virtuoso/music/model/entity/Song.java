/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Class for reflect table from persistence layout.
 *
 * @version 1.0
 */
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
    @NotEmpty
    @OneToMany(
        mappedBy = "song",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @Fetch( FetchMode.JOIN )
    protected Set<SongLocale> data = new HashSet<>();

    @JsonManagedReference
    @NotNull
    @NotEmpty
    @OneToMany(
        mappedBy = "song",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @Fetch( FetchMode.JOIN )
    protected Set<Staff> staffs = new HashSet<>();

    @JsonManagedReference
    @NotNull
    @NotEmpty
    @OneToMany(
        mappedBy = "song",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @Fetch( FetchMode.JOIN )
    protected Set<Lyrics> lyrics = new HashSet<>();

    @JsonManagedReference
    @NotNull
    @OneToMany(
        mappedBy = "song",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @Fetch( FetchMode.JOIN )
    protected Set<Video> videos = new HashSet<>();

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
     * @param lyrics     List of lyricses.
     * @param videos    List of videos.
     * @param locale    Locale.
     */
    public Song(
        Set<Lyrics> lyrics,
        Set<Video> videos,
        String locale
    ) {
        //- Initialization -//
        this();

        this.lyrics = lyrics;
        this.videos = videos;
        this.locale = locale;
    }

    /**
     * Constructor for create new song.
     *
     * @param data         Localized data.
     * @param lyrics        List of lyricses.
     * @param videos       List of videos.
     * @param locale       Locale.
     */
    public Song(
        Set<SongLocale> data,
        Set<Lyrics> lyrics,
        Set<Video> videos,
        String locale

    ) {
        //- Initialization -//
        this();

        data.forEach( songLocale -> this.addSongLocale( songLocale ) );
        lyrics.forEach( text -> this.addText( text ) );
        videos.forEach( video -> this.addVideo( video ) );

        this.locale = locale;
    }

    /**
     * Constructor for create new song.
     *
     * @param data         Localized data.
     * @param staffs       List of staffs.
     * @param lyricses        List of lyricses.
     * @param videos       List of videos.
     * @param locale       Locale.
     */
    public Song(
        Set<SongLocale> data,
        Set<Staff> staffs,
        Set<Lyrics> lyricses,
        Set<Video> videos,
        String locale

    ) {
        //- Initialization -//
        this();

        for ( SongLocale songLocale : data ) {
            this.addSongLocale( songLocale );
        }

        for ( Staff staff : staffs ) {
            staff.setSong( this );
            this.addStaff( staff );
        }

        for ( Lyrics lyrics : lyricses ) {
            lyrics.setSong( this );
            this.addText( lyrics );
        }

        for ( Video video : videos ) {
            this.addVideo( video );
        }

        this.locale = locale;
    }

    /**
     * Constructor for create new song.
     *
     * @param locale       Locale.
     */
    public Song(
        String locale
    ) {
        this.locale = locale;
    }

    //- SECTION :: GET -//
    /**
     * Get ID of song.
     *
     * @return Long ID of song.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Get data for locale.
     *
     * @return List of SongLocale.
     */
    public Set<SongLocale> getData() {
        return data;
    }

    /**
     * Get musical staffs.
     *
     * @return List of SongStaff.
     */
    public Set<Staff> getStaffs() {
        return staffs;
    }

    /**
     * Get lyrics.
     *
     * @return List of SongText.
     */
    public Set<Lyrics> getLyrics() {
        return lyrics;
    }

    /**
     * Get video.
     *
     * @return List of Video.
     */
    public Set<Video> getVideos() {
        return videos;
    }

    /**
     * Get locate of song.
     *
     * @return String.
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get song's date of write.
     *
     * @return Calendar.
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
     * Set ID of song.
     *
     * @param id ID of song.
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set data for current locale.
     *
     * @param data Localized data.
     */
    public void setData( Set<SongLocale> data ) {
        this.data = data;
    }

    /**
     * Set musical staffs.
     *
     * @param staffs List of staffs.
     */
    public void setStaffs( Set<Staff> staffs ) {
        this.staffs = staffs;
    }

    /**
     * Set lyrics.
     *
     * @param lyricses List of lyricses.
     */
    public void setLyrics( Set<Lyrics> lyricses ) {
        this.lyrics = lyricses;
    }

    /**
     * Set video.
     *
     * @param videos List of videos.
     */
    public void setVideos( Set<Video> videos ) {
        this.videos = videos;
    }

    /**
     * Set locale of song.
     *
     * @param locale Locale of song.
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set song's date of write.
     *
     * @param writeDate Date of write.
     */
    public void setWriteDate( Calendar writeDate ) {
        this.writeDate = writeDate;
    }


    /**
     * Add lyrics.
     *
     * @param lyrics    Lyrics of song.
     */
    public void addText( Lyrics lyrics ) {
        //- Set Song-//
        lyrics.setSong( this );

        //- Add Lyrics-//
        if ( !this.lyrics.contains( lyrics ) ) {
            this.lyrics.add( lyrics );
        }
    }

    /**
     * Add localized data.
     *
     * @param songLocale    Localized data.
     */
    public void addSongLocale( SongLocale songLocale ) {
        //- Set Song-//
        songLocale.setSong( this );

        //- Add song locale -//
        if ( !this.data.contains( songLocale ) ) {
            this.data.add( songLocale );
        }
    }

    /**
     * Add a new staff.
     *
     * @param staff    Staff.
     */
    public void addStaff( Staff staff ) {
        //- Set Song-//
        staff.setSong( this );

        //- Add staff -//
        if ( !this.staffs.contains( staff ) ) {
            this.staffs.add( staff );
        }
    }

    /**
     * Add video.
     *
     * @param video    Video.
     */
    public void addVideo( Video video ) {
        //- Set Song-//
        video.setSong( this );

        //- Add video-//
        if ( !this.videos.contains( video ) ) {
            this.videos.add( video );
        }
    }
}
