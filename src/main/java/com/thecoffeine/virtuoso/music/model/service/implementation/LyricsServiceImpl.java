/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service.implementation;

import com.thecoffeine.virtuoso.music.model.entity.Lyrics;
import com.thecoffeine.virtuoso.music.model.repository.TextRepository;
import com.thecoffeine.virtuoso.music.model.service.LyricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for work with lyricses.
 *
 * @version 1.0
 */
@Service
public class LyricsServiceImpl implements LyricsService {

    /// *** Properties  *** ///
    @Autowired
    protected TextRepository textRepository;


    /// *** Methods     *** ///
    //- SECTION :: MAIN -//
    /**
     * Find song's lyricses for page.
     *
     * @param page  Number of page for search.
     * @param limit Count of items per page.
     *
     * @return List of song's lyricses per page.
     */
    public List<Lyrics> findAll( int page, int limit ) {
        return this.textRepository.findAll(
            new PageRequest( page, limit )
        )
            .getContent();
    }

    /**
     * Create.
     *
     * @param lyrics Data for create new song's lyrics
     * @return Lyrics Created
     */
    public Lyrics create( Lyrics lyrics ) {
        return this.textRepository.save( lyrics );
    }

    /**
     * Find song's lyrics by id.
     *
     * @param id Unique identificator
     * @return Lyrics Found
     */
    public Lyrics find( Long id ) {
        return this.textRepository.findOne( id );
    }

    /**
     * Update.
     *
     * @param lyrics Lyrics for update
     * @return Lyrics Updated lyrics
     */
    public Lyrics update( Lyrics lyrics ) {
        return this.textRepository.save( lyrics );
    }

    /**
     * Delete.
     *
     * @param id Identificator of lyrics
     */
    public void delete( Long id ) {
        this.textRepository.delete( id );
    }
}
