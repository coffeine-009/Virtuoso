/// *** User :: Model :: Service :: Staff   *** *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-04 11:00:53 :: 2014-10-05 12:12:12
 *
 * @address /Ukraine/Ivano-Frankivsk
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service;

import com.coffeine.virtuoso.module.user.model.entity.Staff;

import java.util.List;

/**
 * @version 1.0
 */
public interface StaffService {

    //- SECTION :: MAIN -//
    /**
     * Find staffs for page
     *
     * @param page  Number of page for search
     * @param limit Count of items per page
     * @return List < Staff > List of song's staffs per page
     */
    public List< Staff > findAll( int page, int limit );

    /**
     * Create
     *
     * @param staff Data for create new staff
     * @return Staff Created
     */
    public Staff create( Staff staff );

    /**
     * Find staffs by id
     *
     * @param id Unique identificator
     * @return Staff Found
     */
    public Staff find( Long id );

    /**
     * Update
     *
     * @param staff Staff for update
     * @return Staff Updated
     */
    public Staff update( Staff staff );

    /**
     * Delete
     *
     * @param id Identificator of staff
     */
    public void delete( Long id );
}
