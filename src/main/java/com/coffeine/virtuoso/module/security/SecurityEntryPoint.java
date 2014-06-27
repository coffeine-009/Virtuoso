/// *** Security :: SecurityEntryPoint  *** *** *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-14 15:26:32 :: 2014-06-22 22:00:00
 *
 * @address /Ukraine/Petranka/Grushevskiy/234
 *                                                                  *
*///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0
 */
public final class SecurityEntryPoint implements AuthenticationEntryPoint {

    /// *** Methods     *** ///
    /**
     * Fail authenticate
     *
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     */
    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException
    ) throws IOException {
        //- Set HTTP status 401(Unauthorized) -//
        response.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            "Unauthorized"
        );
    }
}
