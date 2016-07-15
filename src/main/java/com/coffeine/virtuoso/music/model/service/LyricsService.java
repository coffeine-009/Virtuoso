/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.music.model.entity.Lyrics;

import java.util.List;

/**
 * Service for work with lyrics.
 *
 * @version 1.0
 */
public interface LyricsService {

    //- SECTION :: MAIN -//
    /**
     * Find song's lyricses for page.
     *
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of song's lyricses per page
     */
    List<Lyrics> findAll( int page, int limit );

    /**
     * Create.
     *
     * @param lyrics Data for create new song's lyrics.
     *
     * @return Lyrics Created.
     */
    Lyrics create( Lyrics lyrics );

    /**
     * Find song's lyrics by id.
     *
     * @param id Unique identificator.
     *
     * @return Lyrics Found.
     */
    Lyrics find( Long id );

    /**
     * Update.
     *
     * @param lyrics Lyrics for update.
     *
     * @return Lyrics Updated lyrics.
     */
    Lyrics update( Lyrics lyrics );

    /**
     * Delete.
     *
     * @param id Identificator of lyrics.
     */
    void delete( Long id );
}
