/// *** User :: Model :: Service :: Video   *** *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-04 11:26:56 :: 2014-06-.. ..:..:..
 *
 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service;

import com.coffeine.virtuoso.module.user.model.entity.Video;

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
    public List < Video > findAll(
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
