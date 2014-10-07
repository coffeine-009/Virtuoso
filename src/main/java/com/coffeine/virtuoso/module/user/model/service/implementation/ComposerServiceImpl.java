/// *** User :: Model :: Service :: Composer    *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-06-04 15:05:41 :: 2014-06-.. ..:..:..
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service.implementation;

import com.coffeine.virtuoso.module.user.model.entity.Composer;
import com.coffeine.virtuoso.module.user.model.repository.ComposerRepository;
import com.coffeine.virtuoso.module.user.model.service.ComposerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Service for work with composer
 *
 * @version 1.0
 */
public class ComposerServiceImpl implements ComposerService {

    @Autowired
    private ComposerRepository composerRepository;


    //- SECTION :: MAIN -//
    /**
     * Find composers for page
     *
     * @param page  Number of page for search
     * @param limit Count of items per page
     * @return List < Composer > List of composers per page
     */
    public List< Composer > findAll( int page, int limit )  {
        return this.composerRepository.findAll(
            new PageRequest(
                page,
                limit
            )
        )
            .getContent();
    }

    /**
     * Create a new composer
     *
     * @param composer Data for create new composer
     * @return Composer Created composer
     */
    public Composer create( Composer composer ) {
        return this.composerRepository.save( composer );
    }

    /**
     * Find Composer by id
     *
     * @param id Identificator of composer
     * @return Composer found composer
     */
    public Composer find( Long id ) {
        return this.composerRepository.findOne( id );
    }

    /**
     * Update composer data
     *
     * @param composer Composer for update
     * @return Composer Updated composer
     */
    public Composer update( Composer composer ) {
        return this.composerRepository.save( composer );
    }

    /**
     * Delete composer by ID
     *
     * @param id Identificator of composer
     */
    public void delete( Long id ) {
        this.composerRepository.delete( id );
    }
}
