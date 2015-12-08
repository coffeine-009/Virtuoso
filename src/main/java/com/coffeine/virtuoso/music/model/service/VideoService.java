/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Model :: Service :: Video   *** *** *** *** *** *** *** *** ///

//*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.music.model.entity.Video;

import java.util.List;

/**
 * Service for work with video
 *
 * @version 1.0
 */
public interface VideoService {

    //- SECTION :: MAIN -//
    /**
     * Find all videos per page
     *
     * @param page  Requested page
     * @param limit Count items per page
     * @return List < Video >
     */
    public List <Video> findAll(
        int page,
        int limit
    );

    /**
     * Create a new video
     *
     * @param video Data for create
     * @return Video
     */
    public Video create( Video video );

    /**
     * Find video
     *
     * @param id Identificator of video
     * @return Video
     */
    public Video find( Long id );

    /**
     * Update video
     *
     * @param video Data for update
     * @return Video
     */
    public Video update( Video video );

    /**
     * Delete video
     * @param id Identificator of video
     */
    public void delete( Long id );
}
