/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.music.model.entity.Song;
import com.coffeine.virtuoso.music.model.entity.Video;
import com.coffeine.virtuoso.music.model.entity.VideoType;
import com.coffeine.virtuoso.music.model.service.SongService;
import com.coffeine.virtuoso.music.model.service.VideoService;
import com.coffeine.virtuoso.music.model.service.VideoTypeService;
import com.coffeine.virtuoso.music.view.form.VideoForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.util.Assert.notNull;

/**
 * Controller for video.
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/user/video" )
public class VideoController {

//    private final static

    /// *** Properties  *** ///
    @Autowired
    protected VideoTypeService videoTypeService;

    @Autowired
    private SongService songService;

    @Autowired
    protected VideoService videoService;


    /// *** Methods     *** ///
    //- SECTION :: ACTION -//
    /**
     * Find all videos per page.
     *
     * @param page  Number of page
     * @param limit Count of items per page
     *
     * @return List of videos
     */
    @RequestMapping( method = RequestMethod.GET )
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<Video> findAllAction(
        @RequestParam( value = "page", required = false, defaultValue = "1" )
        int page,

        @RequestParam( value = "limit", required = false, defaultValue = "10" )
        int limit
    ) {
        //- Search page -//
        return this.videoService.findAll(
            Math.max( page - 1, 0 ),
            limit
        );
    }

    /**
     * Create a new video.
     *
     * @param form      Video data for create.
     * @param response  Used for set HTTP status.
     *
     * @return Video.
     */
    @RequestMapping( method = RequestMethod.POST )
    @ResponseBody
    public Video createAction(
        @RequestBody
        @Valid
        VideoForm form,

        HttpServletResponse response
    ) {
        //- Try to create new type of video -//
        try {
            //- Search video type -//
            VideoType videoType = this.videoTypeService.find( form.getVideoTypeId() );
            Song song = this.songService.find( form.getSongId() );

            //- Check -//
            notNull( videoType );
            notNull( song );

            //- Set HTTP status -//
            response.setStatus( HttpStatus.CREATED.value() );

            //- Success. Return created video type -//
            return this.videoService.create(
                new Video(
                    song,
                    videoType,
                    form.getLocale(),
                    form.getTitle(),
                    form.getDescription(),
                    form.getFileName()
                )
            );
        } catch ( IllegalArgumentException e ) {
            //- Failure. Cannot find related entities -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create video type -//
            response.setStatus( HttpServletResponse.SC_CONFLICT );
        }

        return null;
    }

    /**
     * Find.
     *
     * @param id          ID of video.
     * @param response    Used for set HTTP status.
     *
     * @return Found video.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public Video findAction(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Search -//
            Video video = this.videoService.find( id );

            //- Check -//
            notNull( video );

            return video;
        } catch ( IllegalArgumentException e ) {
            //- Failure. Cannot find video type -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return null;
    }

    /**
     * Update video.
     *
     * @param id        ID of video for update
     * @param form      Data for update
     * @param response  Used for set HTTP status
     *
     * @return Video
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    @ResponseBody
    public Video updateAction(
        @PathVariable( "id" )
        Long id,

        @RequestBody
        @Valid
        VideoForm form,

        HttpServletResponse response
    ) {
        //- Try to update the video -//
        try {
            //- Search video type -//
            VideoType videoType = this.videoTypeService.find( form.getVideoTypeId() );
            Song song = this.songService.find( form.getSongId() );
            Video video = this.videoService.find( id );

            //- Check -//
            notNull( videoType );
            notNull( song );
            notNull( video );

            //- Update value  -//
            video.setSong( song );
            video.setVideoType( videoType );
            video.setLocale( form.getLocale() );
            video.setTitle( form.getTitle() );
            video.setDescription( form.getDescription() );
            video.setFileName( form.getFileName() );

            //- Success. Return created form type -//
            return this.videoService.update( video );
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create form type -//
            response.setStatus( HttpServletResponse.SC_CONFLICT );
        }

        return null;
    }

    /**
     * Delete video.
     *
     * @param id        ID of video for delete
     * @param response  Used for set HTTP status
     */
    @RequestMapping( value = "/{id}" )
    @ResponseBody
    public void deleteAction(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        //- Try to delete Video -//
        try {
            //- Success. Delete video -//
            this.videoService.delete( id );
        } catch ( EmptyResultDataAccessException e ) {
            //- Failure. Cannot find -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
    }
}
