/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 4/29/16 9:29 PM
 */

package com.thecoffeine.virtuoso.music.model.persistence.mock;

import com.thecoffeine.virtuoso.music.model.entity.Style;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock for style.
 */
public class StyleMock {

    /**
     * Get list of styles.
     *
     * @return List of styles.
     */
    public static List<Style> findAll() {
        //- Mock style -//
        return new ArrayList<Style>() {{
            add(
                new Style(
                    1L,
                    "WALTZ",
                    "Waltz",
                    "Waltz."
                )
            );
        }};
    }

    /**
     * Get style.
     *
     * @return Style.
     */
    public static Style find() {
        return new Style(
            1L,
            "WALTZ",
            "Waltz",
            "Waltz."
        );
    }
}
