/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service.implementation;

import com.thecoffeine.virtuoso.music.model.entity.StaffType;
import com.thecoffeine.virtuoso.music.model.repository.StaffTypeRepository;
import com.thecoffeine.virtuoso.music.model.service.StaffTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of staff type service.
 *
 * @version 1.0
 */
@Service
public class StaffTypeServiceImpl implements StaffTypeService {

    @Autowired
    protected StaffTypeRepository staffTypeRepository;


    /**
     * Find all types of staff.
     * 
     * @param page Page for search.
     * @param limit Count of items per page.
     *
     * @return List of staff types.
     */
    public List<StaffType> findAll( int page, int limit ) {
        return this.staffTypeRepository.findAll(
            new PageRequest( page, limit )
        )
            .getContent();
    }

    /**
     * Find staff type by id.
     * 
     * @param id Id of staff type
     * @return StaffType Found by id
     */
    public StaffType find( Long id) {
        return this.staffTypeRepository.findOne( id );
    }

    /**
     * Create a new type of Staff.
     * 
     * @param staffType Staff type
     * @return StaffType Created type of Staff
     */
    public StaffType create( StaffType staffType ) {
        return this.staffTypeRepository.save( staffType );
    }

    /**
     * Update exists type of Staff.
     * 
     * @param staffType Staff type
     * @return StaffType Updated type of video
     */
    public StaffType update( StaffType staffType ) {
        return this.staffTypeRepository.save( staffType );
    }

    /**
     * Delete StaffType.
     * 
     * @param id ID of existed type of staff
     */
    public void delete( Long id ) {
        this.staffTypeRepository.delete( id );
    }
}
