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

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Form for text of song.
 *
 * @version 1.0
 */
public class LyricsForm {

    /// *** Properties  *** ///
    @NotNull
    @Min( 0 )
    private Long songId;

    /**
     * Ids list of poets.
     */
    @NotNull
    @NotEmpty
    private List<Long> poetIds;

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    private String locale;

    @NotNull
    @NotEmpty
    @Length( min = 64 )
    private String content;


    /// *** Methods     *** ///
    /**
     * Default constructor.
     */
    public LyricsForm() {

    }

    /**
     * Create text for song.
     *
     * @param locale    Locale.
     */
    public LyricsForm(
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
     * Get list of poet's ids.
     *
     * @return List of ids.
     */
    public List<Long> getPoetIds() {
        return poetIds;
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
     * Get lyrics content.
     *
     * @return Lyrics content.
     */
    public String getContent() {
        return content;
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
     * Set list of poet's ids.
     *
     * @param poetIds    Poet's ids.
     */
    public void setPoetIds( List<Long> poetIds ) {
        this.poetIds = poetIds;
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
     * Set lyrics content.
     *
     * @param content    Lyrics content.
     */
    public void setContent( String content ) {
        this.content = content;
    }
}
