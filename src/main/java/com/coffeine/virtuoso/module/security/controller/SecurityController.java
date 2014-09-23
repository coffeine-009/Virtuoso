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
import com.coffeine.virtuoso.module.user.model.entity.*;
import com.coffeine.virtuoso.module.user.model.service.RoleService;
import com.coffeine.virtuoso.module.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
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
    //- SECTION :: CRYPTOGRAPHIA -//
    @Autowired
    private ShaPasswordEncoder passwordEncoder;


    //- SETION :: SERVICES -//
    @Autowired
    private RoleService roleService;

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
        @Valid
        RegistrationForm registrationForm
    ) {
        //- Recognise roles -//
        List < String > roles = registrationForm.getRoles();

        //- Create new user -//
        User newUser = new User();
            //- Set roles -//
            newUser.setRoles(
                this.roleService.findByCodes( roles )
            );
            //- Add e-mail -//
            newUser.addEmail(
                new Email(
                    registrationForm.getUsername()
                )
            );
            //- Add access params -//
            newUser.addAccess(
                new Access(
                    this.passwordEncoder.encodePassword(
                        registrationForm.getPassword(),
                        null
                    )
                )
            );
            //- User info -//
            newUser.setFirstName(registrationForm.getFirstName());
            newUser.setLastName(registrationForm.getLastName());
            newUser.setGender( registrationForm.getGender() );
            //- User's locale -//
            newUser.setLocale( registrationForm.getLocale() );

            //- Create a new composer -//
            if( roles.contains( Roles.COMPOSER ) ) {
                //- Linking composer with user -//
                newUser.addComposer(
                    new Composer(
                        newUser.getLocale(),
                        newUser.getGender(),
                        newUser.getCreation(),//TODO
                        newUser.getCreation()
                    )
                );
            }

            //- Create a new poet -//
            if ( roles.contains( Roles.POET ) ) {
                //- Linking poet with user -//
                newUser.addPoet(
                    new Poet(
                        newUser.getLocale(),
                        newUser.getGender(),
                        newUser.getCreation(),//TODO
                        newUser.getCreation()
                    )
                );
            }

        return this.userService.save( newUser );
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
