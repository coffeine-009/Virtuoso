/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service;

import com.thecoffeine.virtuoso.music.model.entity.StaffType;

import java.util.List;

/**
 * Interface for staff type service.
 
 * @version 1.0
 */
public interface StaffTypeService {

    //-Section :: Main -//
    /**
     * Find all staff type for page.
     * 
     * @param page Page for search.
     * @param limit Count items per page.
     *
     * @return List of staff types.
     */
    List<StaffType> findAll(
        int page,
        int limit
    );

    /**
     * Find staff type by id.
     * 
     * @param id Identificator of staff type.
     *
     * @return StaffType Found by id.
     */
    StaffType find( Long id );

    /**
     * Create a new type of Staff.
     * 
     * @param staffType Staff type.
     *
     * @return StaffType Created type of Staff.
     */
    StaffType create( StaffType staffType );

    /**
     * Update exists type of Staff.
     * 
     * @param staffType Staff type.
     *
     * @return StaffType Updated type of video.
     */
    StaffType update( StaffType staffType);

    /**
     * Delete StaffType.
     * 
     * @param id ID of existed type of staff.
     */
    void delete( Long id);
}
