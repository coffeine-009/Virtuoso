/// *** User :: Model :: Service :: Song    *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-03-26 17:37:42 :: 2014-10-05 12:30:32
     *
     * @address /Ukraine/Ivano-Frankivsk
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service.implementation;

import com.coffeine.virtuoso.module.user.model.entity.Song;
import com.coffeine.virtuoso.module.user.model.repository.SongRepository;
import com.coffeine.virtuoso.module.user.model.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
     * Get list of song for page
     *
     * @param page Number of page for return
     * @param limit Count items per page
     * @return List < Song >
     */
    public List < Song > findAll( int page, int limit ) {
        return songRespository.findAll(
            new PageRequest(
                Math.max( page - 1, 0 ),
                limit
            )
        )
            .getContent();
    }

    /**
     * Save song
     *
     * @param song New song for create or update
     * @return Song
     */
    @Override
    public Song create( Song song ) {
        return this.songRespository.save( song );
    }

    /**
     * Get song by ID
     *
     * @param Id Unique identificator of song
     * @return Song
     */
    @Override
    public Song find( Long Id ) {
        return songRespository.findOne( Id );
    }

    /**
     * Update
     *
     * @param song Data for update
     * @return Songs
     */
    @Override
    public Song update( Song song ) {
        return this.songRespository.save( song );
    }

    /**
     * Delete song
     *
     * @param id Identificator for delete
     */
    @Override
    public void delete( Long id ) {
        this.songRespository.delete( id );
    }
}
