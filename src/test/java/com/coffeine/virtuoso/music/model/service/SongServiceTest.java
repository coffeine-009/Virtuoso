/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.module.model.service.AbstractServiceTest;
import com.coffeine.virtuoso.music.model.entity.Song;
import com.coffeine.virtuoso.music.model.persistence.mock.SongMock;
import com.coffeine.virtuoso.music.model.repository.SongRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Tests for SongService
 * @see SongService
 *
 * @version 1.0
 */
public class SongServiceTest extends AbstractServiceTest {

    /// *** Properties  *** ///
    @Mock
    private SongRepository songRepository;

    @InjectMocks
    @Autowired
    private SongService songService;


    /// *** Methods     *** ///
    /**
     * Prepare environment for run tests
     */
    @Before
    @Override
    public void tearUp() {
        super.tearUp();

        //- Init mocks -//
        MockitoAnnotations.initMocks( this );
    }

    /**
     * Test findAll
     */
    @Test
    public void testFindAll() {

        //- Make mock for service -//
        when(
            songRepository.findAll(
                new PageRequest(
                    anyInt(),
                    10
                )
            )
        ).thenReturn( new PageImpl<Song>( SongMock.getList() ) );

        //- Using of service -//
        List < Song > songs = this.songService.findAll( 1, 10 );

        //- Assertions -//
        assertEquals( 1, songs.size() );
    }
}
