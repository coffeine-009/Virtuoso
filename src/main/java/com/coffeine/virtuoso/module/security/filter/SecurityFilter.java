/// *** Security :: Filter :: SecurityFilter    *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-22 15:26:32 :: 2014-06-22 21:58:09
 *
 * @address /Ukraine/Petranka/Grushevskiy/234
 *                                                                  *
*///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.security.filter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 */
public class SecurityFilter extends UsernamePasswordAuthenticationFilter {

    /// *** Constants   *** ///
    protected final String USERNAME = "username";
    protected final String PASSWORD = "password";

    /// *** Properties  *** ///
    /**
     * JSON mapper
     */
//    ObjectMapper mapper;


    /// *** Methods     *** ///

    /**
     * Default constructor
     *
     * @constructor
     */
    SecurityFilter() {
        super();
        //- Initialization -//
//        this.mapper = new ObjectMapper();
    }


    //- SECTION :: MAIN -//
    /**
     * Authenticate
     *
     * @param request
     * @param response
     * @return boolean
     */
    @Override
    protected boolean requiresAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        //- Params -//
        String username = request.getParameter( USERNAME );
        String password = request.getParameter( PASSWORD );

        if ( username == null || password == null ) {
            return true;
        }

        try {
            successfulAuthentication(
                request,
                response,
                this.attemptAuthentication(
                    request,
                    response
                )
            );
        }
        catch ( AuthenticationException failed ) {
            try {
                unsuccessfulAuthentication( request, response, failed );
            } catch ( Exception e ) {

            }
            return false;
        }
        catch ( Exception e ) {
            return false;
        }

        return false;
    }


    //- SECTION :: HELPER -//
    /**
     * Get username from request
     *
     * @param request
     * @return String
     */
    protected String obtainUsername( HttpServletRequest request ) {
        return request.getParameter( USERNAME );
    }

    /**
     * Get password from request
     *
     * @param request
     * @return String
     */
    protected String obtainPassword( HttpServletRequest request ) {
        return request.getParameter( PASSWORD );
    }
}
