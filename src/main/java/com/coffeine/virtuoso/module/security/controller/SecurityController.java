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

import com.coffeine.virtuoso.module.security.Form.RegistrationForm;
import com.coffeine.virtuoso.module.security.model.entity.Roles;
import com.coffeine.virtuoso.module.user.model.entity.Composer;
import com.coffeine.virtuoso.module.user.model.entity.Email;
import com.coffeine.virtuoso.module.user.model.entity.Poet;
import com.coffeine.virtuoso.module.user.model.entity.User;

import com.coffeine.virtuoso.module.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @Transactional
    @RequestMapping( value = "/signup", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus( value = HttpStatus.CREATED )
    @ResponseBody
    public User registrationAction(
        @RequestBody
        @Valid
        RegistrationForm registrationForm
    ) {
        //- Create new user -//
        User newUser = new User();
            newUser.addEmail(
                new Email(
                    registrationForm.getUsername()
                )
            );
            //newUser.setPassword( registrationForm.getPassword() );
            newUser.setFirstName( registrationForm.getFirstName() );
            newUser.setLastName(registrationForm.getLastName());
            newUser.setGender(registrationForm.getGender());
            newUser.setLocale(registrationForm.getLocale());

        User user = this.userService.save( newUser );

        //- Create roles -//
        List < String > roles = registrationForm.getRoles();

        //- Create a new composer -//
        if( roles.contains( Roles.COMPOSER ) ) {
            Composer composer = new Composer(
                user.getLocale(),
                user.getGender(),
                user.getCreation(),//TODO
                user.getCreation()
            );
                composer.setUser( user );
            //TODO
        }

        //- Create a new poet -//
        if ( roles.contains( Roles.POET ) ) {
            Poet poet = new Poet(
                user.getLocale(),
                user.getGender(),
                user.getCreation(),//TODO
                user.getCreation()
            );
                poet.setUser( user );
            //TODO
        }

        return user;
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
