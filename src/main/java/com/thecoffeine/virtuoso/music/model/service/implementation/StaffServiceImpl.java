/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service.implementation;

import com.thecoffeine.virtuoso.music.model.entity.Staff;
import com.thecoffeine.virtuoso.music.model.repository.StaffRepository;
import com.thecoffeine.virtuoso.music.model.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of staff service.
 *
 * @version 1.0
 */
@Service
public class StaffServiceImpl implements StaffService {

    /// *** Properties  *** ///
    @Autowired
    protected StaffRepository staffRepository;


    //- SECTION :: MAIN -//
    /**
     * Find staffs for page.
     *
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of song's staffs per page.
     */
    public List<Staff> findAll( int page, int limit ) {
        return this.staffRepository.findAll(
            new PageRequest( page, limit )
        )
            .getContent();
    }

    /**
     * Create.
     *
     * @param staff Data for create new staff
     * @return Staff Created
     */
    public Staff create( Staff staff ) {
        return this.staffRepository.save( staff );
    }

    /**
     * Find staffs by id.
     *
     * @param id Unique identificator
     * @return Staff Found
     */
    public Staff find( Long id ) {
        return this.staffRepository.findOne( id );
    }

    /**
     * Update.
     *
     * @param staff Staff for update
     * @return Staff Updated
     */
    public Staff update( Staff staff ) {
        return this.staffRepository.save( staff );
    }

    /**
     * Delete.
     *
     * @param id Identificator of staff
     */
    public void delete( Long id ) {
        this.staffRepository.delete( id );
    }
}
