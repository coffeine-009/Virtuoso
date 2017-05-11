/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service;

import com.thecoffeine.virtuoso.music.model.entity.Poet;

import java.util.List;
import java.util.Set;

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
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of poets per page.
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
    Set<Poet> find( List<Long> ids );

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
