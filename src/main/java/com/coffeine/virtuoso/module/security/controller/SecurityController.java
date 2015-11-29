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

import com.coffeine.virtuoso.module.security.model.entity.Roles;
import com.coffeine.virtuoso.module.security.view.form.RegistrationForm;
import com.coffeine.virtuoso.module.user.model.entity.*;
import com.coffeine.virtuoso.module.user.model.service.RoleService;
import com.coffeine.virtuoso.module.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.isTrue;

/**
 * Security controller.
 * Registration, forgot password.
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/security" )
public class SecurityController {

    /// *** Properties  *** ///
    //- SECTION :: CRYPTOGRAPHY -//
    /**
     * Encoder for create hash of password.
     */
    @Autowired
    private ShaPasswordEncoder passwordEncoder;


    //- SECTION :: SERVICES -//
    /**
     * Service for work with roles.
     */
    @Autowired
    private RoleService roleService;

    /**
     * Service for work with users.
     */
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
        final RegistrationForm registrationForm,

        HttpServletResponse response
    ) {
        try {
            //- Recognise roles -//
            List < String > requestRoles = registrationForm.getRoles();
            List < Role > roles = this.roleService.findByCodes( requestRoles );

            //- Check if roles exists in persistence layout -//
            isTrue( requestRoles.size() == roles.size() );

            //- Create new user -//
            User newUser = new User(
                //- Set roles -//
                roles,
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
                if( requestRoles.contains( Roles.COMPOSER.name() ) ) {
                    //- Linking composer with user -//
                    newUser.setComposer(
                        new Composer(
                            newUser.getLocale(),
                            newUser.getGender(),
                            registrationForm.getBirthday(),
                            registrationForm.getDeathDay(),
                            new ArrayList < ComposerLocale >() {{
                                add(
                                    new ComposerLocale(
                                        registrationForm.getFirstName(),
                                        registrationForm.getLastName(),
                                        registrationForm.getLocale()
                                    )
                                );
                            }}
                        )
                    );
                }

                //- Create a new poet -//
                if ( requestRoles.contains( Roles.POET.name() ) ) {
                    //- Linking poet with user -//
                    newUser.setPoet(
                        new Poet(
                            newUser.getLocale(),
                            newUser.getGender(),
                            registrationForm.getBirthday(),
                            registrationForm.getDeathDay(),
                            new ArrayList < PoetLocale >() {{
                                add(
                                    new PoetLocale(
                                        registrationForm.getFirstName(),
                                        registrationForm.getLastName(),
                                        registrationForm.getLocale()
                                    )
                                );
                            }}
                        )
                    );
                }

            //- Success -//
            response.setStatus( HttpServletResponse.SC_CREATED );

            //- Persist -//
            return this.userService.create( newUser );
        } catch ( DataIntegrityViolationException | IllegalArgumentException e ) {
            //- Cannot save this data -//
            response.setStatus( HttpServletResponse.SC_CONFLICT );

            //FIXME: log
        }

        return null;
    }

    /**
     * Forgot password.
     *
     * @param model
     *
     * @return Boolean
     */
    @RequestMapping( value = "/forgotPassword", method = RequestMethod.POST )
    @ResponseStatus( value = HttpStatus.OK )
    @ResponseBody
    public Boolean forgotPasswordAction(
        Model model
    ) {
        return false;//TODO: implement
    }
}
