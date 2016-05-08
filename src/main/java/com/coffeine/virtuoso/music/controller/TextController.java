/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.music.model.entity.Song;
import com.coffeine.virtuoso.music.model.entity.Text;
import com.coffeine.virtuoso.music.model.service.SongService;
import com.coffeine.virtuoso.music.model.service.TextService;
import com.coffeine.virtuoso.music.view.form.TextForm;

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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import static org.springframework.util.Assert.notNull;

/**
 * Controller for work with song's text(s).
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/music/texts" )
public class TextController {

    /// *** Properties  *** ///
    @Autowired
    protected TextService textService;

    @Autowired
    protected SongService songService;


    /**
     * Find all texts per page.
     *
     * @param page  Number of page.
     * @param limit Count items on page.
     *
     * @return List of texts.
     */
    @GET
    @RequestMapping( method = RequestMethod.GET )
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<Text> findAll(
        @RequestParam( value = "page", required = false, defaultValue = "1" )
        int page, 

        @RequestParam( value = "limit", required = false, defaultValue = "10" )
        int limit
    ) {
        return this.textService.findAll( 
            Math.max( page - 1, 0 ),
            limit
        );
    }

    /**
     * Create text.
     *
     * @param form        Form for input.
     * @param response    Use for work with HTTP.
     *
     * @return Text.
     */
    @POST
    @RequestMapping( method = RequestMethod.POST )
    @ResponseBody
    public Text createAction(
        @RequestBody
        @Valid
        TextForm form,

        HttpServletResponse response
    ) {
        //- Try add a new text of song -//
        try {
            //- Search song -//
            Song song = this.songService.find( form.getSongId() );

            //- Check -//
            notNull( song );

            //- Set HTTP status -//
            response.setStatus( HttpStatus.CREATED.value() );
            
            //- Try to create text -//
            return this.textService.create(
                new Text( 
                    song,
                    form.getLocale()
                )
            );
        } catch ( IllegalArgumentException e ) {
            //- Failure. Cannot find related entities -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create text -//
            response.setStatus( HttpServletResponse.SC_CONFLICT );
        }

        return null;
    }

    /**
     * Find text.
     *
     * @param id          Id of text.
     * @param response    Use for work with HTTP.
     *
     * @return Text.
     */
    @GET
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public Text findAction(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Search text -//
            Text text = this.textService.find( id );

            //- Check -//
            notNull( text );

            return text;
        } catch ( IllegalArgumentException e ) {
            //- Failure. Cannot find text -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return null;
    }

    /**
     * Update text.
     *
     * @param id          Id of text.
     * @param form        Form for input.
     * @param response    Use for work with HTTP.
     *
     * @return Text.
     */
    @PUT
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    public Text updateAction(
        @PathVariable( "id" )
        Long id,

        @RequestBody
        TextForm form,

        HttpServletResponse response
    ) {
        try {
            //- Search depended entities -//
            Text text = this.textService.find( id );
            Song song = this.songService.find( form.getSongId() );

            //- Check -//
            notNull( text );
            notNull( song );

            //- Update fields -//
            text.setSong( song );
            text.setLocale( form.getLocale() );

            //- Try to update Text -//
            return this.textService.update( text );
        } catch ( IllegalArgumentException e ) {
            //- Failure. Cannot found related entities -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to update text-//
            response.setStatus( HttpServletResponse.SC_CONFLICT );
        }

        return null;
    }

    /**
     * Delete text.
     *
     * @param id          Id of text.
     * @param response    Use for work with HTTP.
     */
    @DELETE
    @RequestMapping( value = "/{id}" )
    @ResponseBody
    public void deleteAction(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Try to delete text -//
            this.textService.delete( id );
        } catch ( DataAccessException e ) {
            // Failure. Text doesn't exists
            //- Set HTTP status -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
    }
}
