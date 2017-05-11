/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 01/7/2016 09:40 PM
 */

package com.thecoffeine.virtuoso.music.model.persistence.mock;

import com.thecoffeine.virtuoso.music.model.entity.Poet;
import com.thecoffeine.virtuoso.music.model.entity.PoetLocale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Mock for Poet in persistence layout.
 * @see Poet
 *
 * @version 1.0
 */
public class PoetMock {

    /**
     * Get list of poets.
     *
     * @return List of poets.
     */
    public static Set<Poet> findAll() {
        //- Mock poet -//
        final Poet poet = new Poet(
            new ArrayList<PoetLocale>() {{
                add(
                    new PoetLocale(
                        "Test",
                        "Unit",
                        "Mockito",
                        "uk-UA"
                    )
                );
            }},
            "uk-UA",
            true,
            LocalDate.now().minusYears( 32 ),
            null
        );
        poet.setId( 1L );

        return new HashSet<Poet>() {{
            add( poet );
        }};
    }
}
