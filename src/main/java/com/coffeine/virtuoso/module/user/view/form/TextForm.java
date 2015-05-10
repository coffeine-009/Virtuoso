/**
 * @copyright (c) 2015 by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

package com.coffeine.virtuoso.module.user.view.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Form for text of song
 *
 * @version 1.0
 */
public class TextForm {

    /// *** Properties  *** ///
    protected Long id;

    @NotNull
    @Min( 0 )
    protected Long songId;

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    protected String locale;


    /// *** Methods     *** ///
    /**
     * Default constructor
     */
    public TextForm() {

    }

    /**
     * Create text for song
     *
     * @param locale
     */
    public TextForm(
        String locale
    ) {
        //- Initialization -//
        this.locale = locale;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of song text
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get id of song
     *
     * @return Long
     */
    public Long getSongId() {
        return songId;
    }

    /**
     * Get locale
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of song text
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set song of text
     *
     * @param songId
     */
    public void setSongId( Long songId ) {
        this.songId = songId;
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
