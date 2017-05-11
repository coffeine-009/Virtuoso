/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service.implementation;

import com.thecoffeine.virtuoso.music.model.entity.Poet;
import com.thecoffeine.virtuoso.music.model.repository.PoetRepository;
import com.thecoffeine.virtuoso.music.model.service.PoetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;

/**
 * Service for work with post.
 *
 * @version 1.0
 */
@Transactional
@Service
public class PoetServiceImpl implements PoetService {

    @Autowired
    private PoetRepository poetRepository;


    //- SECTION :: MAIN -//
    /**
     * Find Poets for page.
     *
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of poets per page.
     */
    public List<Poet> findAll( int page, int limit ) {
        return this.poetRepository.findAll(
            new PageRequest( page, limit )
        )
            .getContent();
    }

    /**
     * Create a new Poet.
     *
     * @param poet Data for create new poet
     * @return Poet Created
     */
    public Poet create( Poet poet ) {
        return this.poetRepository.save( poet );
    }

    /**
     * Find Poet by id.
     *
     * @param id Unique identificator
     * @return Poet Found
     */
    public Poet find( Long id ) {
        return this.poetRepository.findOne( id );
    }

    /**
     * Find Poets by ids.
     *
     * @param ids Unique ids.
     * @return Poet Found
     */
    @Override
    public Set<Poet> find( List<Long> ids ) {
        return new HashSet<>( this.poetRepository.find( ids ) );
    }

    /**
     * Update.
     *
     * @param poet Poet for update
     * @return Poet Updated poet
     */
    public Poet update( Poet poet ) {
        return this.poetRepository.save( poet );
    }

    /**
     * Delete.
     *
     * @param id Identificator of poet
     */
    public void delete( Long id ) {
        this.poetRepository.delete( id );
    }
}
