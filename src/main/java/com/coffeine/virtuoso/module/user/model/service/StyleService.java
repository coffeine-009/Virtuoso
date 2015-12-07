/// *** User :: Model :: Service :: Style   *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-09-16 16:24:24 :: 2014-06-.. ..:..:..
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service;

import com.coffeine.virtuoso.module.user.model.entity.Style;

import java.util.List;

/**
 * Service for work with style
 *
 * @version 1.0
 */
public interface StyleService {

    //- SECTION :: MAIN -//
    /**
     * Find styles for page
     *
     * @param page  Number of page for search
     * @param limit Count of items per page
     * @return List < Style > List of song's styles per page
     */
    public List< Style > findAll( int page, int limit );

    /**
     * Create
     *
     * @param style Data for create new style
     * @return Style Created
     */
    public Style create( Style style );

    /**
     * Find styles by id
     *
     * @param id Unique identificator
     * @return Style Found
     */
    public Style find( Long id );

    /**
     * Update
     *
     * @param style Style for update
     * @return Style Updated
     */
    public Style update( Style style );

    /**
     * Delete
     *
     * @param id Identificator of style
     */
    public void delete( Long id );
}
