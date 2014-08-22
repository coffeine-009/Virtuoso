/// *** Security :: Model :: Provider :: UserAuthenticationProvider *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-08-22 12:57:52 :: 2014-08-22 12:48:08
 *
 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.security.model.provider.implementation;

import com.coffeine.virtuoso.module.security.model.entity.AuthenticationToken;
import com.coffeine.virtuoso.module.user.model.service.AccessOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 */
public class UserAuthenticationProvider implements AuthenticationProvider {

    /// *** Properties  *** ///
    @Autowired
    AccessOAuthService accessService;


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

        //TODO
        authentication.getPrincipal();
        if ( true ) {
            //- Set authorities -//
            List < GrantedAuthority > authorities = new ArrayList<>();

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
