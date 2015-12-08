/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.music.model.entity.Video;
import com.coffeine.virtuoso.music.model.entity.VideoType;
import com.coffeine.virtuoso.music.model.service.VideoService;
import com.coffeine.virtuoso.music.model.service.VideoTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

/**
 * Controller for video
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/user/video" )
public class VideoController {

//    private final static

    /// *** Properties  *** ///
    @Autowired
    protected VideoService videoService;

    @Autowired
    protected VideoTypeService videoTypeService;


    /// *** Methods     *** ///
    //- SECTION :: ACTION -//
    /**
     * Find all videos per page
     *
     * @param page  Number of page
     * @param limit Count of items per page
     * @return List < Video > List of videos
     */
    @GET
    @RequestMapping( value = "/list/{PAGE}/{LIMIT}" )
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List <Video> findAllAction(
            @PathVariable( "PAGE" )
            int page,

            @PathVariable( "LIMIT" )
            int limit
    ) {
        //- Search page -//
        return this.videoService.findAll(
            Math.max( page - 1, 0 ),
            limit
        );
    }

    /**
     * Create a new video
     *
     * @param video Video for create
     * @param response  Used for set HTTP status
     * @return Video
     */
    @POST
    @RequestMapping( value = "/" )
    @ResponseBody
    public Video createAction(
            @RequestBody
            @Valid
            Video video,

            HttpServletResponse response
    ) {
        //- Search video type -//
        VideoType videoType = this.videoTypeService.find( 1L );

        // TODO: not null

        //- Set relations -//
        video.setVideoType( videoType );

        //- Try to create new type of video -//
        try {
            //- Set HTTP status -//
            response.setStatus( HttpStatus.CREATED.value() );

            //- Success. Return created video type -//
            return this.videoService.create( video );
        }
        catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create video type -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }
        catch ( Exception e ) {
            //- Failure. Can not to create video type -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }

        return null;
    }

    /**
     * Update video
     *
     * @param id        ID of video for update
     * @param video     Data for update
     * @param response  Used for set HTTP status
     * @return Video
     */
    @PUT
    @RequestMapping( value = "/{ID}", method = RequestMethod.PUT )
    @ResponseBody
    public Video updateAction(
            @PathVariable( "ID" )
            Long id,

            @RequestBody
            @Valid
            Video video,

            HttpServletResponse response
    ) {
        //- Try to create new type of video -//
        try {
            //- Set HTTP status -//
            response.setStatus( HttpStatus.OK.value() );

            //- Update value  -//
//            videoOrigin.setTitle( videoType.getTitle() );
//            videoOrigin.setCode( videoType.getCode() );
//            videoOrigin.setDescription( videoType.getDescription() );

            //- Success. Return created video type -//
            return this.videoService.update( video );
        }
        catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create video type -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }
        catch ( Exception e ) {
            //- Failure. Can not to create video type -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }

//        //- Not exists ID of video type -//
//        response.setStatus( HttpStatus.NOT_FOUND.value() );
//        return null;

        return null;
    }

    /**
     * Delete video type
     *
     * @param id        ID of video for delete
     * @param response  Used for set HTTP status
     */
    @DELETE
    @RequestMapping( value = "/{ID}" )
    @ResponseBody
    public void deleteAction(
            @PathVariable( "ID" )
            Long id,

            HttpServletResponse response
    ) {
        //- Try to delete Video type -//
        try {
            //- Set HTTP status -//
            response.setStatus( HttpStatus.OK.value() );

            //- Success. Delete video type -//
            this.videoService.delete( id );
        }
        catch ( EmptyResultDataAccessException e ) {
            //- Not exists ID -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }
        catch ( Exception e ) {
            //- Not exists ID -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }
    }
}
