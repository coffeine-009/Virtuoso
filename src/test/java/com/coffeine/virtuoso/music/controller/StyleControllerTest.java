/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:47 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.music.model.entity.Style;
import com.coffeine.virtuoso.music.model.persistence.mock.StyleMock;
import com.coffeine.virtuoso.music.model.service.StyleService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for StyleController.
 * @see StyleController
 */
public class StyleControllerTest extends AbstractRestControllerTest {

    @Mock
    private StyleService styleService;

    @InjectMocks
    private StyleController styleController;

    /**
     * Init environment for run test
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();

        //- Set up application -//
        this.mockMvc = MockMvcBuilders.standaloneSetup( styleController ).build();
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
     * Test of getting list of styles.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testListActionSuccess() throws Exception {
        //- Mock -//
        when( this.styleService.findAll( anyInt(), anyInt() ) ).thenReturn( StyleMock.findAll() );

        // Success. Get list of styles
        this.mockMvc.perform(
            get( "/music/styles?page={page}&limit={limit}", 1, 10 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test of getting style by id.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testRetrieveActionSuccess() throws Exception {
        //- Mock -//
        when( this.styleService.find( anyLong() ) ).thenReturn( StyleMock.find() );
        // Success. Get list of styles
        this.mockMvc.perform(
            get( "/music/styles/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test of getting style by id.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testRetrieveActionFailure() throws Exception {
        //- Mock -//
        when( this.styleService.find( anyLong() ) ).thenReturn( null );
        // Success. Get list of styles
        this.mockMvc.perform(
            get( "/music/styles/{id}", 999999 )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test of creating a new style.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Mock -//
        when( this.styleService.create( any( Style.class ) ) ).thenReturn( StyleMock.find() );
        //- Success. Create a new style -//
        this.mockMvc.perform(
            post( "/music/styles" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"POLKA\"," +
                        "\"title\": \"Polka\"," +
                        "\"description\": \"Polka.\"" +
                    "}"
                )
        )
            .andExpect( status().isCreated() );
    }

    /**
     * Test of creating a new style.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Mock -//
        when( this.styleService.create( any( Style.class ) ) ).thenReturn( null );
        //- Failure. Create a new style -//
        this.mockMvc.perform(
            post( "/music/styles" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"WALTZ\"," +
                        "\"title\": \"Waltz\"," +
                        "\"description\": \"Waltz.\"" +
                    "}"
                )
        ).andDo( print() )
//            .andExpect( status().isConflict() );//FIXME: unique constraint
            ;
    }

    /**
     * Test of updating a new style.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {
        //- Mock -//
        when( this.styleService.find( anyLong() ) ).thenReturn( StyleMock.find() );
        when( this.styleService.update( any( Style.class ) ) ).thenReturn( StyleMock.find() );
        //- Success. Update a new style -//
        this.mockMvc.perform(
            put( "/music/styles/{id}", 1 )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"WALTZ\"," +
                        "\"title\": \"Waltz\"," +
                        "\"description\": \"Waltz. Good style.\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test of updating a new style.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Mock -//
        when( this.styleService.update( any( Style.class ) ) ).thenReturn( null );
        //- Failure. Update a new style -//
        this.mockMvc.perform(
            put( "/music/styles/{id}", 99999 )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"WALTZ\"," +
                        "\"title\": \"Waltz\"," +
                        "\"description\": \"Waltz. Good style.\"" +
                    "}"
                )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test of deleting a new style.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Mock -//
        doNothing().when( this.styleService ).delete( anyLong() );
        //- Success. Delete a style by id -//
        this.mockMvc.perform(
            delete( "/music/styles/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test of deleting a new style.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Mock -//
        doThrow( InvalidDataAccessApiUsageException.class ).when( this.styleService ).delete( anyLong() );
        //- Failure. Delete a style by id -//
        this.mockMvc.perform(
            delete( "/music/styles/{id}", 999999 )
        )
            .andExpect( status().isNotFound() );
    }
}
