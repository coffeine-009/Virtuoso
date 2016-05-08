/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 3/20/16 10:49 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
     * Init environment for run test.
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();
    }

    /**
     * Clean environment.
     */
    @After
    @Override
    public void tearDown() {
        //- Clean environment after run tests -//
    }


    /**
     * Test successful getting list of songs.
     *
     * @throws Exception    General application exception.
     */
    @Test
    public void testListActionSuccess() throws Exception {

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs?page={page}&limit={limit}", 1, 10 )
            .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$", hasSize( 2 ) ) )
            .andExpect( jsonPath( "$[*].id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].id", containsInAnyOrder( 1, 2 ) ) )
            //- Composer -//
            .andExpect( jsonPath( "$[*].composers", notNullValue() ) )
            .andExpect( jsonPath( "$[*].composers", not( empty() ) ) )
            .andExpect( jsonPath( "$[*].composers", hasSize( 2 ) ) )
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
            .andExpect( jsonPath( "$[*].videos", notNullValue() ) )
            .andDo( document( "songs-list-example" ) );
    }

    /**
     * Test of retrieving song by id.
     * Success.
     *
     * @throws Exception    Exception    General application exception.
     */
    @Test
    public void testRetrieveSongActionSuccess() throws Exception {

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$id" ).value( 1 ) )
            //- Composer -//
            .andExpect( jsonPath( "$composers", notNullValue() ) )
            .andExpect( jsonPath( "$composers", not( empty() ) ) )
            .andExpect( jsonPath( "$composers", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$composers[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].firstName", containsInAnyOrder( "Test" ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].lastName", containsInAnyOrder( "Unit" ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].middleName", containsInAnyOrder( "Mockito" ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$composers[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$composers[*].gender", containsInAnyOrder( true ) ) )
            .andExpect( jsonPath( "$composers[*].birthday", notNullValue() ) )
            .andExpect( jsonPath( "$composers[0].deathDate", nullValue() ) )
            //- Poet -//
            .andExpect( jsonPath( "$poets[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].firstName", containsInAnyOrder( "Test" ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].lastName", containsInAnyOrder( "Unit" ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].middleName", containsInAnyOrder( "Mockito" ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$poets[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$poets[*].gender", containsInAnyOrder( true ) ) )
            .andExpect( jsonPath( "$poets[*].birthday", notNullValue() ) )
            .andExpect( jsonPath( "$poets[0].deathDate", nullValue() ) )
            //- Song -//
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$writeDate", notNullValue() ) )
            .andExpect( jsonPath( "$data", notNullValue() ) )
            .andExpect( jsonPath( "$staffs", notNullValue() ) )
            .andExpect( jsonPath( "$texts", notNullValue() ) )
            .andExpect( jsonPath( "$videos", notNullValue() ) )
            .andDo(
                document(
                    "songs-retrieve-success-example",
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of song." ),
                        fieldWithPath( "composers" ).description( "List of composers." ),
                        fieldWithPath( "poets" ).description( "List of poets." ),
                        fieldWithPath( "data" ).description( "Localized data of song." ),
                        fieldWithPath( "staffs" ).description( "List of musical notes." ),
                        fieldWithPath( "texts" ).description( "List of texts." ),
                        fieldWithPath( "videos" ).description( "List of videos." ),
                        fieldWithPath( "locale" ).description( "Locale of song." ),
                        fieldWithPath( "writeDate" ).description( "Write date of song." ),
                        fieldWithPath( "title" ).description( "Title of song for current locale." ),
                        fieldWithPath( "creation" ).description( "Time of creation of this record." )
                    )
                )
            );
    }

    /**
     * Test of retrieving song by id.
     * Failure.
     *
     * @throws Exception    General application exception.
     */
    @Test
    public void testRetrieveSongActionFailure() throws Exception {

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs/{id}", 64 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "songs-retrieve-failure-example" ) );
    }

    @Test
    public void testCreateSongActionSuccess() throws Exception {
        //- Success. Create a new style -//
        this.mockMvc.perform(
            post( "/music/songs" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"composerIds\": [ 1 ]," +
                        "\"poetIds\": [ 1 ]," +
                        "\"data\": [ {" +
                            "\"title\": \"Rose\"," +
                            "\"locale\": \"en-US\"" +
                        "} ]," +
                        "\"staffs\": [ {" +
                            "\"musicNotesTypeId\": 1," +
                            "\"styleId\": 1," +
                            "\"file\": \"<xml></xml>\"" +
                        "} ]," +
                        "\"texts\": [ {" +
                            "\"locale\": \"en-US\"," +
                            "\"lyrics\": \"Слова, слова,  \\n" +
                                "Немов вуаль,  \\n" +
                                "Там, де тонка діагональ  \\n" +
                                "Звучить кришталь.  \\n" +
                                "А ніч летить туди у даль,  \\n" +
                                "Не залишаючи для нас  \\n" +
                                "Путів, на жаль...  \\n" +
                                "\\n" +
                                "#### Приспів:  \\n" +
                                "Стріляй!  \\n" +
                                "Скажи, чому боїшся ти  \\n" +
                                "Зробити цей останній крок?!?  \\n" +
                                "Давай!  \\n" +
                                "Най буде так, як хочеш ти,  \\n" +
                                "Я заплатив за свій урок!  \\n" +
                                "Прощай, мій Ангелок...  \\n" +
                                "Давай! Тисни гачок!  \\n" +
                                "\\n" +
                                "Слова, слова - \\n" +
                                "Алмаз вини -  \\n" +
                                "Мов зачаїлися в думках  \\n" +
                                "На мить вони...  \\n" +
                                "І от дуель, і от фінал  \\n" +
                                "Там, де навколо Колізей  \\n" +
                                "Звучить метал...  \\n" +
                                "\\n" +
                                "#### Приспів. (2)\\n" +
                                "\\n" +
                                ">Прощай, мій Ангелок...  \\n" +
                                "Стріляй!\"" +
                        "} ]," +
                        "\"videos\": [ {" +
                            "\"locale\": \"en-US\"," +
                            "\"title\": \"Rose\"," +
                            "\"description\": \"Rose.\"," +
                            "\"link\": \"rose.mp4\"" +
                        "} ]," +
                        "\"locale\": \"en-US\"," +
                        "\"writeDate\": \"2016-05-08\"" +
                    "}"
                )
        ).andDo( print() )
            .andExpect( status().isCreated() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$id" ).value( 1 ) )
            //- Composer -//
            .andExpect( jsonPath( "$composers", notNullValue() ) )
            .andExpect( jsonPath( "$composers", not( empty() ) ) )
            .andExpect( jsonPath( "$composers", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$composers[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].firstName", containsInAnyOrder( "Test" ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].lastName", containsInAnyOrder( "Unit" ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].middleName", containsInAnyOrder( "Mockito" ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$composers[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$composers[*].gender", containsInAnyOrder( true ) ) )
            .andExpect( jsonPath( "$composers[*].birthday", notNullValue() ) )
            .andExpect( jsonPath( "$composers[0].deathDate", nullValue() ) )
            //- Poet -//
            .andExpect( jsonPath( "$poets[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].firstName", containsInAnyOrder( "Test" ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].lastName", containsInAnyOrder( "Unit" ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].middleName", containsInAnyOrder( "Mockito" ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$poets[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$poets[*].gender", containsInAnyOrder( true ) ) )
            .andExpect( jsonPath( "$poets[*].birthday", notNullValue() ) )
            .andExpect( jsonPath( "$poets[0].deathDate", nullValue() ) )
            //- Song -//
//            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
//            .andExpect( jsonPath( "$writeDate", notNullValue() ) )
            .andExpect( jsonPath( "$data", notNullValue() ) )
            .andExpect( jsonPath( "$staffs", notNullValue() ) )
            .andExpect( jsonPath( "$texts", notNullValue() ) )
            .andExpect( jsonPath( "$videos", notNullValue() ) )
            .andDo(
                document(
                    "songs-create-success-example",
                    requestFields(
                        fieldWithPath( "composerIds" ).description( "List of composers' ids." ),
                        fieldWithPath( "poetIds" ).description( "List of poets' ids." ),
                        fieldWithPath( "data" ).description( "Localized data of song." ),
                        fieldWithPath( "staffs" ).description( "List of musical notes." ),
                        fieldWithPath( "texts" ).description( "List of texts." ),
                        fieldWithPath( "videos" ).description( "List of videos." ),
                        fieldWithPath( "locale" ).description( "Locale of song." ),
                        fieldWithPath( "writeDate" ).description( "Write date of song." )
                    )
                )
            );
    }
}
