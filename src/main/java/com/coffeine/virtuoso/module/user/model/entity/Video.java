/// *** User :: Model :: Entity :: Video    *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Namisnyk Valentyn <Valentun_Prodyser@ukr.net>
     *
     * @date 2014-04-28 23:06:43 :: 2014-04-27 12:59:38
     *
     * @address /Ukraine/Ivano-Frankivsk/Chornovola/104
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

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
import java.io.Serializable;
import java.util.Calendar;

/**
 * Class for reflect video table from persistence layout
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

    @JsonIgnore
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

    @Column( name = "description", columnDefinition = "TEXT" )
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
     * Default constructor
     */
    public Video() {

    }

    /**
     * Create video
     *
     * @param title
     * @param locale
     * @param description
     * @param fileName
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


    //- SECTION :: GET -//
    /**
     * Get ID of video
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get videotype for this video
     *
     * @return VideoType
     */
    public VideoType getVideoType() {
        return videoType;
    }

    /**
     * Get Song for this video
     *
     * @return Song
     */
    public Song getSong() {
        return song;
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
     * Get title of video
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get description of video
     *
     * @return String
     */

    public String getDescription() {
        return description;
    }

    /**
     * Get filename of this video
     * @return String
     */
    public String getFileName() {
        return fileName;
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
     * Set ID of video
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set video type
     *
     * @param videoType
     */
    public void setVideoType( VideoType videoType ) {
        this.videoType = videoType;
    }

    /**
     * Set song of this video
     *
     * @param song
     */
    public void setSong( Song song ) {
        this.song = song;
    }

    /**
     * Set locale
     *
     * @param locale
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set title of video
     *
     * @param title
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set description of video
     *
     * @param description
     */
    public void setDescription( String description ) {
        this.description = description;
    }

    /**
     * Set filename of video
     *
     * @param fileName
     */
    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }
}
