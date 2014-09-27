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
import org.apache.commons.codec.binary.Base64;
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
    public void testGetAccessToken() throws Exception {

        //- Success -//
        this.mockMvc.perform(
            post("/oauth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .header(
                    "Authorization",
                    "Basic " + new String(
                        Base64.encodeBase64(
                            "developer:developer32".getBytes()
                        )
                    )
                )
                .param("grant_type", "password")
                .param("scope", "read")
                .param("clientId", "developer")
                .param("clientSecret", "developer32")
                .param("username", "user@virtuoso.com")
                .param("password", "123")
        )
            .andExpect( status().isOk() )
            .andExpect( content().contentType( MediaType.APPLICATION_JSON + ";charset=UTF-8" ) )
            .andExpect( jsonPath( "$.access_token", notNullValue() ) )
            .andExpect( jsonPath( "$.access_token", not( empty() ) ) )
            .andExpect( jsonPath( "$.expires_in", notNullValue() ) )
            .andExpect( jsonPath( "$.expires_in", not( empty() ) ) )
            .andExpect( jsonPath( "$.token_type" ).value( "bearer" ) )
            .andExpect( jsonPath( "$.scope" ).value( "read" ) )
            .andDo( print() );
    }
}
