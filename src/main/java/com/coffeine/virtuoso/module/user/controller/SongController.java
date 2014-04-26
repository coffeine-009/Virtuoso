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
import com.coffeine.virtuoso.module.user.model.service.SongServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * SongController
 */
@Controller
@RequestMapping( value = "/user/song" )
public class SongController {

    @Resource
    private SongServiceImpl songService;


    //- SECTION :: ACTIONS -//
    /**
     * GET list of songs
     *
     * @param model
     * @return List<Song>
     */
    @RequestMapping( value = "/list", method = RequestMethod.GET )
    @ResponseStatus( value = HttpStatus.OK )
    @ResponseBody
    public List< Song > listAction( Model model ) {
        //- Get list of song from persistence layout -//
        List < Song > songList = songService.getList();

        return songList;
    }

    @RequestMapping( value = "/create", method = RequestMethod.POST )
    @ResponseStatus( value = HttpStatus.CREATED )
    @ResponseBody
    public Song createAction( Model model ) {
        Song newSong = new Song();
        
        return newSong;
    }
}
