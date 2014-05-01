/// *** User :: Model :: Service :: Song    *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-05-01 22:09:13 :: 2014-05-.. ..:..:..
     *
     * @address /Ukraine/Petranka/Grushevskiy/234
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service;

import com.coffeine.virtuoso.module.user.model.entity.Song;
import com.coffeine.virtuoso.module.user.model.repository.SongRepository;
import java.util.List;

/**
 *
 * @version 1.0
 */
public interface SongService {

    //- SECTION :: MAIN -//
    /**
     * Get list of Song
     *
     * @return List < Song >
     */
    public List < Song > getList();

    /**
     * Get song by ID
     *
     * @param Id
     * @return Song
     */
    public Song getSong( Long Id );


    //- SECTION :: SET -//
    /**
     * Set repository for song. Use IoC(DI)
     *
     * @param songRespository
     */
    public void setSongRespository( SongRepository songRespository );
}
