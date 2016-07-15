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
 * Class for reflect song_text table from persistence layout..
 *
 * @version 1.0
 */
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

    @JsonManagedReference
    @NotNull
    @Valid
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
        name = "song_notes_composers",
        uniqueConstraints = {
            @UniqueConstraint(
                columnNames = {
                    "id_composer",
                    "id_song_notes"
                }
            )
        },
        joinColumns = {
            @JoinColumn(
                name = "id_song_notes",
                nullable = false,
                updatable = false
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name = "id_composer",
                nullable = false,
                updatable = false
            )
        }
    )
    protected Set<Composer> composers = new HashSet<>();

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
    @Column( name = "file" )
    @Type( type = "text" )
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
        //- Default initialization -//
    }

    /**
     * Constructor for create new Staff.
     *
     * @param staffType    Staff type.
     * @param style        Style.
     * @param file         File content(MusicXML).
     */
    public Staff(
        StaffType staffType,
        Style style,
        String file
    ) {
        this.staffType = staffType;
        this.style = style;
        this.file = file;
    }

    /**
     * Constructor for create new Staff.
     *
     * @param staffType    Staff type.
     * @param style        Style.
     * @param file         File content(MusicXML).
     * @param locale       Locale.
     */
    public Staff(
        Set<Composer> composers,
        StaffType staffType,
        Style style,
        String file,
        String locale
    ) {
        this(staffType, style, file);

        this.composers.forEach( (composer) -> composers.add( composer ) );
        this.locale = locale;
    }

    /**
     * Constructor for create new staff.
     *
     * @param song         Song.
     * @param staffType    Staff type.
     * @param style        Style.
     * @param locale       Locale.
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
     * @param style     Style.
     * @param locale    Locale.
     * @param file      File content.
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
     * Create staff for song.
     *
     * @param locale    Locale.
     */
    public Staff(
        String locale
    ) {
        //- Initialization -//
        this.locale = locale;
    }

    /**
     * Create staff.
     *
     * @param song         Song.
     * @param staffType    Staff type.
     * @param style        Style.
     * @param locale       Locale.
     * @param file         File content.
     */
    public Staff(
        Song song,
        StaffType staffType,
        Style style,
        String locale,
        String file
    ) {
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
     * Get composers.
     *
     * @return  Composers.
     */
    public Set<Composer> getComposers() {
        return composers;
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

    /**
     * Get file content.
     *
     * @return Content of file.
     */
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
     * @param id    Id of staff.
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set song of this staffs.
     *
     * @param song    Song.
     */
    public void setSong( Song song ) {
        this.song = song;
    }

    /**
     * Set composers.
     *
     * @param composers    Composers.
     */
    public void setComposers( Set<Composer> composers ) {
        this.composers = composers;
    }

    /**
     * Set type of staffs.
     *
     * @param staffType    Staff type.
     */
    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    /**
     * Set style of song.
     *
     * @param style    Style.
     */
    public void setStyle( Style style ) {
        this.style = style;
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
     * Set content of file.
     *
     * @param file    Content of file.
     */
    public void setFile(String file) {
        this.file = file;
    }
}
