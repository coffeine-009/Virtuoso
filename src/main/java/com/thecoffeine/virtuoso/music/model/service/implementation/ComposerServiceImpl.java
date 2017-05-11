/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service.implementation;

import com.thecoffeine.virtuoso.music.model.entity.Composer;
import com.thecoffeine.virtuoso.music.model.repository.ComposerRepository;
import com.thecoffeine.virtuoso.music.model.service.ComposerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;

/**
 * Service for work with composer.
 *
 * @version 1.0
 */
@Transactional
@Service
public class ComposerServiceImpl implements ComposerService {

    @Autowired
    private ComposerRepository composerRepository;


    //- SECTION :: MAIN -//
    /**
     * Find composers for page.
     *
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of composers per page.
     */
    public Set<Composer> findAll( int page, int limit )  {
        return new HashSet<>(
            this.composerRepository.findAll(
                new PageRequest(
                    page,
                    limit
                )
            )
                .getContent()
        );
    }

    /**
     * Create a new composer.
     *
     * @param composer Data for create new composer
     * @return Composer Created composer
     */
    public Composer create( Composer composer ) {
        return this.composerRepository.save( composer );
    }

    /**
     * Find Composer by id.
     *
     * @param id        Identificator of composer
     * @return Composer
     */
    public Composer find( Long id ) {
        return this.composerRepository.findOne( id );
    }

    /**
     * Find Composer by ids.
     *
     * @param ids Ids of composer
     * @return Composer found composer
     */
    @Override
    public Set<Composer> find( List<Long> ids ) {
        return new HashSet<>( this.composerRepository.find( ids ) );
    }

    /**
     * Update composer data.
     *
     * @param composer Composer for update
     * @return Composer Updated composer
     */
    public Composer update( Composer composer ) {
        return this.composerRepository.save( composer );
    }

    /**
     * Delete composer by ID.
     *
     * @param id Identificator of composer
     */
    public void delete( Long id ) {
        this.composerRepository.delete( id );
    }
}
