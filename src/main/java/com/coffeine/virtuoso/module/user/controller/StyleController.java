/// *** User :: Controller :: Style *** *** *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-09-16 16:28:24 :: 2014-06-.. ..:..:..
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.controller;

import com.coffeine.virtuoso.module.user.model.entity.Style;
import com.coffeine.virtuoso.module.user.model.service.StyleService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import java.util.List;

/**
 * Controller for style
 *
 * @version 1.0
 */
@Controller
@RequestMapping( value = "/user/style" )
public class StyleController {

    @Autowired
    protected StyleService styleService;


    @GET
    @RequestMapping( value = "/list/{PAGE}/{LIMIT}" )
    @ResponseBody
    public List < Style > findAllAction(
        @PathVariable( "PAGE" )
        int page,

        @PathVariable( "LIMIT" )
        int limit
    ) {
        return this.styleService.findAll(
            Math.max( page - 1, 0 ),
            limit
        );
    }

    /**
     *
     * @param style
     * @param response
     * @return
     */
    @POST
    @RequestMapping( value = "/" )
    @ResponseBody
    public Style createAction(
        @RequestBody
        @Valid
        Style style,

        HttpServletResponse response
    ) {
        try {
            //- Set HTTP status -//
            response.setStatus( HttpStatus.CREATED.value() );

            //- Success. Return created style -//
            return this.styleService.create( style );
        }
        catch ( ConstraintViolationException e ) {
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
     * Update
     *
     * @param id        ID of style
     * @param style     Updated data
     * @param response  Use for set HTTP status
     * @return Style
     */
    @PUT
    @RequestMapping( value = "/{ID}" )
    @ResponseBody
    public Style createAction(
        @PathVariable( "ID" )
        Long id,

        @RequestBody
        @Valid
        Style style,

        HttpServletResponse response
    ) {
        //- Search origin style -//
        Style styleOrigin = this.styleService.find( id );

        if ( styleOrigin == null ) {
            //- Failure. Style not found -//
            response.setStatus( HttpStatus.NOT_FOUND.value() );
            return null;
        }

        //- Update style -//
        try {
            //- Set HTTP status -//
            response.setStatus( HttpStatus.OK.value() );

            //- Set new data -//
            styleOrigin.setCode( style.getCode() );
            styleOrigin.setTitle( style.getTitle() );
            styleOrigin.setDescription( style.getDescription() );

            //- Success. Return created style -//
            return this.styleService.update( styleOrigin );
        }
        catch ( ConstraintViolationException e ) {
            //- Failure. Can not to create video type -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }
        catch ( Exception e ) {
            //- Failure. Can not to create video type -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }

        return null;
    }

    @DELETE
    @RequestMapping( value = "/{ID}" )
    @ResponseBody
    public void deleteAction(
        @PathVariable( "ID" )
        Long id,

        HttpServletResponse response
    ) {
        try {   
            //- Set HTTP status -//
            response.setStatus( HttpStatus.OK.value() );

            //- Try to delete style -//
            this.styleService.delete( id );
        }
        catch( InvalidDataAccessApiUsageException e ) {
            // Failure. Style doesn't exists
            //- Set HTTP status -//
            response.setStatus( HttpStatus.NOT_FOUND.value() );
        }
        catch( Exception e ) {
            // Failure. Some exception with persistence
            //- Set HTTP status -//
            response.setStatus( HttpStatus.NOT_FOUND.value() );
        }
    }
}
