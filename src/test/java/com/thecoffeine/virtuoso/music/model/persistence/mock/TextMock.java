/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/8/16 10:42 AM
 */

package com.thecoffeine.virtuoso.music.model.persistence.mock;

import com.thecoffeine.virtuoso.music.model.entity.Lyrics;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock of Lyrics.
 */
public class TextMock {

    /**
     * Get list of lyricses.
     *
     * @return List of lyricses.
     */
    public static List<Lyrics> findAll() {
        //- Mock style -//
        return new ArrayList<Lyrics>() {{
            add(
                new Lyrics(
                    PoetMock.findAll(),
                    SongMock.retrieve(),
                    "uk-UA",
                    "Lyrics"
                )
            );
        }};
    }

    /**
     * Get text.
     *
     * @return Lyrics.
     */
    public static Lyrics find() {
        return new Lyrics(
            PoetMock.findAll(),
            SongMock.retrieve(),
            "uk-UA",
            "Lyrics"
        );
    }
}
