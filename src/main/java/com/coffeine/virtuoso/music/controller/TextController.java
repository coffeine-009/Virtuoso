/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Controller :: Text *** *** *** *** *** *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.music.model.entity.Song;
import com.coffeine.virtuoso.music.model.entity.Text;
import com.coffeine.virtuoso.music.model.service.SongService;
import com.coffeine.virtuoso.music.model.service.TextService;
import com.coffeine.virtuoso.music.view.form.TextForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
 *
 * @version 1.0
 */
@SuppressWarnings("serial")
@RestController
@RequestMapping( value = "/user/text" )
public class TextController {

    /// *** Properties  *** ///
    @Autowired
    protected TextService textService;

    @Autowired
    protected SongService songService;


    /**
     * Find all texts per page
     *
     * @param page  Number of page
     * @param limit Count items on page
     * @return List < Text >
     */
    @GET
    @RequestMapping( value = "/list/{PAGE}/{LIMIT}" )
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List < Text > findAll(
        @PathVariable( "PAGE" )
        int page, 

        @PathVariable( "LIMIT" )
        int limit
    ) {
        return this.textService.findAll( 
            Math.max( page -1, 0 ), 
            limit
        );
    }

    /**
     * Create text
     *
     * @param textForm
     * @param response
     * @return Text
     */
    @POST
    @RequestMapping( method = RequestMethod.POST )
    @ResponseBody
    public Text createAction(
        @RequestBody
        @Valid
        TextForm textForm,

        HttpServletResponse response
    ) {
        //- Try add a new text of song -//
        try{
            //- Set HTTP status -//
            response.setStatus( HttpStatus.CREATED.value() );
            
            //- Try to create text -//
            return this.textService.create(
                new Text( 

                )
            );
        }
        catch( DataIntegrityViolationException e ) {
            //- Failure. Can not to create text -//
            response.setStatus( HttpStatus.FORBIDDEN.value());
        }
        catch( Exception e ) {
            //- Failure. Can not to create text-//
            response.setStatus( HttpStatus.FORBIDDEN.value());
        }

        return null;
    }

    /**
     * Update text
     *
     * @param id
     * @param textForm
     * @param response
     * @return Text 
     */
    @PUT
    @RequestMapping( value = "/{ID}", method = RequestMethod.PUT )
    public Text updateAction(
        @PathVariable
        Long id,

        @RequestBody
        TextForm textForm,

        HttpServletResponse response
    ) {
        Text text = this.textService.find( id );
        Song song = this.songService.find( textForm.getSongId() );

        if( song == null || text == null ) {
            //- Set Http status -//
            response.setStatus( HttpStatus.NOT_FOUND.value() );

            return null;
        }
        try {
            //- Set Http status -//
            response.setStatus( HttpStatus.OK.value() );

                //Set song and locale -//
                text.setSong( song );
                text.setLocale( textForm.getLocale() );

                //-Try to update Text
                return this.textService.update( text );
        }
        catch( Exception e ) {
            //- Failure. Can not to update text-//
            response.setStatus( HttpStatus.FORBIDDEN.value());
        }

        return null;
    }

    /**
     * Delete text
     * @param id
     * @param response 
     */
    @DELETE
    @RequestMapping( value = "/{ID}")
    @ResponseBody
    public void deleteAction(
        @PathVariable( "ID" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Set HTTP status -//
            response.setStatus( HttpStatus.OK.value() );

            //- Try to delete text -//
            this.textService.delete( id );
        }
        catch( InvalidDataAccessApiUsageException e) {
            // Failure. Text doesn't exists
            //- Set HTTP status -//
            response.setStatus( HttpStatus.NOT_FOUND.value() );
        }
        catch( Exception e ) {
            // Failure. Text doesn't exists
            //- Set HTTP status -//
            response.setStatus( HttpStatus.NOT_FOUND.value() );
        }
    }
}