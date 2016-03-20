/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 11/30/15 11:14 PM
 */

package com.coffeine.virtuoso.security.model.provider.implementation;

import com.coffeine.virtuoso.security.model.entity.AuthenticationToken;
import com.coffeine.virtuoso.security.model.entity.User;
import com.coffeine.virtuoso.security.model.service.UserService;

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
 * Authentication provider of users.
 *
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
    private UserService userService;


    /// *** Methods     *** ///
    //- SECTION :: MAIN -//
    /**
     * Authentication.
     *
     * @param authentication    Authentication object.
     *
     * @return Authentication AuthenticationToken
     *
     * @throws AuthenticationException if credentials are bad.
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
            List<GrantedAuthority> authorities = new ArrayList<>();

            //- Populate roles into authentication object -//
            user.getRoles().forEach( 
                role -> authorities.add(
                    new SimpleGrantedAuthority(
                        role.getCode()
                    )
                )
            );

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
     * Supports.
     *
     * @param authentication    Class of authentication object.
     *
     * @return boolean true - supported, false - unsupported.
     */
    @Override
    public boolean supports( Class<?> authentication ) {
        return true;
    }
}
