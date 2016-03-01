/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.music.model.entity.Composer;
import com.coffeine.virtuoso.music.model.entity.Poet;
import com.coffeine.virtuoso.music.model.entity.Song;
import com.coffeine.virtuoso.music.model.service.ComposerService;
import com.coffeine.virtuoso.music.model.service.PoetService;
import com.coffeine.virtuoso.music.model.service.SongService;
import com.coffeine.virtuoso.music.view.form.SongForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import static org.springframework.util.Assert.notEmpty;
import static org.springframework.util.Assert.notNull;

/**
 * SongController.
 *
 * @version 1.0
 */
@Controller
@RequestMapping( value = "/music/songs" )
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private ComposerService composerService;

    @Autowired
    private PoetService poetService;

    //- SECTION :: ACTIONS -//
    /**
     * GET list of songs.
     *
     * @param page Number of page.
     * @param limit Count items per page.
     *
     * @return List of songs for requested page
     */
    @GET
    @RequestMapping( method = RequestMethod.GET )
    @ResponseStatus( value = HttpStatus.OK )
    @ResponseBody
    public List<Song> listAction(
        @RequestParam( value = "page", required = false, defaultValue = "1" )
        int page,

        @RequestParam( value = "limit", required = false, defaultValue = "10" )
        int limit
    ) {
        //- Get list of song from persistence layout -//
        return this.songService.findAll(
            Math.max( page - 1, 0 ),
            limit
        );
    }

    /**
     * Create new song.
     *
     * @param form    Song for create.
     *
     * @return Song
     */
    @POST
    @RequestMapping(
        method = RequestMethod.POST,
        produces = {
            "application/json"
        }
    )
    @ResponseBody
    public Song createAction(
        @RequestBody
        @Valid
        SongForm form,

        Locale locale,

        HttpServletResponse response
    ) {
        try {
            //- Search related entities -//
            List<Composer> composers = this.composerService.find( form.getComposerIds() );
            List<Poet> poets = this.poetService.find( form.getPoetIds() );

            //- Check -//
            notNull( composers );
            notNull( poets );
            notEmpty( composers );
            notEmpty( poets );

            //- Save song -//
            return this.songService.create(
                new Song(
                    composers,
                    poets,
//                    form.getData(),
//                    form.getStaffs(),
//                    form.getTexts(),
//                    form.getVideos(),
                    form.getLocale()
                )
            );
        } catch ( IllegalArgumentException e ) {
            //- Failure. Cannot find related entities -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Cannot save this song -//
            response.setStatus( HttpServletResponse.SC_CONFLICT );
        }

        return null;
    }

    /**
     * Get data about song by ID.
     *
     * @param id        id of song.
     * @param response  Use for work with HTTP.
     *
     * @return Song.
     */
    @GET
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public Song readAction( 
        @PathVariable( value = "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Search -//
            Song song = this.songService.find( id );

            //- Check -//
            notNull( song );

            return song;
        } catch ( IllegalArgumentException e ) {
            //- Failure. cannot find song -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return null;
    }

    /**
     * Update song by ID.
     *
     * @param id        id of song.
     * @param form      Form for input.
     * @param response  Use for work with HTTP.
     *
     * @return Song.
     */
    @Secured( "MUSICIAN" )
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    @ResponseBody
    public Song updateAction(
        @PathVariable( value = "id" )
        Long id,

        @Valid
        @RequestBody
        SongForm form,

        HttpServletResponse response
    ) {
        try {
            //- Search related entities -//
            Song song = this.songService.find( id );
            List<Composer> composers = this.composerService.find( form.getComposerIds() );
            List<Poet> poets = this.poetService.find( form.getPoetIds() );

            //- Check -//
            notNull( song );
            notNull( composers );
            notNull( poets );
            notEmpty( composers );
            notEmpty( poets );

            //- Update data -//
            song.setComposers( composers );
            song.setPoets( poets );
//            song.setData( form.getData() );
//            song.setStaffs( form.getStaffs() );
//            song.setTexts( form.getTexts() );
//            song.setVideos( form.getVideos() );
            song.setLocale( form.getLocale() );
        } catch ( IllegalArgumentException e ) {
            //- Failure. Cannot find related entities -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Cannot update song -//
            response.setStatus( HttpServletResponse.SC_CONFLICT );
        }

        return null;
    }

    /**
     * Delete song by ID.
     *
     * @param id        id of song.
     * @param response  Use for work with HTTP.
     */
    @Secured( "ADMINISTRATOR" )
    @DELETE
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    @ResponseBody
    public void deleteAction(
        @PathVariable( value = "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            this.songService.delete( id );
        } catch ( EmptyResultDataAccessException e ) {
            //- Failure. Cannot find this song -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
    }
}
