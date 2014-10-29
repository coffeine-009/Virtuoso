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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for reflect song_text table from persistence layout
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

    @JsonIgnore
    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn( name = "id_song" )
    protected Song song;

    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn( name = "id_staff_type" )
    protected StaffType staffType;

    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn( name = "id_style" )
    protected Style style;

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
    public Staff() {

    }

    /**
     * Create text for song
     *
     * @param locale
     */
    public Staff(
        String locale
    ) {
        //- Initialization -//
        this.locale = locale;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of song staffs
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get song of this staffs
     *
     * @return Song
     */
    public Song getSong() {
        return song;
    }

    /**
     * Get staffs type
     *
     * @return StaffType
     */
    public StaffType getStaffType() {
        return staffType;
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
     * Set ID of song staffs
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set song of this staffs
     *
     * @param song
     */
    public void setSong( Song song ) {
        this.song = song;
    }

    /**
     * Set type of staffs
     *
     * @param staffType
     */
    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
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
