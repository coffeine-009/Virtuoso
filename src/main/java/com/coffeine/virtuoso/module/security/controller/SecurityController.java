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
package com.coffeine.virtuoso.module.security.controller;

import com.coffeine.virtuoso.module.user.Form.RegistrationForm;
import com.coffeine.virtuoso.module.user.model.entity.Song;
import com.coffeine.virtuoso.module.user.model.entity.User;
import java.util.List;

import com.coffeine.virtuoso.module.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @version 1.0
 */
@Controller
@RequestMapping( value = "/security" )
public class SecurityController {

    /// *** Properties  *** ///
    //- SETION :: SERVICES -//
    @Autowired
    private UserService userService;


    /// *** Methods     *** ///
    //- SECTION :: ACTIONS -//
    /**
     * Registration new user
     *
     * @param registrationForm
     * @return User
     */
    @RequestMapping( value = "/signup", method = RequestMethod.POST )
    @ResponseStatus( value = HttpStatus.CREATED )
    @ResponseBody
    public User registrationAction(
        @RequestBody
        RegistrationForm registrationForm
    ) {
        //- Create new user -//
        User newUser = new User();
            newUser.setFirstName( registrationForm.getFirstName() );

        return this.userService.save( newUser );
    }

    /**
     * Authorization
     *
     * @param model
     * @return User
     */
    @RequestMapping( value = "/signin", method = RequestMethod.POST )
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
