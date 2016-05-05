/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.music.model.entity.Video;

import java.util.List;

/**
 * Service for work with video.
 *
 * @version 1.0
 */
public interface VideoService {

    //- SECTION :: MAIN -//
    /**
     * Find all videos per page.
     *
     * @param page  Requested page.
     * @param limit Count items per page.
     *
     * @return List of Video.
     */
    List<Video> findAll(
        int page,
        int limit
    );

    /**
     * Create a new video.
     *
     * @param video Data for create.
     *
     * @return Video.
     */
    Video create( Video video );

    /**
     * Find video.
     *
     * @param id Identificator of video.
     *
     * @return Video.
     */
    Video find( Long id );

    /**
     * Update video.
     *
     * @param video Data for update.
     *
     * @return Video.
     */
    Video update( Video video );

    /**
     * Delete video.
     *
     * @param id Identificator of video.
     */
    void delete( Long id );
}
