/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.music.model.entity.Poet;

import java.util.List;

/**
 * Service for work with poet.
 *
 * @version 1.0
 */
public interface PoetService {

    //- SECTION :: MAIN -//
    /**
     * Find Poets for page.
     *
     * @param page  Number of page for search
     * @param limit Count of items per page
     * @return List < Poet > List of poets per page
     */
    List<Poet> findAll( int page, int limit );

    /**
     * Create a new Poet.
     *
     * @param poet Data for create new poet
     * @return Poet Created
     */
    Poet create( Poet poet );

    /**
     * Find Poet by id.
     *
     * @param id Unique identificator
     * @return Poet Found
     */
    Poet find( Long id );

    /**
     * Find Poets by ids.
     *
     * @param ids Unique ids.
     *
     * @return Poet Found
     */
    List<Poet> find( List<Long> ids );

    /**
     * Update.
     *
     * @param poet Poet for update
     * @return Poet Updated poet
     */
    Poet update( Poet poet );

    /**
     * Delete.
     *
     * @param id Identificator of poet
     */
    void delete( Long id );
}
