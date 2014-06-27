package com.coffeine.virtuoso.module.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
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
 * Created by vitaliy on 6/6/14.
 */
public class AuthFailure extends SimpleUrlAuthenticationFailureHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();

//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws ServletException, IOException {
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
//
//        if (savedRequest == null) {
//            clearAuthenticationAttributes(request);
//            return;
//        }
//        String targetUrlParam = getTargetUrlParameter();
//        if (isAlwaysUseDefaultTargetUrl() ||
//                (targetUrlParam != null &&
//                        StringUtils.hasText(request.getParameter(targetUrlParam)))) {
//            requestCache.removeRequest(request, response);
//            clearAuthenticationAttributes(request);
//            return;
//        }
//
//        clearAuthenticationAttributes(request);
//    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
