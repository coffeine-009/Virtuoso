/**
 * @copyright 2014 (c), by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-09-05 19:32:00 :: 2014-09-05 19:18:40
 *
 * @address /Ukraine/Ivano-Frankivsk/Petranka
 */
package com.coffeine.virtuoso.module.security.controller;

import com.coffeine.virtuoso.module.controller.AbstractControllerTest;
import junit.framework.Assert;
import org.apache.commons.codec.binary.Base64;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test security controller
 *
 * @version 1.0
 */
public class SecurityControllerTest extends AbstractControllerTest {

    /// *** Methods     *** ///
    //- SECTION :: TEST -//
    @Test
    @Ignore
    public void testGetAccessToken() {

        //- Success -//
        try {
            this.mockMvc.perform(
                post( "/oauth/token" )
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(
                            "Authorization",
                            "Basic " + new String(
                                    Base64.encodeBase64(
                                            "developer:developer32".getBytes()
                                    )
                            )
                    )
                    .param( "grant_type", "password" )
                    .param( "scope", "read" )
                    .param( "clientId", "developer" )
                    .param( "clientSecret", "developer32" )
                    .param( "username", "user@virtuoso.com" )
                    .param( "password", "123" )
//                    .content(
//                        (
//                            "grant_type=password" +
//                            "&scope=read%20write" +
//                            "&clientId=developer" +
//                            "&clientSecret=developer32" +
//                            "&username=user@virtuoso.com" +
//                            "&password=123"
//                        ).getBytes()
//                    )
            )
                .andExpect(status().isUnauthorized());
        }
        catch ( Exception ex ) {
            Assert.fail( "Fail authenticate!" );
        }
    }
}
