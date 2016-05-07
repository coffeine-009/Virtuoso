/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/7/16 1:35 PM
 */

package com.coffeine.virtuoso.music.model.persistence.mock;

import com.coffeine.virtuoso.music.model.entity.VideoType;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock of video type.
 */
public class VideoTypeMock {

    /**
     * Get list of video types..
     *
     * @return List of video types.
     */
    public static List<VideoType> findAll() {
        //- Mock style -//
        return new ArrayList<VideoType>() {{
            add(
                new VideoType(
                    1L,
                    "YOUTUBE",
                    "Youtube",
                    "Youtube."
                )
            );
        }};
    }

    /**
     * Get video type.
     *
     * @return VideoType.
     */
    public static VideoType find() {
        return new VideoType(
            1L,
            "YOUTUBE",
            "Youtube",
            "Youtube."
        );
    }
}
