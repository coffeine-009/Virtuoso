/// *** User :: Controller :: Song  *** *** *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-03-25 15:26:32 :: ....-..-.. ..:..:..
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.controller;

import com.coffeine.virtuoso.module.user.model.entity.Song;
import com.coffeine.virtuoso.module.user.model.service.SongService;
import com.coffeine.virtuoso.security.model.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

/**
 * SongController
 */
@Controller
@RequestMapping( value = "/user/song" )
public class SongController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;


    //- SECTION :: ACTIONS -//
    /**
     * GET list of songs
     *
     * @param page Number of page
     * @param limit Count items per page
     * @return List < Song > List of songs for requested page
     */
    @GET
    @RequestMapping( value = "/list/{PAGE}/{LIMIT}", method = RequestMethod.GET )
    @ResponseStatus( value = HttpStatus.OK )
    @ResponseBody
    public List < Song > listAction(
        @PathVariable( "PAGE" )
        int page,

        @PathVariable( "LIMIT" )
        int limit
    ) {
        //- Get list of song from persistence layout -//
        return this.songService.findAll(
            Math.max( page - 1, 0 ),
            limit
        );
    }

    /**
     * Create new song
     *
     * @param song
     * @return Song
     */
    @POST
    @RequestMapping(
        value = "/",
        method = RequestMethod.POST,
        produces = {
            "application/json"
        }
    )
    @ResponseStatus( value = HttpStatus.CREATED )
    @ResponseBody
    public Song createAction(
        @RequestBody
        @Valid
        Song song,

        Locale locale
    ) {
        //- Save song -//
        return this.songService.create( song );
    }

    /**
     * Get data about song by ID
     *
     * @param Id
     * @return Song
     */
    @GET
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseStatus( value = HttpStatus.OK )
    @ResponseBody
    public Song readAction( 
        @PathVariable( value = "id" )
        Long Id
    ) {
        Song song = songService.find( Id );
        
        return song;
    }

    /**
     * Update song by ID
     *
     * @param Id
     * @return Song
     */
    @Secured( "MUSICIAN" )
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    @ResponseStatus( value = HttpStatus.OK )
    @ResponseBody
    public Song updateAction(
        @PathVariable( value = "id" )
        Long Id
    ) {
        //TODO: to implement
        return null;
    }

    /**
     * Delete song by ID
     *
     * @param id
     */
//    @Secured( "ADMIN" )
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    @ResponseStatus( value = HttpStatus.OK )
    public void deleteAction(
        @PathVariable( value = "id" )
        Long id
    ) {
        //this.songService.delete( id );
        this.userService.delete( id );
    }
}
