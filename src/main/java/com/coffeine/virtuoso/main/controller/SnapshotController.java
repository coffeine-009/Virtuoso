/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/10/16 11:38 PM
 */

package com.coffeine.virtuoso.main.controller;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter for making snapshots for dynamic pages.
 * Only for search crawlers.
 *
 * @class SnapshotController
 */
@RestController
@RequestMapping( "/snapshots" )
public class SnapshotController {

    @RequestMapping( method = RequestMethod.GET )
    public void create(
        @RequestParam( value = "_escaped_fragment_" )
        String path,

        HttpServletRequest request,
        HttpServletResponse response
    ) {

        try {
            // use the headless browser to obtain an HTML snapshot
            final WebClient webClient = new WebClient();

            HtmlPage page = webClient.getPage("http://www.musician-virtuoso.com/#!" + path );
            // important!  Give the headless browser enough time to execute JavaScript
            // The exact time to wait may depend on your application.
            webClient.waitForBackgroundJavaScript(32000);

            // return the snapshot
            response.getWriter().write(page.asXml().replaceFirst( "<\\?xml\\ version=\"1.0\"\\ encoding=\"UTF-8\"\\?>", "" ));
        } catch ( IOException e ) {
            e.printStackTrace();
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        }
    }
}
