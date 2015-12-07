/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Model :: Service :: Song    *** *** *** *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.music.model.entity.Song;

import java.util.List;

/**
 * Service for work with song
 *
 * @version 1.0
 */
public interface SongService {

    //- SECTION :: MAIN -//
    /**
     * Get list of Song for page
     *
     * @param page Number of page for return
     * @param limit Count items per page
     * @return List < Song > List of found songs
     */
    public List <Song> findAll( int page, int limit );

    /**
     * Create a new song
     *
     * @param song Data for create
     * @return Song
     */
    public Song create( Song song );

    /**
     * Get song by ID
     *
     * @param Id Identificator for search
     * @return Song
     */
    public Song find( Long Id );

    /**
     * Update
     *
     * @param song Data for update
     * @return Song
     */
    public Song update( Song song );

    /**
     * Delete song
s     *
     * @param id Unique identificator
     */
    public void delete( Long id );
}
