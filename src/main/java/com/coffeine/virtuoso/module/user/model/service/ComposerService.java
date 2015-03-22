/// *** User :: Model :: Service :: Composer    *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-06-04 10:56:41 :: 2014-09-29 22:32:18
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service;


import com.coffeine.virtuoso.module.user.model.entity.Composer;

import java.util.List;

/**
 * Service for work with Composer.
 * @see com.coffeine.virtuoso.module.user.model.entity.Composer
 *
 * @version 1.0
 */
public interface ComposerService {

    //- SECTION :: MAIN -//
    /**
     * Find composers for page
     *
     * @param page  Number of page for search
     * @param limit Count of items per page
     * @return List < Composer > List of composers per page
     */
    public List < Composer > findAll( int page, int limit );

    /**
     * Create a new composer
     *
     * @param composer Data for create new composer
     * @return Composer Created composer
     */
    public Composer create( Composer composer );

    /**
     * Find Composer by id
     *
     * @param id        Identificator of composer
     * @param locale    Locale code
     * @return Composer found composer
     */
    public Composer find( Long id, String locale );

    /**
     * Update composer data
     *
     * @param composer Composer for update
     * @return Composer Updated composer
     */
    public Composer update( Composer composer );

    /**
     * Delete composer by ID
     *
     * @param id Identificator of composer
     */
    public void delete( Long id );
}
