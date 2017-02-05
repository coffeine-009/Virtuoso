/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/29/16 11:05 PM
 */

package com.thecoffeine.virtuoso.music.model.service.implementation;

import com.thecoffeine.virtuoso.music.model.entity.Collection;
import com.thecoffeine.virtuoso.music.model.repository.CollectionRepository;
import com.thecoffeine.virtuoso.music.model.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Service for work with Collection.
 *
 * @version 1.0
 */
@Service
public class CollectionServiceImpl implements CollectionService {

    /**
     * Repository for store collection.
     */
    @Autowired
    private CollectionRepository collectionRepository;


    /**
     * Find collection for page.
     *
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of collections per page.
     */
    @Override
    public Set<Collection> findAll( int page, int limit ) {
        return new HashSet<>(
            this.collectionRepository.findAll(
                new PageRequest(
                    page,
                    limit
                )
            ).getContent()
        );
    }

    /**
     * Create a new collection.
     *
     * @param collection Data for create new collection.
     *
     * @return Collection   Created collection.
     */
    @Override
    public Collection create( Collection collection ) {
        this.collectionRepository.save( collection );
        return this.collectionRepository.findOne( collection.getId() );
    }

    /**
     * Find Collection by id.
     *
     * @param id Id of collection.
     *
     * @return Collection.
     */
    @Override
    public Collection find( Long id ) {
        return this.collectionRepository.findOne( id );
    }

    /**
     * Update collection data.
     *
     * @param collection Composer for update.
     *
     * @return Collection Updated collection.
     */
    @Override
    public Collection update( Collection collection ) {
        return this.collectionRepository.save( collection );
    }

    /**
     * Delete collection by ID.
     *
     * @param id Id of collection.
     */
    @Override
    public void delete( Long id ) {
        this.collectionRepository.delete( id );
    }
}
