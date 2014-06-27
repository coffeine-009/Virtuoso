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
import java.util.List;
import java.util.Locale;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * SongController
 */
@Controller
@RequestMapping( value = "/user/song" )
public class SongController {

    @Autowired
    MessageSource messageSource;

    @Resource
    private SongService songService;


    //- SECTION :: ACTIONS -//
    /**
     * GET list of songs
     *
     * @return List<Song>
     */
    @RequestMapping( value = "/list", method = RequestMethod.GET )
    @ResponseStatus( value = HttpStatus.OK )
    @ResponseBody
    public List < Song > listAction() {
        //- Get list of song from persistence layout -//
        List < Song > songList = songService.getList();

        return songList;
    }

    /**
     * Create new song
     *
     * @param song
     * @return Song
     */
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
        Song song,

        Locale locale
    ) {
        Song newSong = new Song();
            newSong.setLocale(
                messageSource.getMessage("test", null, locale).substring(0, 4)
            );
        //TODO: to implement
        return song;
    }

    /**
     * Get data about song by ID
     *
     * @param Id
     * @return Song
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseStatus( value = HttpStatus.OK )
    @ResponseBody
    public Song readAction( 
        @PathVariable( value = "id" )
        Long Id
    ) {
        Song song = songService.getSong( Id );
        
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
     * @param Id
     */
    @Secured( "ADMIN" )
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    @ResponseStatus( value = HttpStatus.OK )
    public void deleteAction(
        @PathVariable( value = "id" )
        Long Id
    ) {
        //TODO: to implement
    }


    //- SECTION :: SET -//
    /**
     * Setter for IoC(DI)
     *
     * @param messageSource
     */
    private void setMessageSource( MessageSource messageSource ) {
        this.messageSource = messageSource;
    }
}
