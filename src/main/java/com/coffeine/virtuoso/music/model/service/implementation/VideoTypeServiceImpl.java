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
package com.coffeine.virtuoso.music.model.service.implementation;

import com.coffeine.virtuoso.music.model.entity.VideoType;
import com.coffeine.virtuoso.music.model.repository.VideoTypeRepository;
import com.coffeine.virtuoso.music.model.service.VideoTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of video type service
 *
 * @version 1.0
 */
@Service
public class VideoTypeServiceImpl implements VideoTypeService {

    @Autowired
    protected VideoTypeRepository videoTypeRepository;


    /**
     * Find all video types for page
     *
     * @param page  Page for search
     * @param limit Count of items per page
     * @return List < VideoType > List of video types
     */
    public List<VideoType> findAll(
        int page,
        int limit
    ) {
        return this.videoTypeRepository.findAll(
            new PageRequest( page, limit )
        )
            .getContent();
    }

    /**
     * Find video type by ID
     *
     * @param id Identificator of video type
     * @return VideoType Found by ID
     */
    public VideoType find( Long id ) {
        return this.videoTypeRepository.findOne( id );
    }

    /**
     * Create a new type of Video
     *
     * @param videoType Video type
     * @return VideoType Created type of video
     */
    public VideoType create( VideoType videoType ) {
        return this.videoTypeRepository.save( videoType );
    }

    /**
     * Update exists type of Video
     *
     * @param videoType Video type
     * @return VideoType Created type of video
     */
    public VideoType update( VideoType videoType ) {
        return this.videoTypeRepository.save( videoType );
    }

    /**
     * Delete VideoType
     *
     * @param id ID of Existed type of video
     */
    public void delete( Long id ) {
        this.videoTypeRepository.delete( id );
    }
}
