/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/8/15 10:09 PM
 */

package com.thecoffeine.virtuoso.music.controller;

import com.thecoffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.thecoffeine.virtuoso.music.model.entity.Staff;
import com.thecoffeine.virtuoso.music.model.persistence.mock.SongMock;
import com.thecoffeine.virtuoso.music.model.persistence.mock.StaffMock;
import com.thecoffeine.virtuoso.music.model.persistence.mock.StaffTypeMock;
import com.thecoffeine.virtuoso.music.model.persistence.mock.StyleMock;
import com.thecoffeine.virtuoso.music.model.service.SongService;
import com.thecoffeine.virtuoso.music.model.service.StaffService;
import com.thecoffeine.virtuoso.music.model.service.StaffTypeService;
import com.thecoffeine.virtuoso.music.model.service.StyleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for StaffController.
 *
 * @version 1.0
 */
public class StaffControllerTest extends AbstractRestControllerTest {

    @Mock
    private SongService songService;

    @Mock
    private StyleService styleService;

    @Mock
    private StaffTypeService staffTypeService;

    @Mock
    private StaffService staffService;

    @InjectMocks
    private StaffController staffController;


    /**
     * Init environment for run test.
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();

        //- Set up application -//
        this.mockMvc = MockMvcBuilders.standaloneSetup(
            this.staffController
        ).build();
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
     * Test get list of staffs.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testListActionSuccess() throws Exception {
        //- Mock -//
        when( this.staffService.findAll( anyInt(), anyInt() ) ).thenReturn( StaffMock.findAll() );

        //- Success -//
        this.mockMvc.perform(
            get( "/music/staffs?page={page}&limit={limit}", 1, 10 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test get staff.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionSuccess() throws Exception {
        //- Mock -//
        when( this.staffService.find( anyLong() ) ).thenReturn( StaffMock.find() );

        //- Success -//
        this.mockMvc.perform(
            get( "/music/staffs/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test get staff.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionFailure() throws Exception {
        //- Mock -//
        when( this.staffService.find( anyLong() ) ).thenReturn( null );

        //- Failure -//
        this.mockMvc.perform(
            get( "/music/staffs/{id}", 1 )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test create a staff.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Mock -//
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        when( this.styleService.find( anyLong() ) ).thenReturn( StyleMock.find() );
        when( this.staffTypeService.find( anyLong() ) ).thenReturn( StaffTypeMock.find() );
        when( this.staffService.create( any( Staff.class ) ) ).thenReturn( StaffMock.find() );

        //- Success -//
        this.mockMvc.perform(
            post( "/music/staffs" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"staffTypeId\": 1," +
                        "\"styleId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isCreated() );
    }

    /**
     * Test create a staff.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Mock -//
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        when( this.styleService.find( anyLong() ) ).thenReturn( StyleMock.find() );
        when( this.staffTypeService.find( anyLong() ) ).thenReturn( StaffTypeMock.find() );
        doThrow( DataIntegrityViolationException.class ).when(
            this.staffService
        ).create( any( Staff.class ) );

        //- Failure -//
        this.mockMvc.perform(
            post( "/music/staffs" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"staffTypeId\": 1," +
                        "\"styleId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isBadRequest() );
    }

    /**
     * Test update a staff.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {
        //- Mock -//
        when( this.staffService.find( anyLong() ) ).thenReturn( StaffMock.find() );
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        when( this.styleService.find( anyLong() ) ).thenReturn( StyleMock.find() );
        when( this.staffTypeService.find( anyLong() ) ).thenReturn( StaffTypeMock.find() );
        when( this.staffService.update( any( Staff.class ) ) ).thenReturn( StaffMock.find() );

        //- Success -//
        this.mockMvc.perform(
            put( "/music/staffs/{id}", 1 )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"staffTypeId\": 1," +
                        "\"styleId\": 1," +
                        "\"locale\": \"en-US\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test update a staff.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Mock -//
        when( this.staffService.find( anyLong() ) ).thenReturn( StaffMock.find() );
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        when( this.styleService.find( anyLong() ) ).thenReturn( StyleMock.find() );
        when( this.staffTypeService.find( anyLong() ) ).thenReturn( StaffTypeMock.find() );
        doThrow( DataIntegrityViolationException.class ).when(
            this.staffService
        ).update( any( Staff.class ) );

        //- Success -//
        this.mockMvc.perform(
            put( "/music/staffs/{id}", 1 )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"staffTypeId\": 1," +
                        "\"styleId\": 1," +
                        "\"locale\": \"en-US\"" +
                    "}"
                )
        )
            .andExpect( status().isBadRequest() );
    }

    /**
     * Test delete a staff.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Mock -//
        doNothing().when( this.staffService ).delete( anyLong() );

        //- Success -//
        this.mockMvc.perform(
            delete( "/music/staffs/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test delete a staff.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Mock -//
        doThrow( EmptyResultDataAccessException.class ).when(
            this.staffService
        ).delete( anyLong() );

        //- Success -//
        this.mockMvc.perform(
            delete( "/music/staffs/{id}", 1 )
        )
            .andExpect( status().isNotFound() );
    }
}
