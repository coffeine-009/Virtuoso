/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.controller;

import com.thecoffeine.virtuoso.music.model.entity.Lyrics;
import com.thecoffeine.virtuoso.music.model.entity.Poet;
import com.thecoffeine.virtuoso.music.model.entity.Song;
import com.thecoffeine.virtuoso.music.model.service.LyricsService;
import com.thecoffeine.virtuoso.music.model.service.PoetService;
import com.thecoffeine.virtuoso.music.model.service.SongService;
import com.thecoffeine.virtuoso.music.view.form.LyricsForm;
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
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import static org.springframework.util.Assert.notEmpty;
import static org.springframework.util.Assert.notNull;

/**
 * Controller for work with song's lyrics.
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/music/lyrics" )
public class LyricsController {

    /// *** Properties  *** ///
    @Autowired
    protected LyricsService lyricsService;

    @Autowired
    private PoetService poetService;

    @Autowired
    protected SongService songService;


    /**
     * Find all lyrics per page.
     *
     * @param page  Number of page.
     * @param limit Count items on page.
     *
     * @return List of lyrics.
     */
    @GET
    @RequestMapping( method = RequestMethod.GET )
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<Lyrics> findAll(
        @RequestParam( value = "page", required = false, defaultValue = "1" )
        int page, 

        @RequestParam( value = "limit", required = false, defaultValue = "10" )
        int limit
    ) {
        return this.lyricsService.findAll(
            Math.max( page - 1, 0 ),
            limit
        );
    }

    /**
     * Create lyrics.
     *
     * @param form        Form for input.
     * @param response    Use for work with HTTP.
     *
     * @return Lyrics.
     */
    @POST
    @RequestMapping( method = RequestMethod.POST )
    @ResponseBody
    public Lyrics createAction(
        @RequestBody
        @Valid
        LyricsForm form,

        HttpServletResponse response
    ) {
        //- Try add a new text of song -//
        try {
            //- Search song -//
            Song song = this.songService.find( form.getSongId() );
            Set<Poet> poets = this.poetService.find( form.getPoetIds() );

            //- Check -//
            notNull( song );
            notNull( poets );
            notEmpty( poets );

            //- Set HTTP status -//
            response.setStatus( HttpStatus.CREATED.value() );
            
            //- Try to create text -//
            return this.lyricsService.create(
                new Lyrics(
                    poets,
                    song,
                    form.getLocale(),
                    form.getContent()
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
     * Find lyrics.
     *
     * @param id          Id of lyrics.
     * @param response    Use for work with HTTP.
     *
     * @return Lyrics.
     */
    @GET
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public Lyrics findAction(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Search lyrics -//
            Lyrics lyrics = this.lyricsService.find( id );

            //- Check -//
            notNull( lyrics );

            return lyrics;
        } catch ( IllegalArgumentException e ) {
            //- Failure. Cannot find text -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return null;
    }

    /**
     * Update lyrics.
     *
     * @param id          Id of lyrics.
     * @param form        Form for input.
     * @param response    Use for work with HTTP.
     *
     * @return Lyrics.
     */
    @PUT
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    public Lyrics updateAction(
        @PathVariable( "id" )
        Long id,

        @RequestBody
        LyricsForm form,

        HttpServletResponse response
    ) {
        try {
            //- Search depended entities -//
            Lyrics lyrics = this.lyricsService.find( id );
            Song song = this.songService.find( form.getSongId() );
            //TODO: Poets

            //- Check -//
            notNull( lyrics );
            notNull( song );

            //- Update fields -//
            lyrics.setSong( song );
            lyrics.setLocale( form.getLocale() );

            //- Try to update Lyrics -//
            return this.lyricsService.update( lyrics );
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
            this.lyricsService.delete( id );
        } catch ( DataAccessException e ) {
            // Failure. Lyrics doesn't exists
            //- Set HTTP status -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
    }
}
