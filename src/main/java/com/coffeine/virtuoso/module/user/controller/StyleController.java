/// *** User :: Model :: Service :: Style   *** *** *** *** *** *** *** *** ///

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

import com.coffeine.virtuoso.module.user.model.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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


    @RequestMapping( value = "/{ID}", method = RequestMethod.DELETE )
    public void deleteAction(
        @PathVariable( "ID" )
        Long id
    ) {
        try {
            this.styleService.delete(id);
        }
        catch( InvalidDataAccessApiUsageException e ) {
            // Style doesn't exists
            //TODO: impl
        }
    }
}
