/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/30/16 12:35 AM
 */

package com.thecoffeine.virtuoso.music.view.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;

/**
 * Collection form.
 *
 * @version 1.0
 */
public class CollectionForm {

    /**
     * Title of collection.
     */
    @NotNull
    @NotEmpty
    @Length( max = 32 )
    private String title;

    /**
     * Description of collection.
     */
    private String description;

    /**
     * Locale code.
     */
    @NotNull
    @NotEmpty
    @Length( min = 2, max = 5 )
    private String locale;

    /**
     * List of ids of songs.
     */
    @NotNull
    @NotEmpty
    private Set<Long> songsIds = new HashSet<>();


    /**
     * Default controller.
     */
    public CollectionForm() {
        //- Init -//
    }

    /**
     * Create a new Collection.
     *
     * @param title       Collection title.
     * @param locale      Collection locale.
     * @param songsIds    list of song's ids.
     */
    public CollectionForm(
        String title,
        String locale,
        Set<Long> songsIds
    ) {
        this.title = title;
        this.locale = locale;
        this.songsIds = songsIds;
    }

    //- SECTION :: GET -//
    /**
     * Get title.
     *
     * @return Title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get description.
     *
     * @return Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get locale.
     *
     * @return Locale code.
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get list of song's ids.
     *
     * @return List of ids.
     */
    public Set<Long> getSongsIds() {
        return songsIds;
    }


    //- SECTION :: SET -//
    /**
     * Set title.
     *
     * @param title    Collection title.
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set description.
     *
     * @param description    Description.
     */
    public void setDescription( String description ) {
        this.description = description;
    }

    /**
     * Set locale.
     *
     * @param locale    Locale code.
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set list of song's ids.
     *
     * @param songsIds    List of ids.
     */
    public void setSongsIds( Set<Long> songsIds ) {
        this.songsIds = songsIds;
    }
}
