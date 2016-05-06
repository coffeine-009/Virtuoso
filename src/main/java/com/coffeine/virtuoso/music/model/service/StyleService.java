/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

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
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of song's styles per page.
     */
    List<Style> findAll( int page, int limit );

    /**
     * Create.
     *
     * @param style Data for create new style.
     *
     * @return Style Created.
     */
    Style create( Style style );

    /**
     * Find styles by id.
     *
     * @param id Unique identificator.
     *
     * @return Style Found.
     */
    Style find( Long id );

    /**
     * Update.
     *
     * @param style Style for update.
     *
     * @return Style Updated.
     */
    Style update( Style style );

    /**
     * Delete.
     *
     * @param id Identificator of style.
     */
    void delete( Long id );
}
