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
 * @see Composer
 *
 * @version 1.0
 */
public interface ComposerService {

    //- SECTION :: MAIN -//
    /**
     * Find composers for page.
     *
     * @param page  Number of page for search
     * @param limit Count of items per page
     * @return List < Composer > List of composers per page
     */
    public List < Composer > findAll( int page, int limit );

    /**
     * Create a new composer.
     *
     * @param composer Data for create new composer
     * @return Composer Created composer
     */
    public Composer create( Composer composer );

    /**
     * Find Composer by id.
     *
     * @param id        Identificator of composer
     * @return Composer found composer
     */
    public Composer find( Long id );

    /**
     * Update composer data.
     *
     * @param composer Composer for update
     * @return Composer Updated composer
     */
    public Composer update( Composer composer );

    /**
     * Delete composer by ID.
     *
     * @param id Identificator of composer
     */
    public void delete( Long id );
}
