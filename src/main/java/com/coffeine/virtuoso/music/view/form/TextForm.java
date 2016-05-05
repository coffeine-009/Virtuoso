/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.view.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Form for text of song.
 *
 * @version 1.0
 */
public class TextForm {

    /// *** Properties  *** ///
    @NotNull
    @Min( 0 )
    private Long songId;

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    private String locale;


    /// *** Methods     *** ///
    /**
     * Default constructor.
     */
    public TextForm() {

    }

    /**
     * Create text for song.
     *
     * @param locale    Locale.
     */
    public TextForm(
        String locale
    ) {
        //- Initialization -//
        this.locale = locale;
    }


    //- SECTION :: GET -//
    /**
     * Get id of song.
     *
     * @return Long
     */
    public Long getSongId() {
        return songId;
    }

    /**
     * Get locale.
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }


    //- SECTION :: SET -//
    /**
     * Set song of text.
     *
     * @param songId    Song id.
     */
    public void setSongId( Long songId ) {
        this.songId = songId;
    }

    /**
     * Set locale.
     *
     * @param locale    Locale.
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }
}
