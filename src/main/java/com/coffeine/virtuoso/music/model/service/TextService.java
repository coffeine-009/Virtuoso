/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.music.model.entity.Text;

import java.util.List;

/**
 * Service for work with text.
 *
 * @version 1.0
 */
public interface TextService {

    //- SECTION :: MAIN -//
    /**
     * Find song's texts for page.
     *
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of song's texts per page
     */
    List<Text> findAll( int page, int limit );

    /**
     * Create.
     *
     * @param text Data for create new song's text.
     *
     * @return Text Created.
     */
    Text create( Text text );

    /**
     * Find song's text by id.
     *
     * @param id Unique identificator.
     *
     * @return Text Found.
     */
    Text find( Long id );

    /**
     * Update.
     *
     * @param text Text for update.
     *
     * @return Text Updated text.
     */
    Text update( Text text );

    /**
     * Delete.
     *
     * @param id Identificator of text.
     */
    void delete( Long id );
}
