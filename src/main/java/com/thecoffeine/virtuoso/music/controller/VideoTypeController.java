/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.controller;

import com.thecoffeine.virtuoso.music.model.entity.VideoType;
import com.thecoffeine.virtuoso.music.model.service.VideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
 * Controller for managing types of video.
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/music/videos/types" )
public class VideoTypeController {

    /// *** Properties  *** ///
    @Autowired
    protected VideoTypeService videoTypeService;


    /// *** Methods     *** ///
    //- SECTION :: ACTION -//
    /**
     * Find all video types per page.
     *
     * @param page  Number of page
     * @param limit Count of items per page
     *
     * @return List of video types
     */
    @RequestMapping( method = RequestMethod.GET )
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<VideoType> findAllAction(
        @RequestParam( value = "page", required = false, defaultValue = "1" )
        int page,

        @RequestParam( value = "limit", required = false, defaultValue = "10" )
        int limit
    ) {
        //- Search page -//
        return this.videoTypeService.findAll(
            Math.min( page - 1, 0 ),
            limit
        );
    }

    /**
     * Create a new video type.
     *
     * @param videoType Type of video for create
     * @param response  Used for set HTTP status
     *
     * @return Created type of video
     */
    @RequestMapping( method = RequestMethod.POST )
    @ResponseBody
    public VideoType createAction(
        @RequestBody
        @Valid
        VideoType videoType,

        HttpServletResponse response
    ) {
        //- Try to create new type of video -//
        try {
            //- Set HTTP status -//
            response.setStatus( HttpStatus.CREATED.value() );

            //- Success. Return created video type -//
            return this.videoTypeService.create( videoType );
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create video type -//
            response.setStatus( HttpServletResponse.SC_CONFLICT );
        }

        return null;
    }

    /**
     * Find.
     *
     * @param id          ID of video type.
     * @param response    Used for set HTTP status.
     *
     * @return Found video type.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public VideoType findAction(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Search -//
            VideoType videoType = this.videoTypeService.find( id );

            //- Check -//
            notNull( videoType );

            return videoType;
        } catch ( IllegalArgumentException e ) {
            //- Failure. Cannot find video type -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return null;
    }

    /**
     * Update video type.
     *
     * @param id        ID of video type for update
     * @param videoType Data for update
     * @param response  Used for set HTTP status
     *
     * @return Updated type of video.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    @ResponseBody
    public VideoType updateAction(
        @PathVariable( "id" )
        Long id,

        @RequestBody
        @Valid
        VideoType videoType,

        HttpServletResponse response
    ) {
        //- Search video type by ID -//
        VideoType videoTypeOrigin = this.videoTypeService.find( id );

        if ( videoTypeOrigin == null ) {
            //- Not exists ID of video type -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
            return null;
        }

        //- Try to create new type of video -//
        try {
            //- Set HTTP status -//
            response.setStatus( HttpStatus.OK.value() );

            //- Update value  -//
            videoTypeOrigin.setTitle( videoType.getTitle() );
            videoTypeOrigin.setCode( videoType.getCode() );
            videoTypeOrigin.setDescription( videoType.getDescription() );

            //- Success. Return created video type -//
            return this.videoTypeService.update( videoTypeOrigin );
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create video type -//
            response.setStatus( HttpStatus.CONFLICT.value() );
        }

        return null;
    }

    /**
     * Delete video type.
     *
     * @param id        ID of video type for delete
     * @param response  Used for set HTTP status
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    @ResponseBody
    public void deleteAction(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        //- Try to delete Video type -//
        try {
            //- Success. Delete video type -//
            this.videoTypeService.delete( id );
        } catch ( DataAccessException e ) {
            //- Not exists ID -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
    }
}
