/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/8/16 10:42 AM
 */

package com.coffeine.virtuoso.music.model.persistence.mock;

import com.coffeine.virtuoso.music.model.entity.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock of Text.
 */
public class TextMock {

    /**
     * Get list of texts.
     *
     * @return List of texts.
     */
    public static List<Text> findAll() {
        //- Mock style -//
        return new ArrayList<Text>() {{
            add(
                new Text(
                    SongMock.retrieve(),
                    "uk-UA"
                )
            );
        }};
    }

    /**
     * Get text.
     *
     * @return Text.
     */
    public static Text find() {
        return new Text(
            SongMock.retrieve(),
            "uk-UA"
        );
    }
}
