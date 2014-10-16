/// *** User :: Model :: Service :: StaffType    *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Prodyser
     *
     * @author Namisnyk Valentyn <Valentun_Prodyser@ukr.net>
     *
     * @date 2014-15-10 22:30:26 :: 2014-15-10 22:54::10
     *
     * @address /Ukraine/Ivano-Frankivsk/Mateyku/5
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service.implementation;

import com.coffeine.virtuoso.module.user.model.entity.StaffType;
import com.coffeine.virtuoso.module.user.model.repository.StaffTypeRepository;
import com.coffeine.virtuoso.module.user.model.service.StaffTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Implementation of staff type service
 *
 * @version 1.0
 */
@Service
public class StaffTypeServiceImpl implements StaffTypeService{

    @Autowired
    protected StaffTypeRepository staffTypeRepository;


    /**
     * Find all types of staff
     * 
     * @param page Page for search
     * @param limit Count of items per page
     * @return List < StaffType > List of staff types
     */
    public List < StaffType > findAll(
    int page,
    int limit
    ) {
        return this.staffTypeRepository.findAll(
            new PageRequest( page, limit )
        )
            .getContent();
    }

    /**
     * Find staff type by id
     * 
     * @param id Identificator of staff type
     * @return StaffType Found by id
     */
    public StaffType find( Long id) {
        return this.staffTypeRepository.findOne( id );
    }

    /**
     * Create a new type of Staff
     * 
     * @param staffType Staff type
     * @return StaffType Created type of Staff
     */
    public StaffType create( StaffType staffType ) {
        return this.staffTypeRepository.save( staffType );
    }

    /**
     * Update exists type of Staff
     * 
     * @param staffType Staff type
     * @return StaffType Updated type of video
     */
    public StaffType update( StaffType staffType ) {
        return this.staffTypeRepository.save( staffType );
    }

    /**
     * Delete StaffType
     * 
     * @param id ID of existed type of staff
     */
    public void delete( Long id ) {
        this.staffTypeRepository.delete( id );
    }
}
