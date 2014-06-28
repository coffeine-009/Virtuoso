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

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Map;

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
    ObjectMapper mapper;


    /// *** Methods     *** ///

    /**
     * Default constructor
     *
     * @constructor
     */
    SecurityFilter() {
        super();
        //- Initialization -//
        this.mapper = new ObjectMapper();
    }


    //- SECTION :: MAIN -//
    /**
     * Authenticate
     *
     * @param request
     * @param response
     * @return Authentications
     */
    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        //- Params -//
        String username = "";
        String password = "";

        StringBuilder data = new StringBuilder();
        try {
            //- Read request body -//
            BufferedReader br = request.getReader();
            String line;
            while ( ( line = br.readLine() ) != null ) {
                data.append( line );
            }

            //- Create map of params -//
            Map < String, Object > params = this.mapper.readValue(
                    data.toString(),
                    Map.class
            );

            //- Set params from request -//
            username = params.get( USERNAME ).toString();
            password = params.get( PASSWORD ).toString();
        }
        catch ( Exception e ) {
            //- Bad request or something else -//
            //TODO: add logging
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
            username,
            password
        );

        // Allow subclasses to set the "details" property
        setDetails( request, authRequest );

        return this.getAuthenticationManager().authenticate( authRequest );
    }
}
