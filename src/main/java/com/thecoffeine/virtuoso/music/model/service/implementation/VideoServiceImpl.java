/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service.implementation;

import com.thecoffeine.virtuoso.music.model.entity.Video;
import com.thecoffeine.virtuoso.music.model.repository.VideoRepository;
import com.thecoffeine.virtuoso.music.model.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of video service.
 *
 * @version 1.0
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    protected VideoRepository videoRepository;


    //- SECTION :: MAIN -//
    /**
     * Find all videos per page.
     *
     * @param page  Requested page.
     * @param limit Count items per page.
     *
     * @return List of Videos.
     */
    public List<Video> findAll(
            int page,
            int limit
    ) {
        return this.videoRepository.findAll(
            new PageRequest(
                page,
                limit
            )
        )
            .getContent();
    }

    /**
     * Create a new video.
     *
     * @param video Data for create
     * @return Video
     */
    public Video create( Video video ) {
        return this.videoRepository.save( video );
    }

    /**
     * Find video.
     *
     * @param id Identificator of video
     * @return Video
     */
    public Video find( Long id ) {
        return this.videoRepository.findOne( id );
    }

    /**
     * Update video.
     *
     * @param video Data for update
     * @return Video
     */
    public Video update( Video video ) {
        return this.videoRepository.save( video );
    }

    /**
     * Delete video.
     * @param id Identificator of video
     */
    public void delete( Long id ) {
        this.videoRepository.delete( id );
    }
}
