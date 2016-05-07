/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/7/16 6:11 PM
 */

package com.coffeine.virtuoso.music.model.persistence.mock;

import com.coffeine.virtuoso.music.model.entity.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock of Video.
 */
public class VideoMock {

    /**
     * Get list of videos.
     *
     * @return List of videos.
     */
    public static List<Video> findAll() {
        //- Mock style -//
        return new ArrayList<Video>() {{
            add(
                new Video(
                    VideoTypeMock.find(),
                    "uk-UA",
                    "Rose",
                    "rose.mp4"
                )
            );
        }};
    }

    /**
     * Get video.
     *
     * @return Video.
     */
    public static Video find() {
        return new Video(
            VideoTypeMock.find(),
            "uk-UA",
            "Rose",
            "rose.mp4"
        );
    }
}
