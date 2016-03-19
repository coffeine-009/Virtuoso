/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

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
 * Class for reflect song_text table from persistence layout..
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( name = "song_notes" )
public class Staff implements Serializable {

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
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_staff_type" )
    protected StaffType staffType;

    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_style" )
    protected Style style;

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    @Column( name = "locale", length = 5 )
    protected String locale;

    @NotNull
    @NotEmpty
    @Column( name = "file", columnDefinition = "TEXT")
    protected String file;

    @Column(
        name = "creation",
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Default constructor.
     */
    public Staff() {

    }

    /**
     * Constructor for create new Staff.
     *
     * @param staffType
     * @param style
     * @param locale
     */
    public Staff(
        StaffType staffType,
        Style style,
        String locale
    ) {
        this.staffType = staffType;
        this.style = style;
        this.locale = locale;
    }

    /**
     * Constructor for create new staff.
     * @param song
     * @param staffType
     * @param style
     * @param locale
     */
    public Staff(
        Song song,
        StaffType staffType,
        Style style,
        String locale
    ) {
        this.song = song;
        this.staffType = staffType;
        this.style = style;
        this.locale = locale;

        // Init links
        this.song.addStaff(this);
    }

    /**
     * Constructor for create new staff.
     *
     * @param style
     * @param locale
     */

    public Staff(
        Style style,
        String locale,
        String file
    ) {
        this.song = null;
        this.staffType = null;
        this.style = style;
        this.locale = locale;
        this.file = file;
    }
    /**
     * Create text for song.
     *
     * @param locale
     */
    public Staff(
        String locale
    ) {
        //- Initialization -//
        this.locale = locale;
    }

    public Staff(Song song, StaffType staffType, Style style, String locale, String file) {
        this.song = song;
        this.staffType = staffType;
        this.style = style;
        this.locale = locale;
        this.file = file;

        this.song.addStaff(this);
    }

    //- SECTION :: GET -//
    /**
     * Get ID of song staffs.
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get song of this staffs.
     *
     * @return Song
     */
    public Song getSong() {
        return song;
    }

    /**
     * Get staffs type.
     *
     * @return StaffType
     */
    public StaffType getStaffType() {
        return staffType;
    }

    /**
     * Get style of song.
     *
     * @return Style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Get locale.
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }

    public String getFile() {
        return file;
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
     * Set ID of song staffs.
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set song of this staffs.
     *
     * @param song
     */
    public void setSong( Song song ) {
        this.song = song;
    }

    /**
     * Set type of staffs.
     *
     * @param staffType
     */
    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    /**
     * Set style of song.
     *
     * @param style
     */
    public void setStyle( Style style ) {
        this.style = style;
    }

    /**
     * Set locale.
     *
     * @param locale
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
