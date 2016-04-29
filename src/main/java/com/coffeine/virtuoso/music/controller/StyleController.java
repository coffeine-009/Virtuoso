/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.music.model.entity.Style;
import com.coffeine.virtuoso.music.model.service.StyleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.util.Assert.notNull;

/**
 * Controller for style.
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/music/styles" )
public class StyleController {

    /// *** Properties  *** ///
    /**
     * Service for work with styles.
     */
    @Autowired
    protected StyleService styleService;


    /// *** Methods     *** ///
    /**
     * Get list of styles.
     *
     * @param page     Number of page.
     * @param limit    Count items per page.
     *
     * @return List of items for requested page.
     */
    @RequestMapping( method = RequestMethod.GET )
    @ResponseBody
    public List<Style> findAllAction(
        @RequestParam( value = "page", required = false, defaultValue = "1" )
        int page,

        @RequestParam( value = "limit", required = false, defaultValue = "10" )
        int limit
    ) {
        //- Search page of styles -//
        return this.styleService.findAll(
            Math.max( page - 1, 0 ),
            limit
        );
    }

    /**
     * Create a new style.
     *
     * @param style       Form with input.
     * @param response    Use for work with HTTP.
     *
     * @return Created style.
     */
    @RequestMapping( method = RequestMethod.POST )
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
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create video type -//
            response.setStatus( HttpStatus.CONFLICT.value() );
        }

        return null;
    }

    /**
     * Get style by id.
     *
     * @param id          Id of style.
     * @param response    Use for work with HTTP.
     *
     * @return Found style.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public Style find(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Search requested style -//
            Style style = this.styleService.find( id );

            //- Check if style was found -//
            notNull( style );

            return style;
        } catch ( IllegalArgumentException e ) {
            //- Error. Cannot find this style -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return null;
    }

    /**
     * Update already exist style.
     *
     * @param id        ID of style
     * @param style     Updated data
     * @param response  Use for set HTTP status
     *
     * @return Updated style.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    @ResponseBody
    public Style updateAction(
        @PathVariable( "id" )
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
            //- Set new data -//
            styleOrigin.setCode( style.getCode() );
            styleOrigin.setTitle( style.getTitle() );
            styleOrigin.setDescription( style.getDescription() );

            //- Success. Return created style -//
            return this.styleService.update( styleOrigin );
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create video type -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }

        return null;
    }

    /**
     * Delete style.
     *
     * @param id          Id of style.
     * @param response    Use for work with HTTP.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    @ResponseBody
    public void deleteAction(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {   
            //- Try to delete style -//
            this.styleService.delete( id );
        } catch ( InvalidDataAccessApiUsageException e ) {
            // Failure. Style doesn't exists
            //- Set HTTP status -//
            response.setStatus( HttpStatus.NOT_FOUND.value() );
        }
    }
}
