/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/29/16 10:59 PM
 */

package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.music.model.entity.Collection;

import java.util.Set;

/**
 * Service for work with Collection.
 *
 * @version 1.0
 * @see com.coffeine.virtuoso.music.model.entity.Collection
 */
public interface CollectionService {

    //- SECTION :: MAIN -//
    /**
     * Find collection for page.
     *
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of collections per page.
     */
    Set<Collection> findAll( int page, int limit );

    /**
     * Create a new collection.
     *
     * @param collection    Data for create new collection.
     *
     * @return Collection   Created collection.
     */
    Collection create( Collection collection );

    /**
     * Find Collection by id.
     *
     * @param id        Id of collection.
     *
     * @return Collection.
     */
    Collection find( Long id );

    /**
     * Update collection data.
     *
     * @param collection    Composer for update.
     *
     * @return Collection Updated collection.
     */
    Collection update( Collection collection );

    /**
     * Delete collection by ID.
     *
     * @param id    Id of collection.
     */
    void delete( Long id );
}
