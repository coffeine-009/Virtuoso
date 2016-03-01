/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.service.implementation;

import com.coffeine.virtuoso.music.model.entity.Song;
import com.coffeine.virtuoso.music.model.repository.SongRepository;
import com.coffeine.virtuoso.music.model.service.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for work with songs.
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
     * Get list of song for page.
     *
     * @param page Number of page for return
     * @param limit Count items per page
     * @return List < Song >
     */
    public List <Song> findAll( int page, int limit ) {
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
     * @param song New song for create or update.
     * @return Song
     */
    @Override
    public Song create( Song song ) {
        return this.songRespository.save( song );
    }

    /**
     * Get song by ID.
     *
     * @param id Unique identificator of song
     * @return Song
     */
    @Override
    public Song find( Long id) {
        return songRespository.findOne(id);
    }

    /**
     * Update.
     *
     * @param song Data for update
     * @return Songs
     */
    @Override
    public Song update( Song song ) {
        return this.songRespository.save( song );
    }

    /**
     * Delete song.
     *
     * @param id Identificator for delete
     */
    @Override
    public void delete( Long id ) {
        this.songRespository.delete( id );
    }
}
