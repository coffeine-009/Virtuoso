/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Model :: Service :: Text    *** *** *** *** *** *** *** *** ///

//*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.music.model.service.implementation;

import com.coffeine.virtuoso.music.model.entity.Text;
import com.coffeine.virtuoso.music.model.repository.TextRepository;
import com.coffeine.virtuoso.music.model.service.TextService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 */
@Service
public class TextServiceImpl implements TextService {

    /// *** Properties  *** ///
    @Autowired
    protected TextRepository textRepository;


    /// *** Methods     *** ///
    //- SECTION :: MAIN -//
    /**
     * Find song's texts for page
     *
     * @param page  Number of page for search
     * @param limit Count of items per page
     * @return List < Text > List of song's texts per page
     */
    public List< Text > findAll( int page, int limit ) {
        return this.textRepository.findAll(
            new PageRequest( page, limit )
        )
            .getContent();
    }

    /**
     * Create
     *
     * @param text Data for create new song's text
     * @return Text Created
     */
    public Text create( Text text ) {
        return this.textRepository.save( text );
    }

    /**
     * Find song's text by id
     *
     * @param id Unique identificator
     * @return Text Found
     */
    public Text find( Long id ) {
        return this.textRepository.findOne( id );
    }

    /**
     * Update
     *
     * @param text Text for update
     * @return Text Updated text
     */
    public Text update( Text text ) {
        return this.textRepository.save( text );
    }

    /**
     * Delete
     *
     * @param id Identificator of text
     */
    public void delete( Long id ) {
        this.textRepository.delete( id );
    }
}
