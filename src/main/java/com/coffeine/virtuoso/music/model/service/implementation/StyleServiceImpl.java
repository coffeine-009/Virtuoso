/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.service.implementation;

import com.coffeine.virtuoso.music.model.entity.Style;
import com.coffeine.virtuoso.music.model.repository.StyleRepository;
import com.coffeine.virtuoso.music.model.service.StyleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of style service.
 *
 * @version 1.0
 */
@Service
public class StyleServiceImpl implements StyleService {

    /// *** Properties  *** ///
    @Autowired
    protected StyleRepository styleRepository;


    /// *** Methods     *** ///
    //- SECTION :: MAIN -//
    /**
     * Find styles for page.
     *
     * @param page  Number of page for search
     * @param limit Count of items per page
     * @return List < Style > List of song's styles per page
     */
    public List<Style> findAll( int page, int limit ) {
        return this.styleRepository.findAll(
            new PageRequest( page, limit )
        )
            .getContent();
    }

    /**
     * Create
     *
     * @param style Data for create new style.
     * @return Style Created
     */
    public Style create( Style style ) {
        return this.styleRepository.save( style );
    }

    /**
     * Find styles by id.
     *
     * @param id Unique identificator
     * @return Style Found
     */
    public Style find( Long id ) {
        return this.styleRepository.findOne( id );
    }

    /**
     * Update.
     *
     * @param style Style for update
     * @return Style Updated
     */
    public Style update( Style style ) {
        return this.styleRepository.save( style );
    }

    /**
     * Delete.
     *
     * @param id Identificator of style
     */
    public void delete( Long id ) {
        this.styleRepository.delete( id );
    }
}
