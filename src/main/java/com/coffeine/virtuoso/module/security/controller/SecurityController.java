/// *** User :: Controller :: Song  *** *** *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by TheCoffeine Inc
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
import com.coffeine.virtuoso.module.user.model.entity.*;
import com.coffeine.virtuoso.module.user.model.service.RoleService;
import com.coffeine.virtuoso.module.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Security controller.
 * Registration, forgot password.
 *
 * @version 1.0
 */
@Controller
@RequestMapping( value = "/security" )
public class SecurityController {

    /// *** Properties  *** ///
    //- SECTION :: CRYPTOGRAPHY -//
    @Autowired
    private ShaPasswordEncoder passwordEncoder;


    //- SECTION :: SERVICES -//
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;


    /// *** Methods     *** ///
    //- SECTION :: ACTIONS -//
    /**
     * Registration a new user.
     *
     * @param registrationForm  Data from registration form for register a new user.
     *
     * @return User Created user.
     */
    @RequestMapping( value = "/signup", method = RequestMethod.POST )
    @ResponseBody
    public User registrationAction(
        @RequestBody
        @Valid
        RegistrationForm registrationForm,

        HttpServletResponse response
    ) {
        try {
            //- Recognise roles -//
            List < String > roles = registrationForm.getRoles();

            //- Create new user -//
            User newUser = new User(
                //- Set roles -//
                this.roleService.findByCodes( roles ), 
                //- Add access params -//
                new Access(
                    this.passwordEncoder.encodePassword(
                        registrationForm.getPassword(),
                        null
                    )
                ), 
                //- Add e-mail -//
                new Email( registrationForm.getUsername() ), 
                //- User info -//
                registrationForm.getFirstName(), 
                registrationForm.getLastName(), 
                registrationForm.getGender(), 
                //- User's locale -//
                registrationForm.getLocale()
            );

                //- Create a new composer -//
                if( roles.contains( Roles.COMPOSER.name() ) ) {
                    //- Linking composer with user -//
                    newUser.setComposer(
                        new Composer(
                            newUser.getLocale(),
                            newUser.getGender(),
                            registrationForm.getBirthday(),
                            registrationForm.getDeathday()
                        )
                    );
                }

                //- Create a new poet -//
                if ( roles.contains( Roles.POET.name() ) ) {
                    //- Linking poet with user -//
                    newUser.setPoet(
                        new Poet(
                            newUser.getLocale(),
                            newUser.getGender(),
                            registrationForm.getBirthday(),
                            registrationForm.getDeathday()
                        )
                    );
                }

            //- Success -//
            response.setStatus( HttpServletResponse.SC_CREATED );

            //- Persist -//
            return this.userService.create( newUser );
        } catch ( DataIntegrityViolationException e ) {
            //- Cannot save this data -//
            response.setStatus( HttpServletResponse.SC_CONFLICT );

            //FIXME: log
        }

        return null;
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
    public Boolean forgotPasswordAction(
        Model model
    ) {
        return false;
    }
}
