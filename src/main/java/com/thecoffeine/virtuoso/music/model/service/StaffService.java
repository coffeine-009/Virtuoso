/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service;

import com.thecoffeine.virtuoso.music.model.entity.Staff;

import java.util.List;

/**
 * Staff service.
 *
 * @version 1.0
 */
public interface StaffService {

    //- SECTION :: MAIN -//
    /**
     * Find staffs for page.
     *
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of song's staffs per page.
     */
    List<Staff> findAll( int page, int limit );

    /**
     * Create.
     *
     * @param staff Data for create new staff.
     *
     * @return Staff Created
     */
    Staff create( Staff staff );

    /**
     * Find staffs by id.
     *
     * @param id Unique identificator.
     *
     * @return Staff Found.
     */
    Staff find( Long id );

    /**
     * Update.
     *
     * @param staff Staff for update.
     *
     * @return Staff Updated.
     */
    Staff update( Staff staff );

    /**
     * Delete.
     *
     * @param id Identificator of staff.
     */
    void delete( Long id );
}
