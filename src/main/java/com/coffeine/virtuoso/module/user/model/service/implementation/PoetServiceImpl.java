/// *** User :: Model :: Service :: Poet    *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-06-04 15:07:16 :: 2014-10-01 00:33:59
     *
     * @address /Ukraine/Ivano-Frankivsk
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service.implementation;

import com.coffeine.virtuoso.module.user.model.entity.Poet;
import com.coffeine.virtuoso.module.user.model.repository.PoetRepository;
import com.coffeine.virtuoso.module.user.model.service.PoetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Service for work with post
 *
 * @version 1.0
 */
public class PoetServiceImpl implements PoetService {

    @Autowired
    private PoetRepository poetRepository;


    //- SECTION :: MAIN -//
    /**
     * Find Poets for page
     *
     * @param page  Number of page for search
     * @param limit Count of items per page
     * @return List < Poet > List of poets per page
     */
    public List < Poet > findAll( int page, int limit ) {
        return this.poetRepository.findAll(
            new PageRequest( page, limit )
        )
            .getContent();
    }

    /**
     * Create a new Poet
     *
     * @param poet Data for create new poet
     * @return Poet Created
     */
    public Poet create( Poet poet ) {
        return this.poetRepository.save( poet );
    }

    /**
     * Find Poet by id
     *
     * @param id Unique identificator
     * @return Poet Found
     */
    public Poet find( Long id ) {
        return this.poetRepository.findOne( id );
    }

    /**
     * Update
     *
     * @param poet Poet for update
     * @return Poet Updated poet
     */
    public Poet update( Poet poet ) {
        return this.poetRepository.save( poet );
    }

    /**
     * Delete
     *
     * @param id Identificator of poet
     */
    public void delete( Long id ) {
        this.poetRepository.delete( id );
    }
}
