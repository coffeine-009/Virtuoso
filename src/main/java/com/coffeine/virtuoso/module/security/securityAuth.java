package com.coffeine.virtuoso.module.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vitaliy on 6/6/14.
 */
@Component( "restAuthenticationEntryPoint" )
public class securityAuth implements AuthenticationEntryPoint {


        @Override
        public void commence( HttpServletRequest request, HttpServletResponse response,
                              AuthenticationException authException ) throws IOException {
            response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
        }
    }
