/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Model :: Service :: VideoType   *** *** *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.music.model.entity.VideoType;

import java.util.List;

/**
 * Interface for video type service.
 *
 * @version 1.0
 */
public interface VideoTypeService {

    //- SECTION :: MAIN -//
    /**
     * Find all video types for page.
     *
     * @param page  Page for search
     * @param limit Count of items per page
     * @return List < VideoType > List of video types
     */
    public List < VideoType > findAll(
        int page,
        int limit
    );

    /**
     * Find video type by ID.
     *
     * @param id Identificator of video type
     * @return VideoType Found by ID
     */
    public VideoType find( Long id );

    /**
     * Create a new type of Video.
     *
     * @param videoType Video type
     * @return VideoType Created type of video
     */
    public VideoType create( VideoType videoType );

    /**
     * Update exists type of Video.
     *
     * @param videoType Video type
     * @return VideoType Created type of video
     */
    public VideoType update( VideoType videoType );

    /**
     * Delete VideoType.
     *
     * @param id ID of Existed type of video
     */
    public void delete( Long id );
}
