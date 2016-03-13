/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.service;


import com.coffeine.virtuoso.music.model.entity.Composer;

import java.util.List;

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
    List<Composer> findAll( int page, int limit );

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
    List<Composer> find( List<Long> ids );

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
