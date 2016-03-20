/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com">Vitaliy Tsutsman</a>
 *
 * @date 11/30/15 11:14 PM
 */

package com.coffeine.virtuoso.security.controller;

import com.coffeine.virtuoso.music.model.entity.Composer;
import com.coffeine.virtuoso.music.model.entity.ComposerLocale;
import com.coffeine.virtuoso.music.model.entity.Poet;
import com.coffeine.virtuoso.music.model.entity.PoetLocale;
import com.coffeine.virtuoso.notification.model.entity.EmailAddress;
import com.coffeine.virtuoso.security.model.entity.Access;
import com.coffeine.virtuoso.security.model.entity.Email;
import com.coffeine.virtuoso.security.model.entity.Role;
import com.coffeine.virtuoso.security.model.entity.Roles;
import com.coffeine.virtuoso.security.model.entity.User;
import com.coffeine.virtuoso.security.model.service.AccessRecoveryService;
import com.coffeine.virtuoso.security.model.service.RoleService;
import com.coffeine.virtuoso.security.model.service.UserService;
import com.coffeine.virtuoso.security.view.form.AccessRecoveryForm;
import com.coffeine.virtuoso.security.view.form.ForgotPasswordForm;
import com.coffeine.virtuoso.security.view.form.RegistrationForm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

    /// *** Constants   *** ///
    /**
     * Logger.
     */
    private static final Logger log = LogManager.getLogger( SecurityController.class );

    /**
     * Marker for actions.
     */
    private static final Marker ACTION_MARKER = MarkerManager.getMarker( "ACTION" );


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

    /**
     * Service for recovery access to account.
     */
    @Autowired
    private AccessRecoveryService accessRecoveryService;


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
        //- Log action -//
        log.info( ACTION_MARKER, "Sign Up" );

        try {
            //- Recognise roles -//
            List<String> requestRoles = registrationForm.getRoles();
            List<Role> roles = this.roleService.findByCodes( requestRoles );

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
            if ( requestRoles.contains( Roles.COMPOSER.name() ) ) {
                //- Linking composer with user -//
                newUser.setComposer(
                    new Composer(
                        newUser.getLocale(),
                        newUser.getGender(),
                        registrationForm.getBirthday(),
                        registrationForm.getDeathDate(),
                        new ArrayList<ComposerLocale>() {{
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
                        registrationForm.getDeathDate(),
                        new ArrayList<PoetLocale>() {{
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

            log.warn( "Attempt to register duplicate.", e );
        }

        return null;
    }

    /**
     * Forgot password.
     * Request for recovering access.
     *
     * @param form    Filled form if you forgot password.
     */
    @RequestMapping( value = "/forgotPassword", method = RequestMethod.POST )
    @ResponseBody
    public void forgotPasswordAction(
        @Valid
        @RequestBody
        final ForgotPasswordForm form,

        HttpServletResponse response
    ) {
        //- Log action -//
        log.info( ACTION_MARKER, "Request for recovering password." );

        //- Try to make request for recovery access to account -//
        try {
            //- Inform about loosing access -//
            this.accessRecoveryService.lostAccess(
                new EmailAddress( form.getEmail() )
            );
        } catch ( IllegalArgumentException e ) {
            //- Error. Cannot find user -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        } catch ( IOException e ) {
            //- Error. Cannot apply template -//
            response.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
        }
    }

    /**
     * Recovery access.
     *
     * @param form        Form with data for recovering.
     * @param response    HTTP response.
     */
    @RequestMapping( value = "/access/recovery", method = RequestMethod.POST )
    @ResponseBody
    public void accessRecoveryAction(
        @Valid
        @RequestBody
        AccessRecoveryForm form,

        HttpServletResponse response
    ) {
        //- Log action -//
        log.info( ACTION_MARKER, "Access recovery::Reset password." );

        try {
            //- Set up new access params -//
            this.accessRecoveryService.restore(
                form.getHash(),
                form.getPassword()
            );
        } catch ( DataIntegrityViolationException | IllegalArgumentException e ) {
            //- Invalid request -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );

            log.error( "Cannot recovery access.", e );
        }
    }
}
