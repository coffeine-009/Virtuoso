/// *** User :: Model :: Service :: StaffType    *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Prodyser
     *
     * @author Namisnyk Valentyn <Valentun_Prodyser@ukr.net>
     *
     * @date 2014-15-10 22:07:38 :: 2014-15-10 22:26:55
     *
     * @address /Ukraine/Ivano-Frankivsk/Mateyku/5
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///

package com.coffeine.virtuoso.module.user.model.service;

import com.coffeine.virtuoso.module.user.model.entity.StaffType;

import java.util.List;

/**
 * Interface for staff type service
 
 * @version 1.0
 */
public interface StaffTypeService {

    //-Section :: Main -//
    /**
     * Find all staff type for page
     * 
     * @param page Page for search
     * @param limit Count items per page
     * @return List < StaffType > List of staff types
     */
    public List <StaffType> findAll(
        int page,
        int limit
    );

    /**
     * Find staff type by id
     * 
     * @param id Identificator of staff type
     * @return StaffType Found by id
     */
    public StaffType find( Long id );

    /**
     * Create a new type of Staff
     * 
     * @param staffType Staff type
     * @return StaffType Created type of Staff
     */
    public StaffType create( StaffType staffType );

    /**
     * Update exists type of Staff
     * 
     * @param staffType Staff type
     * @return StaffType Updated type of video
     */
    public StaffType update( StaffType staffType);

    /**
     * Delete StaffType
     * 
     * @param id ID of existed type of staff
     */
    public void delete( Long id);
}
