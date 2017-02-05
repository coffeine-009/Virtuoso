/**
 * Copyright (c) 2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com">Vitaliy Tsutsman </a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service.implementation;

import com.thecoffeine.virtuoso.music.model.entity.Song;
import com.thecoffeine.virtuoso.music.model.repository.SongRepository;
import com.thecoffeine.virtuoso.music.model.service.SongService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;

/**
 * Service for work with songs.
 *
 * @version 1.0
 */
@Transactional
@Service
public class SongServiceImpl implements SongService {

    private static final Logger log = LogManager.getLogger( SongServiceImpl.class );

    /// *** Properties  *** ///
    @Autowired
    private SongRepository songRepository;


    /// *** Methods     *** ///
    //- SECTION :: MAIN -//
    /**
     * Get list of song for page.
     *
     * @param page Number of page for return.
     * @param limit Count items per page.
     *
     * @return List of Songs.
     */
    public List<Song> findAll( int page, int limit ) {
        //- Log request -//
        log.info( "Search songs[page={}, limit={}]", page, limit );

        //- Return found songs -//
        return songRepository.findAll(
            new PageRequest(
                Math.max( page - 1, 0 ),
                limit
            )
        )
            .getContent();
    }

    /**
     * Save song.
     *
     * @param song New song for create or update.
     * @return Song
     */
    @Override
    public Song create( Song song ) {
        return this.songRepository.save( song );
    }

    /**
     * Get song by ID.
     *
     * @param id    Unique id of song.
     *
     * @return Song.
     */
    @Override
    public Song find( Long id ) {
        return songRepository.findOne( id );
    }

    /**
     * Find songs by their ids.
     *
     * @param songsIds List of ids.
     *
     * @return List of songs.
     */
    @Override
    public Set<Song> findByIds( Set<Long> songsIds ) {
        return new HashSet<>( this.songRepository.findByIds( songsIds ) );
    }

    /**
     * Update.
     *
     * @param song Data for update
     * @return Songs
     */
    @Override
    public Song update( Song song ) {
        return this.songRepository.save( song );
    }

    /**
     * Delete song.
     *
     * @param id Identificator for delete
     */
    @Override
    public void delete( Long id ) {
        this.songRepository.delete( id );
    }
}
