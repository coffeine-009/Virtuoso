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
 * Class for reflect video table from persistence layout.
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( name = "video" )
public class Video implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_video_type" )
    protected VideoType videoType;

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

    @NotNull
    @NotEmpty
    @Length( max = 32 )
    @Column( name = "title", length = 32 )
    protected String title;

    @Column( name = "description" )
    @Type( type = "text" )
    protected String description;

    @NotNull
    @NotEmpty
    @Length( max = 64 )
    @Column( name = "file_name", length = 64 )
    protected String fileName;

    @Column(
        name = "creation",
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Default constructor.
     */
    public Video() {

    }

    /**
     * Create video.
     *
     * @param title          Title.
     * @param locale         Locale.
     * @param description    Description.
     * @param fileName       File name.
     */
    public Video(
        String title,
        String locale,
        String description,
        String fileName
    ) {
        //- Initialization -//
        this.title = title;
        this.locale = locale;
        this.description = description;
        this.fileName = fileName;
    }

    /**
     * Constructor for create new video.
     *
     * @param videoType    Type of video.
     * @param locale       Locale.
     * @param title        Title.
     * @param fileName     File name.
     */
    public Video(
        VideoType videoType,
        String locale,
        String title,
        String fileName
    ) {
        this.videoType = videoType;
        this.locale = locale;
        this.title = title;
        this.fileName = fileName;
    }

    /**
     * Create a new video from form.
     *
     * @param song           Song of this video
     * @param videoType      Type of video.
     * @param locale         Locale.
     * @param title          Title of video.
     * @param description    Description of video.
     * @param filename       Video file name.
     */
    public Video(
        Song song,
        VideoType videoType,
        String locale,
        String title,
        String description,
        String filename
    ) {
        this.song = song;
        this.videoType = videoType;
        this.locale = locale;
        this.title = title;
        this.description = description;
        this.fileName = filename;
    }

    //- SECTION :: GET -//
    /**
     * Get ID of video.
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get videotype for this video.
     *
     * @return VideoType
     */
    public VideoType getVideoType() {
        return videoType;
    }

    /**
     * Get Song for this video.
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
     * Get title of video.
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get description of video.
     *
     * @return String
     */

    public String getDescription() {
        return description;
    }

    /**
     * Get filename of this video.
     * @return String
     */
    public String getFileName() {
        return fileName;
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
     * Set ID of video.
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set video type.
     *
     * @param videoType
     */
    public void setVideoType( VideoType videoType ) {
        this.videoType = videoType;
    }

    /**
     * Set song of this video.
     *
     * @param song
     */
    public void setSong( Song song ) {
        this.song = song;
    }

    /**
     * Set locale.
     *
     * @param locale
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set title of video.
     *
     * @param title
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set description of video.
     *
     * @param description
     */
    public void setDescription( String description ) {
        this.description = description;
    }

    /**
     * Set filename of video.
     *
     * @param fileName
     */
    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }
}
