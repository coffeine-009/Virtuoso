/// *** User :: Model :: Service :: Text    *** *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-04 15:11:06 :: 2014-06-.. ..:..:..
 *
 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service.implementation;

import com.coffeine.virtuoso.module.user.model.entity.Text;
import com.coffeine.virtuoso.module.user.model.repository.TextRepository;
import com.coffeine.virtuoso.module.user.model.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @version 1.0
 */
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
