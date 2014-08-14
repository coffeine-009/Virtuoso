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
package com.coffeine.virtuoso.module.user.model.service.implementation;

import com.coffeine.virtuoso.module.user.model.entity.Song;
import com.coffeine.virtuoso.module.user.model.repository.SongRepository;
import com.coffeine.virtuoso.module.user.model.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for work with songs
 *
 * @version 1.0
 */
@Service
public class SongServiceImpl implements SongService {

    /// *** Properties  *** ///
    @Autowired
    private SongRepository songRespository;


    /// *** Methods     *** ///
    //- SECTION :: MAIN -//
    /**
     * Get list of Song
     *
     * @return List<Song> List of all songs
     */
    @Override
    public List < Song > getList() {
        return songRespository.findAll();
    }

    /**
     * Get song by ID
     *
     * @param Id Unique identificator of song
     * @return Song Found song
     */
    @Override
    public Song getSong( Long Id ) {
        return songRespository.findOne( Id );
    }

    /**
     * Save song
     *
     * @param song New song for save or update
     * @return Song Created or updated song
     */
    @Override
    public Song save( Song song ) {
        return this.songRespository.save( song );
    }
}
