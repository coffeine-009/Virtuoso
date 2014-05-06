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
import com.coffeine.virtuoso.module.user.model.entity.User;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author vitaliy
 */
@Controller
@RequestMapping( value = "/user" )
public class AuthorizationController {

    //- SECTION :: ACTIONS -//
    /**
     * Registration new user
     *
     * @param model
     * @return User
     */
    @RequestMapping( value = "/registration", method = RequestMethod.POST )
    @ResponseStatus( value = HttpStatus.CREATED )
    @ResponseBody
    public User registrationAction( Model model ) {
        //- Create new user -//
        User newUser = new User();
            newUser.setFirstName("Vitaliy");

        return newUser;
    }

    /**
     * Authorization
     *
     * @param model
     * @return User
     */
    @RequestMapping( value = "/authorization", method = RequestMethod.POST )
    @ResponseStatus( value = HttpStatus.OK )
    @ResponseBody
    public User authorizationAction( 
        Model model 
    ) {
        return new User();
    }

    /**
     * Forgot password
     *
     * @param model
     * @return Boolean
     */
    @RequestMapping( value = "/forgotPassword", method = RequestMethod.POST )
    @ResponseStatus( value = HttpStatus.OK )
    @ResponseBody
    public Boolean forgotPasswordAtion(
        Model model
    ) {
        return false;
    }
}
