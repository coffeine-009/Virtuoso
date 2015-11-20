/**
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 9/25/14 9:00 PM :: 9/25/14 10:55 PM
 *
 * @address /Ukraine/Ivano-Frankivsk
 */

package com.coffeine.virtuoso.module.user.controller;

import com.coffeine.virtuoso.module.user.model.entity.VideoType;
import com.coffeine.virtuoso.module.user.model.service.VideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import java.util.List;

/**
 * Controller for managing types of video
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/user/video/type" )
public class VideoTypeController {

    /// *** Properties  *** ///
    @Autowired
    protected VideoTypeService videoTypeService;


    /// *** Methods     *** ///
    //- SECTION :: ACTION -//
    /**
     * Find all video types per page
     *
     * @param page  Number of page
     * @param limit Count of items per page
     * @return List < VideoType > List of video types
     */
    @RequestMapping( value = "/list/{PAGE}/{LIMIT}", method = RequestMethod.GET )
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List < VideoType > findAllAction(
        @PathVariable( "PAGE" )
        int page,

        @PathVariable( "LIMIT" )
        int limit
    ) {
        //- Search page -//
        return this.videoTypeService.findAll( page, limit );
    }

    /**
     * Create a new video type
     *
     * @param videoType Type of video for create
     * @param response  Used for set HTTP status
     * @return VideoType Created type of video
     */
    @RequestMapping( value = "/", method = RequestMethod.POST )
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
     * Update video type
     *
     * @param id        ID of video type for update
     * @param videoType Data for update
     * @param response  Used for set HTTP status
     * @return VideoType Updated type of video
     */
    @RequestMapping( value = "/{ID}", method = RequestMethod.PUT )
    @ResponseBody
    public VideoType updateAction(
        @PathVariable( "ID" )
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
            response.setStatus( HttpStatus.NOT_FOUND.value() );
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
     * Delete video type
     *
     * @param id        ID of video type for delete
     * @param response  Used for set HTTP status
     */
    @DELETE
    @RequestMapping( value = "/{ID}", method = RequestMethod.DELETE )
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
            this.videoTypeService.delete( id );
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
