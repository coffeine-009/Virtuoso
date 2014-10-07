/// *** User :: Model :: Service :: Text    *** *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-04 11:25:06 :: 2014-10-05 11:51:02
 *
 * @address /Ukraine/Ivano-Frankivsk
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service;

import com.coffeine.virtuoso.module.user.model.entity.Text;

import java.util.List;

/**
 * @version 1.0
 */
public interface TextService {

    //- SECTION :: MAIN -//
    /**
     * Find song's texts for page
     *
     * @param page  Number of page for search
     * @param limit Count of items per page
     * @return List < Text > List of song's texts per page
     */
    public List< Text > findAll( int page, int limit );

    /**
     * Create
     *
     * @param text Data for create new song's text
     * @return Text Created
     */
    public Text create( Text text );

    /**
     * Find song's text by id
     *
     * @param id Unique identificator
     * @return Text Found
     */
    public Text find( Long id );

    /**
     * Update
     *
     * @param text Text for update
     * @return Text Updated text
     */
    public Text update( Text text );

    /**
     * Delete
     *
     * @param id Identificator of text
     */
    public void delete( Long id );
}
