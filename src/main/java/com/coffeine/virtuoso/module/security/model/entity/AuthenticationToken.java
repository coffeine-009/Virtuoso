/// *** Security :: Model :: Entity :: AuthenticationToken  *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-08-22 15:29:11 :: 2014-08-22 15:38:09
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.security.model.entity;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @version 1.0
 */
public class AuthenticationToken extends AbstractAuthenticationToken {

    /// *** Properties  *** ///
    /**
     * Principal
     */
    protected final Object principal;

    /**
     * Credentials
     */
    protected Object credential;


    /// *** Methods     *** ///
    /**
     * Constructor for create new Auth token
     *
     * @param principal
     * @param credential
     * @param authorities
     */
    public AuthenticationToken(
        Object principal,
        Object credential,
        Collection < ? extends GrantedAuthority > authorities
    ) {
        //- Delegate authorities -//
        super( authorities );

        //- Init -//
        this.principal = principal;
        this.credential = credential;

        //- Mark user as authenticated -//
        super.setAuthenticated( true );
    }

    //- SECTION :: GET -//
    /**
     * Get Credentials
     *
     * @return Object
     */
    @Override
    public Object getCredentials() {
        return this.credential;
    }

    /**
     * Get principal
     *
     * @return Object
     */
    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
