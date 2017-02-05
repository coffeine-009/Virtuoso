/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.persistence.mock;

import com.thecoffeine.virtuoso.music.model.entity.Lyrics;
import com.thecoffeine.virtuoso.music.model.entity.Song;
import com.thecoffeine.virtuoso.music.model.entity.SongLocale;
import com.thecoffeine.virtuoso.music.model.entity.Staff;
import com.thecoffeine.virtuoso.music.model.entity.StaffType;
import com.thecoffeine.virtuoso.music.model.entity.Style;
import com.thecoffeine.virtuoso.music.model.entity.Video;
import com.thecoffeine.virtuoso.music.model.entity.VideoType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Mock for song table in persistence layout.
 *
 * @version 1.0
 */
public abstract class SongMock {

    /**
     * Get list of songs.
     *
     * @return List of Songs.
     */
    public static List<Song> getList() {
        //- Create mock for song -//
        final Song song = new Song(
            "uk-UA"
        );
        song.setId( 1L );

        return new ArrayList<Song>() {{
            add( song );
        }};
    }

    /**
     * Get song.
     *
     * @return Song.
     */
    public static Song retrieve() {
        Song song = new Song(
            "uk-UA"
        );
        song.setId( 1L );

        return song;
    }

    public static Song find() {
        Song song = new Song(
            //-Create composer-//
            //- Create list of song locale -//
            new HashSet<SongLocale>() {{
                add(
                    new SongLocale(
                        "user",
                        "uk-UA"
                    )
                );
            }},
            //- Create list of staff -//
            new HashSet<Staff>() {{
                add(
                    new Staff(
                        new StaffType(
                            "CHORDS",
                            "Chords",
                            "Standard chords"
                        ),
                        new Style(
                            "One",
                            "Two",
                            "three"
                        ),
                        "uk-UA"
                    )
                );
            }},
            //- Create list of text -//
            new HashSet<Lyrics>() {{
                add(
                    new Lyrics(
                        PoetMock.findAll(),
                        "uk-UA",
                        "Lyrics"
                    )
                );
            }},
            //- Create list of video -//
            new HashSet<Video>() {{
                add(
                    new Video(
                        new VideoType(
                            "POLKA",
                            "Polka",
                            "Ukrainian polka"
                        ),
                        "uk-UA",
                        "user",
                        "video1"
                    )
                );
            }},
            "uk-UA"
        );
        song.setId( 1L );

        return song;
    }
}
