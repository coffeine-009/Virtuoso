/// *** User :: Model :: Service :: Song    *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-03-26 17:37:42 :: ....-..-.. ..:..:..
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service;

import com.coffeine.virtuoso.module.user.model.entity.Song;
import com.coffeine.virtuoso.module.user.model.repository.SongRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SongServiceImpl {

    /// *** Properties  *** ///
    @Autowired
    private SongRepository songRespository;


    /// *** Methods     *** ///
    //- SECTION :: MAIN -//
    /**
     * Get list of Song
     *
     * @return List < Song >
     */
    public List < Song > getList() {
        return songRespository.findAll();
    }

    //- SECTION :: SET -//
    /**
     * Set repository for song. Use IoC(DI)
     *
     * @param songRespository
     */
    public void setSongRespository( SongRepository songRespository ) {
        this.songRespository = songRespository;
    }
}
