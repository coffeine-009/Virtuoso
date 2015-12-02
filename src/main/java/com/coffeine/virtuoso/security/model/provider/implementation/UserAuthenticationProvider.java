/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/30/15 11:14 PM
 */

/// *** Security :: Model :: Provider :: UserAuthenticationProvider *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.security.model.provider.implementation;

import com.coffeine.virtuoso.module.user.model.entity.Role;
import com.coffeine.virtuoso.module.user.model.entity.User;
import com.coffeine.virtuoso.module.user.model.service.UserService;
import com.coffeine.virtuoso.security.model.entity.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 */
public class UserAuthenticationProvider implements AuthenticationProvider {

    /// *** Properties  *** ///
    //- SECTION :: CRYPTOGRAPHY -//
    /**
     * Encoder for create hash of password.
     */
    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;


    /// *** Methods     *** ///
    //- SECTION :: MAIN -//
    /**
     * Authentication
     *
     * @param authentication
     * @return Authentication
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(
        Authentication authentication
    ) throws AuthenticationException {

        //- Search user -//
        User user = this.userService.findByUsernameAndPassword(
            authentication.getPrincipal().toString(),
            this.passwordEncoder.encodePassword(
                authentication.getCredentials().toString(),
                null
            )
        );
        if ( user != null ) {
            //- Set authorities -//
            List < GrantedAuthority > authorities = new ArrayList<>();
                for ( Role role : user.getRoles() ) {
                    authorities.add(
                        new SimpleGrantedAuthority(
                            role.getCode()
                        )
                    );
                }

            //- Create new auth token -//
            AuthenticationToken authToken = new AuthenticationToken(
                authentication.getPrincipal(),
                authentication.getAuthorities(),
                authorities
            );

            return authToken;
        } else {
            throw new BadCredentialsException( "Bad user credentials" );
        }
    }

    /**
     * Supports
     *
     * @param authentication
     * @return boolean
     */
    @Override
    public boolean supports( Class < ? > authentication ) {
        return true;
    }
}
