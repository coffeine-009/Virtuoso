/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 3/20/16 10:49 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Functional tests for SongController.
 *
 * @version 1.0
 * @see StyleController
 */
public class FunctionalSongControllerTest extends AbstractRestControllerTest {

    /**
     * Init environment for run test
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();
    }

    @Test
    public void testListActionSuccess() throws Exception {

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs?page={page}&limit={limit}", 1, 10 )
            .session( this.session )
        ).andDo( print() )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$[*].id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].id", containsInAnyOrder( 1 ) ) )
            //- Composer -//
            .andExpect( jsonPath( "$[*].composers", notNullValue() ) )
            .andExpect( jsonPath( "$[*].composers", not( empty() ) ) )
            .andExpect( jsonPath( "$[*].composers", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$[*].composers[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].firstName", containsInAnyOrder( "Test" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].lastName", containsInAnyOrder( "Unit" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].middleName", containsInAnyOrder( "Mockito" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].gender", containsInAnyOrder( true ) ) )
            .andExpect( jsonPath( "$[*].composers[*].birthday", notNullValue() ) )
            .andExpect( jsonPath( "$[*].composers[0].deathDate", nullValue() ) )
            //- Poet -//
            .andExpect( jsonPath( "$[*].poets[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$[*].poets[*].data[*].firstName", containsInAnyOrder( "Test" ) ) )
            .andExpect( jsonPath( "$[*].poets[*].data[*].lastName", containsInAnyOrder( "Unit" ) ) )
            .andExpect( jsonPath( "$[*].poets[*].data[*].middleName", containsInAnyOrder( "Mockito" ) ) )
            .andExpect( jsonPath( "$[*].poets[*].data[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].poets[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].poets[*].gender", containsInAnyOrder( true ) ) )
            .andExpect( jsonPath( "$[*].poets[*].birthday", notNullValue() ) )
            .andExpect( jsonPath( "$[*].poets[0].deathDate", nullValue() ) )
            //- Song -//
            .andExpect( jsonPath( "$[*].title", notNullValue() ) )
            .andExpect( jsonPath( "$[*].locale", notNullValue() ) )
            .andExpect( jsonPath( "$[*].writeDate", notNullValue() ) )
            .andExpect( jsonPath( "$[*].data", notNullValue() ) )
            .andExpect( jsonPath( "$[*].staffs", notNullValue() ) )
            .andExpect( jsonPath( "$[*].texts", notNullValue() ) )
            .andExpect( jsonPath( "$[*].videos", notNullValue() ) );
    }
}
