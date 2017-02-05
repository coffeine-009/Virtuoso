/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service;


import com.thecoffeine.virtuoso.music.model.entity.Composer;

import java.util.List;
import java.util.Set;

/**
 * Service for work with Composer.
 *
 * @version 1.0
 * @see Composer
 */
public interface ComposerService {

    //- SECTION :: MAIN -//
    /**
     * Find composers for page.
     *
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of composers per page.
     */
    Set<Composer> findAll( int page, int limit );

    /**
     * Create a new composer.
     *
     * @param composer Data for create new composer
     * @return Composer Created composer
     */
    Composer create( Composer composer );

    /**
     * Find Composer by id.
     *
     * @param id        Identificator of composer
     * @return Composer found composer
     */
    Composer find( Long id );

    /**
     * Find Composer by ids.
     *
     * @param ids       Ids of composer
     * @return Composer found composer
     */
    Set<Composer> find( List<Long> ids );

    /**
     * Update composer data.
     *
     * @param composer Composer for update
     * @return Composer Updated composer
     */
    Composer update( Composer composer );

    /**
     * Delete composer by ID.
     *
     * @param id Identificator of composer
     */
    void delete( Long id );
}
