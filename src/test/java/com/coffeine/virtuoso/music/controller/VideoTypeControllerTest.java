/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.music.model.entity.VideoType;
import com.coffeine.virtuoso.music.model.repository.VideoTypeRepository;
import com.coffeine.virtuoso.music.model.service.VideoTypeService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Tests for VideoType Controller
 * @see VideoTypeController
 *
 * @version 1.0
 */
public class VideoTypeControllerTest extends AbstractRestControllerTest {

    /// *** Constants   *** ///
    protected final String URI_LIST = "/user/video/type/list/{PAGE}/{LIMIT}";

    @Spy
    protected VideoTypeRepository videoTypeRepository = new VideoTypeRepository() {
        @Override
        public List<VideoType> findAll() {
            return null;
        }

        @Override
        public List<VideoType> findAll(Sort orders) {
            return null;
        }

        @Override
        public List<VideoType> save(Iterable<? extends VideoType> videoTypes) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public VideoType saveAndFlush(VideoType videoType) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<VideoType> videoTypes) {

        }

        @Override
        public Page<VideoType> findAll(Pageable pageable) {
            return new PageImpl(new ArrayList<VideoType>() {{
                add(
                        new VideoType(
                                1L,
                                "CLIP",
                                "Video clip",
                                "Video clip, misical."
                        )
                );
            }});
        }

        @Override
        public VideoType save(VideoType videoType) {
            return null;
        }

        @Override
        public VideoType findOne(Long aLong) {
            return null;
        }

        @Override
        public boolean exists(Long aLong) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void delete(Long aLong) {

        }

        @Override
        public void delete(VideoType videoType) {

        }

        @Override
        public void delete(Iterable<? extends VideoType> videoTypes) {

        }

        @Override
        public void deleteAll() {

        }
    };

    @Autowired
    protected VideoTypeService videoTypeService;


    /// *** Methods     *** //
    @Before
    public void tearUp() {
        super.tearUp();

        MockitoAnnotations.initMocks(this);

        doReturn(
            new PageImpl(new ArrayList<VideoType>() {{
                add(
                    new VideoType(
                        1L,
                        "CLIP",
                        "Video clip",
                        "Video clip, misical."
                    )
                );
            }})
        ).when( videoTypeRepository ).findAll(new PageRequest(1, 2));

    }

    //- SECTION :: TEST -//
    /**
     * Test for findAll action
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindAll() throws Exception {

        this.mockMvc.perform(
            get( URI_LIST, "1", "2" )
                .contentType( MediaType.APPLICATION_JSON )
//                .session( this.session )
        ).andDo( print() );
//            .andExpect( status().isOk() );
    }
}
