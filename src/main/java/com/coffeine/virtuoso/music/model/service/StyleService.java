/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Model :: Service :: Style   *** *** *** *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.music.model.entity.Style;

import java.util.List;

/**
 * Service for work with style.
 *
 * @version 1.0
 */
public interface StyleService {

    //- SECTION :: MAIN -//
    /**
     * Find styles for page.
     *
     * @param page  Number of page for search
     * @param limit Count of items per page
     * @return List < Style > List of song's styles per page
     */
    public List< Style > findAll( int page, int limit );

    /**
     * Create.
     *
     * @param style Data for create new style
     * @return Style Created
     */
    public Style create( Style style );

    /**
     * Find styles by id.
     *
     * @param id Unique identificator
     * @return Style Found
     */
    public Style find( Long id );

    /**
     * Update.
     *
     * @param style Style for update
     * @return Style Updated
     */
    public Style update( Style style );

    /**
     * Delete.
     *
     * @param id Identificator of style
     */
    public void delete( Long id );
}
