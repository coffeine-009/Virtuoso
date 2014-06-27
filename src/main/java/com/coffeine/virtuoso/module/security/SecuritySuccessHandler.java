/// *** Security :: SecuritySuccessHandler  *** *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-22 16:26:32 :: 2014-06-22 22:31:19
 *
 * @address /Ukraine/Petranka/Grushevskiy/234
 *                                                                  *
*///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0
 */
public class SecuritySuccessHandler
    extends SimpleUrlAuthenticationSuccessHandler
{
    /// *** Properties  *** ///
    private RequestCache requestCache;


    /// *** Methods     *** ///

    /**
     * Default constructor
     *
     * @constructor
     */
    SecuritySuccessHandler() {
        super();

        //- Initialization -//
        this.requestCache = new HttpSessionRequestCache();
    }


    //- SECTION :: MAIL -//
    /**
     * Success of authenticate
     *
     * @param request
     * @param response
     * @param authentication
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws ServletException, IOException {
        //- Params -//
        SavedRequest savedRequest = requestCache.getRequest(
            request,
            response
        );

        if ( savedRequest == null ) {
            clearAuthenticationAttributes( request );
            return;
        }
        String targetUrlParam = getTargetUrlParameter();
        if (
            isAlwaysUseDefaultTargetUrl()
            ||
            (
                targetUrlParam != null
                &&
                StringUtils.hasText( request.getParameter( targetUrlParam ) )
            )
        ) {
            requestCache.removeRequest( request, response );
            clearAuthenticationAttributes( request );
            return;
        }

        clearAuthenticationAttributes( request );
    }


    //- SECTION :: SET -//
    /**
     * Setter for requestCache
     *
     * @param requestCache
     */
    public void setRequestCache( RequestCache requestCache ) {
        this.requestCache = requestCache;
    }
}
